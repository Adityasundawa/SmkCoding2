package com.adityasundawa.lawancorona

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_news.*

class News_Fragment : Fragment() {
    lateinit var listTeman : ArrayList<News>

    private fun simulasiDataNews() {
        listTeman = ArrayList()
        listTeman.add(
            News("Seorang Pasien Positif Nekad Kabur", "Seorang Pasien Positif Nekad Kabur Rumah Sakit")
        )
        listTeman.add(News("Telah terjadi Kebakaran Kamar Mayat","Telah terjadi Kebakaran Kamar Mayat Dilaporkan 4 Mayat Tewas"))
    }
    private fun tampilNews() {
        rv_News.layoutManager = LinearLayoutManager(activity)
        rv_News.adapter = NewsAdapter(activity!!, listTeman)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
// Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }
    private fun initView() {
        simulasiDataNews()
        tampilNews()
    }
    override fun onDestroy() {
        super.onDestroy()
        this.clearFindViewByIdCache()
    }
}