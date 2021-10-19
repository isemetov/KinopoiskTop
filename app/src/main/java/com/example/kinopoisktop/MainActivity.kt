package com.example.kinopoisktop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kinopoisktop.ui.list.FilmsListFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, FilmsListFragment.newInstance())
            .commitNow()


    }


}