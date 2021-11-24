package com.db.structure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Account extends Fragment { // 계좌 버튼 누르는 창
    Button btnGet, btnDelete, btnPatch;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home,container,false);

        btnGet = v.findViewById(R.id.btnGet);  // 계좌 조회
        btnDelete = v.findViewById(R.id.btnDelete); // 계좌 탈퇴
        btnPatch = v.findViewById(R.id.btnUpdate); // 비밀번호 변경

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putString("BtnSelected", "account_get");

                com.db.structure.Authentication auth = new com.db.structure.Authentication();
                auth.setArguments(data);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frame,auth);
                fragmentTransaction.addToBackStack("accountGet");
                fragmentTransaction.commit();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putString("BtnSelected", "account_delete");

                com.db.structure.Authentication auth = new com.db.structure.Authentication();
                auth.setArguments(data);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frame,auth);
                fragmentTransaction.addToBackStack("accountDelete");
                fragmentTransaction.commit();
            }
        });

        btnPatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putString("BtnSelected", "account_patch");

                com.db.structure.Authentication auth = new com.db.structure.Authentication();
                auth.setArguments(data);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_frame,auth);
                fragmentTransaction.addToBackStack("accountPatch");
                fragmentTransaction.commit();
            }
        });

        return v;
    }
}