package com.hyvikk.retrofitappdemo;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hyvikk.retrofitappdemo.Adapter.TransactionAdapter;
import com.hyvikk.retrofitappdemo.Model.Transaction.TransactionData;
import com.hyvikk.retrofitappdemo.TransactionDatabaseModel.TransactionModel;
import com.hyvikk.retrofitappdemo.databinding.ItemTransactionBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TransactionDbAdapter extends RecyclerView.Adapter {

    Context ctx;
   List<TransactionModel> transactionModelArrayList;

    public TransactionDbAdapter(Context ctx, List<TransactionModel> transactionModelArrayList) {
        this.ctx = ctx;
        this.transactionModelArrayList = transactionModelArrayList;
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

        TransactionModel transactionModel = transactionModelArrayList.get(position);
        TransactionDbAdapter.TransactionItemContainer container = (TransactionDbAdapter.TransactionItemContainer) holder;

        String timestamp = transactionModel.getTimestamp();
        String customername = transactionModel.getCustomer();
        String receiptno = transactionModel.getReceiptNo();
        String paymentStatus = transactionModel.getPaymentStatus();
        String orderid = transactionModel.getOrderId();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        try {
            Date dateobj = sdf.parse(timestamp);
            String finalDate = new SimpleDateFormat("dd-MMM-yyyy hh:mm aaa").format(dateobj);
            container.itemTransactionBinding.itemTransactionDateTime.setText(finalDate);


        } catch (ParseException e) {
            e.printStackTrace();
        }
        container.itemTransactionBinding.itemTransactionCustomername.setText(transactionModel.getCustomer());
        container.itemTransactionBinding.itemTransactionReceiptno.setText(transactionModel.getReceiptNo());
        container.itemTransactionBinding.itemTransactionPaymentstatus.setText(transactionModel.getPaymentStatus());
        container.itemTransactionBinding.itemTransactionOrderid.setText(transactionModel.getOrderId());
        //container.itemTransactionBinding.itemTransactionDateTime.setText(transactionModel.getTimestamp());
        if (TextUtils.isEmpty(timestamp)){
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
            container.itemTransactionBinding.itemTransactionPaymentstatus.setTextColor(ctx.getResources().getColor(R.color.yellow));
        } else if (paymentStatus.equals("pending")){
            container.itemTransactionBinding.itemTransactionPaymentstatus.setTextColor(ctx.getResources().getColor(R.color.green));
        } else{
            container.itemTransactionBinding.itemTransactionPaymentstatus.setTextColor(ctx.getResources().getColor(R.color.red));
        }
    }

    @Override
    public int getItemCount() {
        return transactionModelArrayList.size();
    }

    public class TransactionItemContainer extends RecyclerView.ViewHolder {

        ItemTransactionBinding itemTransactionBinding;

        public TransactionItemContainer(@NonNull ItemTransactionBinding itemTransactionBinding) {
            super(itemTransactionBinding.getRoot());
            this.itemTransactionBinding = itemTransactionBinding;
        }
    }
}
