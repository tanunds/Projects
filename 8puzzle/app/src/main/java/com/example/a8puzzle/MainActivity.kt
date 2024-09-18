package com.example.a8puzzle

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var mainView:ViewGroup?=null
    private var board: Board?=null
    private var boardView: BoardView?=null
    private var moves:TextView? = null
    private var boardSize = 3


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainView = findViewById(R.id.mainView)
        moves = findViewById(R.id.tvNumMoves)
        moves!!.setTextColor(Color.RED)
        moves!!.textSize = 22f
        newGame()
    }

    private fun newGame(){
        board = Board(boardSize)
        board!!.addBoardChangeListener(boardChangeListener)
        board!!.rearrange()
        mainView!!.removeView(boardView)
        boardView = BoardView(this, board!!)
        mainView!!.addView(boardView)
        moves!!.text = "# of moves : 0"
    }
    fun changeSize(newSize:Int){
        if (newSize != boardSize){
            boardSize = newSize
            newGame()
            boardView!!.invalidate()
        }
    }
    private val boardChangeListener : BoardChangeListener = object :BoardChangeListener{
        override fun tileSlid(from: Place?, to: Place?, numOfMoves: Int) {
            moves!!.text = "# of moves : ${numOfMoves}"
        }

        override fun solved(numOfMoves: Int) {
            moves!!.text = "Solved in ${numOfMoves} moves"

            AlertDialog.Builder(this@MainActivity).setTitle("You won !")
                .setIcon(R.drawable.ic_celebration)
                .setMessage("you won in $numOfMoves moves. \n Do you want a New Game?")
                .setPositiveButton("Yes"){
                    dialog, _->

                    board!!.rearrange()
                    moves!!.text = "# of moves : 0"
                    boardView!!.invalidate()

                    dialog.dismiss()
                }
                .setNegativeButton("No"){
                    dialog, _->
                    dialog.dismiss()
                }
                .create().show()
            val edtPlayerName : EditText = findViewById(R.id.edtPlayerName)
            var player = PlayerItemDAO()
            player.strName = edtPlayerName.text.toString()
            if(player.strName == ""){
                player.strName = "no name"
            }
            player.score = numOfMoves
            player.size = "$boardSize x $boardSize"
            HttpManager.retrofitService.createPost(player).enqueue(object : retrofit2.Callback<PlayerItemDAO?>{
                override fun onResponse(call: Call<PlayerItemDAO?>,
                                        response: Response<PlayerItemDAO?>
                ) {
                    val temp = response.body()
                    Log.d("onResponse", "onResponse: $temp")
                }

                override fun onFailure(call: Call<PlayerItemDAO?>, t: Throwable) {
                    Log.d("debug", "onFailure: $t")
                }

            })
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_newGame->{
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("New Game")
                    .setIcon(R.drawable.ic_new_game)
                    .setMessage("Start New Game?")
                    .setPositiveButton("Yes"){
                        dialog, _->

                        board!!.rearrange()
                        moves!!.text = "# of moves : 0"
                        boardView!!.invalidate()

                        dialog.dismiss()
                    }
                    .setNegativeButton("No"){
                        dialog, _->
                        dialog.dismiss()

                    }
                    .create().show()
                true
            }
            R.id.action_settings->{
                val settings = SettingDialogFragment(boardSize)
                settings.show(supportFragmentManager, "fragment_settings")
                true
            }
            R.id.action_help->{
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Instructions")
                    .setMessage("Click on the tile to move it to adjacent empty place. Rearrange the board to match the goal state.")
                    .setPositiveButton("Understood. Let's play"){
                        dialog, _->
                        dialog.dismiss()
                    }
                    .create().show()
                true
            }
            R.id.action_leaderboard -> {
                val myIntent = Intent(this, Leaderboard::class.java)
                startActivity(myIntent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}