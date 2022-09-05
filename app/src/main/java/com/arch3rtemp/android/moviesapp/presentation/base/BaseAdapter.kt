package com.arch3rtemp.android.moviesapp.presentation.base

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<M : Any, VB: ViewBinding, VH: BaseViewHolder<M, VB>>(callback: DiffUtil.ItemCallback<M>) :
ListAdapter<M, VH>(callback) {

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.doBindings(getItem(position))
        holder.bind()
    }
}