package com.douglas.gitlist.view

import android.app.ProgressDialog
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.douglas.gitlist.R
import com.douglas.gitlist.adapter.RepositoriesAdapter
import com.douglas.gitlist.presenter.MainActivityPresenter

class MainActivity : AppCompatActivity(), MainActivityPresenter.View {

    private var presenter: MainActivityPresenter? = null
    private var rvRepositories: RecyclerView?=null
    private var progressBar: ProgressBar?=null
    private var tvErrorNetwork: AppCompatTextView?=null
    private var btnErrorNetwork: AppCompatButton?=null
    private var ivErro: AppCompatImageView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainActivityPresenter(this, this)
        initViews()
        presenter!!.getRepositories()
        btnErrorNetwork!!.setOnClickListener {
            presenter!!.getRepositories()
        }
    }

    private fun initViews(){
        rvRepositories = findViewById(R.id.rvRepositories)
        progressBar = findViewById(R.id.progressBar)
        tvErrorNetwork = findViewById(R.id.tvErrorNetwork)
        btnErrorNetwork = findViewById(R.id.btnErrorNetwork)
        ivErro = findViewById(R.id.ivErro)
    }

    override fun showProgressBar() {
        progressBar!!.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progressBar!!.visibility = View.INVISIBLE
    }

    override fun showErrorNetwork() {
        tvErrorNetwork!!.visibility = View.VISIBLE
        btnErrorNetwork!!.visibility = View.VISIBLE
        ivErro!!.visibility = View.VISIBLE
    }

    override fun hideErrorNetwork() {
        tvErrorNetwork!!.visibility = View.GONE
        btnErrorNetwork!!.visibility = View.GONE
        ivErro!!.visibility = View.GONE
    }

    override fun setAdapter(adapter: RepositoriesAdapter) {
        rvRepositories?.adapter = adapter
        rvRepositories?.layoutManager = LinearLayoutManager(this);
    }


}