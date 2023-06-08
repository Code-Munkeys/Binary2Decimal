// Developed by Franz Ayestaran - http://franz.ayestaran.co.uk

// You may use this code in your own projects and upon doing so, you the programmer are solely
// responsible for determining it's worthiness for any given application or task. Here clearly
// states that the code is for learning purposes only and is not guaranteed to conform to any
// programming style, standard, or be an adequate answer for any given problem.

package co.uk.codemunkeys.binary2decimal

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import co.uk.codemunkeys.binary2decimal.databinding.FragmentConvertBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ConvertFragment : Fragment() {

    private var _binding: FragmentConvertBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentConvertBinding.inflate(inflater, container, false)

        val txtBinaryValue = binding.txtBinaryValue
        val txtDecimalValue = binding.txtDecimalValue
        val sekDecimalValueSlider = binding.sekDecimalValue
        var strBinary: String
        var strDecimal: String

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
                    Toast.makeText(activity,message, Toast.LENGTH_SHORT).show()
                }
            } else {
                val message = "Decimal value must be between 0 and 65535"
                Toast.makeText(activity,message, Toast.LENGTH_SHORT).show()
            }
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
                Toast.makeText(activity,message, Toast.LENGTH_SHORT).show()
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
                    Toast.makeText(activity,message, Toast.LENGTH_SHORT).show()
                    txtDecimalValue.setTextColor(Color.parseColor("#8BC34A"))
                } else {
                    val message = "Binary and decimal do not equal the same value!"
                    Toast.makeText(activity,message, Toast.LENGTH_SHORT).show()
                    txtDecimalValue.setTextColor(Color.parseColor("#ff0000"))
                }
            } else {
                val message = "Decimal value must be between 0 and 65535"
                Toast.makeText(activity,message, Toast.LENGTH_SHORT).show()
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

        binding.btnAbout.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        println("ConvertFragment.kt/onCreateView - $binding")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        println("ConvertFragment.kt/onViewCreated - $binding")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("ConvertFragment.kt/onDestroyView - $binding")
        _binding = null
    }
}