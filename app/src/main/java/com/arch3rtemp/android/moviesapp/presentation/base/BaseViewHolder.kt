package com.arch3rtemp.android.moviesapp.presentation.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseViewHolder<M, VB : ViewBinding> constructor(viewbinding: VB) :
RecyclerView.ViewHolder(viewbinding.root) {
    private var item: M? = null

    fun doBindings(data: M?) {
        this.item = data
    }

    abstract fun bind()

    fun getRowItem(): M? {
        return item
    }
}