package com.loginov.hospital.main.person.create

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.loginov.hospital.R
import com.loginov.hospital.main.ward.one.WardFragment.Companion.WARD_KEY
import com.loginov.hospital.model.PersonDto
import kotlinx.android.synthetic.main.dialog_create_person.*
import kotlinx.android.synthetic.main.fragment_create_person.*
import kotlinx.android.synthetic.main.fragment_create_person.dlg_person__diagnosis
import kotlinx.android.synthetic.main.fragment_create_person.dlg_person__father_name
import kotlinx.android.synthetic.main.fragment_create_person.dlg_person__first_name
import kotlinx.android.synthetic.main.fragment_create_person.dlg_person__last_name
import kotlinx.android.synthetic.main.fragment_create_person.dlg_person__ok

class PersonCreateFragment : Fragment() {

    companion object {
        fun newInstance() = PersonCreateFragment()
    }

    private lateinit var viewModel: PersonCreateViewModel
    private lateinit var adapter: DiagnosisSpinnerAdapter
    private var wardId: Long = -1L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        wardId = requireArguments().getLong(WARD_KEY)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create_person, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PersonCreateViewModel::class.java)

        viewModel.getDiagnosis().observe(viewLifecycleOwner, Observer {
            adapter.bindData(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DiagnosisSpinnerAdapter(requireContext())
        frg_create_person__spinner.setAdapter(adapter)

        dlg_person__ok.setOnClickListener {
            if (!TextUtils.isEmpty(dlg_person__first_name.text)
                && !TextUtils.isEmpty(dlg_person__last_name.text)
                && !TextUtils.isEmpty(dlg_person__father_name.text)
            ) {
                val firstName = dlg_person__first_name.text.toString()
                val lastName = dlg_person__last_name.text.toString()
                val fatherName = dlg_person__father_name.text.toString()
                val diagnosis = adapter.items[frg_create_person__spinner.selectedIndex].name

                viewModel.postPerson(
                    PersonDto(
                        firstName,
                        lastName,
                        fatherName,
                        diagnosis,
                        wardId
                    )
                ) {
                    findNavController().popBackStack()
                }
            } else {
                Toast.makeText(requireContext(), R.string.wrong_data, Toast.LENGTH_LONG).show()
            }
        }
    }

}