package com.example.hospital.utils;

import androidx.annotation.NonNull;

import com.example.hospital.local.MyRoomDatabase;
import com.example.hospital.models.ModelAuthCache;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SharedModel {

    public static boolean cache =false;
    
    private static String type;
    private static String login_type;
    private static String username;
    private static String email;
    private static String gender;
    private static String birth;
    private static String address;
    private static String status;
    private static String phone;
    private static String token;

    public static int wrong_counter =0;
    public static int right_counter =0;
    public static String wrong_Message ="";

    public static String selected_dr_name ="Select Doctor";

    private static int call_id =0;


    private static int selected_dr_id = 0;

    public static String getSelected_dr_name() {
        return selected_dr_name;
    }

    public static void setSelected_dr_name(String selected_dr_name) {
        SharedModel.selected_dr_name = selected_dr_name;
    }

    public static int getSelected_dr_id() {
        return selected_dr_id;
    }

    public static void setSelected_dr_id(int selected_dr_id) {
        SharedModel.selected_dr_id = selected_dr_id;
    }

    public static int getCall_id() {
        return call_id;
    }

    public static void setCall_id(int call_id) {
        SharedModel.call_id = call_id;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        SharedModel.type = type;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        SharedModel.username = username;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        SharedModel.token = token;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        SharedModel.email = email;
    }

    public static String getGender() {
        return gender;
    }

    public static void setGender(String gender) {
        SharedModel.gender = gender;
    }

    public static String getBirth() {
        return birth;
    }

    public static void setBirth(String birth) {
        SharedModel.birth = birth;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        SharedModel.address = address;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        SharedModel.status = status;
    }

    public static String getPhone() {
        return phone;
    }

    public static void setPhone(String phone) {
        SharedModel.phone = phone;
    }

    public static String getLogin_type() {
        return login_type;
    }

    public static void setLogin_type(String login_type) {
        SharedModel.login_type = login_type;
    }


    public static void cache(List<ModelAuthCache> list){

        MyRoomDatabase.getInstance().getDao().insert(list).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }

    public static void delete(ModelAuthCache model){

        MyRoomDatabase.getInstance().getDao().delete(model).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });

    }

}
