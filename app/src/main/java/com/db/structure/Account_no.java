package com.db.structure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.db.structure.requestDTO.AccountCreateRequestDTO;
import com.db.structure.responseDTO.AccountResponse;
import com.db.structure.retrofit.MyApi;
import com.db.structure.retrofit.RetrofitHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Account_no extends AppCompatActivity { // 계정이 없을 때
    Button btnRegister;
    public static Activity secondActivity;
    EditText txtPw,txtType, txtAdd, txtBirth, txtEmail, txtJob, txtName, txtId, txtPhone;
    private final  String TAG = getClass().getSimpleName();

    public void onBackPressed() {
        Intent intent = new Intent(Account_no.this,MainActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no);

        overridePendingTransition(R.anim.enter_from_right,R.anim.none);

        secondActivity = Account_no.this;

        MainActivity firstActivity = (MainActivity)MainActivity.firstActivity;
        firstActivity.finish();

        txtPw = findViewById(R.id.txtPw);
        txtType = findViewById(R.id.txtType);
        txtAdd = findViewById(R.id.txtAdd);
        txtBirth = findViewById(R.id.txtBirth);
        txtEmail = findViewById(R.id.txtEmail);
        txtJob = findViewById(R.id.txtJob);
        txtName = findViewById(R.id.txtName);
        txtId = findViewById(R.id.txtId);
        txtPhone = findViewById(R.id.txtPhone);

        btnRegister = (Button)findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);

                Retrofit retrofit = RetrofitHandler.generateRetrofit(txtPw.getText().toString());

                MyApi myApi = retrofit.create(MyApi.class);

                AccountCreateRequestDTO accountRequestBody = AccountCreateRequestDTO.builder()
                        .birthday(txtBirth)
                        .address(txtAdd)
                        .accountType(txtType)
                        .name(txtName)
                        .phoneNum(txtPhone)
                        .job(txtJob)
                        .accountPassword(txtPw)
                        .userId(txtId)
                        .email(txtEmail)
                        .build();

                Call<AccountResponse> accountResponseCall = myApi.createAccount(accountRequestBody);
                accountResponseCall.enqueue(new Callback<AccountResponse>() {
                    @Override
                    public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                        if(response.isSuccessful()){
                            RetrofitHandler.accountId = response.body().getId();
                            Integer activity = 0;
                            intent.putExtra("Activity",activity);
                            startActivity(intent);
                        }else{
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
    }
}
