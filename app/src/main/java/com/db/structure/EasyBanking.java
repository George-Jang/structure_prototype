package com.db.structure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class EasyBanking extends Fragment {
    Button btnTrans, btnHistory, btnDeposit, btnWithdraw; // 송금버튼, 케밥메뉴(점 세개 있는거), 입금버튼, 출금버튼

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_easy_banking,container,false);

        btnTrans = v.findViewById(R.id.btnBankTrans);
        btnHistory = v.findViewById(R.id.btnBankHistory);
        btnDeposit = v.findViewById(R.id.btnBankDeposit);
        btnWithdraw = v.findViewById(R.id.btnBankWithdraw);

        btnTrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,
                        R.anim.enter_from_left,R.anim.exit_to_right);

                fragmentTransaction.replace(R.id.main_frame,new Transaction());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,
                        R.anim.enter_from_left,R.anim.exit_to_right);

                fragmentTransaction.replace(R.id.main_frame,new EasyBanking_History());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,
                        R.anim.enter_from_left,R.anim.exit_to_right);

                fragmentTransaction.replace(R.id.main_frame,new EasyBanking_Deposit());
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        btnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putString("BtnSelected", "EasyBanking_Withdraw");

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