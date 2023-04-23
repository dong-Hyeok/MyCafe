package com.hdh.mycafe

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.hdh.mycafe.databinding.ActivityMainBinding
import com.hdh.mycafe.ui.fragment.AddCafeFragment
import com.hdh.mycafe.ui.fragment.HomeFragment
import com.hdh.mycafe.ui.fragment.MyFavoriteFragment
import com.hdh.mycafe.ui.fragment.MyInfoFragment

class MainActivity : AppCompatActivity() {

    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.toolbar.apply {
            title = "HOME"
            background = ColorDrawable(resources.getColor(R.color.purple_200, theme))
        }

        binding.mainViewPager.adapter = PagerAdapter(this)
        binding.mainViewPager.registerOnPageChangeCallback(PageChangeCallback())

        binding.mainBottomNavigationView.setOnItemSelectedListener {
            navigationSelected(it)
        }

    }

    private  inner class PagerAdapter(activity: MainActivity) : FragmentStateAdapter(activity){
        override fun getItemCount(): Int {
            return 4
        }

        override fun createFragment(position: Int): Fragment {
            return when(position){
                0 -> HomeFragment()
                1 -> AddCafeFragment()
                2 -> MyFavoriteFragment()
                3 -> MyInfoFragment()
                else -> error("no Such position")
            }
        }

    }

    private fun navigationSelected(item: MenuItem) : Boolean{
        val checked = item.setChecked(true)
        when (checked.itemId){
            R.id.home_fragment_Item -> {
                binding.mainViewPager.currentItem = 0
                binding.toolbar.title = "HOME"
                return true
            }

            R.id.addCafe_fragment_Item -> {
                binding.mainViewPager.currentItem = 1
                binding.toolbar.title = "ADD"
                return true
            }

            R.id.myFavorite_fragment_Item -> {
                binding.mainViewPager.currentItem = 2
                return true
            }

            R.id.myInfo_fragment_Item -> {
                binding.mainViewPager.currentItem = 3
                return true
            }

        }

        return false
    }

    private  inner class PageChangeCallback : ViewPager2.OnPageChangeCallback(){
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            binding.mainBottomNavigationView.selectedItemId =
                when(position){
                    0 -> R.id.home_fragment_Item
                    1 -> R.id.addCafe_fragment_Item
                    2 -> R.id.myFavorite_fragment_Item
                    3 -> R.id.myInfo_fragment_Item
                    else -> error("no such position")
                }
        }
    }
}