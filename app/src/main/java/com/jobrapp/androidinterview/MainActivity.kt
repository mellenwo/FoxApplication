package com.jobrapp.androidinterview

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.jobrapp.androidinterview.databinding.ActivityMainBinding
import com.jobrapp.server.User
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
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

        val linearLayout = LinearLayoutManager(this)

        binding.userRv.addOnScrollListener(InfiniteScrollListener({ binding.viewModel!!.loadUsers() }, linearLayout))

        binding.swipyrefreshlayout.setOnRefreshListener {
            binding.viewModel!!.loadUsers()
            binding.swipyrefreshlayout.isRefreshing = false
        }

        binding.viewModel!!.loadUsers()

        binding.userRv.layoutManager = linearLayout
        binding.userRv.adapter = userRecyclerViewAdapter
        viewModel.users.observe(this,
                Observer<ArrayList<User>> { it?.let{ userRecyclerViewAdapter.replaceData(it)} })
    }

    override fun onItemClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.menu_refresh -> {
            binding.viewModel!!.loadUsers()
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}
