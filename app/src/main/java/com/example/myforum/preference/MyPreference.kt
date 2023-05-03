package com.example.myforum.preference

import android.content.Context
import com.example.myforum.data.RegisterUser

const val SHARE_PREFERENCE_FILE_NAME = "user_info"

object MyPreference {

    private lateinit var context: Context

    fun initMySharePreference(context: Context) {
        this.context = context
    }

    //保存数据在sharePreference中（例如每次返回界面不用重新登录）
    fun saveUserInfo(user: RegisterUser) {
        this.context.getSharedPreferences(SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE).edit().run {
            this.putString("name", user.name)
            this.putString("password", user.password)
            this.putInt("age", user.age)
            apply()
        }
    }

    fun deleteUserInfo(user: RegisterUser) {
        this.context.getSharedPreferences(SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE).edit().run {
            this.remove("name")
            this.remove("password")
            this.remove("age")
            apply()
        }
    }

    fun getUserInfo(user: RegisterUser): RegisterUser? {
        return this.context.getSharedPreferences(SHARE_PREFERENCE_FILE_NAME, Context.MODE_PRIVATE).takeIf {
            it.getString("name", null) != null && it.getString("password", null) != null
        }?.run {
            RegisterUser(
                name = this.getString("name", "")!!,  //此时一定不为空，忽略空值检查
                password = this.getString("password", "")!!,
                age = this.getInt("age", 0)!!
            )
        } ?: null
    }
}