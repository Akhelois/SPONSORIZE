package com.enterprenuership.sponsorize.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    var userId : String,
    var username : String,
    var email : String,
    var password : String,
    var roles : String
) : Parcelable
