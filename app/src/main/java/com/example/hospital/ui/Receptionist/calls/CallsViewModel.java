package com.example.hospital.ui.Receptionist.calls;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hospital.models.ModelCall;
import com.example.hospital.network.RetroConnection;
import com.example.hospital.utils.SharedModel;

import java.util.ArrayList;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CallsViewModel extends ViewModel {

    MutableLiveData<ArrayList<ModelCall.DataDTO>> calls = new MutableLiveData<>();


    public void getData(String date){



        RetroConnection.getServices().getCalls(date)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ModelCall>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ModelCall modelCall) {
                        Log.e("TAG", "onSuccess: "+modelCall );
                        calls.setValue((ArrayList<ModelCall.DataDTO>) modelCall.getData());

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e("TAG", "onError: "+e.getMessage() );

                    }
                });

    }

}
