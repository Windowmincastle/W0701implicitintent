package kr.ac.kumoh.s20191325.w1102volleywithviewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kr.ac.kumoh.prof.w1102prevolleywithviewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var model: SongViewModel
    private var songs: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        model = ViewModelProvider(this)[SongViewModel::class.java]
        model.requestSong()

        model.songs.observe(this,
            Observer<ArrayList<String>> {
                //Toast.makeText(this, model.songs.value.toString(), Toast.LENGTH_LONG).show()
                songs = model.songs.value?.toTypedArray()
                binding.listSongs.adapter = ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1,
                    songs as Array<out String>)
            })
    }
}