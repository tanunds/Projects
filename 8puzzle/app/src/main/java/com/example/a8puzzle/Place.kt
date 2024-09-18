package com.example.a8puzzle

class Place (var x:Int, var y:Int, board: Board){
    var tile:Tile? = null
    private val board:Board
    constructor(x: Int,y: Int,number: Int, board: Board): this (x,y,board) {
        tile = Tile(number)
    }
    fun hasTile():Boolean{
        return tile != null
    }
    fun slideable():Boolean{
        return hasTile() && board.slidable(this)
    }
    fun slide(){
        board.slide(tile!!)
    }
    init {
        this.y = y
        this.board = board
    }
}