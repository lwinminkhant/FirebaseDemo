package com.lmkhant.firebasedemo.fcm

data class PushNotification(
    val data: NotificationData,
    val to: String
)
