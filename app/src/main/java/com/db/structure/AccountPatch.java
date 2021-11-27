package com.db.structure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.db.structure.requestDTO.AccountPasswordUpdateDTO;
import com.db.structure.responseDTO.AccountResponse;
import com.db.structure.retrofit.MyApi;
import com.db.structure.retrofit.RetrofitHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AccountPatch extends Fragment { // 비밀번호 변경
    EditText txt;
    Button btnNewPw; // 비밀번호 변경 버튼
    private final  String TAG = getClass().getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_patch_account,container,false);

        btnNewPw = v.findViewById(R.id.btnNewPw);
        txt = v.findViewById(R.id.auth_pw);

        String pwd = getArguments().getString("pwd");
        
        btnNewPw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AccountPasswordUpdateDTO passwordUpdateRequestBody = AccountPasswordUpdateDTO.builder().password(txt.getText().toString()).build();

                MyApi myApi = RetrofitHandler.generateMyApi(pwd);

                Call<AccountResponse> accountResponseCall = myApi.updateAccountPassword(String.valueOf(RetrofitHandler.accountId),passwordUpdateRequestBody);
                accountResponseCall.enqueue(new Callback<AccountResponse>() {
                    @Override
                    public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                        if(response.isSuccessful()) {
                            Toast.makeText(getContext(), "비밀번호 변경 완료", Toast.LENGTH_LONG).show();
                            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                            FragmentTransaction ft = fragmentManager.beginTransaction();
                            ft.setCustomAnimations(R.anim.none, R.anim.exit_to_right);
                            ft.remove(AccountPatch.this);
                            fragmentManager.popBackStack();
                            ft.commit();
                        }else{
                            Toast.makeText(getContext(), "비밀번호 다시 확인", Toast.LENGTH_LONG).show();
                            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                            FragmentTransaction ft = fragmentManager.beginTransaction();
                            ft.setCustomAnimations(R.anim.none, R.anim.exit_to_right);
                            ft.remove(AccountPatch.this);
                            fragmentManager.popBackStack();
                            ft.commit();
                            Log.d(TAG,"Status Code : " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<AccountResponse> call, Throwable t) {
                        Log.d(TAG,"Fail msg : " + t.getMessage());
                    }
                });

                
            }
        });

        return v;
    }
}