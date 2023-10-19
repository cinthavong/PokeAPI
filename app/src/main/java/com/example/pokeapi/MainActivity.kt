package com.example.pokeapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    var pokeImageURL = ""

    private fun getNextImage(button: Button, imageView: ImageView) {
        button.setOnClickListener {
            getPokePhoto()
            }

            Glide.with(this)
                .load(pokeImageURL)
                .fitCenter()
                .into(imageView)

        }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val pokeButton = findViewById<Button>(R.id.pokeButton)
        pokeButton.setOnClickListener {
            getPokePhoto()
        }
}

    private fun getPokePhoto() {
        val client = AsyncHttpClient()


        client["https://pokeapi.co/api/v2/pokemon-species/mew", object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JsonHttpResponseHandler.JSON) {
                Log.d("Poke", "response successful$json")
                // Access a JSON array response with json.jsonArray
                Log.d("DEBUG ARRAY", json.jsonArray.toString())
                // Access a JSON object response with 'json.jsonObject'
                Log.d("DEBUG OBJECT", json.jsonObject.toString())

                val pokeImageURL = json.jsonObject.getString("message")
                Log.d("pokeImageURL", pokeImageURL)
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    throwable: Throwable?
                ) {
                    Log.d("Poke Error", errorResponse)
                }
            }]

}}