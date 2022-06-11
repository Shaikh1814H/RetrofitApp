package com.hyvikk.retrofitappdemo;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hyvikk.retrofitappdemo.databinding.ActivityDisplayDataBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class DisplayDataActivity extends AppCompatActivity {

    ActivityDisplayDataBinding activityDisplayDataBinding;
    String email,name,dateOfBirth,city;
    private SimpleDateFormat dateFormat;
    private String date;
    Context ctx = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDisplayDataBinding = DataBindingUtil.setContentView(this,R.layout.activity_display_data);

       setText();
       HandleEvent();
    }

    private void HandleEvent() {

        activityDisplayDataBinding.activityDisplaydataButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ctx,TransactionDisplayDataActivity.class));
            }
        });
    }

    private void setText() {

        Bundle bundle = getIntent().getExtras();
        email = bundle.getString(Constant.KEY_EMAIL);
        name = bundle.getString("name");
        city = bundle.getString("city");
        String date = bundle.getString("date");

        final SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        final Date dateObj;
        try {
            dateObj = sdfDate.parse(date);
            Log.d(TAG, "setText: " + dateObj);
            String finalDate = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH).format(Objects.requireNonNull(dateObj));
            activityDisplayDataBinding.activityDisplayDataDateofbirth.setText(finalDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        activityDisplayDataBinding.activityDisplayDataEmail.setText(email);
        activityDisplayDataBinding.activityDisplayDataName.setText(name);
        activityDisplayDataBinding.activityDisplayDataCity.setText(city);
    }
}