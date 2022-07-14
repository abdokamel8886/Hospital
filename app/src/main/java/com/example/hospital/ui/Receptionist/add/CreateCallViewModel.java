package com.example.hospital.ui.Receptionist.add;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hospital.models.ModelCreateCall;
import com.example.hospital.models.ModelEmployee;
import com.example.hospital.network.RetroConnection;

import java.util.ArrayList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CreateCallViewModel extends ViewModel {

    public MutableLiveData<ModelCreateCall> response = new MutableLiveData<>();

    public MutableLiveData<String> response2 = new MutableLiveData<>();


    public void sendData(String name , int id , String age , String phone , String des){
        RetroConnection.getServices().createCall(name, id, age, phone, des)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ModelCreateCall>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ModelCreateCall modelCreateCall) {
                        response.setValue(modelCreateCall);


                    }

                    @Override
                    public void onError(Throwable e) {
                        response2.setValue(e.getMessage());
                    }
                });
    }


}
