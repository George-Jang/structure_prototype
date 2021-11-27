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
import android.widget.Toast;


public class CardPost extends Fragment implements  onBackPressedListener{ // 카드 만들기
    Button btnCardPost; // 카드 생성 버튼
    EditText txtCardPost1,txtCardPost2,txtCardPost3; // 1: 계좌 이름  2: 카드 한도  3: 카드 타입

    @Override
    public void onBackPressed() {
        goToMain();
    }

    private void goToMain(){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.none,R.anim.exit_to_right);
        ft.remove(CardPost.this);
        fragmentManager.popBackStack();
        ft.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_post_card,container,false);

        btnCardPost = v.findViewById(R.id.btnCardPost);
        btnCardPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"카드 생성 완료",Toast.LENGTH_LONG).show();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.setCustomAnimations(R.anim.none,R.anim.exit_to_right);
                ft.remove(CardPost.this);
                fragmentManager.popBackStack();
                ft.commit();
            }
        });

        return v;
    }
}