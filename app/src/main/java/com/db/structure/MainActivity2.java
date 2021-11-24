package com.db.structure;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity2 extends AppCompatActivity { // 메인창
    BottomNavigationView bottomNavigationView;
    private long lastTimeBackPressed;

    @Override
    public void onBackPressed() {

        //프래그먼트 onBackPressedListener사용
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        for(Fragment fragment : fragmentList){
            if(fragment instanceof onBackPressedListener){
                ((onBackPressedListener)fragment).onBackPressed();
                return;
            }
        }

        //두 번 클릭시 어플 종료
        if(System.currentTimeMillis() - lastTimeBackPressed < 1500){
            finish();
            return;
        }
        lastTimeBackPressed = System.currentTimeMillis();
        Toast.makeText(this,"'뒤로' 버튼을 한 번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Intent rxIntent = getIntent();
        Bundle extras = rxIntent.getExtras();
        if(extras != null){
            Integer rxActivity = extras.getInt("Activity");
            if(rxActivity == 0){
                Account_no account = (Account_no) Account_no.secondActivity;
                account.finish();
            }
            else if(rxActivity == 1){
                Account_yes account = (Account_yes) Account_yes.secondActivity;
                account.finish();
            }
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new Transaction()).commit();

        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNavi);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new Account()).commit();
                        break;
                    case R.id.menu_card:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new Card()).commit();
                        break;
                    case R.id.menu_transaction:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new Transaction()).commit();
                        break;
                }

                return true;
            }
        });
    }
}
