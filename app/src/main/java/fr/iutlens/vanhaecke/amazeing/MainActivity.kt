package fr.iutlens.vanhaecke.amazeing

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun play(view: View) {
        val playPage = Intent(this, LabyrintheActivity::class.java);
        startActivity(playPage);
    }

    fun levels(view: View) {
        val levelsPage = Intent(this, LabyrintheActivity::class.java);
        startActivity(levelsPage);
    }

    fun exit(view: View) {
        System.exit(0);
    }
}