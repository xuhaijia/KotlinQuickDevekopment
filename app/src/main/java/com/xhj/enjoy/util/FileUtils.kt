package com.xhj.enjoy.util

import android.R
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.xhj.enjoy.AppContext
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


object FileUtils {


    fun saveImg(context: Context, imgUrl: String) {
        AlertDialog.Builder(context)
            .setMessage("保存图片到本地")
            .setNegativeButton(R.string.cancel,
                DialogInterface.OnClickListener { anInterface, i -> anInterface.dismiss() })
            .setPositiveButton(R.string.ok,
                DialogInterface.OnClickListener { anInterface, i ->
                    anInterface.dismiss()
                    Glide.get(context).clearMemory()
                    Glide.with(context)
                        .asBitmap()
                        .load(imgUrl)
                        .into(Target<Bitmap>())
                }).show()

    }

    class Target<T> : CustomTarget<Bitmap>() {
        override fun onLoadCleared(placeholder: Drawable?) {
        }

        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
            saveToSystemGallery(resource, AppContext)
        }

        fun saveToSystemGallery(bmp: Bitmap, context: Context) {
            // 首先保存图片
            val fileDir =
                File(Environment.getExternalStorageDirectory(), "enjoy")
            if (!fileDir.exists()) {
                fileDir.mkdir()
            }
            val fileName = System.currentTimeMillis().toString() + ".jpg"
            val file = File(fileDir, fileName)
            try {
                val fos = FileOutputStream(file)
                bmp.compress(android.graphics.Bitmap.CompressFormat.JPEG, 100, fos)
                fos.flush()
                fos.close()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            // 其次把文件插入到系统图库
            try {
                MediaStore.Images.Media.insertImage(
                    context.contentResolver,
                    file.absolutePath, fileName, null
                )
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
            //图片保存成功，图片路径：
            Toast.makeText(
                context,
                "图片保存路径：" + file.absolutePath, Toast.LENGTH_SHORT
            ).show()
        }

    }
}