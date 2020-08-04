package com.xhj.enjoy.model.bean

import com.xhj.enjoy.model.bean.Article


data class Navigation(val articles: List<Article>,
                      val cid: Int,
                      val name: String)