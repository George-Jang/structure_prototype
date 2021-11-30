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
import com.db.structure.requestDTO.CardCreateRequestDTO;
import com.db.structure.responseDTO.AccountResponse;
import com.db.structure.responseDTO.CardResponse;
import com.db.structure.responseDTO.CardType;
import com.db.structure.retrofit.MyApi;
import com.db.structure.retrofit.RetrofitHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EasyBanking_Deposit extends Fragment implements onBackPressedListener{
    EditText DepositValue;
    Button btnDeposit;

    private final  String TAG = getClass().getSimpleName();

    @Override
    public void onBackPressed() {
        goToMain();
    }

    private void goToMain(){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.none,R.anim.exit_to_right);
        ft.remove(EasyBanking_Deposit.this);
        fragmentManager.popBackStack();
        ft.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_easy_banking__deposit,container,false);

        btnDeposit = v.findViewById(R.id.btnDepositGo);
        DepositValue = v.findViewById(R.id.txtDepositValue);

        btnDeposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BankingRequestDTO bankingRequestDTO = new BankingRequestDTO(DepositValue.getText().toString());

                MyApi myApi = RetrofitHandler.generateMyApi("");

                Call<AccountResponse> accountResponseCall =myApi.deposit(bankingRequestDTO,String.valueOf(RetrofitHandler.accountId));
                accountResponseCall.enqueue(new Callback<AccountResponse>() {
                    @Override
                    public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getContext(),"입금 완료",Toast.LENGTH_LONG).show();
                            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                            FragmentTransaction ft = fragmentManager.beginTransaction();
                            ft.setCustomAnimations(R.anim.none,R.anim.exit_to_right);
                            ft.remove(EasyBanking_Deposit.this);
                            fragmentManager.popBackStack();
                            ft.commit();
                        }else{
                            Toast.makeText(getContext(),"잠시 후 다시 시도해주세요",Toast.LENGTH_LONG).show();
                            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                            FragmentTransaction ft = fragmentManager.beginTransaction();
                            ft.setCustomAnimations(R.anim.none,R.anim.exit_to_right);
                            ft.remove(EasyBanking_Deposit.this);
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
                        ft.setCustomAnimations(R.anim.none,R.anim.exit_to_right);
                        ft.remove(EasyBanking_Deposit.this);
                        fragmentManager.popBackStack();
                        ft.commit();
                    }
                });

            }
        });

        return v;
    }
}