package com.db.structure;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Authentication extends Fragment { // 비밀번호 입력창
    String btn;
    Button btnGo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle data = getArguments();
        if(data != null) {
            btn = data.getString("BtnSelected");
        }else{
            btn = "none";
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_authentication,container,false);
        btnGo = v.findViewById(R.id.btnAuth);

        btnGo.setOnClickListener(new View.OnClickListener() { // 전의 프래그먼트에 따라 이벤트가 달라짐
            @Override
            public void onClick(View view) {
                switch (btn){
                    case "Trans":

                        Toast.makeText(getContext(),"송금 완료",Toast.LENGTH_LONG).show();
                        TransactionGet trans = new TransactionGet();

                        FragmentTransaction fragmentTransaction6 = getFragmentManager().beginTransaction();
                        fragmentTransaction6.replace(R.id.main_frame,trans);
                        //fragmentTransaction3.addToBackStack("cardGet");
                        fragmentTransaction6.commit();

                        break;

                    case "account_get":

                        AccountGet account = new AccountGet();

                        FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();
                        fragmentTransaction1.replace(R.id.main_frame,account);
                        //fragmentTransaction.addToBackStack("accountGet");
                        fragmentTransaction1.commit();

                        break;
                    case "account_delete":
                        Toast.makeText(getContext(),"계정 탈퇴 완료",Toast.LENGTH_LONG).show();
                        FragmentManager manager1 = getActivity().getSupportFragmentManager();
                        manager1.beginTransaction().remove(Authentication.this).commit();
                        manager1.popBackStack();
                        break;

                    case "account_patch":
                        AccountPatch account2 = new AccountPatch();

                        FragmentTransaction fragmentTransaction2 = getFragmentManager().beginTransaction();
                        fragmentTransaction2.replace(R.id.main_frame,account2);
                        //fragmentTransaction2.addToBackStack("accountPatch");
                        fragmentTransaction2.commit();

                        break;
                    case "card_get":
                        CardGet card = new CardGet();

                        FragmentTransaction fragmentTransaction3 = getFragmentManager().beginTransaction();
                        fragmentTransaction3.replace(R.id.main_frame,card);
                        //fragmentTransaction3.addToBackStack("cardGet");
                        fragmentTransaction3.commit();

                        break;
                    case "card_delete":
                        Toast.makeText(getContext(),"카드 삭제 완료",Toast.LENGTH_LONG).show();
                        FragmentManager manager2 = getActivity().getSupportFragmentManager();
                        manager2.beginTransaction().remove(Authentication.this).commit();
                        manager2.popBackStack();
                        break;

                    case "card_post":
                        CardPost card2 = new CardPost();

                        FragmentTransaction fragmentTransaction5 = getFragmentManager().beginTransaction();
                        fragmentTransaction5.replace(R.id.main_frame,card2);
                        //fragmentTransaction5.addToBackStack("cardPost");
                        fragmentTransaction5.commit();

                        break;

                }
            }
        });
        return v;
    }

}