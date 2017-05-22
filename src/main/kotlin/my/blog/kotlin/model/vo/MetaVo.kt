package my.blog.kotlin.model.vo

import java.io.Serializable

/**
 * Created by chbtc on 2017/5/22.
 */
open class  MetaVo:Serializable
{
    /**
     * 项目主键
     */
     var mid: Int? = null

    /**
     * 名称
     */
     var name: String? = null

    /**
     * 项目缩略名
     */
     var slug: String? = null

    /**
     * 项目类型
     */
     var type: String? = null

    /**
     * 选项描述
     */
     var description: String? = null

    /**
     * 项目排序
     */
     var sort: Int? = null

     var parent: Int? = null

    companion object {

        private const val serialVersionUID = 1L
    }

}