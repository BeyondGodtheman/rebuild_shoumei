package com.coco_sh.shmstore.archives.view

import com.coco_sh.shmstore.base.IBaseView
import com.coco_sh.shmstore.mine.model.Profile

/**
 *
 * Created by zhangye on 2018/9/20.
 */
interface IArchivesView : IBaseView {
    //显示档案数据
    fun showArchiveData(profile: Profile)

}