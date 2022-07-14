package com.example.hospital.ui.hr.Reg;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hospital.models.LoginModel;
import com.example.hospital.network.RetroConnection;
import com.example.hospital.utils.SharedModel;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RegViewModel extends ViewModel {

    MutableLiveData<Integer> Status = new MutableLiveData<>();
    MutableLiveData<Integer> wrongid = new MutableLiveData<>();


    public void Reg(String Email , String Password , String fname , String lname , String gender , String birth , String status , String Address , String Phone , String Type ) {
        RetroConnection.getServices().Register(Email,Password,fname,lname,gender,"docotr",birth,status,Address,Phone,Type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<LoginModel>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(LoginModel loginModel) {

                        if (loginModel.getMessage().equals("")){
                            SharedModel.wrong_Message = "CHECK YOUR INTERNET CONNECTION";
                            if(SharedModel.wrong_counter>0){
                                wrongid.setValue(1+SharedModel.wrong_counter);
                                SharedModel.wrong_counter += 1;
                            }
                            else{
                                wrongid.setValue(1);
                                SharedModel.wrong_counter += 1;
                            }
                        }
                        else{
                            SharedModel.wrong_Message = loginModel.getMessage();

                            if (loginModel.getStatus()==1){

                                if(SharedModel.right_counter>0){
                                    Status.setValue(1+SharedModel.right_counter);
                                    SharedModel.right_counter += 1;
                                }
                                else{
                                    Status.setValue(1);
                                    SharedModel.right_counter += 1;
                                }

                            }
                            else{
                                if(SharedModel.wrong_counter>0){
                                    wrongid.setValue(1+SharedModel.wrong_counter);
                                    SharedModel.wrong_counter += 1;
                                }
                                else{
                                    wrongid.setValue(1);
                                    SharedModel.wrong_counter += 1;
                                }
                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        SharedModel.wrong_Message = "CHECK YOUR INTERNET CONNECTION";
                        if(SharedModel.wrong_counter>0){
                            wrongid.setValue(1+SharedModel.wrong_counter);
                            SharedModel.wrong_counter += 1;
                        }
                        else{
                            wrongid.setValue(1);
                            SharedModel.wrong_counter += 1;
                        }

                    }
                });
    }
}
