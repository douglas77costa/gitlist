package com.douglas.gitlist.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.douglas.gitlist.R
import com.douglas.gitlist.model.RepositoryEntity


class RepositoriesAdapter(
    private val listRepositoryEntity: List<RepositoryEntity>,
    private val context: Context
) : RecyclerView.Adapter<RepositoriesAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_repository, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repositoryEntity = listRepositoryEntity[position]
        holder.tvTitleRepo?.text = repositoryEntity.name
        holder.tvUser?.text = repositoryEntity.owner?.login
        holder?.ivAvatar?.let {
            Glide.with(context).load(repositoryEntity.owner?.avatarUrl).apply(RequestOptions.circleCropTransform()).into(
                it
            )
        };
        holder?.ivLink?.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse( repositoryEntity.htmlUrl)
            context.startActivity(openURL)
        }

    }

    override fun getItemCount() = listRepositoryEntity.size


    open class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvTitleRepo: TextView? = null
        var tvUser: TextView? = null
        var ivAvatar: ImageView? = null
        var ivLink: ImageView? = null
        init {
            tvTitleRepo = view.findViewById(R.id.tvTitleRepo)
            tvUser = view.findViewById(R.id.tvUser)
            ivAvatar = view.findViewById(R.id.ivAvatar)
            ivLink = view.findViewById(R.id.ivLink)
        }
    }

}