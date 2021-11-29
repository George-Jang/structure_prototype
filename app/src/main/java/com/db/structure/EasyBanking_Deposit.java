package com.db.structure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class EasyBanking_Deposit extends Fragment implements onBackPressedListener{
    EditText DepositValue;
    Button btnDeposit;

    @Override
    public void onBackPressed() {
        goToMain();
    }

    private void goToMain(){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.none,R.anim.exit_to_right);
        ft.remove(EasyBanking_Deposit.this);
        fragmentManager.popBackStack();
        ft.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_easy_banking__deposit,container,false);

        btnDeposit = v.findViewById(R.id.btnDepositGo);
        DepositValue = v.findViewById(R.id.txtDepositValue);

        btnDeposit.setOnClickListener(new View.OnClickListener() { // TODO : 입금기능
            @Override
            public void onClick(View view) {

            }
        });

        return v;
    }
}