package pl.edu.uwr.studenthardlife

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import pl.edu.uwr.studenthardlife.databinding.FragmentEditBinding


class Edit : Fragment() {
    private lateinit var binding: FragmentEditBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dbHandler = DBHandler(requireContext())
        val iD = requireArguments().getInt("valId")
        val num = requireArguments().getInt("valNum")
        val bin = FragmentEditBinding.inflate(LayoutInflater.from(context))
        bin.apply {

            binding.editTextText1.setText(dbHandler.getElementt(iD)[0])

            binding.buttonSAVE1.setOnClickListener {

                val updateInfo = binding.editTextText1.text.toString()
                if (updateInfo.isNotEmpty()) {
                    dbHandler.updateElement(iD, updateInfo)
                    Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show()
                }

            }


        }
    }
}