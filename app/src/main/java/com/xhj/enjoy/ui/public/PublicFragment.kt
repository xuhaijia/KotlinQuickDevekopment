package com.xhj.enjoy.ui.public

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.assent.Permission
import com.afollestad.assent.askForPermissions
import com.hitomi.tilibrary.style.index.NumberIndexIndicator
import com.hitomi.tilibrary.style.progress.ProgressBarIndicator
import com.hitomi.tilibrary.transfer.TransferConfig
import com.hitomi.tilibrary.transfer.Transferee
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
import com.scwang.smartrefresh.layout.listener.OnRefreshListener
import com.vansz.glideimageloader.GlideImageLoader
import com.xhj.enjoy.AppContext
import com.xhj.enjoy.R
import com.xhj.enjoy.adapter.GankAdapter
import com.xhj.enjoy.base.BaseVMFragment
import com.xhj.enjoy.model.bean.Gank
import com.xhj.enjoy.util.FileUtils
import kotlinx.android.synthetic.main.fragment_public.*

class PublicFragment : BaseVMFragment<PublicViewModel>(), OnRefreshListener, OnLoadMoreListener {

    override fun providerVMClass(): Class<PublicViewModel>? {
        return PublicViewModel::class.java
    }

    private val gankAdapter by lazy { GankAdapter() }
    private var currentPage = 1

    override fun getLayoutId(): Int {
        return R.layout.fragment_public
    }

    override fun initView() {
        mRefreshLayout.setOnRefreshListener(this)
        mRefreshLayout.setOnLoadMoreListener(this)
        rv_gank.run {
            layoutManager = LinearLayoutManager(activity)
        }
        initAdapter()
    }

    private fun initAdapter() {


        gankAdapter.run {
            setOnItemClickListener { _, _, position ->
//                startKtxActivity<BrowserNormalActivity>(value = BrowserNormalActivity.URL to homeArticleAdapter.data[position].link)
            }
            setOnItemChildClickListener { adapter, view, position ->
                when (view.id) {
                    R.id.iv_gank -> {
                        val gank = adapter.data as List<Gank>
                        Transferee.getDefault(activity).apply(TransferConfig.build().setImageLoader(GlideImageLoader.with(AppContext))
                            .setOnLongClickListener{view , url , pos ->
                                askForPermissions(Permission.WRITE_EXTERNAL_STORAGE , Permission.CAMERA) {
                                    FileUtils.saveImg(activity as Context , url  )
                                }
                            }
                            .bindImageView(view as ImageView?,gank[position].url)

                        ).show();
                    }
                }
            }
            setEnableLoadMore(false)
        }
        rv_gank.adapter = gankAdapter
    }



    override fun initData() {
        mRefreshLayout.autoRefresh()
    }

    override fun startObserve() {
        super.startObserve()
        mViewModel.apply {
            mGank.observe(this@PublicFragment, Observer { it ->
                it?.let { setGank(it) }
            })
        }
    }

    private fun setGank(it: List<Gank>) {
        gankAdapter.run {
            if (currentPage == 1)
                replaceData(it)
            else addData(it)
        }
        currentPage++
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        currentPage = 1
        mViewModel.getGankList(currentPage)
        refreshLayout.finishRefresh()
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        mViewModel.getGankList(currentPage)
        refreshLayout.finishLoadMore()
    }


}


