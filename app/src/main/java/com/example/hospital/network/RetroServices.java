package com.example.hospital.network;


import com.example.hospital.models.LoginModel;
import com.example.hospital.models.ModelCall;
import com.example.hospital.models.ModelCallDetails;
import com.example.hospital.models.ModelCreateCall;
import com.example.hospital.models.ModelEmployee;

import io.reactivex.Single;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetroServices {

    @FormUrlEncoded
    @POST("login")
    Single<LoginModel> login(@Field("email") String email , @Field("password") String password , @Field("device_token") String device_token );




    @FormUrlEncoded
    @POST("register")
    Single<LoginModel> Register(@Field("email") String email
            , @Field("password") String password
            , @Field("first_name") String first_name
            , @Field("last_name") String last_name
            , @Field("gender") String gender
            , @Field("specialist") String specialist
            , @Field("birthday") String birthday
            , @Field("status") String status
            , @Field("address") String address
            , @Field("mobile") String mobile
            , @Field("type") String type
    );


    @GET("calls")
    Single<ModelCall> getCalls (@Query("date") String date);

    @GET("calls/{id}")
    Single<ModelCallDetails> getCall (@Path("id") int id);


    @GET("doctors")
    Single<ModelEmployee> getemployees (@Query("type") String type);


    @FormUrlEncoded
    @POST("calls")
    Single<ModelCreateCall> createCall(@Field("patient_name") String name
            , @Field("doctor_id") int id
            , @Field("age") String age
            , @Field("phone") String phone
            , @Field("description") String description
    );










}
