package com.xhj.enjoy.adapter

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.xhj.enjoy.App
import com.xhj.enjoy.AppContext
import com.xhj.enjoy.R
import com.xhj.enjoy.model.bean.Gank

class GankAdapter(layoutResId: Int = R.layout.item_gank) : BaseQuickAdapter<Gank, BaseViewHolder>(layoutResId) {
    override fun convert(helper: BaseViewHolder, item: Gank?) {
        Glide.with(mContext).load(item?.url).into(helper.getView(R.id.iv_gank))
        helper.setText(R.id.tv_gank , item?.desc)
            .addOnClickListener(R.id.iv_gank)
    }
}