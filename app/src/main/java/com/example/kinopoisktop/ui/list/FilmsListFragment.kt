package com.example.kinopoisktop.ui.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kinopoisktop.R
import com.example.kinopoisktop.data.state.Result
import com.example.kinopoisktop.databinding.FragmentMainBinding
import com.example.kinopoisktop.ui.list.adapter.FilmsListAdapter
import kotlinx.coroutines.NonDisposableHandle.parent


class FilmsListFragment : Fragment() {

    private lateinit var filmsViewModel: FilmsViewModel
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: FilmsListAdapter

    companion object {
        fun newInstance() = FilmsListFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMainBinding.bind(view)
        binding.filmsListRecycler.layoutManager = LinearLayoutManager(context)
        filmsViewModel = ViewModelProvider(this).get(FilmsViewModel::class.java)

        filmsViewModel.getTopFilms(1).observe(viewLifecycleOwner) {result->
            when(result) {
                is Result.Success -> {
                    binding.filmsListRecycler.adapter = FilmsListAdapter(result.data.films)
                }
            }

        }

    }


}