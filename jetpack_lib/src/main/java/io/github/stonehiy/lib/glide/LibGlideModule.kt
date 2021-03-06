package io.github.stonehiy.lib.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.module.LibraryGlideModule
import java.io.InputStream

/**
https://muyangmin.github.io/glide-docs-cn/doc/generatedapi.html#kotlin
避免在程序库中使用 AppGlideModule
程序库一定 不要 包含 AppGlideModule 实现。这么做将会阻止依赖该库的任何应用程序管理它们的依赖，或配置诸如 Glide 缓存大小和位置之类的选项。
此外，如果两个程序库都包含 AppGlideModule，应用程序将无法在同时依赖两个库的情况下通过编译，而不得不在二者之中做出取舍。
这确实意味着程序库将无法使用 Glide 的 generated API，但是使标准的 RequestBuilder 和 RequestOptions 加载仍然有效（可以在 选项 页找到例子）
 */
@GlideModule
class LibGlideModule : LibraryGlideModule() {

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory())
    }
//
//    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
//        super.registerComponents(context, glide, registry)
//    }
}