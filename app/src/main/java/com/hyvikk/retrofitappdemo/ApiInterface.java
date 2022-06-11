package com.hyvikk.retrofitappdemo;

import com.hyvikk.retrofitappdemo.Model.City.CityClass;
import com.hyvikk.retrofitappdemo.Model.City.CityData;
import com.hyvikk.retrofitappdemo.Model.Transaction.TransactionClass;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("cities")
    Call<CityClass> getResponse();

    @FormUrlEncoded
    @POST("transactions")
    Call<TransactionClass> getTransactionData(@Field("api_token") String apiToken
            , @Field("user_id") int userId
            , @Field("timestamp") String timeStamp);

}
