package com.example.kinopoisktop.ui.details

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.kinopoisktop.R
import com.example.kinopoisktop.data.database.FilmDB
import com.example.kinopoisktop.data.state.Result
import com.example.kinopoisktop.databinding.FragmentFilmDetailsBinding
import com.example.kinopoisktop.ui.list.adapter.FilmsListAdapter

class FilmDetailsFragment : Fragment() {

    private lateinit var filmsViewModel: FilmDetailsViewModel
    private lateinit var binding: FragmentFilmDetailsBinding
    lateinit var sendText: String

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
        val view = inflater.inflate(R.layout.fragment_film_details, container, false)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar1)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setTitle("Информация о фильме:")
//        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
        return view
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
                        binding.year.text = "Год: " + result.data.year
                        binding.rating.text ="Рейтинг КП: " +  result.data.ratingKinopoisk
                        binding.slogan.text ="Слоган: " +  result.data.slogan
                        binding.description.text ="Описание:\n" +  result.data.description
                        sendText = result.data.webUrl?:"здесь должна была быть ссылка на фильм"
                        filmsViewModel.setFilmToDatabase(
                            film = FilmDB(
                                    filmId = 1,
                                    nameRu = result.data.nameRu,
                                    year = result.data.year,
                                    posterUrlPreview = result.data.posterUrlPreview,
                                    slogan = result.data.slogan,
                                    description = result.data.description,
                                    ratingKinopoisk = result.data.ratingKinopoisk
                                )
                        )

                }
                    is Result.Error -> {
                        filmsViewModel.getFilmFromDatabase()
                        binding.crollView.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(activity, "Ошибка при загрузке данных", Toast.LENGTH_LONG)
                }
            }
        }

        filmsViewModel.localFilms.observe(viewLifecycleOwner) {
            binding.crollView.visibility = View.VISIBLE
            binding.progressBar.visibility = View.GONE
            binding.nameRu.text = it.nameRu
            binding.year.text ="Год: " +  it.year
            binding.rating.text ="Рейтинг КП: " +  it.ratingKinopoisk
            binding.slogan.text ="Слоган: " +  it.slogan
            binding.description.text ="Описание:\n " +  it.description
        }

        binding.btnShare.setOnClickListener{
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, sendText)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}