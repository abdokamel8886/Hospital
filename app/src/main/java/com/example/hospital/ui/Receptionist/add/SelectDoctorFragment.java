package com.example.hospital.ui.Receptionist.add;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hospital.R;
import com.example.hospital.adapters.CallsRecyclerAdapter;
import com.example.hospital.adapters.doctorsRecyclerAdapter;
import com.example.hospital.databinding.FragmentSelectDoctorBinding;
import com.example.hospital.models.ModelEmployee;
import com.example.hospital.ui.Receptionist.calls.CallsViewModel;
import com.example.hospital.ui.hr.Employee.EmployeeViewModel;
import com.example.hospital.utils.SharedModel;

import java.util.ArrayList;


public class SelectDoctorFragment extends Fragment {


    FragmentSelectDoctorBinding binding;

    EmployeeViewModel viewModel;

    doctorsRecyclerAdapter adapter = new doctorsRecyclerAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_select_doctor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding =FragmentSelectDoctorBinding.bind(view);
        viewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        onClicks();
        getData();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    private void onClicks(){
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });
        binding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        binding.selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
                //Toast.makeText(getContext(), "id : "+SharedModel.getSelected_dr_id() + " - name : " + SharedModel.getSelected_dr_name(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getData(){
        viewModel.getData("doctor");
        viewModel.employees.observe(getViewLifecycleOwner(), new Observer<ArrayList<ModelEmployee.DataDTO>>() {
            @Override
            public void onChanged(ArrayList<ModelEmployee.DataDTO> dataDTOS) {
                show(dataDTOS);
            }
        });
    }
    private void show(ArrayList<ModelEmployee.DataDTO> list){

        adapter.setList(list);
        binding.recycler.setAdapter(adapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        binding.recycler.addItemDecoration(dividerItemDecoration);


    }
}