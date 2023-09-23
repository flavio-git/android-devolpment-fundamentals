package com.flaviovicentini.modulo02aula03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edtFirstLL = findViewById<EditText>(R.id.edt_first_ll)
        val btnFirstLL = findViewById<Button>(R.id.btn_first_ll)
        val pbFirstLL = findViewById<ProgressBar>(R.id.pb_first_ll)

        val sc = findViewById<ScrollView>(R.id.scv)
        val imSecondLL = findViewById<ImageView>(R.id.im_second_ll)
        val txvTitleSecondLL = findViewById<TextView>(R.id.txv_title_second_ll)
        val txvContentSecondLL = findViewById<TextView>(R.id.txv_content_second_ll)

        btnFirstLL.setOnClickListener {
            lifecycleScope.launch {
                val textUser = edtFirstLL.text.toString()

                edtFirstLL.isEnabled = false
                pbFirstLL.isVisible = true
                btnFirstLL.isVisible = false
                txvTitleSecondLL.text = ""
                txvContentSecondLL.text = ""
                imSecondLL.isVisible = false

                if (textUser.lowercase() == "cyber") {
                    val game = generateGame()
                    sc.isVisible = true
                    txvTitleSecondLL.text = game.title
                    txvContentSecondLL.text = game.summary
                    game.image?.let {
                        imSecondLL.setImageDrawable(it)
                    }
                    imSecondLL.isVisible = true
                } else {
                    delay(3000)
                    Toast.makeText(
                        this@MainActivity,
                        "Game not found: $textUser",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                edtFirstLL.isEnabled = true
                btnFirstLL.isVisible = true
                pbFirstLL.isVisible = false
            }
        }
    }

    // Async function
    private suspend fun generateGame(): GameModel {
        delay(3000)
        return GameModel(
            "Cyberpunk 2077",
            "Cyberpunk 2077 is a 2020 action role-playing video game developed by CD Projekt Red and published by CD Projekt, based on video game designer Mike Pondsmith's game series. Set in a dystopian Cyberpunk universe, the player assumes the role of \"V\" (played by Gavin Drea/Cherami Leigh), a mercenary in the fictional Californian city known as \"Night City\", where they deal with the fallout from a heist gone wrong that results in an experimental cybernetic \"bio-chip\" containing an engram of the legendary rock star and terrorist Johnny Silverhand (played by Keanu Reeves) threatening to slowly overwrite V's mind; as the story progresses V and Johnny must work together to find a way to be separated and save V's life. The game's development began following the release of The Witcher 3: Wild Hunt – Blood and Wine (2016). The game was developed by a team of around 500 people using the REDengine 4 game engine. CD Projekt launched a new division in Wrocław, Poland, and partnered with Digital Scapes, Nvidia, QLOC, and Jali Research to aid the production. Cyberpunk creator Mike Pondsmith was a consultant, and actor Keanu Reeves had a starring role. The original score was led by Marcin Przybyłowicz, and featured the contributions of several licensed artists. After years of anticipation, CD Projekt released Cyberpunk 2077 for PlayStation 4, Stadia, Windows, and Xbox One on 10 December 2020, followed by PlayStation 5 and Xbox Series X/S on 15 February 2022.",
            ContextCompat.getDrawable(this, R.mipmap.cyber)
        )
    }
}