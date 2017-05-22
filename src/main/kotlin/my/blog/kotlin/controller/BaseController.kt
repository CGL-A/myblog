package my.blog.kotlin.controller

import javax.servlet.http.HttpServletRequest

/**
 * Created by chbtc on 2017/5/22.
 */
abstract class  BaseController
{

    fun render(viewName:String):String
    {
        return THEME+"/"+viewName
    }
    fun  title(request: HttpServletRequest, title:String): BaseController {
        request.setAttribute("title",title)
        return this
    }
    fun  keywords(request: HttpServletRequest, keywords:String): BaseController {
        request.setAttribute("keywords",keywords)
        return this
    }

    fun getUid(request: HttpServletRequest):Int
    {
        return 1
    }
    fun render_404():String
    {
        return "comn/error_404"
    }
    companion object
    {
        var THEME="themes/default"
    }
}

