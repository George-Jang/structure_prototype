package com.db.structure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Account_yes extends AppCompatActivity { // 계정이 있을 때
    Button btnLogin;
    public static Activity secondActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yes);

        MainActivity firstActivity = (MainActivity)MainActivity.firstActivity;
        firstActivity.finish();

        secondActivity = Account_yes.this;

        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);

                Integer activity = 1;
                intent.putExtra("Activity",activity);
                startActivity(intent);
            }
        });
    }
}
