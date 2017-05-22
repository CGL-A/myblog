package my.blog.kotlin.utils

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.nio.channels.FileChannel
import java.util.*

/**
 * Created by chbtc on 2017/5/22.
 */
class  Tools
{
    companion object
    {
        private val random=Random()
        @Throws(IOException::class)
        fun copyFileUsingFileChannels(source:File,dest:File)
        {
            var inputChannel: FileChannel? = null
            var outputChannel: FileChannel? = null
            try {
                inputChannel = FileInputStream(source).channel
                outputChannel = FileOutputStream(dest).channel
                outputChannel!!.transferFrom(inputChannel, 0, inputChannel!!.size())
            } finally {
                assert(inputChannel != null)
                inputChannel!!.close()
                assert(outputChannel != null)
                outputChannel!!.close()
            }
        }
        fun rand(min:Int,max:Int):Int
        {
            return random.nextInt(max)%(max-min+1)+min
        }
    }
}