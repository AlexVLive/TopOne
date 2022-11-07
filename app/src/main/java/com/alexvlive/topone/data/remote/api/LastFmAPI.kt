package com.alexvlive.topone.data.remote.api

import com.alexvlive.topone.data.remote.model.alltracks.AllTracks
import com.alexvlive.topone.data.remote.model.info.TrackInfo
import com.alexvlive.topone.data.remote.model.lovedtrack.UserLovedTracks
import com.alexvlive.topone.data.remote.model.similar.SimilarTracks
import com.alexvlive.topone.data.remote.model.similarartist.SimilarArtistTracks
import okhttp3.*
import retrofit2.http.*
import java.math.BigInteger
import java.security.MessageDigest
import java.util.TreeMap
import java.util.TreeSet

interface LastFmAPI {

    companion object{

        private const val API_KEY = "7286ae5ec8662a38dd094df5844ce48f"
        private const val SHARED_SECRET = "8d9f45616e38d4fd33f4694a76684f26"
        private const val USER_NAME = "AlexPeet23"
        private const val USER_PASSWORD = "erwer344gAA$"

        private const val METHOD_TRACK_LOVE = "track.love"
        private const val METHOD_TRACK_UNLOVE = "track.unlove"
        private const val METHOD_AUT_MOBILE_SESSION = "auth.getMobileSession"

        private fun getSignature(parameters: Map<String, String>): String {
            var result = ""
            parameters.toSortedMap().forEach{ (key, value) ->
                result += key + getUTF8(value)
            }
            return md5(result + getUTF8(SHARED_SECRET))
        }

        private fun md5(value: String): String {
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1, md.digest(value.toByteArray())).toString(16)
                .padStart(32, '0')
        }

        private fun getUTF8(value: String): String {
            val charset = Charsets.UTF_8
            val byteArray = value.toByteArray(charset)
            return byteArray.toString(charset)
        }
    }

    @GET("/2.0/?method=chart.gettoptracks&api_key=$API_KEY&format=json")
    suspend fun getTopTracks(): AllTracks

    @GET("/2.0/?method=track.getInfo&api_key=$API_KEY&format=json")
    suspend fun getInfo(@Query("artist") artist:String, @Query("track") track: String): TrackInfo

    @GET("/2.0/?method=track.getsimilar&api_key=$API_KEY&format=json")
    suspend fun getSimilar(@Query("artist") artist:String, @Query("track") track: String): SimilarTracks

    @GET("/2.0/?method=artist.gettoptracks&api_key=$API_KEY&format=json")
    suspend fun getTopTracksByArtist(@Query("artist") artist: String): SimilarArtistTracks

    @GET("/2.0/?method=user.getlovedtracks&user=$USER_NAME&api_key=$API_KEY&format=json")
    suspend fun getUserLovedTracks(): UserLovedTracks

    suspend fun getSessionKey(): String {
        val parametersMap: Map<String, String> = mapOf(
            "method" to getUTF8(METHOD_AUT_MOBILE_SESSION),
            "api_key" to getUTF8(API_KEY),
            "password" to getUTF8(USER_PASSWORD),
            "username" to getUTF8(USER_NAME))
        val client = OkHttpClient()
        val body: RequestBody = FormBody.Builder()
            .add("api_key", API_KEY)
            .add("method", METHOD_AUT_MOBILE_SESSION)
            .add("password", USER_PASSWORD)
            .add("username", USER_NAME)
            .add("api_sig", getSignature(parametersMap))
            .build()
        val request: Request = Request.Builder()
            .url("https://ws.audioscrobbler.com/2.0/")
            .post(body)
            .build()
        val call: Call = client.newCall(request)
        val response: Response = call.execute()
        return response.body?.string()
            ?.substringAfter("<key>")
            ?.substringBefore("</key>")
            ?: ""
    }

    @POST("/2.0/")
    suspend fun addLoveTrackPost(
        @Query("api_key") key: String = getUTF8(API_KEY),
        @Query("api_sig") signature: String,
        @Query("artist") artist: String,
        @Query("method") method: String = getUTF8(METHOD_TRACK_LOVE),
        @Query("sk") sessionKey: String,
        @Query("track") track: String
    )

    suspend fun addLoveTrack(artist: String, track: String, sessionKey: String){
        val parametersMap: Map<String, String> = mapOf(
            "artist" to artist,
            "track" to track,
            "api_key" to API_KEY,
            "method" to METHOD_TRACK_LOVE,
            "sk" to sessionKey)
        addLoveTrackPost(
            artist = getUTF8(artist),
            track = getUTF8(track),
            signature = getSignature(parameters = parametersMap),
            sessionKey = getUTF8(sessionKey)
        )
    }

    @POST("/2.0/")
    suspend fun removeLoveTrackPost(
        @Query("api_key") key: String = getUTF8(API_KEY),
        @Query("api_sig") signature: String,
        @Query("artist") artist: String,
        @Query("method") method: String = getUTF8(METHOD_TRACK_UNLOVE),
        @Query("sk") sessionKey: String,
        @Query("track") track: String
    )

    suspend fun removeLoveTrack(artist: String, track: String, sessionKey: String){
        val parametersMap: Map<String, String> = mapOf(
            "artist" to artist,
            "track" to track,
            "api_key" to API_KEY,
            "method" to METHOD_TRACK_UNLOVE,
            "sk" to sessionKey)
        removeLoveTrackPost(
            artist = getUTF8(artist),
            track = getUTF8(track),
            signature = getSignature(parameters = parametersMap),
            sessionKey = getUTF8(sessionKey)
        )
    }
}