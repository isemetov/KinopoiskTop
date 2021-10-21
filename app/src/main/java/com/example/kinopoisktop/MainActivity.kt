package com.example.kinopoisktop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kinopoisktop.data.database.AppDatabase
import com.example.kinopoisktop.ui.list.FilmsListFragment

class MainActivity : AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, FilmsListFragment.newInstance())
            .commitNow()

        AppDatabase.invoke(applicationContext)

    }


}