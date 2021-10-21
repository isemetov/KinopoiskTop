package com.example.kinopoisktop.ui.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kinopoisktop.R
import com.example.kinopoisktop.data.database.FilmDB
import com.example.kinopoisktop.data.model.Countries
import com.example.kinopoisktop.data.model.Film
import com.example.kinopoisktop.data.model.Genres
import com.example.kinopoisktop.data.state.Result
import com.example.kinopoisktop.databinding.FragmentMainBinding
import com.example.kinopoisktop.ui.details.FilmDetailsFragment
import com.example.kinopoisktop.ui.list.adapter.FilmsListAdapter
import com.google.gson.annotations.SerializedName


class FilmsListFragment : Fragment(), FilmsListAdapter.FilmItemClickListener {

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

        filmsViewModel.getTopFilms(1).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> {
                    binding.filmsListRecycler.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                }
                is Result.Success -> {
                    binding.filmsListRecycler.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.filmsListRecycler.adapter = FilmsListAdapter(result.data, this)


                    filmsViewModel.setAllFilmsToDatabase(
                        films = result.data.map { film ->
                            FilmDB(
                                filmId = film.filmId,
                                nameRu = film.nameRu,
                                nameEn = film.nameEn,
                                year = film.year,
                                filmLength = film.filmLength,
//                                countries = film.countries,
//                                genres = film.genres,
                                rating = film.rating,
                                ratingVoteCount = film.ratingVoteCount,
                                posterUrl = film.posterUrl,
                                posterUrlPreview = film.posterUrlPreview,
                                slogan = film.slogan,
                                description = film.description,
                                ratingKinopoisk = film.ratingKinopoisk
                            )
                        }
                    )
                    Log.d("hello", "sdfsdf")
                }
                is Result.Error -> {
                    binding.filmsListRecycler.visibility = View.GONE
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(context, "Ошибка при загрузке данных", Toast.LENGTH_LONG).show()
                }
            }

        }

        filmsViewModel.localFilms.observe(viewLifecycleOwner) {
           Log.d("hello", "hello")
        }


    }

    override fun onClick(id: Int) {
        val detailsFragment = FilmDetailsFragment.newInstance(id)
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, detailsFragment).addToBackStack(null).commit()
    }


}