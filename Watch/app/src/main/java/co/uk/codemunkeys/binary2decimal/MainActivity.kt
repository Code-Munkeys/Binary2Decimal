// Developed by Franz Ayestaran - http://franz.ayestaran.co.uk

// You may use this code in your own projects and upon doing so, you the programmer are solely
// responsible for determining it's worthiness for any given application or task. Here clearly
// states that the code is for learning purposes only and is not guaranteed to conform to any
// programming style, standard, or be an adequate answer for any given problem.

package co.uk.codemunkeys.binary2decimal

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import co.uk.codemunkeys.binary2decimal.databinding.ActivityMainBinding
import kotlin.concurrent.fixedRateTimer

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val txtBinaryValue = binding.txtBinaryValue
        val txtDecimalValue = binding.txtDecimalValue
        val sekDecimalValueSlider = binding.sekDecimalValue
        var strBinary: String
        var strDecimal: String
        var layoutAdjusted = false

        fun setMargin(left: Int, top: Int, right: Int, bottom: Int, textview: TextView) {
            val param = textview.layoutParams as ViewGroup.MarginLayoutParams
            param.setMargins(left,top,right,bottom)
            textview.layoutParams = param
        }

        fun getWatchSize(): Int {
            val view = findViewById<View>(R.id.frmLayout)
            val width = view.width.toString()
            //String.format("Width %s, Height: %s", x, y)

            var mm = 0
            val txtBinaryLayout = findViewById<View>(R.id.txtBinaryValue)
            val txtBinaryLayoutParams: ViewGroup.LayoutParams = txtBinaryLayout.layoutParams

            val txtDecimalLayout = findViewById<View>(R.id.txtDecimalValue)
            val txtDecimalLayoutParams: ViewGroup.LayoutParams = txtDecimalLayout.layoutParams

            val sekDecimalValueLayout = findViewById<View>(R.id.sekDecimalValue)
            val sekDecimalValueLayoutParams: ViewGroup.LayoutParams = sekDecimalValueLayout.layoutParams

            println("getWatchSize(): $width")

            when(view.width){
                // Round
                272 -> mm = 30
                282 -> mm = 40
                320 -> mm = 44
                322 -> mm = 46
                //Square - Rectangular
                360 -> mm = 47
                402 -> mm = 49
            }

            if (mm == 30)
            {
                txtBinaryValue.textSize = 11.0F
                txtBinaryLayoutParams.width = 250
                txtBinaryValue.layoutParams = txtBinaryLayoutParams

                setMargin(0,85,0,0, txtBinaryValue)

                txtDecimalValue.textSize = 11.0F
                txtDecimalLayoutParams.width = 65
                txtDecimalValue.layoutParams = txtDecimalLayoutParams

                sekDecimalValueLayoutParams.width = 250
            }

            if (mm == 40)
            {
                txtBinaryValue.textSize = 12.0F
                txtBinaryLayoutParams.width = 260
                txtBinaryValue.layoutParams = txtBinaryLayoutParams

                setMargin(0,85,0,0, txtBinaryValue)

                txtDecimalValue.textSize = 12.0F
                txtDecimalLayoutParams.width = 75
                txtDecimalValue.layoutParams = txtDecimalLayoutParams

                sekDecimalValueLayoutParams.width = 260
            }

            if (mm == 44)
            {
                txtBinaryValue.textSize = 15.0F
                txtBinaryLayoutParams.width = 300
                txtBinaryValue.layoutParams = txtBinaryLayoutParams

                setMargin(0,95,0,0, txtBinaryValue)

                txtDecimalValue.textSize = 15.0F
                txtDecimalLayoutParams.width = 100
                txtDecimalValue.layoutParams = txtDecimalLayoutParams

                sekDecimalValueLayoutParams.width = 300
            }

            if (mm == 46)
            {
                txtBinaryValue.textSize = 16.0F
                txtBinaryLayoutParams.width = 300
                txtBinaryValue.layoutParams = txtBinaryLayoutParams

                setMargin(0,90,0,0, txtBinaryValue)

                txtDecimalValue.textSize = 16.0F
                txtDecimalLayoutParams.width = 100
                txtDecimalValue.layoutParams = txtDecimalLayoutParams

                sekDecimalValueLayoutParams.width = 300
            }

            if (mm == 47)
            {
                txtBinaryValue.textSize = 16.0F
                txtBinaryLayoutParams.width = 340
                txtBinaryValue.layoutParams = txtBinaryLayoutParams

                setMargin(0,100,0,0, txtBinaryValue)

                txtDecimalValue.textSize = 16.0F
                txtDecimalLayoutParams.width = 100
                txtDecimalValue.layoutParams = txtDecimalLayoutParams

                sekDecimalValueLayoutParams.width = 340
            }

            if (mm == 49) {
                txtBinaryValue.textSize = 16.0F
                txtBinaryLayoutParams.width = 380
                txtBinaryValue.layoutParams = txtBinaryLayoutParams

                setMargin(0,125,0,0, txtBinaryValue)

                txtDecimalValue.textSize = 16.0F
                txtDecimalLayoutParams.width = 150
                txtDecimalValue.layoutParams = txtDecimalLayoutParams

                sekDecimalValueLayoutParams.width = 380
            }

            layoutAdjusted = true;
            return mm
        }

        binding.btnGenerate.setOnClickListener {
            val intRandomByte : Int = ((0..65535).random())
            val binBinaryValue: String = Integer.toBinaryString(intRandomByte)
            sekDecimalValueSlider.progress = 0

            txtBinaryValue.setText((binBinaryValue).padStart(16, '0'))
            txtDecimalValue.setText("")
            txtDecimalValue.setTextColor(Color.parseColor("#8BC34A"))
        }

        binding.btnDec2Bin.setOnClickListener {
            strDecimal = txtDecimalValue.text.toString()

            if (strDecimal != "") {
                val intDecimal: Int = strDecimal.toInt()
                txtDecimalValue.setTextColor(Color.parseColor("#8BC34A"))

                if (intDecimal < 65536) {
                    txtBinaryValue.setText(Integer.toBinaryString(intDecimal).padStart(16, '0'))
                    sekDecimalValueSlider.progress = intDecimal
                } else {
                    val message = "Decimal value must be between 0 and 65535"
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                }
            } else {
                val message = "Decimal value must be between 0 and 65535"
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnDec2Bin.onFocusChangeListener
        run {
            txtBinaryValue.clearFocus()
        }

        binding.btnBin2Dec.setOnClickListener {
            strBinary = txtBinaryValue.text.toString()

            if (strBinary != "") {
                val intBinary: Int = strBinary.toInt(2)
                txtDecimalValue.setText(intBinary.toString())
                txtDecimalValue.setTextColor(Color.parseColor("#8BC34A"))
                sekDecimalValueSlider.progress = intBinary
            } else {
                val message = "Binary value must be between 0 and 1111111111111111"
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnCompare.setOnClickListener {
            strBinary = txtBinaryValue.text.toString()
            strDecimal = txtDecimalValue.text.toString()

            if (strDecimal != "") {

                val intDecimal: Int = strDecimal.toInt()
                val intBinary: Int = strBinary.toInt(2)

                if (intBinary == intDecimal) {
                    val message = "Binary and decimal equal the same value!"
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                    txtDecimalValue.setTextColor(Color.parseColor("#8BC34A"))
                } else {
                    val message = "Binary and decimal do not equal the same value!"
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                    txtDecimalValue.setTextColor(Color.parseColor("#ff0000"))
                }
            } else {
                val message = "Decimal value must be between 0 and 65535"
                Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
            }
        }

        binding.sekDecimalValue.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                val intDecimal: Int = sekDecimalValueSlider.progress
                txtBinaryValue.setText(Integer.toBinaryString(intDecimal).padStart(16, '0'))
                txtDecimalValue.setText(intDecimal.toString())
                txtDecimalValue.setTextColor(Color.parseColor("#8BC34A"))
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                // you can probably leave this empty
            }
        })

        val fixedRateTimer = fixedRateTimer(
            name = "currentTimeTimer",
            initialDelay = 100, period = 60000, daemon = true
        ) {
            runOnUiThread {
                if (!layoutAdjusted) {
                    println("getWatchSize(): ${getWatchSize()}mm")
                }
            }
        }

        txtBinaryValue.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                txtDecimalValue.clearFocus()
            }
            false
        }

        println("MainActivity.kt/onCreateView - $binding")
    }
}