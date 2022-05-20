package com.federicocotogno.retrofit2example

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.federicocotogno.retrofit2example.api.api
import kotlinx.android.synthetic.main.fragment_country_details.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse

class CoutryDetailFragment : Fragment(R.layout.fragment_country_details) {

    private var name: String = ""

    companion object {
        fun inst(name: String): CoutryDetailFragment {
            val fragment = CoutryDetailFragment()
            fragment.name = name
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getCurrentData()
    }

    private fun getCurrentData() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getCountryStats(name).awaitResponse()
                if (response.isSuccessful) {
                    val list = response.body()!!
                    val data = list.last()
                    Log.d(TAG, data.toString())
                    withContext(Dispatchers.Main) {
                        tv_textView.text = """
                            Country:       ${data.Country}
                            Confirmed:       ${data.Confirmed}
                            Active:       ${data.Active}
                            Deaths:       ${data.Deaths}
                            Recovered:       ${data.Recovered}
                        """.trimIndent()
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
