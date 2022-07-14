package com.example.hospital.ui.hr.Employee;

import android.annotation.SuppressLint;
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

import com.example.hospital.R;
import com.example.hospital.adapters.CallsRecyclerAdapter;
import com.example.hospital.adapters.EmployeesRecyclerAdapter;
import com.example.hospital.databinding.FragmentEmployeeBinding;
import com.example.hospital.models.ModelCall;
import com.example.hospital.models.ModelEmployee;
import com.example.hospital.ui.Receptionist.details.CallsDetailsFragment;
import com.example.hospital.ui.home.HomeFragment;
import com.example.hospital.ui.hr.Reg.RegFragment;
import com.example.hospital.utils.SharedModel;

import java.util.ArrayList;


public class EmployeeFragment extends Fragment {

    FragmentEmployeeBinding binding;

    EmployeeViewModel viewModel;

    EmployeesRecyclerAdapter adapter = new EmployeesRecyclerAdapter();

    ArrayList<ModelEmployee.DataDTO> list =new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_employee, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentEmployeeBinding.bind(view);
        viewModel  = new ViewModelProvider(this).get(EmployeeViewModel.class);
        onClicks();
        start();
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
        binding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame , new RegFragment(), "reg").addToBackStack("reg").commit();
            }
        });

        binding.doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start();
            }
        });
        binding.hr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.doctor.setBackgroundColor(getResources().getColor(R.color.white));
                binding.Nurse.setBackgroundColor(getResources().getColor(R.color.white));
                binding.Analysis.setBackgroundColor(getResources().getColor(R.color.white));
                binding.hr.setBackgroundColor(getResources().getColor(R.color.color1));
                binding.manger.setBackgroundColor(getResources().getColor(R.color.white));
                binding.receptionist.setBackgroundColor(getResources().getColor(R.color.white));
                binding.all.setBackgroundColor(getResources().getColor(R.color.white));
                getData("hr");
            }
        });
        binding.Nurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.doctor.setBackgroundColor(getResources().getColor(R.color.white));
                binding.Nurse.setBackgroundColor(getResources().getColor(R.color.color1));
                binding.Analysis.setBackgroundColor(getResources().getColor(R.color.white));
                binding.hr.setBackgroundColor(getResources().getColor(R.color.white));
                binding.manger.setBackgroundColor(getResources().getColor(R.color.white));
                binding.receptionist.setBackgroundColor(getResources().getColor(R.color.white));
                binding.all.setBackgroundColor(getResources().getColor(R.color.white));
                getData("Nurse");
            }
        });
        binding.Analysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.doctor.setBackgroundColor(getResources().getColor(R.color.white));
                binding.Nurse.setBackgroundColor(getResources().getColor(R.color.white));
                binding.Analysis.setBackgroundColor(getResources().getColor(R.color.color1));
                binding.hr.setBackgroundColor(getResources().getColor(R.color.white));
                binding.manger.setBackgroundColor(getResources().getColor(R.color.white));
                binding.receptionist.setBackgroundColor(getResources().getColor(R.color.white));
                binding.all.setBackgroundColor(getResources().getColor(R.color.white));
                getData("Analysis");
            }
        });
        binding.receptionist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.doctor.setBackgroundColor(getResources().getColor(R.color.white));
                binding.Nurse.setBackgroundColor(getResources().getColor(R.color.white));
                binding.Analysis.setBackgroundColor(getResources().getColor(R.color.white));
                binding.hr.setBackgroundColor(getResources().getColor(R.color.white));
                binding.manger.setBackgroundColor(getResources().getColor(R.color.white));
                binding.receptionist.setBackgroundColor(getResources().getColor(R.color.color1));
                binding.all.setBackgroundColor(getResources().getColor(R.color.white));
                getData("receptionist");
            }
        });
        binding.manger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.doctor.setBackgroundColor(getResources().getColor(R.color.white));
                binding.Nurse.setBackgroundColor(getResources().getColor(R.color.white));
                binding.Analysis.setBackgroundColor(getResources().getColor(R.color.white));
                binding.hr.setBackgroundColor(getResources().getColor(R.color.white));
                binding.manger.setBackgroundColor(getResources().getColor(R.color.color1));
                binding.all.setBackgroundColor(getResources().getColor(R.color.white));
                binding.receptionist.setBackgroundColor(getResources().getColor(R.color.white));
                getData("manger");
            }
        });
        binding.all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getall();
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

    }

    private void start(){
        binding.doctor.setBackgroundColor(getResources().getColor(R.color.color1));
        binding.Nurse.setBackgroundColor(getResources().getColor(R.color.white));
        binding.Analysis.setBackgroundColor(getResources().getColor(R.color.white));
        binding.hr.setBackgroundColor(getResources().getColor(R.color.white));
        binding.manger.setBackgroundColor(getResources().getColor(R.color.white));
        binding.receptionist.setBackgroundColor(getResources().getColor(R.color.white));
        binding.all.setBackgroundColor(getResources().getColor(R.color.white));
        getData("doctor");
    }

    private void getData(String type){
        binding.bar.setVisibility(View.VISIBLE);
        viewModel.getData(type);
        viewModel.employees.observe(getViewLifecycleOwner(), new Observer<ArrayList<ModelEmployee.DataDTO>>() {
            @Override
            public void onChanged(ArrayList<ModelEmployee.DataDTO> dataDTOS) {
                binding.bar.setVisibility(View.GONE);
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