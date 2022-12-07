package kr.ac.kumoh.s20191325.w1102volleywithviewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley

class SongViewModel(application: Application) : AndroidViewModel(application) {
    companion object {
        const val QUEUE_TAG = "SongVolleyRequest"
    }

    private val list = ArrayList<String>()
    val songs = MutableLiveData<ArrayList<String>>()

    private var queue: RequestQueue

    init {
        songs.value = list
        queue = Volley.newRequestQueue(getApplication())
    }

    fun requestSong() {
        // NOTE: 서버 주소는 본인의 서버 IP 사용할 것
        val url = "https://expresssongdb-bakgj.run.goorm.io/"

        // Array를 반환할 경우에는 JsonObjectRequest 대신 JsonArrayRequest 사용
        val request = JsonArrayRequest(
            Request.Method.GET,
            url,
            null,
            {
                Toast.makeText(getApplication(), it.toString(), Toast.LENGTH_LONG).show()
            },
            {
                Toast.makeText(getApplication(), it.toString(), Toast.LENGTH_LONG).show()
            }
        )

        request.tag = QUEUE_TAG
        queue.add(request)
    }

    override fun onCleared() {
        super.onCleared()
        queue.cancelAll(QUEUE_TAG)
    }
}