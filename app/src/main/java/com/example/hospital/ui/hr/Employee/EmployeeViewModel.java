package com.example.hospital.ui.hr.Employee;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hospital.models.ModelCall;
import com.example.hospital.models.ModelEmployee;
import com.example.hospital.network.RetroConnection;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class EmployeeViewModel extends ViewModel {


    public MutableLiveData<ArrayList<ModelEmployee.DataDTO>> employees = new MutableLiveData<>();

    public void getData(String type){

        RetroConnection.getServices().getemployees(type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<ModelEmployee>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(ModelEmployee modelEmployee) {
                        Log.e("TAG", "onSuccess: " );
                        employees.setValue((ArrayList<ModelEmployee.DataDTO>) modelEmployee.getData());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "onError: "+e.getMessage() );
                    }
                });

    }
}
