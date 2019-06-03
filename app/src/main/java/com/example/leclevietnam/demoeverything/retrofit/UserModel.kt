package com.example.leclevietnam.demoeverything.retrofit

import com.google.gson.annotations.SerializedName

data class UserModel(
        @SerializedName("id")
        var id: Int?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("username")
        var username: String?,
        @SerializedName("email")
        var email: String?,
        @SerializedName("address")
        var address: Address?,
        @SerializedName("phone")
        var phone: String?,
        @SerializedName("website")
        var website: String?,
        @SerializedName("company")
        var company: Company?
)

data class Address(
        @SerializedName("street")
        var street: String?,
        @SerializedName("suite")
        var suite: String?,
        @SerializedName("city")
        var city: String?,
        @SerializedName("zipcode")
        var zipcode: String?,
        @SerializedName("geo")
        var geo: Geo?
)

data class Geo(
        @SerializedName("lat")
        var lat: String?,
        @SerializedName("lng")
        var lng: String?
)

data class Company(
        @SerializedName("name")
        var name: String?,
        @SerializedName("catchPhrase")
        var catchPhrase: String?,
        @SerializedName("bs")
        var bs: String?
)
