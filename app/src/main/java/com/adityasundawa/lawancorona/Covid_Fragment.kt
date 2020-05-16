package com.adityasundawa.lawancorona

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.adityasundawa.lawancorona.data.CoronaProvinsiService
import com.adityasundawa.lawancorona.data.apiRequest
import com.adityasundawa.lawancorona.data.httpClient
import com.adityasundawa.lawancorona.util.dismissLoading
import com.adityasundawa.lawancorona.util.showLoading
import com.adityasundawa.lawancorona.util.tampilToast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_covid.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Covid_Fragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_covid, container, false)
    }
    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        callApiGetGithubUser()
    }
    private fun callApiGetGithubUser() {
        showLoading(context!!, swipeRefreshLayout)
        val httpClient = httpClient()
        val apiRequest = apiRequest<CoronaProvinsiService>(httpClient)
        val call = apiRequest.getData()
        call.enqueue(object : Callback<List<DataProvinsiItem>> {
            override fun onFailure(call: Call<List<DataProvinsiItem>>, t: Throwable) {
                dismissLoading(swipeRefreshLayout)
            }
            override fun onResponse(call: Call<List<DataProvinsiItem>>, response:
            Response<List<DataProvinsiItem>>) {
                dismissLoading(swipeRefreshLayout)
                when {
                    response.isSuccessful ->
                        when {
                            response.body()?.size != 0 ->
                                tampilGithubUser(response.body()!!)
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
    private fun tampilGithubUser(coronaProvinsi: List<DataProvinsiItem>) {
        listDataCoronaProvince.layoutManager = LinearLayoutManager(context)
        listDataCoronaProvince.adapter = ProvinsiAdapter(context!!, coronaProvinsi) {
            val githubUser = it
            tampilToast(context!!, githubUser.attributes.provinsi)
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}
