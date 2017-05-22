package my.blog.kotlin.utils

import my.blog.kotlin.model.vo.MetaVo
import org.apache.commons.lang3.StringUtils
import org.springframework.stereotype.Component
import sun.misc.BASE64Decoder
import sun.misc.BASE64Encoder
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

/**
 * Created by chbtc on 2017/5/22.
 */
@Component
class AdminCommons {
    companion object {
        val COLORS = arrayOf("default", "primary", "success", "info", "warning", "danger", "inverse", "purple", "pink");

        /**
         * 判断category和cat的交集
         *
         * @param cats
         * @return
         */
        fun exist_cat(category: MetaVo, cats: String): Boolean {
            val arr = StringUtils.split(cats, ",")
            if (null != arr && arr.size > 0) {
                for (c in arr) {
                    if (c.trim().equals(category.name)) {
                        return true
                    }
                }
            }
            return false;
        }

        fun rand_color(): String {
            val r = Tools.rand(0, COLORS.size - 1)
            return COLORS[r]
        }

        fun flowAutoShow(value: Int): String {
            val kb = 1024
            val mb = 1048576
            val gb = 1073741824
            if (Math.abs(value) > gb)
                if (Math.abs(value) > gb) {
                    return Math.round((value/gb).toFloat()).toString() + "GB"
                } else if (Math.abs(value) > mb) {
                    return Math.round((value/gb).toFloat()).toString() + "MB"
                } else if (Math.abs(value) > kb) {
                    return Math.round((value/gb).toFloat()).toString() + "KB"
                }
            return Math.round(value.toFloat()).toString() + ""
        }
        @Throws(Exception::class)
        fun enAes(data:String,key:String):String
        {
            val cipher=Cipher.getInstance("AES")
            val secretKeySpec= SecretKeySpec(key.toByteArray(charset("UTF-8")),"AES")
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec)
            val encryptedBytes=cipher.doFinal(data.toByteArray())
            return  BASE64Encoder().encode(encryptedBytes)
        }
        @Throws(Exception::class)
        fun deAes(data:String,key:String):String
        {
            val cipher=Cipher.getInstance("AES")
            val secretKeySpec= SecretKeySpec(key.toByteArray(charset("UTF-8")),"AES")
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec)
            val cipherTextBytes=BASE64Decoder().decodeBuffer(data)
            val decValue=cipher.doFinal(cipherTextBytes)
            return  String(decValue)
        }
        /**
         * 判断字符串是否为数字和有正确的值
         *
         * @param str
         * @return
         */
        fun isNumber(str:String):Boolean
        {
            if(null!=str&&0!=str.trim().length&&str.matches("\\d*".toRegex()))
            {
                return  true
            }
            return  false
        }
    }
}