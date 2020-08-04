package com.xhj.enjoy.ui.home

import android.text.TextUtils
import android.util.Log
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.tencent.mmkv.MMKV
import com.xhj.enjoy.R
import com.xhj.enjoy.adapter.HomeArticleAdapter
import com.xhj.enjoy.base.BaseVMFragment
import com.xhj.enjoy.ext.dp2px
import com.xhj.enjoy.ext.startKtxActivity
import com.xhj.enjoy.model.bean.ArticleList
import com.xhj.enjoy.model.bean.Banner
import com.xhj.enjoy.ui.login.LoginViewModel
import com.xhj.enjoy.util.GlideImageLoader
import com.xhj.enjoy.util.SpaceItemDecoration
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.math.log

class HomeFragment : BaseVMFragment<HomeViewModel>() {

    override fun providerVMClass(): Class<HomeViewModel>? {
        return HomeViewModel::class.java
    }

    private val homeArticleAdapter by lazy { HomeArticleAdapter() }
    private var currentPage = 0
    private val banner by lazy { com.youth.banner.Banner(activity) }


    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {

        homeRecycleView.run {
            layoutManager = LinearLayoutManager(activity)
            addItemDecoration(SpaceItemDecoration(homeRecycleView.dp2px(10)))
        }

        initBanner()
        initAdapter()

        homeRefreshLayout.run {
            setOnRefreshListener { refresh() }
            isRefreshing = true
        }
        refresh()


    }

    override fun initData() {
        mViewModel.getBanners()
    }

    override fun startObserve() {
        super.startObserve()
        mViewModel.apply {
            mBanners.observe(this@HomeFragment, Observer { it ->
                it?.let { setBanner(it) }
            })
            mArticleList.observe(this@HomeFragment, Observer { it ->
                it?.let { setArticles(it) }
            })
        }
    }

    private fun initBanner() {

        banner.run {
            layoutParams =
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, banner.dp2px(200))
            setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
            setImageLoader(GlideImageLoader())
            setOnBannerListener { position ->
                run {

                }
            }
        }
    }

    private fun initAdapter() {
        homeArticleAdapter.run {
            setOnItemClickListener { _, _, position ->
//                startKtxActivity<BrowserNormalActivity>(value = BrowserNormalActivity.URL to homeArticleAdapter.data[position].link)
            }
            onItemChildClickListener = this@HomeFragment.onItemChildClickListener
            addHeaderView(banner)
            setOnLoadMoreListener({ loadMore() }, homeRecycleView)
        }
        homeRecycleView.adapter = homeArticleAdapter
    }

    private val onItemChildClickListener =
        BaseQuickAdapter.OnItemChildClickListener { _, view, position ->
            when (view.id) {

            }
        }

    private fun loadMore() {
        mViewModel.getArticleList(currentPage)
    }

    private fun setArticles(articleList: ArticleList) {
        homeArticleAdapter.run {
            if (homeRefreshLayout.isRefreshing) replaceData(articleList.datas)
            else addData(articleList.datas)
            setEnableLoadMore(true)
            loadMoreComplete()
        }
        homeRefreshLayout.isRefreshing = false
        currentPage++
    }

    private val bannerImages = mutableListOf<String>()
    private val bannerTitles = mutableListOf<String>()
    private val bannerUrls = mutableListOf<String>()

    private fun setBanner(bannerList: List<Banner>) {
        for (banner in bannerList) {
            bannerImages.add(banner.imagePath)
            bannerTitles.add(banner.title)
            bannerUrls.add(banner.url)
        }
        banner.setImages(bannerImages)
            .setBannerTitles(bannerTitles)
            .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
            .setDelayTime(3000)
        banner.start()
    }

    fun refresh() {

        homeArticleAdapter.setEnableLoadMore(false)
        homeRefreshLayout.isRefreshing = true
        currentPage = 0
        mViewModel.getArticleList(currentPage)
    }
}