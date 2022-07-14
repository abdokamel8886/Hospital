package com.example.hospital.ui.splash;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hospital.R;
import com.example.hospital.databinding.FragmentSplashBinding;
import com.example.hospital.ui.auth.Login.loginFragment;
import com.example.hospital.utils.SharedModel;


public class SplashFragment extends Fragment {

    FragmentSplashBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentSplashBinding.bind(view);
        onClicks();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void onClicks(){
        binding.HR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedModel.setType("hr");
                requireActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.frame , new loginFragment() , "login")
                        .addToBackStack("login").commit();
            }
        });
        binding.Receptionist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedModel.setType("receptionist");
                requireActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.frame , new loginFragment() , "login")
                        .addToBackStack("login").commit();
            }
        });

        binding.Nurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedModel.setType("Nurse");
                requireActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.frame , new loginFragment() , "login")
                        .addToBackStack("login").commit();
            }
        });
        binding.AnalysisEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedModel.setType("Analysis");
                requireActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.frame , new loginFragment() , "login")
                        .addToBackStack("login").commit();
            }
        });
        binding.doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedModel.setType("doctor");
                requireActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.frame , new loginFragment() , "login")
                        .addToBackStack("login").commit();
            }
        });
        binding.Manger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedModel.setType("manger");
                requireActivity().getSupportFragmentManager()
                        .beginTransaction().replace(R.id.frame , new loginFragment() , "login")
                        .addToBackStack("login").commit();
            }
        });


    }
}