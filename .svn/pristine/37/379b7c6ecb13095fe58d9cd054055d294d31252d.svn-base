package com.youjuke.optimalmaterialtreasure.retrofit.api;


import com.youjuke.optimalmaterialtreasure.retrofit.ResponseBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 描述: 一个公用的Service
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-09-12 17:16
 */
public interface CommonService {
    @FormUrlEncoded
    @POST("ycb_interface")
    Observable<ResponseBean> getData(@Field("json_msg") String json_msg);


}
