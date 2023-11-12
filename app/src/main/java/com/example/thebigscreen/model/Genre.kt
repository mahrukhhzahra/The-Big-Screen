package com.example.thebigscreen.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
class Genre (
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String
): Parcelable