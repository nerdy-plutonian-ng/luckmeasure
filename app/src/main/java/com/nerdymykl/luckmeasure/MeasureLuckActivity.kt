package com.nerdymykl.luckmeasure

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.google.android.material.button.MaterialButton

class MeasureLuckActivity : AppCompatActivity() {

    private var percent : Int = 10
    private var randomNumber : Int = -1
    private lateinit var yoYoString : YoYo.YoYoString
    private lateinit var giftBox : ImageView
    private var count : Int = 0
    private var gameover : Boolean = false
    private lateinit var oneTV : MaterialButton
    private lateinit var twoTV : MaterialButton
    private lateinit var threeTV : MaterialButton
    private lateinit var fourTV : MaterialButton
    private lateinit var fiveTV : MaterialButton
    private lateinit var sixTV : MaterialButton
    private lateinit var sevenTV : MaterialButton
    private lateinit var eightTV : MaterialButton
    private lateinit var nineTV : MaterialButton
    private lateinit var zeroTV : MaterialButton
    private lateinit var randomNumberTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_measure_luck)

        giftBox = findViewById(R.id.giftImageView)
        oneTV = findViewById(R.id.one_Button)
        twoTV = findViewById(R.id.two_Button)
        threeTV = findViewById(R.id.three_Button)
        fourTV = findViewById(R.id.four_Button)
        fiveTV = findViewById(R.id.five_Button)
        sixTV = findViewById(R.id.six_Button)
        sevenTV = findViewById(R.id.seven_Button)
        eightTV = findViewById(R.id.eight_Button)
        nineTV = findViewById(R.id.nine_Button)
        zeroTV = findViewById(R.id.zero_Button)
        randomNumberTextView = findViewById(R.id.randomNumber_TextView)

        animateGiftBox()
        generateRandomNumber()

        oneTV.setOnClickListener(clickListener)
        twoTV.setOnClickListener(clickListener)
        threeTV.setOnClickListener(clickListener)
        fourTV.setOnClickListener(clickListener)
        fiveTV.setOnClickListener(clickListener)
        sixTV.setOnClickListener(clickListener)
        sevenTV.setOnClickListener(clickListener)
        eightTV.setOnClickListener(clickListener)
        nineTV.setOnClickListener(clickListener)
        zeroTV.setOnClickListener(clickListener)

    }

    private fun animateGiftBox(){
        giftBox.visibility = View.VISIBLE
        YoYo.with(Techniques.Pulse)
            .duration(1000)
            .repeat(YoYo.INFINITE)
            .pivot(YoYo.CENTER_PIVOT, YoYo.CENTER_PIVOT)
            .playOn(findViewById(R.id.giftImageView));
    }

    private fun generateRandomNumber(){
        val range : IntRange = 1..10
        randomNumber = range.random()
        val randomNumberTextView : TextView = findViewById(R.id.randomNumber_TextView)
        randomNumberTextView.text = randomNumber.toString()
        Log.d("ML", "generateRandomNumber: $randomNumber")
    }

    private val clickListener: View.OnClickListener = View.OnClickListener { view ->
        val textView = view as TextView
        var chosen : Int = -1
        when (textView.id) {
            R.id.one_Button -> {
                chosen = 1
            }
            R.id.two_Button -> {
                chosen = 2
            }
            R.id.three_Button -> {
                chosen = 3
            }
            R.id.four_Button -> {
                chosen = 4
            }
            R.id.five_Button -> {
                chosen = 5
            }
            R.id.six_Button -> {
                chosen = 6
            }
            R.id.seven_Button -> {
                chosen = 7
            }
            R.id.eight_Button -> {
                chosen = 8
            }
            R.id.nine_Button -> {
                chosen = 9
            }
            R.id.zero_Button -> {
                chosen = 10
            }
        }
        count++
        if(chosen == randomNumber){
            giftBox.visibility = View.GONE
            randomNumberTextView.visibility = View.VISIBLE
            YoYo.with(Techniques.Pulse)
                    .duration(1000)
                    .repeat(YoYo.INFINITE)
                    .pivot(YoYo.CENTER_PIVOT, YoYo.CENTER_PIVOT)
                    .playOn(findViewById(R.id.randomNumber_TextView));
            showReplayDialog(percent)
            setGameOver()
        } else {
            textView.isEnabled = false
            textView.setTextColor(this.resources.getColor(R.color.primaryTextColor))
            percent--
            if(count == 10){
                showReplayDialog(percent)
                setGameOver()
            }
        }
    }

    private fun showReplayDialog(percent : Int){
        val newFragment = ReplayDialog(percent)
        newFragment.isCancelable = false
        newFragment.show(supportFragmentManager, "replay")
    }

    private fun setGameOver(){
        gameover = true
    }

    fun resetGame(){
        oneTV.setTextColor(this.resources.getColor(R.color.primaryColor))
        twoTV.setTextColor(this.resources.getColor(R.color.primaryColor))
        threeTV.setTextColor(this.resources.getColor(R.color.primaryColor))
        fourTV.setTextColor(this.resources.getColor(R.color.primaryColor))
        fiveTV.setTextColor(this.resources.getColor(R.color.primaryColor))
        sixTV.setTextColor(this.resources.getColor(R.color.primaryColor))
        sevenTV.setTextColor(this.resources.getColor(R.color.primaryColor))
        eightTV.setTextColor(this.resources.getColor(R.color.primaryColor))
        nineTV.setTextColor(this.resources.getColor(R.color.primaryColor))
        zeroTV.setTextColor(this.resources.getColor(R.color.primaryColor))
        oneTV.isEnabled = true
        twoTV.isEnabled = true
        threeTV.isEnabled = true
        fourTV.isEnabled = true
        fiveTV.isEnabled = true
        sixTV.isEnabled = true
        sevenTV.isEnabled = true
        eightTV.isEnabled = true
        nineTV.isEnabled = true
        zeroTV.isEnabled = true
        generateRandomNumber()
        count = 0
        gameover = false
        randomNumberTextView.visibility = View.GONE
        percent = 10
        animateGiftBox()
    }

}