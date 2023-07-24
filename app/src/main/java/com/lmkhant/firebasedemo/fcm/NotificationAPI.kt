package com.lmkhant.firebasedemo.fcm

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface NotificationAPI {
    //@Headers("Authorizatin : key = $SERVER_KEY","Content-Type: $CONTENT_TYPE" )
    @POST("fcm/send")
    suspend fun postNotification(
        @Body notification: PushNotification
    ): Response<ResponseBody>
}