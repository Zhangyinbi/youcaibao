package com.youjuke.optimalmaterialtreasure.app.goods;

import com.youjuke.library.base.BaseAdapterCallBack;

import java.util.List;

/**
 * 描述: TODO
 * ------------------------------------------------------------------------
 * 工程:
 * #0000     tian xiao     创建日期: 2017-02-13 11:49
 */
public class GoodsAdapterCallBack extends BaseAdapterCallBack {


    public GoodsAdapterCallBack(List mOldDatas, List mNewDatas) {
        super(mOldDatas, mNewDatas);
    }

    @Override
    public boolean mAreitemsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }

    @Override
    public boolean mAreContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
}
