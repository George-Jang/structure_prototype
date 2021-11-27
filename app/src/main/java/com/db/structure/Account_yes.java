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
