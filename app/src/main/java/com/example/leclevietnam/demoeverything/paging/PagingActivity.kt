package com.example.leclevietnam.demoeverything.paging

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.leclevietnam.demoeverything.DemoApplication
import com.example.leclevietnam.demoeverything.R
import com.example.leclevietnam.demoeverything.databinding.ActivityPagingBinding
import com.example.leclevietnam.demoeverything.paging.model.Photo
import com.example.leclevietnam.demoeverything.paging.photoAdapter.PhotoAdapter
import com.example.leclevietnam.demoeverything.room.Product
import javax.inject.Inject

class PagingActivity : AppCompatActivity(), PagingNavigator, PagingAdapter.ItemListener {

    lateinit var binding: ActivityPagingBinding

    @Inject
    lateinit var pagingViewModel: PagingViewModel

    lateinit var pagingAdapter: PagingAdapter

    lateinit var photoAdapter: PhotoAdapter

    lateinit var pagingRecyclerAdapter: PRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pagingComponent = DaggerPagingComponent.builder()
                .appComponent((application as DemoApplication).appComponent)
                .build()

        pagingComponent.inject(this)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_paging)

        binding.viewModel = pagingViewModel

        pagingViewModel.setNavigator(this)

        //
//        pagingRecyclerAdapter = PRecyclerAdapter(ArrayList())
//        binding.rvPagingList.layoutManager = LinearLayoutManager(applicationContext)
//        binding.rvPagingList.adapter = pagingRecyclerAdapter

    }

    override fun updateList(list: List<Product>) {
        pagingRecyclerAdapter.addAll(list)
    }

    override fun deleteItem(product: Product) {
        pagingViewModel.deleteProduct(product)
    }

    override fun updateProduct(product: Product) {
        pagingViewModel.updateProduct(product)
    }

    override fun updateListPaging(listData: LiveData<PagedList<Product>>) {
        binding.rvPagingList.layoutManager = LinearLayoutManager(applicationContext)
        pagingAdapter = PagingAdapter(this)

        listData.observe(this, Observer<PagedList<Product>> {

            Log.d("Paging", "PagedList ${it.size} : $it")
            pagingAdapter.submitList(it)
        })

        binding.rvPagingList.adapter = pagingAdapter
    }

    override fun updatePhotos(photoLiveData: LiveData<PagedList<Photo>>) {
        binding.rvPagingList.layoutManager = LinearLayoutManager(applicationContext)

        photoAdapter = PhotoAdapter()

        photoLiveData.observe(this, Observer {
            Log.d("Paging", "PagedList photo ${it.size} : $it")
            photoAdapter.submitList(it)
        })

        binding.rvPagingList.adapter = photoAdapter
    }
}
