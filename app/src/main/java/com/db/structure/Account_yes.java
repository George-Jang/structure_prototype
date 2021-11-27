package com.db.structure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.db.structure.responseDTO.AccountResponse;
import com.db.structure.retrofit.MyApi;
import com.db.structure.retrofit.RetrofitHandler;

import java.util.Calendar;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Account_yes extends AppCompatActivity { // 계정이 있을 때
    Button btnLogin;
    EditText txtId, txtPw;
    public static Activity secondActivity;

    private final  String TAG = getClass().getSimpleName();

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Account_yes.this,MainActivity.class);
        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
        overridePendingTransition(R.anim.none,R.anim.exit_to_right);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yes);

        overridePendingTransition(R.anim.enter_from_right,R.anim.none);

        MainActivity firstActivity = (MainActivity)MainActivity.firstActivity;
        firstActivity.finish();

        secondActivity = Account_yes.this;

        btnLogin = (Button)findViewById(R.id.btnLogin);

        txtId = findViewById(R.id.txtId);
        txtPw = findViewById(R.id.txtPw);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);

                MyApi myApi = RetrofitHandler.generateMyApi(txtPw.getText().toString());

                Call<AccountResponse> accountResponseCall = myApi.getAccount(txtId.getText().toString());

                accountResponseCall.enqueue(new Callback<AccountResponse>() {
                    @Override
                    public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                        if(response.isSuccessful()){
                            RetrofitHandler.accountId = response.body().getId();
                            Integer activity = 1;
                            intent.putExtra("Activity",activity);
                            startActivity(intent);
                        }else{
                            System.out.println(response.message());
                            Toast.makeText(getApplicationContext(),"비밀번호 오류",Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<AccountResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(),"다시 시도 ㄱ ㄱ",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });





    }
}
