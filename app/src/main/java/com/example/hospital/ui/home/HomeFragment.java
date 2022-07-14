package com.example.hospital.ui.home;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hospital.R;
import com.example.hospital.databinding.FragmentHomeBinding;
import com.example.hospital.models.ModelAuthCache;
import com.example.hospital.ui.Receptionist.calls.CallsFragment;
import com.example.hospital.ui.hr.Employee.EmployeeFragment;
import com.example.hospital.ui.settings.SettingsFragment;
import com.example.hospital.ui.splash.SplashFragment;
import com.example.hospital.utils.SharedModel;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    ArrayList<ModelAuthCache> caches = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentHomeBinding.bind(view);


        caches.add(new ModelAuthCache(SharedModel.getToken() , SharedModel.getType() , SharedModel.getUsername() , SharedModel.getPhone() , SharedModel.getEmail(),
                SharedModel.getGender() , SharedModel.getBirth() , SharedModel.getAddress() ,SharedModel.getStatus()));
        binding.typeTxt.setText("Specialist , "+SharedModel.getType());
        binding.userTxt.setText(SharedModel.getUsername());
        Cache();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void Cache() {
        SharedModel.cache(caches);
        check();
    }

    private void check(){
        if(SharedModel.getType().equals("hr")){
            getHrHome();
        }
        else if(SharedModel.getType().equals("receptionist")){
            getReceptionistHome();
        }
    }
    private void getHrHome(){
        int resId = getContext().getResources().getIdentifier("user", "drawable", getContext().getPackageName());
        binding.drImg.setImageResource(resId);
        binding.cardh5.setVisibility(View.GONE);
        //onclicks
        binding.drImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame , new SettingsFragment() , "st")
                        .addToBackStack("st").commit();
            }
        });
        binding.cardh1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame , new EmployeeFragment(), "emp")
                        .addToBackStack("emp").commit();
            }
        });
    }
    @SuppressLint("ResourceAsColor")
    private void getReceptionistHome(){

        int resId = getContext().getResources().getIdentifier("user", "drawable", getContext().getPackageName());
        binding.drImg.setImageResource(resId);
        binding.cardh5.setVisibility(View.GONE);

        int resId2 = getContext().getResources().getIdentifier("calls", "drawable", getContext().getPackageName());
        binding.img1.setImageResource(resId2);
        binding.btn1.setBackgroundColor(Color.rgb(95,158,220));
        binding.txt1.setText("Calls");

        //onclicks
        binding.drImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame , new SettingsFragment() , "st")
                        .addToBackStack("st").commit();
            }
        });
        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame , new CallsFragment(), "call")
                        .addToBackStack("call").commit();
            }
        });

    }
}