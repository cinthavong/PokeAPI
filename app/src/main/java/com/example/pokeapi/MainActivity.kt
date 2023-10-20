package com.example.pokeapi

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers

class MainActivity : AppCompatActivity() {

    var pokeImageURL = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val pokeButton = findViewById<Button>(R.id.pokeButton)
        val imageView = findViewById<ImageView>(R.id.pokeImage)

        getNextImage(pokeButton, imageView)

//        pokeButton.setOnClickListener {
//        getMewPhoto()
    }

    private fun getDittoPhotoURL() {
        val client = AsyncHttpClient()


        client["https://pokeapi.co/api/v2/pokemon/ditto/", object :
            JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.d("sprites", "response successful$json")
                // Access a JSON array response with json.jsonArray
                //Log.d("DEBUG ARRAY", json.jsonArray.toString())
                // Access a JSON object response with 'json.jsonObject'
                //Log.d("DEBUG OBJECT", json.jsonObject.toString())

                pokeImageURL = json.jsonObject.getString("front_default")

                //Log.d("pokeImageURL", pokeImageURL)
            }


            override fun onFailure(
                statusCode: Int,
                headers: Headers,
                errorResponse: String,
                throwable: Throwable
            ) {
                Log.d("Mew Error", errorResponse)
            }
        }]
    }

    private fun getSpritePhotoURL() {
        val client = AsyncHttpClient()

        client["https://pokeapi.co/api/v2/pokemon/ditto/", object :
            JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers,
                json: JsonHttpResponseHandler.JSON
            ) {
                //Log.d("DEBUG ARRAY", json.jsonArray.toString())
                // Access a JSON object response with 'json.jsonObject'

//                        Log.d("sprites", json.jsonArray.toString())
//
//                        val resultsJSON = json.jsonArray.getJSONObject(0)
//                        pokeImageURL = resultsJSON.getString("back_female")
//                        Log.d("sprites", pokeImageURL)

                Log.d("sprites", "response successful$json")
                if (json != null) {
                    json.jsonObject.getJSONObject("sprites").getString("back_shiny")
                }

            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers,
                errorResponse: String,
                throwable: Throwable
            ) {
                Log.d("Sprite Error", errorResponse)
            }
        }]
    }


    private fun getNextImage(button: Button, imageView: ImageView) {
        button.setOnClickListener {
            val choice = kotlin.random.Random.nextInt(2)

            if (choice == 0) {
                getDittoPhotoURL()
            } else {
                getSpritePhotoURL()
            }

            Glide.with(this)
                .load(pokeImageURL)
                .fitCenter()
                .into(imageView)

        }

    }
}







