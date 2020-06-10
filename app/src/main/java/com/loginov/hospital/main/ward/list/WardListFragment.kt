package com.loginov.hospital.main.ward.list

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
import com.loginov.hospital.main.showCreateWardDialog
import com.loginov.hospital.main.ward.list.adapter.WardAdapter
import com.loginov.hospital.main.ward.one.WardFragment
import kotlinx.android.synthetic.main.fragment_ward_list.*

class WardListFragment : Fragment() {

    private lateinit var viewModel: WardListViewModel
    private lateinit var adapter: WardAdapter


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this)[WardListViewModel::class.java]
        viewModel.getWards().observe(viewLifecycleOwner, Observer {
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
    ): View? = inflater.inflate(R.layout.fragment_ward_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = WardAdapter {
            val bundle = Bundle()
            bundle.putLong(WardFragment.WARD_KEY, it)
            findNavController().navigate(R.id.navigation_ward, bundle)
        }

        frg_ward_list__recycler.adapter = adapter
        if (!UserUtils.isAdmin) {
            frg_ward_list__fab.hide()
        }

        frg_ward_list__fab.setOnClickListener {
            showCreateWardDialog {
                viewModel.postWard(it)
            }
        }
    }
}
