package com.goalwise.sample.goalwise.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.goalwise.sample.goalwise.MainActivity;
import com.goalwise.sample.goalwise.R;
import com.goalwise.sample.goalwise.model.FundsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<FundsModel> list;

    public Adapter(Context context) {
        this.context = context;
        list= new ArrayList<>();
    }

    public  void clearData()
    {
        if(list!=null && list.size()>0)
            list.clear();
    }
    public void addList(List<FundsModel> list)
    {
        this.list.addAll(list);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        FundsModel model=list.get(i);
        viewHolder.txt_fundname.setText(model.getFundname());
        viewHolder.tx_min_sip_amount.setText("₹ "+model.getMinsipamount());
        viewHolder.tx_min_sip_multiple.setText("₹ "+model.getMinsipmultiple());
        String dates="";
        List<Integer> datelist=model.getSipdates();
        StringBuilder output = new StringBuilder();
        for (Integer part : datelist) {
            if (output.length() > 0) {
                output.append(",");
            }
            output.append(part);
        }
        viewHolder.tx_sip_dates.setText(output);
        viewHolder.txAdd.setOnClickListener(view->{
            if (viewHolder.llFund.getVisibility()==View.VISIBLE)
                viewHolder.llFund.setVisibility(View.GONE);
            else
                viewHolder.llFund.setVisibility(View.VISIBLE);
        });
        
        viewHolder.btnAddFund.setOnClickListener(view->{
            int value= Integer.parseInt(viewHolder.edtFund.getText().toString());
            int fund=model.getMinsipamount()*model.getMinsipmultiple();
            if (value==fund)
            {
                viewHolder.llFund.setVisibility(View.GONE);
                ((MainActivity)context).openDialog();
            }
            else {
                Toast.makeText(context, "Please add correct amount.", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list!=null && list.size()>0)
            return list.size();

        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_fundname)
        TextView txt_fundname;
        @BindView(R.id.tx_min_sip_amount)
        TextView tx_min_sip_amount;

        @BindView(R.id.tx_min_sip_multiple)
        TextView tx_min_sip_multiple;

        @BindView(R.id.tx_sip_dates)
        TextView tx_sip_dates;
        @BindView(R.id.tx_add)
        TextView txAdd;
        @BindView(R.id.tx_add_fund)
        TextView btnAddFund;
        @BindView(R.id.ll_fund)
        LinearLayout llFund;
        @BindView(R.id.edt_fund)
        EditText edtFund;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
