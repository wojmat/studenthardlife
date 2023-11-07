package pl.edu.uwr.studenthardlife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2


class FragmentGalery : Fragment() {
    private val dbHandler by lazy {
        DBHandler(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_galery, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val numlist = requireArguments().getInt("valNum")
        view.findViewById<ViewPager2>(R.id.viewPager2).apply {
            adapter = AdapterPhoto(dbHandler,numlist)
        }

        var button = view.findViewById<Button>(R.id.button_add_photo)
        button.setOnClickListener {
            view.findNavController().navigate(
                FragmentGaleryDirections.actionFragmentGaleryToFragmentAddPhoto(
                    valNum = numlist
                )
            )
        }


    }

}