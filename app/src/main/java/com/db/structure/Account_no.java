package com.db.structure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Account_no extends AppCompatActivity { // 계정이 없을 때
    Button btnRegister;
    public static Activity secondActivity;

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

        btnRegister = (Button)findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity2.class);

                Integer activity = 0;
                intent.putExtra("Activity",activity);
                startActivity(intent);
            }
        });
    }
}
