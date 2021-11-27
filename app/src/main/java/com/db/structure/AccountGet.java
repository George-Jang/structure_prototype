package com.db.structure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.db.structure.responseDTO.AccountResponse;
import com.db.structure.retrofit.MyApi;
import com.db.structure.retrofit.RetrofitHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AccountGet extends Fragment implements onBackPressedListener{ // 계좌 조회
    private final  String TAG = getClass().getSimpleName();

    @Override
    public void onBackPressed() {
        goToMain();
    }

    private void goToMain(){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.none,R.anim.exit_to_right);
        ft.remove(AccountGet.this);
        fragmentManager.popBackStack();
        ft.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_get_account, container, false);


        String pwd = getArguments().getString("pwd");

        MyApi myApi = RetrofitHandler.generateMyApi(pwd);

        Call<AccountResponse> accountResponseCall = myApi.getAccount(String.valueOf(RetrofitHandler.accountId));
        accountResponseCall.enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if(response.isSuccessful()){
                    AccountResponse accountResponse = response.body();


                    TextView accountId = view.findViewById(R.id.accountId);
                    TextView accountType = view.findViewById(R.id.accountType);
                    TextView accountIssuedDate = view.findViewById(R.id.accountIssuedDate);
                    TextView accountOwner = view.findViewById(R.id.accountOwner);
                    TextView cardIssued = view.findViewById(R.id.cardIssued);

                    accountId.setText("계좌 번호 : " + accountResponse.getId());
                    accountType.setText("계좌 타입 : "+ accountResponse.getType());
                    accountIssuedDate.setText("계좌 생성일 : "+ accountResponse.getAccountIssueDate());
                    accountOwner.setText("계좌 소유자 : "+accountResponse.getUser().getName());
                    cardIssued.setText("카드 발급 여부 : "+ ((accountResponse.isCardIssued()) ? "O" : "X"));


                }else{
                    Toast.makeText(getContext(),"비밀번호 오류",Toast.LENGTH_LONG).show();
                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.setCustomAnimations(R.anim.none, R.anim.exit_to_right);
                    ft.remove(AccountGet.this);
                    fragmentManager.popBackStack();
                    ft.commit();
                }
            }

            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {
                Log.d(TAG,"Fail msg : " + t.getMessage());
                Toast.makeText(getContext(),"다시 시도 ㄱ ㄱ",Toast.LENGTH_LONG).show();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.setCustomAnimations(R.anim.none, R.anim.exit_to_right);
                ft.remove(AccountGet.this);
                fragmentManager.popBackStack();
                ft.commit();
            }
        });




        return view;
    }
}