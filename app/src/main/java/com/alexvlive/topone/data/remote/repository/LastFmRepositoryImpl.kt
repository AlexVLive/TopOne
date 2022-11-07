package com.alexvlive.topone.data.remote.repository

import com.alexvlive.topone.common.ResultResponse
import com.alexvlive.topone.data.remote.api.LastFmAPI
import com.alexvlive.topone.data.remote.model.TrackInfoShort
import com.alexvlive.topone.data.remote.model.alltracks.AllTracks
import com.alexvlive.topone.data.remote.model.alltracks.TrackX
import com.alexvlive.topone.data.remote.model.info.TrackInfo
import com.alexvlive.topone.data.remote.model.lovedtrack.Track
import com.alexvlive.topone.util.GetResultWithRetryPolicy
import okhttp3.*

class LastFmRepositoryImpl (private val lastFmApi: LastFmAPI) : LastFmRepository {

    override suspend fun getTopTracks(): ResultResponse<TrackX> = GetResultWithRetryPolicy(
        lmd = { lastFmApi.getTopTracks().tracks.track.first() }
    ).execute()

    override suspend fun getInfo(artist: String, track: String): ResultResponse<TrackInfo> = GetResultWithRetryPolicy(
        lmd = { lastFmApi.getInfo(artist = artist, track = track) }
    ).execute()

    override suspend fun getSimilar(artist: String, track: String): ResultResponse<List<TrackInfoShort>> = GetResultWithRetryPolicy(
        lmd = {
            val similarTracks = lastFmApi.getSimilar(artist = artist, track = track)
            similarTracks.similartracks.track
                .map { TrackInfoShort(name = it.artist.name, track = it.name) }
        }
    ).execute()

    override suspend fun getTopTracksByArtist(artist: String): ResultResponse<List<TrackInfoShort>> = GetResultWithRetryPolicy(
        lmd = {
            val topTracks = lastFmApi.getTopTracksByArtist(artist).toptracks.track
            topTracks.map { TrackInfoShort(name = it.artist.name, track = it.name) }
        }
    ).execute()

    override suspend fun getUserLovedTracks(): ResultResponse<List<Track>> = GetResultWithRetryPolicy(
        lmd = { lastFmApi.getUserLovedTracks().lovedtracks.track }
    ).execute()

    override suspend fun addLoveTrack(artist: String, track: String, sessionKey: String) {
        lastFmApi.addLoveTrack(artist = artist, track = track, sessionKey = sessionKey)
    }

    override suspend fun removeLoveTrack(artist: String, track: String, sessionKey: String) {
        lastFmApi.removeLoveTrack(artist = artist, track = track, sessionKey = sessionKey)
    }

    override suspend fun getSessionKey(): ResultResponse<String> = GetResultWithRetryPolicy(
        lmd = { lastFmApi.getSessionKey() }
    ).execute()


}