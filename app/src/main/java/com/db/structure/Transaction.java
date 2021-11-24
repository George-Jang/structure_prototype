package com.db.structure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class Transaction extends Fragment { // 송금 창
    Button btnTrans;
    EditText txtAccount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_transaction,container,false);

        btnTrans = v.findViewById(R.id.btnTrans);
        txtAccount = v.findViewById(R.id.txtAccount);


        btnTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putString("BtnSelected", "Trans");

                Authentication auth = new Authentication();
                auth.setArguments(data);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frame,auth);
                fragmentTransaction.addToBackStack("fragTrans");
                fragmentTransaction.commit();
            }
        });

        return v;
    }
}