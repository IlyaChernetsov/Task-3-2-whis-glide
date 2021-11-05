package com.example.task_3_2_with_glide

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.annotation.Nullable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.task_3_2_with_glide.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        showPlaceholder()
        binding.imageView.setOnClickListener {
            loadImage(binding.editText.text.toString())
        }
    }


    private fun loadImage(url: String) {
        Glide.with(this)
            .load(url)
            .skipMemoryCache(true)
            .error(R.drawable.ic_baseline_broken_image_24)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: Target<Drawable>?, p3: Boolean): Boolean {
                    err()
                    return false
                }
                override fun onResourceReady(p0: Drawable?, p1: Any?, p2: Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {
                    //do something when picture already loaded
                    return false
                }
            })

            .into(binding.imageView)
    }

    private fun showPlaceholder() {
        binding.imageView.setImageResource(R.drawable.ic_baseline_image_24)
    }

    private fun err() {
        Toast.makeText(this, "Can't load this image, check url", Toast.LENGTH_SHORT).show()
    }
}

