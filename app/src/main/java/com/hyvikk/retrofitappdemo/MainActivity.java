package com.hyvikk.retrofitappdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import com.hyvikk.retrofitappdemo.Model.City.CityClass;
import com.hyvikk.retrofitappdemo.Model.City.CityData;
import com.hyvikk.retrofitappdemo.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Context ctx = this;
    private ActivityMainBinding activityMainBinding;
    ApiInterface apiInterface;
    List<String> cityList =  new ArrayList<>();
    String email,name,city,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityList);

        apiInterface = ApiController.getClient().create(ApiInterface.class);
        Call<CityClass> callCities = apiInterface.getResponse();

        callCities.enqueue(new Callback<CityClass>() {
            @Override
            public void onResponse(Call<CityClass> call, Response<CityClass> response) {

                if (response!=null){
                    CityClass resources = response.body();
                    if (resources.getSuccess().equals("1")){
                        List<CityData> cityClassList = resources.getData();

                        for (int i = 0; i < cityClassList.size(); i++) {
                            cityList.add(cityClassList.get(i).getCity());
                        }
                        Log.d("City", cityClassList.toString());

                        activityMainBinding.activityMainSpinnerCity.setAdapter(arrayAdapter);
                    }else {
                        Toast.makeText(ctx, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                    }

                }
            }

            @Override
            public void onFailure(Call<CityClass> call, Throwable t) {

            }
        });

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        activityMainBinding.activityMainEdittextDateOfBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        Log.d("datey",String.valueOf(year));
                        Log.d("datem",String.valueOf(month));
                        Log.d("dated",String.valueOf(day));
                        month = month + 1;
                        date = day + "/" + month + "/" + year;
                        Log.d("date",date);
                        activityMainBinding.activityMainEdittextDateOfBirth.setText(date);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });
        HandleEvent();
    }

    private Boolean isValidateInput() {

        Boolean isError = false;
        email = activityMainBinding.activityMainEdittextEmail.getText().toString();
        name = activityMainBinding.activityMainEdittextName.getText().toString();
        city = activityMainBinding.activityMainSpinnerCity.getItemAtPosition(activityMainBinding.activityMainSpinnerCity.getSelectedItemPosition()).toString();

        if (email.length()==0){
            activityMainBinding.activityMainEdittextEmail.setError("Email is Compulsory");
            isError = true;
        }
        if (name.length()==0){
            activityMainBinding.activityMainEdittextName.setError("Email is Compulsory");
            isError = true;
        }
//        if(city.contains("Select City")){
//            Toast.makeText(ctx,"PLZ...Select City",Toast.LENGTH_LONG).show();
//            isError = true;
//        }
        if (activityMainBinding.activityMainEdittextDateOfBirth.length()==0){
            activityMainBinding.activityMainEdittextDateOfBirth.setError("Email is Compulsory");
            isError = true;
        }
        return isError;
    }

    private void HandleEvent() {

        activityMainBinding.activityMainButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isValidateInput()==false){
                    Intent intent = new Intent(MainActivity.this,DisplayDataActivity.class);
                    intent.putExtra(Constant.KEY_EMAIL,email);
                    intent.putExtra("name",name);
                    intent.putExtra("city",city);
                    intent.putExtra("date",date);
                    startActivity(intent);
                }
            }
        });
    }
}