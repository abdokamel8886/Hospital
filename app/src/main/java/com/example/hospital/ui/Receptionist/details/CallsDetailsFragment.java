package com.example.hospital.ui.Receptionist.details;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hospital.R;
import com.example.hospital.databinding.FragmentCallsDetailsBinding;
import com.example.hospital.models.ModelCallDetails;
import com.example.hospital.ui.Receptionist.calls.CallsViewModel;


public class CallsDetailsFragment extends Fragment {

    FragmentCallsDetailsBinding binding;
    CallsDetailsViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calls_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentCallsDetailsBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(CallsDetailsViewModel.class);
        getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void  getData(){
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        viewModel.getData();
        viewModel.call.observe(getViewLifecycleOwner(), new Observer<ModelCallDetails>() {
            @Override
            public void onChanged(ModelCallDetails modelCallDetails) {
                binding.name.setText(modelCallDetails.getData().getPatient_name());
                binding.date.setText(modelCallDetails.getData().getCreated_at());
                binding.phone.setText(modelCallDetails.getData().getPhone());
                binding.status.setText(modelCallDetails.getData().getCase_status());
                binding.age.setText(modelCallDetails.getData().getAge());
                binding.description.setText(modelCallDetails.getData().getDescription());
            }
        });
    }
}