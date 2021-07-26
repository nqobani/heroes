package com.example.superheros.views

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.networklayer.interactors.DataState
import com.example.networklayer.localDb.HeroEntity
import com.example.superheros.R
import com.example.superheros.viewModels.HeroesViewModel
import com.example.superheros.views.adapters.HeroesRecyclerViewAdapter

class MainActivity : AppCompatActivity() {
    private val heroesViewModel: HeroesViewModel by viewModels()
    lateinit var heroesRecyclerViewAdapter: HeroesRecyclerViewAdapter
    lateinit var heroesRecyclerView : RecyclerView
    lateinit var searchImageView : ImageView
    lateinit var searchTextView : EditText
    lateinit var mainProgressBar: ProgressBar
    lateinit var statusTextView: TextView
    lateinit var inputManager: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        heroesRecyclerView =  findViewById<RecyclerView>(R.id.heroes_recycler_view)
        searchImageView = findViewById<ImageView>(R.id.search_button_image_view)
        searchTextView = findViewById<EditText>(R.id.search_edit_text)
        mainProgressBar = findViewById<ProgressBar>(R.id.progress_bar)
        statusTextView = findViewById<TextView>(R.id.status_text_view)

        inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        heroesViewModel.setStateEvent(
            HeroesViewModel.MainIntents.searchHeroe(""),
            applicationContext
        )
        setupRecyclerView()
        searchButtonSetup()
        render()
    }

    fun setupRecyclerView(){
        heroesRecyclerViewAdapter = HeroesRecyclerViewAdapter(this, listOf())
        heroesRecyclerView.layoutManager = LinearLayoutManager(this)
        heroesRecyclerView.adapter = heroesRecyclerViewAdapter
    }

    fun searchButtonSetup(){
        searchImageView.setOnClickListener{
            inputManager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            heroesViewModel.setStateEvent(
                HeroesViewModel.MainIntents.searchHeroe(searchTextView.text.toString().trim()),
                applicationContext
            )
        }
    }

    private fun render(){
        heroesViewModel.heroes.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success -> {
                    heroesRecyclerViewAdapter.setData(dataState.data)
                    progressBarIndicator(false)
                    statusTextView.visibility = View.GONE
                }
                is DataState.Loading -> {
                    progressBarIndicator(true)
                    statusTextView.visibility = View.GONE
                }
                is DataState.Failure -> {
                    progressBarIndicator(false)
                    statusTextView.text = getString(R.string.something_went_wrong)
                    statusTextView.visibility = View.VISIBLE
                }
                is DataState.NoResult -> {
                    progressBarIndicator(false)
                    heroesRecyclerViewAdapter.setData(listOf())
                    statusTextView.text = getString(R.string.no_match_found)
                    statusTextView.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun progressBarIndicator(display: Boolean){
        if(display){
            mainProgressBar.visibility = View.VISIBLE
        } else {
            mainProgressBar.visibility = View.GONE
        }
    }
}