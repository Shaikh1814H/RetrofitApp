package com.hyvikk.retrofitappdemo.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

//import com.hyvikk.retrofitappdemo.BR;
import com.hyvikk.retrofitappdemo.Model.Transaction.TransactionClass;
import com.hyvikk.retrofitappdemo.Model.Transaction.TransactionData;
import com.hyvikk.retrofitappdemo.R;
import com.hyvikk.retrofitappdemo.databinding.ItemTransactionBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class TransactionAdapter extends RecyclerView.Adapter {


    Context ctx;
    private List<TransactionData> transactionDataList;

    public TransactionAdapter(Context ctx, List<TransactionData> transactionDataList) {
        this.ctx = ctx;
        this.transactionDataList = transactionDataList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        ItemTransactionBinding itemTransactionBinding = ItemTransactionBinding.inflate(inflater,parent,false);
        return new TransactionItemContainer(itemTransactionBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        TransactionData transactionData = transactionDataList.get(position);
        TransactionItemContainer container = (TransactionItemContainer) holder;

        String timestamp = transactionData.getTimestamp();
        String customername = transactionData.getCustomer();
        String receiptno = transactionData.getReceiptNo();
        String paymentStatus = transactionData.getPaymentStatus();
        String orderid = transactionData.getOrderId();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        try {
            Date dateobj = sdf.parse(timestamp);
            String finaldate = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa").format(dateobj);
            container.itemTransactionBinding.itemTransactionDateTime.setText(finaldate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        container.itemTransactionBinding.itemTransactionCustomername.setText(transactionData.getCustomer());
        container.itemTransactionBinding.itemTransactionReceiptno.setText(transactionData.getReceiptNo());
        container.itemTransactionBinding.itemTransactionPaymentstatus.setText(transactionData.getPaymentStatus());
        container.itemTransactionBinding.itemTransactionOrderid.setText(transactionData.getOrderId());
        if (timestamp == null){
            container.itemTransactionBinding.itemTransactionDateTime.setText("--------");
        }
        if (customername == null){
            container.itemTransactionBinding.itemTransactionCustomername.setText("--------");
        }
        if (receiptno == null){
            container.itemTransactionBinding.itemTransactionReceiptno.setText("--------");
        }
        if (paymentStatus== null){
            container.itemTransactionBinding.itemTransactionPaymentstatus.setText("--------");
        }
        if (orderid == null){
            container.itemTransactionBinding.itemTransactionOrderid.setText("--------");
        }

        if (paymentStatus.equals("succeeded")){
            container.itemTransactionBinding.itemTransactionPaymentstatus.setTextColor(ctx.getResources().getColor(R.color.green));
        } else if (paymentStatus.equals("pending")){
            container.itemTransactionBinding.itemTransactionPaymentstatus.setTextColor(ctx.getResources().getColor(R.color.yellow));
        } else{
            container.itemTransactionBinding.itemTransactionPaymentstatus.setTextColor(ctx.getResources().getColor(R.color.red));
        }
    }

    @Override
    public int getItemCount() {
        return transactionDataList.size();
    }

    public class TransactionItemContainer extends RecyclerView.ViewHolder {

        ItemTransactionBinding itemTransactionBinding;

        public TransactionItemContainer(@NonNull ItemTransactionBinding itemTransactionBinding) {
            super(itemTransactionBinding.getRoot());
            this.itemTransactionBinding = itemTransactionBinding;
        }
    }
}
