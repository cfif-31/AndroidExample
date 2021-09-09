package com.example.poetsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.poetsapp.adapter.PoetRecyckerAdapter
import com.example.poetsapp.types.Poet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val users = MutableLiveData<ArrayList<Poet>>()

    private fun GetData() = CoroutineScope(Dispatchers.IO).launch  {
        users.postValue(Poet.GetData())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewList:RecyclerView = findViewById(R.id.recyclerView)
        viewList.layoutManager = LinearLayoutManager(this)
        GetData()
        users.observe(this, {
            viewList.adapter = PoetRecyckerAdapter(it)
        });
    }
}