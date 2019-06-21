package com.goalwise.sample.goalwise.apicalls;

import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.goalwise.sample.goalwise.model.FundsModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    static String BASE_URL = " https://gord6bd0zi.execute-api.ap-southeast-1.amazonaws.com/";
    static Retrofit retrofit;
//    MainView mainView;


    private static final String TAG = "RetrofitClient";
   public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    public static ApiServices getService()
    {
        return  getClient().create(ApiServices.class);
    }


    public MutableLiveData<List<FundsModel>> getList(String keyword)
    {
        MutableLiveData<List<FundsModel>> list=new MutableLiveData<>();
        Call<List<FundsModel>> call=getService().getList(keyword);
        call.enqueue(new Callback<List<FundsModel>>() {
            @Override
            public void onResponse(Call<List<FundsModel>> call, Response<List<FundsModel>> response) {
                if (response.isSuccessful())
                {
                    Log.e(TAG, "onResponse: "+response.body().size() );
//                    mainView.onSuccess(response.body());
//                    mainView.hideProgressBar();
                    List<FundsModel> items=response.body();
                    list.setValue(items);
                }
            }

            @Override
            public void onFailure(Call<List<FundsModel>> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage() );
//                mainView.hideProgressBar();
//                mainView.onFail("Something is wrong");
            }
        });
        return list;
    }

}
