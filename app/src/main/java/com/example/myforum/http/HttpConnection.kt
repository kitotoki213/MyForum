package com.example.myforum.http

import com.example.myforum.data.Comment
import com.example.myforum.data.Community
import com.example.myforum.data.RegisterUser
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject

const val SERVER_URL = "http://localhost:8084"

enum class ActionType(val value: String) {
    UserAction(value = "/myForum/user.action"),
    CommentAction(value = "/myForum/comment.action"),
    CommunityAction(value = "/myForum/community.action")
}

enum class OperationType(val value: String) {
    Login(value = "login"),
    Register(value = "register"),
    CommentAdd(value = "commentAdd"),
    CommentDelete(value = "commentDelete"),
    CommentQueryAllFather(value = "commentQueryAllFather"),
    CommentQuerySub(value = "commentQuerySub"),
    CommentQueryMine(value = "commentQueryMine"),
    CommunityAdd(value = "communityAdd"),
    CommunityQuery(value = "communityQuery")
}

enum class StatusCode(val value: Int) {
    ERROR(value = 1),
    SUCCESS(value = 0)
}

object HttpConnection {
     private val client = OkHttpClient()

    fun login(user: RegisterUser): Boolean {
        val url = "$SERVER_URL${ActionType.UserAction.value}?operationType=${OperationType.Login.value}&name=${user.name}&password=${user.password}"
        executeUrl(url).run {
            val status = this.getInt("status")
            if (status == StatusCode.ERROR.value) {
                return false
            }
            return true
        }
    }

    fun register(user: RegisterUser): Boolean {
        val url = "$SERVER_URL${ActionType.UserAction.value}?operationType=${OperationType.Register.value}&name=${user.name}&password=${user.password}&age=${user.age}"
        executeUrl(url).run {
            val status = this.getInt("status")
            if (status == StatusCode.ERROR.value) {
                return false
            }
            return true
        }
    }

    fun commentAdd(comment: Comment) : Boolean {
        val url = "$SERVER_URL${ActionType.CommentAction.value}?" +
                "operationType=${OperationType.CommentAdd.value}&id=${comment.id}&" +
                "parentId=${comment.parentId}&author=${comment.author}&title=${comment.title}&" +
                "content=${comment.content}&community${comment.community}&publishTime=${comment.publishTime}&" +
                "thumbCount=${comment.thumbCount}"
        executeUrl(url).run {
            val status = this.getInt("status")
            if (status == StatusCode.ERROR.value) {
                return false
            }
            return true
        }
    }


    fun commentDelete(id: Int): Boolean {
        val url = "$SERVER_URL${ActionType.CommentAction.value}?operationType=${OperationType.CommentDelete.value}&id=$id"
        executeUrl(url).run {
            val status = this.getInt("status")
            if (status == StatusCode.ERROR.value) {
                return false
            }
            return true
        }
    }

    fun commentQueryAllFather(): List<Comment>? {
        val url = "$SERVER_URL${ActionType.CommentAction.value}?operationType=${OperationType.CommentQueryAllFather.value}"
        return executeUrl(url).run {
            val status = this.getInt("status")
            if (status == StatusCode.ERROR.value) {
                return null
            }
            this.getJSONArray("result")
        }.run {
            processComment(this)
        }
    }

    fun commentQuerySub(parentId: Int): List<Comment>? {
        val url = "$SERVER_URL${ActionType.CommentAction.value}?operationType=${OperationType.CommentQuerySub.value}"
        return executeUrl(url).run {
            val status = this.getInt("status")
            if (status == StatusCode.ERROR.value) {
                return null
            }
            this.getJSONArray("result")
        }.run {
                processComment(this)
        }
    }

    fun commentQueryMine(userName: String): List<Comment>? {
        val url = "$SERVER_URL${ActionType.CommentAction.value}?operationType=${OperationType.CommentQueryMine.value}"
        return executeUrl(url).run {
            val status = this.getInt("status")
            if (status == StatusCode.ERROR.value) {
                return null
            }
            this.getJSONArray("result")
        }.run {
            processComment(this)
        }
    }

    fun communityAdd(community: Community): Boolean {
        val url = "$SERVER_URL${ActionType.CommunityAction.value}?" +
                "operationType=${OperationType.CommunityAdd.value}&id=${community.id}&communityName=${community.communityName}&" +
                "communityDescription=${community.communityDescription}&author=${community.author}&" + "publishDate=${community.publishDate}"
        executeUrl(url).run {
            val status = this.getInt("status")
            if (status == StatusCode.ERROR.value) {
                return false
            }
            return true
        }
    }

    fun communityQuery(): List<Community>? {
        val url = "$SERVER_URL${ActionType.CommunityAction.value}?operationType=${OperationType.CommunityQuery.value}"
        return executeUrl(url).run {
            val status = this.getInt("status")
            if (status == StatusCode.ERROR.value) {
                return null
            }
            this.getJSONArray("result")
        }.run {
            processCommunity(this)
        }
    }

    private fun executeUrl(url: String): JSONObject {
        return Request.Builder().url(url).build().run {
            client.newCall(this).execute()
        }.run {
            JSONObject(this.body.toString())
        }
    }

    private fun processComment(response: JSONArray): List<Comment> {
        return (0 until response.length()).map {
            response.getJSONObject(it).run {
                Comment(
                    id = this.getInt("id"),
                    parentId = this.getInt("parentId"),
                    title = this.getString("title"),
                    content = this.getString("content"),
                    author = this.getString("author"),
                    thumbCount = this.getInt("thumbCount"),
                    publishTime = this.getString("publishTime"),
                    community = this.getString("community")
                )
            }
        }
    }

    private fun processCommunity(response: JSONArray): List<Community> {
        return (0 until response.length()).map {
            response.getJSONObject(it).run {
               Community(
                   id = this.getInt("id"),
                   communityName = this.getString("communityName"),
                   communityDescription = this.getString("communityDescription"),
                   author = this.getString("author"),
                   publishDate = this.getString("publishDate")
               )
            }
        }
    }
}