package com.alexvlive.topone.data.remote.repository

import com.alexvlive.topone.common.ResultResponse
import com.alexvlive.topone.data.remote.model.TrackInfoShort
import com.alexvlive.topone.data.remote.model.alltracks.TrackX
import com.alexvlive.topone.data.remote.model.info.TrackInfo
import com.alexvlive.topone.data.remote.model.lovedtrack.Track

interface LastFmRepository {
    suspend fun getTopTracks(): ResultResponse<TrackX>
    suspend fun getInfo(artist: String, track: String): ResultResponse<TrackInfo>
    suspend fun getSimilar(artist: String, track: String): ResultResponse<List<TrackInfoShort>>
    suspend fun getTopTracksByArtist(artist: String): ResultResponse<List<TrackInfoShort>>
    suspend fun getUserLovedTracks():ResultResponse<List<Track>>
    suspend fun addLoveTrack(artist: String, track: String, sessionKey: String)
    suspend fun removeLoveTrack(artist: String, track: String, sessionKey: String)
    suspend fun getSessionKey(): ResultResponse<String>
}