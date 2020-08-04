package com.xhj.enjoy.adapter

import android.text.Html
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.xhj.enjoy.R
import com.xhj.enjoy.ext.fromN
import com.xhj.enjoy.model.bean.Article


class HomeArticleAdapter(layoutResId: Int = R.layout.item_article) : BaseQuickAdapter<Article, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder, item: Article) {
        helper.setText(R.id.article_title, if (fromN()) Html.fromHtml(item.title, Html.FROM_HTML_MODE_LEGACY) else Html.fromHtml(item.title))
                .setText(R.id.article_user, if (item.author.isEmpty()) "匿名" else item.author)
                .setText(R.id.article_time, item.niceDate)

    }
}