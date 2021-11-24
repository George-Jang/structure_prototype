package com.db.structure;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.db.structure.R;

public class MainActivity extends AppCompatActivity { // 처음 뜨는 창

    Button btnYes,btnNo;
    public static Activity firstActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstActivity = MainActivity.this;

        btnYes = (Button)findViewById(R.id.btnYes);
        btnNo = (Button)findViewById(R.id.btnNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.db.structure.Account_yes.class);
                startActivity(intent);
            }
        });
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), com.db.structure.Account_no.class);
                startActivity(intent);
            }
        });
    }
}