package com.adityasundawa.lawancorona

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_news.*

class News_Fragment : Fragment() {
    lateinit var listTeman : ArrayList<News>

    private fun simulasiDataNews() {
        listTeman = ArrayList()
        listTeman.add(News("Nekat, Dua Pasien Virus Corona Kabur dari Rumah Sakit ","Dua perempuan asal Rusia nekat kabur dari rumah sakit meski terindikasi tertular virus Corona.", "https://statik.tempo.co/data/2020/02/11/id_914011/914011_720.jpg")
        )
        listTeman.add(News("BREAKING NEWS - Update Perkembangan Penanganan Virus Corona dari BNPB","BREAKING NEWS - Update Perkembangan Penanganan Virus Corona dari BNPB, Jumat 20 Maret 2020","https://i.ytimg.com/vi/-iaj0K-wL7U/maxresdefault.jpg"))
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