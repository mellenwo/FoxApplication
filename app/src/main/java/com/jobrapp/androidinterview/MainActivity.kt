package com.jobrapp.androidinterview

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.jobrapp.androidinterview.databinding.ActivityMainBinding
import com.jobrapp.server.User
import android.support.v7.widget.LinearLayoutManager
import com.jobrapp.androidinterview.adapters.UserRecyclerViewAdapter
import com.jobrapp.androidinterview.factories.MainViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity(), UserRecyclerViewAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private val userRecyclerViewAdapter = UserRecyclerViewAdapter(arrayListOf(), this)
    @Inject
    lateinit var mainViewModelFactory: MainViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this, mainViewModelFactory)
                .get(UserViewModel::class.java)
        binding.viewModel = viewModel
        binding.executePendingBindings()


        binding.userRv.layoutManager = LinearLayoutManager(this)
        binding.userRv.adapter = userRecyclerViewAdapter
        viewModel.users.observe(this,
                Observer<ArrayList<User>> { it?.let{ userRecyclerViewAdapter.replaceData(it)} })
    }

    override fun onItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
