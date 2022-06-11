package com.hyvikk.retrofitappdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.hyvikk.retrofitappdemo.Adapter.TransactionAdapter;
import com.hyvikk.retrofitappdemo.Model.Transaction.TransactionClass;
import com.hyvikk.retrofitappdemo.Model.Transaction.TransactionData;
import com.hyvikk.retrofitappdemo.TransactionDatabaseModel.TransactionModel;
import com.hyvikk.retrofitappdemo.databinding.ActivityDisplayDataBinding;
import com.hyvikk.retrofitappdemo.databinding.ActivityTransactionDisplayDataBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionDisplayDataActivity extends AppCompatActivity {

    private static final String TAG = "Transaction";
    ActivityTransactionDisplayDataBinding activityTransactionDisplayDataBinding;
    ApiInterface apiInterface;
    Context ctx = this;
    private final String APITOKEN = "inBk5maIlU1I9pxdmcyEe6OGnAt92lRTtUJZGBghRPtsa485f3Xr2LUz7idu";
    private final int USERID = 3;
    private final String TIMESTAMP = "null";
    private TransactionViewModel transactionViewModel;
    private TransactionRepository transactionRepository;
    private ProgressDialog progress;
//    private List<TransactionModel> transactionModelList = new ;
    List<TransactionData> transactionDataList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTransactionDisplayDataBinding = DataBindingUtil.setContentView(this, R.layout.activity_transaction_display_data);
        transactionRepository = new TransactionRepository(getApplication());
        transactionViewModel = new ViewModelProvider(this).get(TransactionViewModel.class);
        transactionDataList = new ArrayList<>();
        displayDataFromNetwork();
    }

    private void showProgressDialog() {

        progress=new ProgressDialog(this);
        progress.setMessage("Please wait...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setProgress(0);
        progress.show();
    }

    private void displayDataFromNetwork() {

       showProgressDialog();
        //activityTransactionDisplayDataBinding.activityTransactiondisplaydataProgressbar.setVisibility(View.VISIBLE);
        apiInterface = ApiController.getClient().create(ApiInterface.class);
        Call<TransactionClass> calltransaction = apiInterface.getTransactionData(APITOKEN,USERID,TIMESTAMP);
        calltransaction.enqueue(new Callback<TransactionClass>() {
            @Override
            public void onResponse(Call<TransactionClass> call, Response<TransactionClass> response) {
                Log.d("TransactionData",response.toString());
                if(response != null){
                    progress.dismiss();
                    TransactionClass resources = response.body();
                    if(resources.getSuccess().equals("1")){
                        List<TransactionData> transactionClassList = resources.getData();
                        for(int i=0;i<transactionClassList.size();i++){

                           //int id = transactionClassList.get(i).getId();
                            String customerName = transactionClassList.get(i).getCustomer();
                            String receiptNo = transactionClassList.get(i).getReceiptNo();
                            String orderId = transactionClassList.get(i).getOrderId();
                            String paymentStatus = transactionClassList.get(i).getPaymentStatus();
                            String timeStamp = transactionClassList.get(i).getTimestamp();

//                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
//                            try {
//                                Date dateobj = sdf.parse(timeStamp);
//                                 finaldate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dateobj);
//                            } catch (ParseException e) {
//                                e.printStackTrace();
//                            }
                            TransactionModel transactionModel = new TransactionModel(customerName,receiptNo,orderId,paymentStatus,timeStamp);
                            transactionRepository.insert(transactionModel);
                            //transactionModelList.add(transactionModel);
                        }
                        LinearLayoutManager layoutManager = new LinearLayoutManager(ctx);
                        activityTransactionDisplayDataBinding.activityTransactiondisplaydataRecyclerviewTransaction.setLayoutManager(layoutManager);
                        TransactionAdapter adapter = new TransactionAdapter(ctx,transactionClassList);
                        activityTransactionDisplayDataBinding.activityTransactiondisplaydataRecyclerviewTransaction.setAdapter(adapter);
                    }
                }
            }
            @Override
            public void onFailure(Call<TransactionClass> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getLocalizedMessage());
            }
        });
    }

    private void displayDataFromRoomDb() {
        transactionViewModel.getAllTransactionData().observe(this, new Observer<List<TransactionModel>>() {
            @Override
            public void onChanged(List<TransactionModel> transactionModels) {
                TransactionData transactionData;
                for(int i=0;i<transactionModels.size();i++){
                    transactionData = new TransactionData();
                    transactionData.setCustomer(transactionModels.get(i).getCustomer());
                    transactionData.setReceiptNo(transactionModels.get(i).getReceiptNo());
                    transactionData.setOrderId(transactionModels.get(i).getOrderId());
                    transactionData.setPaymentStatus(transactionModels.get(i).getPaymentStatus());
                    transactionData.setTimestamp(transactionModels.get(i).getTimestamp());
                    transactionDataList.add(transactionData);
                }

                TransactionAdapter adapter = new TransactionAdapter(ctx,transactionDataList);
                activityTransactionDisplayDataBinding.activityTransactiondisplaydataRecyclerviewTransaction.setAdapter(adapter);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_transaction_display_data,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.menu_activity_transaction_display_data){

            displayDataFromRoomDb();
        }
        return super.onOptionsItemSelected(item);
    }
}