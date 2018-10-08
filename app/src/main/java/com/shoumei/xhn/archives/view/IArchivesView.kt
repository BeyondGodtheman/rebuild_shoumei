package com.shoumei.xhn.archives.view

import com.shoumei.xhn.base.IBaseView
import com.shoumei.xhn.mine.model.Profile

/**
 *
 * Created by zhangye on 2018/9/20.
 */
interface IArchivesView : IBaseView {
    //显示档案数据
    fun showArchiveData(profile: Profile)

}