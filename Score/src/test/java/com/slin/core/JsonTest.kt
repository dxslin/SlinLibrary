package com.slin.core

import com.slin.core.bean.BannerBean
import com.slin.core.utils.fromJsonArray
import org.junit.Test

/**
 * author: slin
 * date: 2021/8/30
 * description:
 *
 */
class JsonTest {

    @Test
    fun fromJson() {

        fromJson("[]")
        fromJson("[{\"desc\":\"扔物线\",\"id\":29,\"imagePath\":\"https://wanandroid.com/blogimgs/5d362c2a-2e9e-4448-8ee4-75470c8c7533.png\",\"isVisible\":1,\"order\":0,\"title\":\"LiveData：还没普及就让我去世？我去你的 Kotlin 协程\",\"type\":0,\"url\":\"https://url.rengwuxian.com/y3zsb\"}]")
        fromJson("[{\"desc\":\"扔物线\",\"id\":29,\"imagePath\":\"https://wanandroid.com/blogimgs/5d362c2a-2e9e-4448-8ee4-75470c8c7533.png\",\"isVisible\":1,\"order\":0,\"title\":\"LiveData：还没普及就让我去世？我去你的 Kotlin 协程\",\"type\":0,\"url\":\"https://url.rengwuxian.com/y3zsb\"},{\"desc\":\"\",\"id\":6,\"imagePath\":\"https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png\",\"isVisible\":1,\"order\":1,\"title\":\"我们新增了一个常用导航Tab~\",\"type\":1,\"url\":\"https://www.wanandroid.com/navi\"},{\"desc\":\"一起来做个App吧\",\"id\":10,\"imagePath\":\"https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png\",\"isVisible\":1,\"order\":1,\"title\":\"一起来做个App吧\",\"type\":1,\"url\":\"https://www.wanandroid.com/blog/show/2\"},{\"desc\":\"\",\"id\":20,\"imagePath\":\"https://www.wanandroid.com/blogimgs/90c6cc12-742e-4c9f-b318-b912f163b8d0.png\",\"isVisible\":1,\"order\":2,\"title\":\"flutter 中文社区 \",\"type\":1,\"url\":\"https://flutter.cn/\"}]")

    }

    fun fromJson(json: String) {

//        val banners: ArrayList<BannerBean>
//        val listType: Type = object : TypeToken<List<BannerBean>>() {}.type
//        banners = GsonUtils.INSTANCE.fromJson(json, listType)

        val banners: List<BannerBean> = json.fromJsonArray()
        println(banners)
    }

}