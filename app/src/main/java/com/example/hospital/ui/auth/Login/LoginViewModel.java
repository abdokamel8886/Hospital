package com.example.hospital.ui.auth.Login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.hospital.models.LoginModel;
import com.example.hospital.network.RetroConnection;
import com.example.hospital.utils.SharedModel;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {


    MutableLiveData<Integer> status = new MutableLiveData<>();

    MutableLiveData<Integer> wrongid = new MutableLiveData<>();



    public void login(String Email , String Password ){

        RetroConnection.getServices().login(Email,Password,"")
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
                                SharedModel.setUsername(loginModel.getData().getFirst_name()+" "+loginModel.getData().getLast_name());
                                SharedModel.setToken(loginModel.getData().getAccess_token());
                                SharedModel.setAddress(loginModel.getData().getAddress());
                                SharedModel.setGender(loginModel.getData().getGender());
                                SharedModel.setBirth(loginModel.getData().getBirthday());
                                SharedModel.setStatus(loginModel.getData().getStatus());
                                SharedModel.setEmail(loginModel.getData().getEmail());
                                SharedModel.setPhone(loginModel.getData().getMobile());
                                SharedModel.setLogin_type(loginModel.getData().getType());
                                if(SharedModel.right_counter>0){
                                    status.setValue(1+SharedModel.right_counter);
                                    SharedModel.right_counter += 1;
                                }
                                else{
                                    status.setValue(1);
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


