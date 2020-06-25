package com.adityasundawa.lawancorona

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
FragmentStateAdapter(fragmentActivity) {
    private val JUMLAH_MENU = 5
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> { return Home_Fragment() }
            1 -> { return Covid_Fragment() }
            2 -> { return News_Fragment() }
            3 -> { return KonsultasiFragment()}
            4 -> { return ProfileFragment()}
            else -> {
                return Covid_Fragment()
            }
        }
    }
    override fun getItemCount(): Int {
        return JUMLAH_MENU
    }
}
