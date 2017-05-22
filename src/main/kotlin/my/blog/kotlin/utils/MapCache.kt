package my.blog.kotlin.utils

import java.util.concurrent.ConcurrentHashMap

/**
 * Created by chbtc on 2017/5/22.
 */
class MapCache @JvmOverloads constructor(cacheCount: Int = MapCache.DEFAULT_CACHES) {
    private val cachePool: MutableMap<String, CacheObject>

    init {
        cachePool = ConcurrentHashMap<String, CacheObject>(cacheCount)
    }

    fun <T>  get(key:String):T?
    {
        val cacheObject=cachePool[key];
        if(null!=cacheObject)
        {
            val cur=System.currentTimeMillis()/1000
            if(cacheObject.expired<=0||cacheObject.expired>cur)
            {
                val result=cacheObject.value
                return result as T
            }
        }
        return null
    }
    /**
     * 读取一个hash类型缓存

     * @param key   缓存key
     * *
     * @param field 缓存field
     * *
     * @param <T>
     * *
     * @return
    </T> */
    fun <T> hget(key: String, field: String): T? {
        var key = key
        key = key + ":" + field
        return this.get<T>(key)
    }
    /**
     * 设置一个缓存

     * @param key   缓存key
     * *
     * @param value 缓存value
     */
    operator fun set(key: String, value: Any) {
        this[key, value] = -1
    }

    /**
     * 设置一个缓存并带过期时间

     * @param key     缓存key
     * *
     * @param value   缓存value
     * *
     * @param expired 过期时间，单位为秒
     */
    operator fun set(key: String, value: Any, expired: Long) {
        var expired = expired
        expired = if (expired > 0) System.currentTimeMillis() / 1000 + expired else expired
        val cacheObject = CacheObject(key, value, expired)
        cachePool.put(key, cacheObject)
    }

    /**
     * 设置一个hash缓存

     * @param key   缓存key
     * *
     * @param field 缓存field
     * *
     * @param value 缓存value
     */
    fun hset(key: String, field: String, value: Any) {
        this.hset(key, field, value, -1)
    }

    /**
     * 设置一个hash缓存并带过期时间

     * @param key     缓存key
     * *
     * @param field   缓存field
     * *
     * @param value   缓存value
     * *
     * @param expired 过期时间，单位为秒
     */
    fun hset(key: String, field: String, value: Any, expired: Long) {
        var key = key
        var expired = expired
        key = key + ":" + field
        expired = if (expired > 0) System.currentTimeMillis() / 1000 + expired else expired
        val cacheObject = CacheObject(key, value, expired)
        cachePool.put(key, cacheObject)
    }

    /**
     * 根据key删除缓存

     * @param key 缓存key
     */
    fun del(key: String) {
        cachePool.remove(key)
    }

    /**
     * 根据key和field删除缓存

     * @param key   缓存key
     * *
     * @param field 缓存field
     */
    fun hdel(key: String, field: String) {
        var key = key
        key = key + ":" + field
        this.del(key)
    }

    /**
     * 清空缓存
     */
    fun clean() {
        cachePool.clear()
    }
    internal class CacheObject(val key: String, val value: Any, val expired: Long)

    companion object {
        private val DEFAULT_CACHES = 1024

        private val INS = MapCache()

        fun single(): MapCache {
            return INS
        }
    }
}