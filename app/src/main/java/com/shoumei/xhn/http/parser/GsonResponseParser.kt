package com.shoumei.xhn.http.parser

import com.google.gson.Gson
import com.shoumei.xhn.base.BaseModel
import com.shoumei.xhn.base.PageModel
import com.shoumei.xhn.base.WriteFileBean
import com.shoumei.xhn.http.UrlConstant
import okhttp3.ResponseBody
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.text.NumberFormat

/**
 * Desc
 * Author 张野
 * Mail zhangye98@foxmail.com
 * Date 2020/4/29 14:51
 */
@Suppress("UNCHECKED_CAST")
class GSonResponseParser : IResponseParser {

    private val mGSon = Gson()


    override fun <T> parserResponse(data: ResponseBody, type: Type, serializedName: String?): T {

        val body = data.string()

        if (type is Class<*>) {
            when (type.name.toString()) {
                String::class.java.name.toString() -> {
                    return body as T
                }

                File::class.java.name.toString() -> {
                    return writeResponseBodyToDisk(data, serializedName) as T

                }
            }

        } else {

            if (type is ParameterizedType) {
                val jsonObj = JSONObject(body)
                when (type.rawType) {
                    BaseModel::class.java -> {
                        val childType = type.actualTypeArguments[0]
                        val baseModel = BaseModel<Any>()
                        baseModel.msg = jsonObj.optString("msg")
                        baseModel.code = jsonObj.optString("code")
                        val mData = mGSon.fromJson<Any>(jsonObj.optString(serializedName), childType)
                        baseModel.data = mData
                        return baseModel as T
                    }
                    PageModel::class.java -> {
                        jsonObj.put("data", jsonObj.optJSONObject(serializedName))
                        jsonObj.remove(serializedName)
                        return mGSon.fromJson(jsonObj.toString(), type)
                    }
                }
            }
        }

        return mGSon.fromJson(body, type)
    }


    private fun writeResponseBodyToDisk(data: ResponseBody, serializedName: String?): File {

        val pathFile = File(UrlConstant.TEMP)
        if (!pathFile.exists()) {
            pathFile.mkdirs()
        }
        val file = File(pathFile, serializedName ?: "${System.currentTimeMillis()}.temp")
        if (file.exists()) {
            file.delete()
        }
        file.createNewFile()

        val byte = ByteArray(4096)
        val fileSize = data.contentLength()
        var downloadSize = 0L

        val inputStream = data.byteStream()
        val outputStream = FileOutputStream(file)
        var progress = 0

        val numberFormat = NumberFormat.getInstance()
        numberFormat.maximumFractionDigits = 0
        val writeFileBean = WriteFileBean(file, downloadSize, fileSize, progress)

        while (true) {
            val read = inputStream.read(byte)
            if (read == -1) {
                break
            }
            outputStream.write(byte, 0, read)
            downloadSize += read
            progress = numberFormat.format((downloadSize.toFloat() / fileSize.toFloat()) * 100).toInt()
            if (progress > writeFileBean.progress) {
                writeFileBean.downloadSize = downloadSize
                writeFileBean.progress = progress
            }
        }
        outputStream.flush()
        return file
    }
}