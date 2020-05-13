package com.adityasundawa.lawancorona

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
FragmentStateAdapter(fragmentActivity) {
    private val JUMLAH_MENU = 4
    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> { return HomeActivity() }
            1 -> { return Covid_Fragment() }
            2 -> { return News_Fragment() }
            3 -> { return ProfileFragment()}
            else -> {
                return Covid_Fragment()
            }
        }
    }
    override fun getItemCount(): Int {
        return JUMLAH_MENU
    }
}
