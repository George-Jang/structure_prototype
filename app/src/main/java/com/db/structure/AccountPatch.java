package com.db.structure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AccountPatch extends Fragment { // 비밀번호 변경
    EditText txt;
    Button btnNewPw; // 비밀번호 변경 버튼

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_patch_account,container,false);

        btnNewPw = v.findViewById(R.id.btnNewPw);

        btnNewPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"비밀번호 변경 완료",Toast.LENGTH_LONG).show();
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().remove(AccountPatch.this).commit();
                manager.popBackStack();
            }
        });

        return v;
    }
}