package com.db.structure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class TransactionGet extends Fragment implements onBackPressedListener{ //송금 정보가져오는 창
    TextView btnReceive, btnSend, btnTransDate, btnTransValue;

    @Override
    public void onBackPressed() {
        goToMain();
    }

    private void goToMain(){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().remove(TransactionGet.this).commit();
        fragmentManager.popBackStack();
    }

    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_transaction_get,container,false);

        btnReceive = v.findViewById(R.id.txtReceive);
        btnSend = v.findViewById(R.id.txtSend);
        btnTransDate = v.findViewById(R.id.txtTransDate);
        btnTransValue = v.findViewById(R.id.txtTransValue);



        return v;
    }
}