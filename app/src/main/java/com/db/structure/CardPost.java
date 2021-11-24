package com.db.structure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class CardPost extends Fragment implements  onBackPressedListener{ // 카드 만들기
    Button btnCardPost;

    @Override
    public void onBackPressed() {
        goToMain();
    }

    private void goToMain(){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().remove(CardPost.this).commit();
        fragmentManager.popBackStack();
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
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().remove(CardPost.this).commit();
                manager.popBackStack();
            }
        });

        return v;
    }
}