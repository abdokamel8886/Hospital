package com.example.hospital.ui.auth.Login;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hospital.R;
import com.example.hospital.databinding.FragmentLoginBinding;
import com.example.hospital.ui.home.HomeFragment;
import com.example.hospital.utils.SharedModel;


public class loginFragment extends Fragment {

    FragmentLoginBinding binding;
    LoginViewModel loginViewModel;
    int wrongid =0;
    int rightid = 0;

    private ProgressDialog progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = FragmentLoginBinding.bind(view);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);



        onClicks();
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void onClicks(){
        // disable dismiss by tapping outside of the dialog


        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Check();
                showLoadingDialog();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        dismissLoadingDialog();
    }

    private void Check(){
        String Email = binding.emailEdit.getText().toString().trim();
        String Password = binding.passEdit.getText().toString().trim();

        if(Email.equals("")){
            binding.emailEdit.setError("required");
        }
        else if(Password.equals("")){
            binding.passEdit.setError("required");
        }
        else{
            login(Email,Password);
        }

    }

    private void login(String Email , String Password){

        loginViewModel.login(Email,Password);

        loginViewModel.wrongid.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(wrongid != integer){
                    wrongid = integer;
                    Toast.makeText(requireContext(), SharedModel.wrong_Message , Toast.LENGTH_SHORT).show();
                    dismissLoadingDialog();
                }
            }
        });

        loginViewModel.status.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(rightid != integer){
                    rightid = integer;
                    if(SharedModel.getType().equalsIgnoreCase(SharedModel.getLogin_type())){
                        Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                        dismissLoadingDialog();
                        requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame , new HomeFragment() , "home").addToBackStack("home").commit();
                    }
                    else{
                        Toast.makeText(getContext(), "Wrong Account Type", Toast.LENGTH_SHORT).show();
                        dismissLoadingDialog();

                    }

                }

            }
        });
    }
}