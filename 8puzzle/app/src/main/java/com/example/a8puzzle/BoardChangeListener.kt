package com.example.a8puzzle

interface BoardChangeListener {
    fun tileSlid(from:Place?, to:Place?, numOfMoves:Int)
    fun solved(numOfMoves: Int)
}