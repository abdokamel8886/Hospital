package com.example.hospital.ui.Receptionist.add;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hospital.R;
import com.example.hospital.databinding.FragmentCreateCallBinding;
import com.example.hospital.models.ModelCreateCall;
import com.example.hospital.ui.splash.SplashFragment;
import com.example.hospital.utils.SharedModel;


public class CreateCallFragment extends Fragment {


    FragmentCreateCallBinding binding;
    CreateCallViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_call, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentCreateCallBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(CreateCallViewModel.class);
        onClicks();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void onClicks(){
        binding.drEdit.setText(SharedModel.getSelected_dr_name());
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });
        binding.card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame , new SelectDoctorFragment() ,"dr")
                        .addToBackStack("dr").commit();
            }
        });
        binding.createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });

    }
    private void check(){

        String name = binding.panameEdit.getText().toString().trim();
        String age = binding.ageEdit.getText().toString().trim();
        String phone = binding.phoneEdit.getText().toString().trim();
        String dr = SharedModel.getSelected_dr_name();
        String des = binding.descriptionEdit.getText().toString().trim();

        if (name.equals("")){
            binding.panameEdit.setError("required");
        }
        else if (age.equals("")){
            binding.ageEdit.setError("required");
        }
        else if (phone.equals("")){
            binding.phoneEdit.setError("required");
        }
        else if(dr.equals("Select Doctor")){
            Toast.makeText(getContext(), "Please Select Doctor", Toast.LENGTH_SHORT).show();
        }
        else if (des.equals("")){
            binding.descriptionEdit.setError("required");
        }
        else{
            SendData(name,SharedModel.getSelected_dr_id() , age , phone ,des);
        }

    }
    private void SendData(String name , int id , String age , String phone , String des){

        viewModel.sendData(name, id, age, phone, des);
        viewModel.response.observe(getViewLifecycleOwner(), new Observer<ModelCreateCall>() {
            @Override
            public void onChanged(ModelCreateCall modelCreateCall) {
                if(modelCreateCall.getStatus() == 1){
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                    requireActivity().onBackPressed();
                    //requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame, new CreateSuccessFragment() ,"ss" ).addToBackStack("ss").commit();
                }
                else{
                    Toast.makeText(getContext(), modelCreateCall.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewModel.response2.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
            }
        });

    }
}