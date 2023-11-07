package pl.edu.uwr.studenthardlife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import pl.edu.uwr.studenthardlife.databinding.FragmentAddBinding


class Add : Fragment() {
    private lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dbHandler = DBHandler(requireContext())
        val numberoflist = requireArguments().getInt("valNum")
        val bin = FragmentAddBinding.inflate(LayoutInflater.from(context))
        bin.apply {

            binding.buttonSAVE.setOnClickListener{

                val updateInfo = binding.editTextText.text.toString()
                if (updateInfo.isNotEmpty()) {
                    val d = ListTable(0,0,updateInfo,numberoflist,0)
                    dbHandler.addElement(d)
                    Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show()
                }

            }

        }

    }
}