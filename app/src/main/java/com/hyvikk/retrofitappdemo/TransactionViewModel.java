package com.hyvikk.retrofitappdemo;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.hyvikk.retrofitappdemo.TransactionDatabaseModel.TransactionModel;

import java.util.List;

public class TransactionViewModel extends AndroidViewModel {

    // creating a new variable for course repository.
    private TransactionRepository transactionRepository;

    // below line is to create a variable for live
    // data where all the courses are present.
    private LiveData<List<TransactionModel>> getAllTransactionData;
    public TransactionViewModel(@NonNull Application application) {
        super(application);
        transactionRepository = new TransactionRepository(application);
        getAllTransactionData = transactionRepository.getAllTransactionData();
    }

    // below method is use to insert the data to our repository.
    public void insert(TransactionModel model) {
        transactionRepository.insert(model);
    }

    // below method is to get all the courses in our list.
    public LiveData<List<TransactionModel>> getAllTransactionData() {
        return getAllTransactionData;
    }
}
