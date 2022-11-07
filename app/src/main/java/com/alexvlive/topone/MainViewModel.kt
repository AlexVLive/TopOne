package com.alexvlive.topone

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexvlive.topone.common.ResultResponse
import com.alexvlive.topone.common.takeSuccessData
import com.alexvlive.topone.data.local.repository.SharedPreferencesRepository
import com.alexvlive.topone.data.remote.model.TrackInfoFull
import com.alexvlive.topone.data.remote.model.TrackInfoShort
import com.alexvlive.topone.data.remote.model.info.TrackInfo
import com.alexvlive.topone.data.remote.model.info.getTags
import com.alexvlive.topone.data.remote.model.lovedtrack.Track
import com.alexvlive.topone.data.remote.repository.LastFmRepository
import com.alexvlive.topone.util.DialogExitParameters
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val lastFmRepository: LastFmRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository
) : ViewModel() {

    private val _sessionKey = MutableStateFlow<ResultResponse<String>>(ResultResponse.Loading)

    private val _topTrack =
        MutableStateFlow<ResultResponse<TrackInfoFull>>(ResultResponse.Loading)
    val topTrack = _topTrack.asStateFlow()

    private val _similarFlow =
        MutableStateFlow<ResultResponse<List<TrackInfoShort>>>(ResultResponse.Loading)
    val similarFlow = _similarFlow.asStateFlow()

    private val _lovedTrackFlow =
        MutableStateFlow<ResultResponse<List<Track>>>(ResultResponse.Loading)

    val isDarkMode = mutableStateOf(false)
    val isShowDialogExit = mutableStateOf(DialogExitParameters(false, null))

    init {

        viewModelScope.launch(Dispatchers.IO) {
            fetchLovedTracks()
        }

        fetchSessionKey()
        _sessionKey.onEach{
            it.takeSuccessData()?.let {
                fetchTopTrack()
            }
        }.flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }

    private fun fetchSessionKey(){
        val key = sharedPreferencesRepository.getSessionKey()
        if(key == ""){
            viewModelScope.launch(Dispatchers.IO) {
                val keyResponse = lastFmRepository.getSessionKey()
                keyResponse.takeSuccessData()?.let {
                    _sessionKey.emit(keyResponse)
                    sharedPreferencesRepository.updateSessionKey(it)
                }
            }
        }
        else{
            _sessionKey.tryEmit(ResultResponse.Success(key))
        }
    }

    private suspend fun fetchTopTrack(){
        lastFmRepository.getTopTracks()
            .takeSuccessData()?.let { topTrack ->
                getInfoFull(artist = topTrack.artist.name, track = topTrack.name)
                    ?.let { trackInfo ->
                        _topTrack.emit(
                            ResultResponse.Success(trackInfo)
                        )
                    }
            }
    }

    fun fetchSimilar(artist: String, track: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _similarFlow.emit(ResultResponse.Loading)
            lastFmRepository.getSimilar(artist = artist, track = track)
                .takeSuccessData()?.let { listSimilar ->
                    if(listSimilar.isEmpty()){
                        lastFmRepository.getTopTracksByArtist(artist = artist)
                            .takeSuccessData()?.let { listTopTracks ->
                                if (listTopTracks.isNotEmpty()) {
                                    _similarFlow.emit(ResultResponse.Success(listTopTracks))
                                }
                            }
                    }
                    else{
                        _similarFlow.emit(ResultResponse.Success(listSimilar))
                    }
                }
        }
    }

    suspend fun getInfo(artist: String, track: String): ResultResponse<TrackInfo> =
        lastFmRepository.getInfo(artist = artist, track = track)

    suspend fun getInfoFull(artist: String, track: String): TrackInfoFull? {

        var result: TrackInfoFull? = null
        lastFmRepository.getInfo(artist = artist, track = track)
            .takeSuccessData()?.let { trackInfo ->

                result = TrackInfoFull(
                    artist = artist,
                    track = track,
                    length = trackInfo.track?.duration?.toLong(),
                    listeners = trackInfo.track?.listeners?.toLong(),
                    playCount = trackInfo.track?.playcount?.toLong(),
                    imageUrl = trackInfo.track?.album?.image?.last()?.url,
                    info = trackInfo.track?.wiki?.content?.substringBefore("<a href="),
                    tags = trackInfo.track?.toptags?.getTags()
                )

            }
        return result
    }

    fun addLoveTrack(artist: String, track: String){
        _sessionKey.value.takeSuccessData()?.let {
            viewModelScope.launch(Dispatchers.IO) {
                lastFmRepository.addLoveTrack(artist = artist, track = track, sessionKey = it)
                fetchLovedTracks()
            }
        }
    }

    fun removeLoveTrack(artist: String, track: String) {
        _sessionKey.value.takeSuccessData()?.let {
            viewModelScope.launch(Dispatchers.IO) {
                lastFmRepository.removeLoveTrack(artist = artist, track = track, sessionKey = it)
                fetchLovedTracks()
            }
        }
    }

    private suspend fun fetchLovedTracks(){
        _lovedTrackFlow.tryEmit(lastFmRepository.getUserLovedTracks())
    }

    fun isLovedTrack(artist: String, track: String): Boolean {
        var result = false
        _lovedTrackFlow.value.takeSuccessData()?.let { list ->
            result = list.any{it.artist.name == artist && it.name == track}
        }
        return result
    }
}