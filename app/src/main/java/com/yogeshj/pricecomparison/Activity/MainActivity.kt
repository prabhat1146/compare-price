package com.yogeshj.pricecomparison.Activity

import android.os.Bundle
import android.provider.MediaStore.Images
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.yogeshj.pricecomparison.Activity.Adapter.CategoryAdaptor
import com.yogeshj.pricecomparison.Activity.Adapter.SliderAdapter
import com.yogeshj.pricecomparison.Activity.Model.CategoryModel
import com.yogeshj.pricecomparison.Activity.Model.SliderModel
import com.yogeshj.pricecomparison.Activity.ViewModel.MianViewModel
import com.yogeshj.pricecomparison.R
import com.yogeshj.pricecomparison.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel=MianViewModel()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBanners()
        initCategories()

    }

    private fun initCategories() {
        binding.progressBarCategory.visibility=View.VISIBLE
        viewModel.category.observe(this, Observer { it: MutableList<CategoryModel>!
                binding.viewCategory.layoutManager=LinearLayoutManager(this@MainActivity,LinearLayoutManager.HORIZONTAL,false)
            binding.viewCategory.adapter=CategoryAdaptor(it)
            binding.progressBarCategory.visibility=View.GONE
        })
        viewModel.loadCategory()

    }

    private fun initBanners(){
        binding.progressBar.visibility=View.VISIBLE
        viewModel.banners.observe(this,{
            banners(it)
            binding.progressBar.visibility=View.GONE
        })
    }
    private fun banners(images: List<SliderModel>){
//        viewpager2 means viewpageslider
        binding.viewPager2.adapter=SliderAdapter(images,binding.viewPagerSlider)
        binding.viewPager2.clipToPadding=false
        binding.viewPager2.clipChildren=false
        binding.viewPager2.offscreenPageLimit=3
        binding.viewPager2.getChildAt(0).overScrollMode=RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer=CompositePageTransformer().apply { addTransformer(MarginPageTransformer(40)) }

        binding.viewPager2.setPageTransformer(compositePageTransformer)
        if(images.size>1){
            binding.dotIndicator.visibility= View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewPager2)
        }
    }
}