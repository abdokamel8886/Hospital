package com.example.hospital.ui.settings;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hospital.R;
import com.example.hospital.databinding.FragmentSettingsBinding;
import com.example.hospital.models.ModelAuthCache;
import com.example.hospital.ui.splash.SplashFragment;
import com.example.hospital.utils.SharedModel;

import java.util.ArrayList;


public class SettingsFragment extends Fragment {

    FragmentSettingsBinding binding;
    ArrayList<ModelAuthCache> caches = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentSettingsBinding.bind(view);
        caches.add(new ModelAuthCache(SharedModel.getToken() , SharedModel.getType() , SharedModel.getUsername() , SharedModel.getPhone() , SharedModel.getEmail(),
                SharedModel.getGender() , SharedModel.getBirth() , SharedModel.getAddress() ,SharedModel.getStatus()));

        onClicks();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding  = null;
    }

    private void onClicks(){

        binding.genderTxt.setText(SharedModel.getGender());
        binding.SpecialistTxt.setText("Specialist "+SharedModel.getType());
        binding.birthTxt.setText(SharedModel.getBirth());
        binding.adressTxt.setText(SharedModel.getAddress());
        binding.stateTxt.setText(SharedModel.getStatus());
        binding.mailTxt.setText(SharedModel.getEmail());
        binding.phoneTxt.setText(SharedModel.getPhone());
        binding.nameTxt.setText(SharedModel.getUsername());


        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        binding.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedModel.delete(caches.get(0));
                SharedModel.cache = false;
                FragmentManager fm = getActivity().getSupportFragmentManager();
                for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
                    fm.popBackStack();
                }
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, new SplashFragment()).commit();
            }
        });
    }

}