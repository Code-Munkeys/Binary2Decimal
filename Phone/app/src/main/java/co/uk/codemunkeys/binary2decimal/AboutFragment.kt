// Developed by Franz Ayestaran - http://franz.ayestaran.co.uk

// You may use this code in your own projects and upon doing so, you the programmer are solely
// responsible for determining it's worthiness for any given application or task. Here clearly
// states that the code is for learning purposes only and is not guaranteed to conform to any
// programming style, standard, or be an adequate answer for any given problem.

package co.uk.codemunkeys.binary2decimal

import android.os.Bundle
import android.text.method.LinkMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import co.uk.codemunkeys.binary2decimal.databinding.FragmentAboutBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAboutBinding.inflate(inflater, container, false)
        println("AboutFragment.kt/onCreateView - $binding")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAbout.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)

            val txtUrlValue = binding.txtUrl

            txtUrlValue.movementMethod = LinkMovementMethod.getInstance()

            println("AboutFragment.kt/onViewCreated - $binding")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("AboutFragment.kt/onDestroyView - $binding")
        _binding = null
    }
}