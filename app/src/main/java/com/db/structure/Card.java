package com.db.structure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Card extends Fragment { // 카드 버튼 누르는 창
    Button btnCardMake,btnCardDelete,btnCardLook;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_card,container,false);

        btnCardMake = v.findViewById(R.id.btnCardMake);
        btnCardDelete = v.findViewById(R.id.btnCardDelete);
        btnCardLook = v.findViewById(R.id.btnCardLook);

        btnCardMake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putString("BtnSelected", "card_post");

                Authentication auth = new Authentication();
                auth.setArguments(data);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,
                        R.anim.enter_from_left,R.anim.exit_to_right);

                fragmentTransaction.replace(R.id.main_frame,auth);
                fragmentTransaction.addToBackStack("cardPost");
                fragmentTransaction.commit();
            }
        });

        btnCardDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putString("BtnSelected", "card_delete");

                Authentication auth = new Authentication();
                auth.setArguments(data);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,
                        R.anim.enter_from_left,R.anim.exit_to_right);

                fragmentTransaction.replace(R.id.main_frame,auth);
                fragmentTransaction.addToBackStack("cardDelete");
                fragmentTransaction.commit();
            }
        });

        btnCardLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle data = new Bundle();
                data.putString("BtnSelected", "card_get");

                Authentication auth = new Authentication();
                auth.setArguments(data);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

                fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,
                        R.anim.enter_from_left,R.anim.exit_to_right);

                fragmentTransaction.replace(R.id.main_frame,auth);
                fragmentTransaction.addToBackStack("cardGet");
                fragmentTransaction.commit();
            }
        });

        return v;
    }
}