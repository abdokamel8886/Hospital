package com.example.hospital.ui.hr.Reg;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
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
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.hospital.R;
import com.example.hospital.databinding.FragmentRegBinding;
import com.example.hospital.ui.home.HomeFragment;
import com.example.hospital.utils.SharedModel;

import java.util.Calendar;


public class RegFragment extends Fragment {


    FragmentRegBinding binding;
    String [] gender_items = {"male","female"};
    String [] sp_items = {"hr","manger","doctor","Analysis","Nurse","receptionist"};
    String [] status_items = {"single","married"};

    DatePickerDialog.OnDateSetListener setListener;
    String Date = "" ;

    int wrongid =0;
    int rightid = 0;


    RegViewModel viewModel;

    private ProgressDialog progress;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reg, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentRegBinding.bind(view);

        viewModel = new ViewModelProvider(this).get(RegViewModel.class);

        SharedModel.wrong_counter = 0;
        SharedModel.right_counter = 0;

        ArrayAdapter<String> Genderadapter = new ArrayAdapter(getContext(),R.layout.drop_item , gender_items);
        binding.genderEdit.setAdapter(Genderadapter);

        ArrayAdapter<String> spadapter = new ArrayAdapter(getContext(),R.layout.drop_item , sp_items);
        binding.SpecialistEdit.setAdapter(spadapter);

        ArrayAdapter<String> statusadapter = new ArrayAdapter(getContext(),R.layout.drop_item , status_items);
        binding.StatusEdit.setAdapter(statusadapter);



        onClicks();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        dismissLoadingDialog();
    }

    public void showLoadingDialog() {

        if (progress == null) {
            progress = new ProgressDialog(getContext());
            progress.setTitle("Loading");
            progress.setCancelable(false);
        }
        progress.show();
    }

    public void dismissLoadingDialog() {

        if (progress != null && progress.isShowing()) {
            progress.dismiss();
        }
    }

    private void onClicks(){
        Calendar calendar =Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().onBackPressed();
            }
        });

        binding.birthEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext() ,setListener,year,month,day
                );
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1+1;
                Date=i+"-"+i1+"-"+i2;
                binding.birthEdit.setText(Date);
            }
        };

        binding.createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                check();
            }
        });
    }


    private void check(){

        String fname = binding.fnameEdit.getText().toString().trim();
        String lname = binding.snameEdit.getText().toString().trim();
        String gender = binding.genderEdit.getText().toString().trim();
        String type = binding.SpecialistEdit.getText().toString().trim();
        String birth = binding.birthEdit.getText().toString().trim();
        String status = binding.StatusEdit.getText().toString().trim();
        String phone = binding.phoneEdit.getText().toString().trim();
        String email = binding.mailEdit.getText().toString().trim();
        String address = binding.adressEdit.getText().toString().trim();
        String password = binding.passEdit.getText().toString().trim();

        if (fname.equals("")){
            binding.fnameEdit.setError("Required");
        }
        else if (lname.equals("")){
            binding.snameEdit.setError("Required");
        }
        else if (gender.equals("")){
            binding.genderEdit.setError("Required");
        }
        else if (type.equals("")){
            binding.SpecialistEdit.setError("Required");
        }
        else if (status.equals("")){
            binding.StatusEdit.setError("Required");
        }
        else if (phone.equals("")){
            binding.phoneEdit.setError("Required");
        }
        else if (birth.equals("")){
            binding.birthEdit.setError("Required");
        }
        else if (email.equals("")){
            binding.mailEdit.setError("Required");
        }
        else if (address.equals("")){
            binding.adressEdit.setError("Required");
        }
        else if (password.equals("")){
            binding.passEdit.setError("Required");
        }
        else{
            showLoadingDialog();
            Reg(email ,password , fname ,lname , gender , birth , status , address , phone , type);
        }


    }

    private void Reg(String Email , String Password , String fname , String lname , String gender , String birth , String status , String Address , String Phone , String Type ) {

        viewModel.Reg(Email ,Password , fname ,lname , gender , birth , status , Address , Phone , Type);

        viewModel.Status.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(rightid != integer){
                    rightid = integer;
                    Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                    dismissLoadingDialog();
                    requireActivity().onBackPressed();

                }
            }
        });

        viewModel.wrongid.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(wrongid != integer){
                    wrongid = integer;
                    Toast.makeText(requireContext(), SharedModel.wrong_Message , Toast.LENGTH_SHORT).show();
                    dismissLoadingDialog();
                }
            }
        });

    }



    }