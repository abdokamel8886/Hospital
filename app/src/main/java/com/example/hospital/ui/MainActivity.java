package com.example.hospital.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.hospital.R;
import com.example.hospital.databinding.ActivityMainBinding;
import com.example.hospital.local.MyRoomDatabase;
import com.example.hospital.models.ModelAuthCache;
import com.example.hospital.ui.home.HomeFragment;
import com.example.hospital.ui.splash.SplashFragment;
import com.example.hospital.utils.SharedModel;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getcache();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void splash(){
        if(SharedModel.cache == false){
            getSupportFragmentManager().beginTransaction().replace(R.id.frame , new SplashFragment()).commit();
        }
        else{
            getSupportFragmentManager().beginTransaction().replace(binding.frame.getId(),new HomeFragment()).commit();
        }

    }

    private void getcache(){
        MyRoomDatabase.getInstance().getDao().getall()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<ModelAuthCache>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull List<ModelAuthCache> modelAuthCaches) {


                        if(modelAuthCaches.size()>0){
                            SharedModel.cache = true;
                            SharedModel.setToken(modelAuthCaches.get(0).getToken());
                            SharedModel.setType(modelAuthCaches.get(0).getType());
                            SharedModel.setUsername(modelAuthCaches.get(0).getUsername());
                            SharedModel.setEmail(modelAuthCaches.get(0).getEmail());
                            SharedModel.setPhone(modelAuthCaches.get(0).getPhone());
                            SharedModel.setBirth(modelAuthCaches.get(0).getBirth());
                            SharedModel.setGender(modelAuthCaches.get(0).getGender());
                            SharedModel.setStatus(modelAuthCaches.get(0).getStatus());
                            SharedModel.setAddress(modelAuthCaches.get(0).getAddress());
                            splash();
                        }
                        else{
                            splash();
                        }


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        splash();
                    }
                });
    }
}