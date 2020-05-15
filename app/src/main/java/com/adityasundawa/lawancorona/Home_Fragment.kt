package com.adityasundawa.lawancorona

import HomeAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.adityasundawa.lawancorona.data.CoronaService
import com.adityasundawa.lawancorona.data.apiRequest
import com.adityasundawa.lawancorona.data.httpClient
import com.adityasundawa.lawancorona.util.dismissLoading
import com.adityasundawa.lawancorona.util.showLoading
import com.adityasundawa.lawancorona.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home_Fragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        callKawalCorona()
    }
    private fun callKawalCorona() {
        showLoading(context!!, swipeRefreshLayout)
        val httpClient = httpClient()
        val apiRequest = apiRequest<CoronaService>(httpClient)
        val call = apiRequest.getData()
        call.enqueue(object : Callback<List<DataIndonesiaItem>>{
            override fun onFailure(call: Call<List<DataIndonesiaItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }
            override fun onResponse(call: Call<List<DataIndonesiaItem>>, response:
            Response<List<DataIndonesiaItem>>) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilCorona(response.body()!!)
                            else -> {
                                tampilToast(context!!, "Berhasil")
                            }
                        }
                    else -> {
                        tampilToast(context!!, "Gagal")
                    }
                }
            }
        })
    }
    private fun tampilCorona(positifCorona: List<DataIndonesiaItem>) {
        listDataCorona.layoutManager = LinearLayoutManager(context)
        listDataCorona.adapter = HomeAdapter(context!!, positifCorona) {
            val positif_Corona = it
            tampilToast(context!!, positif_Corona.name)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}
