package com.example.fifaplayerpotentials.ui.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.fifaplayerpotentials.R
import com.example.fifaplayerpotentials.ui.shared.SharedViewModel
import androidx.lifecycle.Observer
import android.util.Log
import com.example.fifaplayerpotentials.LOG_TAG
import com.example.fifaplayerpotentials.databinding.FragmentDetailBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (requireActivity() as AppCompatActivity).run {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        setHasOptionsMenu(true)
        navController = Navigation.findNavController(
            requireActivity(), R.id.nav_host
        )

        viewModel = ViewModelProviders.of(requireActivity()).get(SharedViewModel::class.java)

        val binding = FragmentDetailBinding.inflate(
            inflater, container, false
        )
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            navController.navigateUp()
        }
        return super.onOptionsItemSelected(item)
    }


}
