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
    EditText txtAccount,txtAmount,txtType,txtComment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_transaction,container,false);

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left,R.anim.none);

        btnTrans = v.findViewById(R.id.btnTrans);
        txtAccount = v.findViewById(R.id.txtAccount);
        txtAmount = v.findViewById(R.id.txtAmount);
        txtType = v.findViewById(R.id.txtType);
        txtComment = v.findViewById(R.id.txtComment);


        btnTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putString("BtnSelected", "Trans");
                data.putString("account",txtAccount.getText().toString());
                data.putString("amount",txtAmount.getText().toString());
                data.putString("type",txtType.getText().toString());
                data.putString("comment",txtComment.getText().toString());

                Authentication auth = new Authentication();
                auth.setArguments(data);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,
                        R.anim.enter_from_left,R.anim.exit_to_right);

                fragmentTransaction.replace(R.id.main_frame,auth);
                fragmentTransaction.addToBackStack("fragTrans");
                fragmentTransaction.commit();
            }
        });

        return v;
    }
}