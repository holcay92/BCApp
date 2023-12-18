package com.example.blockchainapp.model

data class UserData(
    var name: String = "",
    var surname: String = "",
    var ssn: String = "",
    var phoneNumber: String = "",
    var itemTitle: String = "",
    var itemDescription: String = "",
    var isFavorite: Boolean = false,
)
