package com.example.myforum.view

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.myforum.R

class CustomToolBar(context: Context, attr: AttributeSet): LinearLayout(context, attr) {

    private var title : String = ""
    private var isShowBackButton: Boolean = true

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_toolbar, this)
        //获取在attrs文件中定义的属性集合
        context.obtainStyledAttributes(attr, R.styleable.CustomToolBar).apply {
            title = this.getString(R.styleable.CustomToolBar_title) ?: ""
            isShowBackButton = this.getBoolean(R.styleable.CustomToolBar_isShowBackButton, false)
        }.recycle()   //防止内存泄漏，获取完属性之后回收对象
        //根据图片id获取到drawable
        findViewById<ImageView>(R.id.ivBack_toolBar).apply {
            visibility = if (isShowBackButton) View.VISIBLE else View.GONE
        }.setOnClickListener{
            (context as Activity).finish()
        }
        findViewById<TextView>(R.id.tvTitle_toolBar).apply {
            text = title
        }
    }
}