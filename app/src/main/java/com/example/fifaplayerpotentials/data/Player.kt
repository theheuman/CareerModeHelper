package com.example.fifaplayerpotentials.data

data class Player (
    var playerName : String,
    var squad : String,
    var potential : Int,
    var overall : Int,
    var postion : String,
    var altPositions : String,
    var country : String,
    var skills : Int,
    var weakFoot : Int,
    var soldFor: Double,
    var sellOnClause: Double,
    var transferredTo : String,
    var imagePath : String,
    var rating : Rating
)

