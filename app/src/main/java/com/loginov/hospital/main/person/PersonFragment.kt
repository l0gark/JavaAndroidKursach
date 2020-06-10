package com.loginov.hospital.main.person

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.loginov.hospital.R
import com.loginov.hospital.main.person.adapter.PersonAdapter
import kotlinx.android.synthetic.main.fragment_person_list.*

class PersonFragment : Fragment() {

    private lateinit var viewModel: PersonViewModel
    private lateinit var adapter: PersonAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProvider(this)[PersonViewModel::class.java]
        viewModel.getPersons().observe(viewLifecycleOwner, Observer {
            adapter.bindData(it)
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
            viewModel.deletePerson(it)
        }
        frg_person_list__recycler.adapter = adapter
        frg_person_list__fab.visibility = View.GONE
    }
}
