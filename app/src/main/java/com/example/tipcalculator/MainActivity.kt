package com.example.tipcalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calcButton.setOnClickListener{calculateTip()}
    }

    @SuppressLint("StringFormatInvalid")
    private fun calculateTip() {
        val stringTextField = binding.txtInput.text.toString()
        val selectId = binding.tipOptions.checkedRadioButtonId
        val roundUp = binding.roundSwitch.isChecked

        val cost : Double = stringTextField.toDouble()
        val tipPercent = when (selectId) {
            R.id.optionTwenty -> 0.20
            R.id.optionFifteen -> 0.15
            R.id.optionTen -> 0.10
            else -> 0.18
        }
        var tip = cost * tipPercent
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
        binding.tipAmt.text = getString(R.string.amount, formattedTip)
    }
}