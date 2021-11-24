package com.db.structure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AccountGet extends Fragment implements onBackPressedListener{ // 계좌 조회

    @Override
    public void onBackPressed() {
        goToMain();
    }

    private void goToMain(){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().remove(AccountGet.this).commit();
        fragmentManager.popBackStack();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_get_account, container, false);
    }
}