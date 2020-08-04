package com.xhj.enjoy.model.bean

import com.xhj.enjoy.model.bean.SystemChild
import java.io.Serializable


data class SystemParent(val children: List<SystemChild>,
                        val courseId: Int,
                        val id: Int,
                        val name: String,
                        val order: Int,
                        val parentChapterId: Int,
                        val visible: Int,
                        val userControlSetTop: Boolean) : Serializable