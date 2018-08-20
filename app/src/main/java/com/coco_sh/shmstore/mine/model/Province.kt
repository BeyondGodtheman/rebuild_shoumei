package com.coco_sh.shmstore.mine.model

/**
 * 省份
 * Created by zhangye on 2018/8/6.
 */
data class Province(
        var provinceID: Int,
        var divisionCode: String,
        var provinceName: String,
        var citys:ArrayList<City>
)