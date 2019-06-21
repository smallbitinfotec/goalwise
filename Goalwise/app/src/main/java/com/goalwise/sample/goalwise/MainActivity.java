package com.goalwise.sample.goalwise;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.goalwise.sample.goalwise.adapter.Adapter;
import com.goalwise.sample.goalwise.model.FundsModel;
import com.goalwise.sample.goalwise.viewModel.FundsViewModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements  TextWatcher {
//
    @BindView(R.id.edt_search)
    EditText edt_search;
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    RecyclerView.LayoutManager layoutManager;
    Adapter adapter;
    FundsViewModel fundsViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarBackground();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        rvList.setLayoutManager(layoutManager);
        adapter=new Adapter(this);
        edt_search.addTextChangedListener(this);
        fundsViewModel= ViewModelProviders.of(this).get(FundsViewModel.class);

    }

    public void finshActivity(View view) {
        finish();
    }

    public void openDialog()
    {
        Dialog dialog=new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog);
        TextView gotIt=dialog.findViewById(R.id.tx_gotit);
        gotIt.setOnClickListener(view->dialog.dismiss());
        dialog.show();
    }
    public void setStatusBarBackground()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            Drawable background = getResources().getDrawable(R.drawable.gradient);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length()>=3)
            {
//                mainPresenterImp.fetchList(s.toString());
                fundsViewModel.getList(s.toString()).observe(this, new Observer<List<FundsModel>>() {
                    @Override
                    public void onChanged(@Nullable List<FundsModel> fundsModels) {
                        adapter.clearData();
                        adapter.addList(fundsModels);
                        rvList.setAdapter(adapter);
                    }
                });
            }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
