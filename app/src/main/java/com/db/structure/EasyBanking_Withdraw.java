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

import com.db.structure.requestDTO.BankingRequestDTO;
import com.db.structure.responseDTO.AccountResponse;
import com.db.structure.retrofit.MyApi;
import com.db.structure.retrofit.RetrofitHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EasyBanking_Withdraw extends Fragment implements onBackPressedListener{
    EditText withdrawValue;
    Button btnWithdraw;

    private final  String TAG = getClass().getSimpleName();

    @Override
    public void onBackPressed() {
        goToMain();
    }

    private void goToMain(){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.none,R.anim.exit_to_right);
        ft.remove(EasyBanking_Withdraw.this);
        fragmentManager.popBackStack();
        ft.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_easy_banking__withdraw,container,false);

        btnWithdraw = v.findViewById(R.id.btnWithdrawGo);
        withdrawValue = v.findViewById(R.id.txtWithdrawValue);

        String pwd = getArguments().getString("pwd");



        btnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                BankingRequestDTO bankingRequestDTO = new BankingRequestDTO(withdrawValue.getText().toString());

                MyApi myApi = RetrofitHandler.generateMyApi(pwd);

                Call<AccountResponse> accountResponseCall =myApi.withdraw(bankingRequestDTO,String.valueOf(RetrofitHandler.accountId));
                accountResponseCall.enqueue(new Callback<AccountResponse>() {
                    @Override
                    public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getContext(),"출금 완료",Toast.LENGTH_LONG).show();
                            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                            FragmentTransaction ft = fragmentManager.beginTransaction();
                            ft.setCustomAnimations(R.anim.none,R.anim.exit_to_right);
                            ft.remove(EasyBanking_Withdraw.this);
                            fragmentManager.popBackStack();
                            ft.commit();
                        }else{
                            if(response.code()==404){
                                Toast.makeText(getContext(), "비밀번호를 입력해주세요.", Toast.LENGTH_LONG).show();
                                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                                FragmentTransaction ft = fragmentManager.beginTransaction();
                                ft.setCustomAnimations(R.anim.none, R.anim.exit_to_right);
                                ft.remove(EasyBanking_Withdraw.this);
                                fragmentManager.popBackStack();
                                ft.commit();
                            }else if(response.code()==400){
                                Toast.makeText(getContext(), "잔액이 부족합니다.", Toast.LENGTH_LONG).show();
                                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                                FragmentTransaction ft = fragmentManager.beginTransaction();
                                ft.setCustomAnimations(R.anim.none, R.anim.exit_to_right);
                                ft.remove(EasyBanking_Withdraw.this);
                                fragmentManager.popBackStack();
                                ft.commit();
                            }else if(response.code() == 403){
                                Toast.makeText(getContext(), "비번 오류.", Toast.LENGTH_LONG).show();
                                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                                FragmentTransaction ft = fragmentManager.beginTransaction();
                                ft.setCustomAnimations(R.anim.none, R.anim.exit_to_right);
                                ft.remove(EasyBanking_Withdraw.this);
                                fragmentManager.popBackStack();
                                ft.commit();
                            }else {
                                Toast.makeText(getContext(), "잠시 후 다시 시도해주세요", Toast.LENGTH_LONG).show();
                                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                                FragmentTransaction ft = fragmentManager.beginTransaction();
                                ft.setCustomAnimations(R.anim.none, R.anim.exit_to_right);
                                ft.remove(EasyBanking_Withdraw.this);
                                fragmentManager.popBackStack();
                                ft.commit();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AccountResponse> call, Throwable t) {
                        Log.d(TAG,"Fail msg : " + t.getMessage());
                        Toast.makeText(getContext(),"다시 시도 ㄱ ㄱ",Toast.LENGTH_LONG).show();
                        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.setCustomAnimations(R.anim.none,R.anim.exit_to_right);
                        ft.remove(EasyBanking_Withdraw.this);
                        fragmentManager.popBackStack();
                        ft.commit();
                    }
                });

            }
        });

        return v;
    }
}