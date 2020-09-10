package com.douglas.gitlist.presenter

import android.content.Context
import com.douglas.gitlist.adapter.RepositoriesAdapter
import com.douglas.gitlist.helper.Constants
import com.douglas.gitlist.model.RepositoryEntity
import com.douglas.gitlist.service.Endpoint
import com.douglas.gitlist.service.RetrofitUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityPresenter(view: View,context: Context) {
    private var view: View? = null
    private var repositoryEntity: RepositoryEntity? = null
    private var context:Context?=null

    fun getRepositories() {
        try {
            view?.showProgressBar()
            view?.hideErrorNetwork()
            val retrofitClient = RetrofitUtils
                .getRetrofitInstance(Constants.GIT_BASE_URL)

            val endpoint = retrofitClient.create(Endpoint::class.java)
            val callback = endpoint.getRepositories()

            callback.enqueue(object : Callback<List<RepositoryEntity>> {
                override fun onFailure(call: Call<List<RepositoryEntity>>, t: Throwable) {
                    view?.hideProgressBar();
                    view?.showErrorNetwork()
                }

                override fun onResponse(
                    call: Call<List<RepositoryEntity>>,
                    response: Response<List<RepositoryEntity>>
                ) {
                    if (response.code()==200){
                        val listRepositoryEntity = response.body()
                        val adapter = RepositoriesAdapter(listRepositoryEntity!!,context!!)
                        view?.setAdapter(adapter)
                    }else{
                        view?.showErrorNetwork()
                    }
                    view?.hideProgressBar();
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    interface View {
        fun showProgressBar()
        fun hideProgressBar()
        fun showErrorNetwork()
        fun hideErrorNetwork()
        fun setAdapter(adapter: RepositoriesAdapter)
    }

    init {
        repositoryEntity = RepositoryEntity()
        this.view = view
        this.context = context;
    }
}