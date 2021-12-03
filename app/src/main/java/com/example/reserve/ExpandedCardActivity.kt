package com.example.reserve

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ExpandedCardActivity : AppCompatActivity() {

    private lateinit var backButton: FloatingActionButton
    private lateinit var reserveButton: Button
    private lateinit var imageView: ImageView
    private lateinit var favoriteButtonActivated: FloatingActionButton
    private lateinit var favoriteButtonInactivated: FloatingActionButton

    private lateinit var roomNameTV: TextView
    private lateinit var buildingName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expanded_card)

        roomNameTV = findViewById(R.id.roomName)
        buildingName = findViewById(R.id.buildingName)
        backButton = findViewById(R.id.backButton)
        reserveButton = findViewById(R.id.reserveButton)
        imageView = findViewById(R.id.image)
        favoriteButtonActivated = findViewById(R.id.favoriteButtonActivated)
        favoriteButtonInactivated = findViewById(R.id.favoriteButtonInactivated)

        val building = intent.extras?.getString("building")
        val roomName = intent.extras?.getString("room_name")
        val features = intent.extras?.getString("features")
        val capacity = intent.extras?.getString("capacity")
        val image = intent.extras?.getString("image")


        if (building != null) buildingName.text = building
        if (roomName != null) this.roomNameTV.text = roomName
        if (image != null) Glide.with(this).load(image).into(imageView)


        val roomObj = Room("ENG quad?", this.roomNameTV.text.toString(), buildingName.text.toString(), false, 100, "", "", "Jan 1, 2021", "Saturday")

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).apply {
            }
            startActivity(intent)
        }

        reserveButton.setOnClickListener {
            val intent = Intent(this, RequestReservationActivity::class.java).apply {
                putExtra("building", building)
                putExtra("room", roomName)
            }
            startActivity(intent)
        }

        // favorite button implementation
        var isFavorite = false
        for (favorite in Repository.favorites) {
            if (favorite.room_name == this.roomNameTV.text.toString()) {
                isFavorite = true
            }
        }

        if (isFavorite) {
            favoriteButtonInactivated.hide()
            favoriteButtonActivated.show()
        } else {
            favoriteButtonActivated.hide()
            favoriteButtonInactivated.show()
        }

        favoriteButtonActivated.setOnClickListener {
            isFavorite = false
            favoriteButtonActivated.hide()
            favoriteButtonInactivated.show()
            Repository.favorites.remove(roomObj)
            Log.d("FAVORITES", "removed")
            Log.d("FAVORITES", Repository.favorites.toString())
        }

        favoriteButtonInactivated.setOnClickListener {
            isFavorite = true
            favoriteButtonInactivated.hide()
            favoriteButtonActivated.show()
            Repository.favorites.add(roomObj)
            Log.d("FAVORITES", "added")
            Log.d("FAVORITES", Repository.favorites.toString())
        }

    }

}