package com.adityasundawa.lawancorona
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {
    lateinit var listProfile : ArrayList<Profile>

    private fun simulasiDataProfile() {
        listProfile = ArrayList()
        listProfile.add(Profile("Aditya Sundawa","1000","12k","1023","https://blue.kumparan.com/image/upload/w_1200,h_1200,c_fill,ar_1:1,f_jpg,q_auto/l_auy4e3oe2hru5hfaphbw,g_south,w_600/rlvfhzzw8gmcwpjndugk.jpg"))
    }
    private fun tampilProfile() {
        rv_Profile.layoutManager = LinearLayoutManager(activity)
        rv_Profile.adapter = ProfileAdapter(activity!!, listProfile)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile,
            container, false)
    }
    override fun onViewCreated(
        view: View,
        @Nullable savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        simulasiDataProfile()
        tampilProfile()
    }
}
