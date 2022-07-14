package com.example.hospital.ui.Receptionist.calls;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.hospital.R;
import com.example.hospital.adapters.CallsRecyclerAdapter;
import com.example.hospital.databinding.FragmentCallsBinding;
import com.example.hospital.models.ModelCall;
import com.example.hospital.ui.Receptionist.add.CreateCallFragment;
import com.example.hospital.ui.Receptionist.details.CallsDetailsFragment;
import com.example.hospital.utils.SharedModel;

import java.util.ArrayList;
import java.util.Calendar;


public class CallsFragment extends Fragment {


    FragmentCallsBinding binding;
    CallsViewModel viewModel;

    CallsRecyclerAdapter adapter = new CallsRecyclerAdapter();

    DatePickerDialog.OnDateSetListener setListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calls, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentCallsBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(CallsViewModel.class);
        getData("");
        onClicks();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void onClicks(){

        SharedModel.setSelected_dr_name("Select Doctor");

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        binding.datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDate();
            }
        });

        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame , new CreateCallFragment() , "create")
                        .addToBackStack("create").commit();
            }
        });



    }

    private void getData(String date){
        binding.bar.setVisibility(View.VISIBLE);
        viewModel.getData(date);
        viewModel.calls.observe(getViewLifecycleOwner(), new Observer<ArrayList<ModelCall.DataDTO>>() {
            @Override
            public void onChanged(ArrayList<ModelCall.DataDTO> dataDTOS) {
                binding.bar.setVisibility(View.GONE);
                show(dataDTOS);
            }
        });
    }

    private void getDate(){
        Calendar calendar =Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext() ,setListener,year,month,day
        );
        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
        datePickerDialog.show();

        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1+1;
                String Date=i+"-"+i1+"-"+i2;
                binding.date.setText(Date);
                getData(Date);
            }
        };

    }



    private void show(ArrayList<ModelCall.DataDTO> list){

        adapter.setList(list);
        binding.recyclerCalls.setAdapter(adapter);

        adapter.setOnItemClick(new CallsRecyclerAdapter.OnItemClick() {
            @Override
            public void OnClick(int id) {
                SharedModel.setCall_id(id);
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame , new CallsDetailsFragment() ,"d")
                        .addToBackStack("d").commit();
            }
        });

    }
}