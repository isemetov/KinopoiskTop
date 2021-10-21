package com.example.kinopoisktop.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.kinopoisktop.R
import com.example.kinopoisktop.data.state.Result
import com.example.kinopoisktop.databinding.FragmentFilmDetailsBinding
import com.example.kinopoisktop.ui.list.adapter.FilmsListAdapter

class FilmDetailsFragment : Fragment() {

    private lateinit var filmsViewModel: FilmDetailsViewModel
    private lateinit var binding: FragmentFilmDetailsBinding

    companion object {
        fun newInstance(id: Int) : FilmDetailsFragment {
            val args = Bundle()
            args.putInt("id", id)
            val fragment = FilmDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var actionBar = activity?.actionBar
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_film_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFilmDetailsBinding.bind(view)
        filmsViewModel = ViewModelProvider(this).get(FilmDetailsViewModel::class.java)

        filmsViewModel.getFilm(this.requireArguments().getInt("id")).observe(viewLifecycleOwner) { result->
            when(result) {
                    is Result.Loading -> {
                        binding.crollView.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                }
                    is Result.Success -> {
                        binding.crollView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        binding.posterPreview.load(result.data.posterUrlPreview)
                        binding.nameRu.text = result.data.nameRu
                        binding.year.text = result.data.year
                        binding.rating.text = result.data.ratingKinopoisk
                        binding.slogan.text = result.data.slogan
                        binding.description.text = result.data.description
                }
                    is Result.Error -> {
                        binding.crollView.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(activity, "Ошибка при загрузке данных", Toast.LENGTH_LONG)
                }
            }
        }
    }
}