package com.example.suitmediaaplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suitmediaaplication.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val firstFragment = FirstFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment !is FirstFragment) {
            fragmentTransaction.replace(
                R.id.fragment_container,
                firstFragment,
                FirstFragment::class.java.simpleName
            ).commit()
        }
    }


}