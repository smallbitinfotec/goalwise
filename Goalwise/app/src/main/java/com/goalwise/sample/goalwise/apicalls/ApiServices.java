package com.goalwise.sample.goalwise.apicalls;

import com.goalwise.sample.goalwise.model.FundsModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiServices {
    @Headers({
            "content-type: application/json",
            "x-api-key:GQCqJDU1hZ3e4aXOhLG905HbKoiBV1ZU6ipCB9Oc",
            "cache-contro: no-cache"
    })
    @POST("dev/search")
    @FormUrlEncoded
    Call<List<FundsModel>> getList(@Field("keyword") String keyword);

}
