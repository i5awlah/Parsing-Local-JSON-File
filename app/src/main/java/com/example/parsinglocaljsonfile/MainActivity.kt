package com.example.parsinglocaljsonfile

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.parsinglocaljsonfile.databinding.ActivityMainBinding
import org.json.JSONArray
import java.io.IOException

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: RVAdapter
    lateinit var binding: ActivityMainBinding
    private var images = arrayListOf<Image>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRV()
        fetchData()

    }

    private fun setupRV() {
        recyclerView = binding.rvPhotos
        adapter = RVAdapter(images)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun fetchData() {
        val json = getJsonDataFromAsset(this)
        val jsonArray = JSONArray(json)
        images = arrayListOf()

        for (i in 0 until jsonArray.length()) {
            val title = jsonArray.getJSONObject(i).getString("title")
            val url = jsonArray.getJSONObject(i).getString("url")
            images.add(Image(title, url))
        }
        adapter.update(images)
    }

    private fun getJsonDataFromAsset(context: Context): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open("data.json").bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString
    }
}