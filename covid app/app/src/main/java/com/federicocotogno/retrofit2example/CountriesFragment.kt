package com.federicocotogno.retrofit2example

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.federicocotogno.retrofit2example.api.api
import kotlinx.android.synthetic.main.fragment_countries.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class CountriesFragment : Fragment(R.layout.fragment_countries) {

    private val adapter = CountriesAdapter {
        with(activity ?: return@CountriesAdapter) {
            val transaction = this.supportFragmentManager.beginTransaction()
            transaction.addToBackStack(null)
            val fragment = CoutryDetailFragment.inst(it)
            transaction.replace(R.id.fragment_container, fragment)
            transaction.commit()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(requireContext())
        getCurrentData()
    }

    private fun getCurrentData() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getCountries().awaitResponse()
                if (response.isSuccessful) {
                    val data = response.body()!!
                    Log.d(TAG, data.toString())
                    withContext(Dispatchers.Main) {
                        progressBar.visibility = View.GONE
                        adapter.list = data
                        adapter.notifyDataSetChanged()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main){
                    Toast.makeText(
                        context ?: return@withContext,
                        "Seems like something went wrong...",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}