package com.loginov.hospital.main.ward.one

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.loginov.hospital.R
import com.loginov.hospital.UserUtils
import com.loginov.hospital.main.person.adapter.PersonAdapter
import kotlinx.android.synthetic.main.fragment_person_list.*

class WardFragment : Fragment() {

    private var wardId: Long = 0

    private lateinit var viewModel: WardViewModel
    private lateinit var adapter: PersonAdapter

    companion object {
        const val WARD_KEY = "WARD_KEY"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        wardId = requireArguments().getLong(WARD_KEY)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this, WardViewModelFactory(wardId))[WardViewModel::class.java]
        viewModel.getPersons().observe(viewLifecycleOwner, Observer {
            adapter.bindData(it)
        })

        viewModel.getSystemMessage().observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_person_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PersonAdapter {
            viewModel.delete(it)
        }
        frg_person_list__recycler.adapter = adapter

        frg_person_list__fab.setOnClickListener {
            val bundle = Bundle()
            bundle.putLong(WARD_KEY, wardId)
            findNavController().navigate(
                R.id.action_navigation_ward_to_navigation_create_person,
                bundle
            )
        }
    }

}
