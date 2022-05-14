package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.retrofitdemo.databinding.ActivityMainBinding
import com.example.retrofitdemo.model.User
import com.example.retrofitdemo.retrofit.ApiInterface
import com.example.retrofitdemo.retrofit.ApiUtility
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    private lateinit var apiInterface: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerViewAdapter = RecyclerViewAdapter()
        binding.recyclerView.adapter= recyclerViewAdapter
        binding.recyclerView.layoutManager= LinearLayoutManager(this)

        apiInterface = ApiUtility.getApi()

        lifecycleScope.launch(IO) {

            try {

                var response = apiInterface.getAllUser()

                recyclerViewAdapter.setData(this@MainActivity, response.body()!!)
                Log.d("mytag", response.body().toString())

                var result = MutableLiveData<List<User>>()
                if(response.isSuccessful){
                    result.postValue(response.body())
                    Log.d("mytag",response.isSuccessful.toString())
                }
                result.observe(this@MainActivity) {

                    recyclerViewAdapter.setData(this@MainActivity,it)
                    Log.d("mytag", "tag")
                    Log.d("mytag", "tag "+ it.toString())
                }

                result.observe(this@MainActivity) {
                    Log.d("mytag", "tag $it")
                }


            } catch(e:Exception){


            Log.d("exception", e.message.toString ())

        }
        }
    }
}