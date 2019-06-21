package com.goalwise.sample.goalwise.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.goalwise.sample.goalwise.apicalls.RetrofitClient;
import com.goalwise.sample.goalwise.model.FundsModel;

import java.util.List;

public class FundsViewModel extends ViewModel{

    RetrofitClient retrofitClient=new RetrofitClient();
    MutableLiveData<List<FundsModel>> list;

    public MutableLiveData<List<FundsModel>> getList(String keyword) {
        if (list == null)
        {
            list=retrofitClient.getList(keyword);
        }
        return list;
    }
}
