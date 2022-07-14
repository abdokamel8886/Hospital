package com.example.hospital.ui.Receptionist.details;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hospital.models.ModelCall;
import com.example.hospital.models.ModelCallDetails;
import com.example.hospital.network.RetroConnection;
import com.example.hospital.utils.SharedModel;

import java.util.ArrayList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CallsDetailsViewModel extends ViewModel {

    MutableLiveData<ModelCallDetails> call= new MutableLiveData<>();


    public void getData(){



        RetroConnection.getServices()
                .getCall(SharedModel.getCall_id())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ModelCallDetails>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ModelCallDetails modelCallDetails) {
                        if (modelCallDetails.getStatus() == 1){
                            call.setValue(modelCallDetails);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

}
