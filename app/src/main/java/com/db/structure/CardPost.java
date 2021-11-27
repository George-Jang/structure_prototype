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

import com.db.structure.requestDTO.CardCreateRequestDTO;
import com.db.structure.responseDTO.AccountResponse;
import com.db.structure.responseDTO.CardResponse;
import com.db.structure.responseDTO.CardType;
import com.db.structure.retrofit.MyApi;
import com.db.structure.retrofit.RetrofitHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CardPost extends Fragment implements  onBackPressedListener{ // 카드 만들기
    Button btnCardPost; // 카드 생성 버튼
    EditText txtCardPost2,txtCardPost3; // 2: 카드 한도  3: 카드 타입

    private final  String TAG = getClass().getSimpleName();

    @Override
    public void onBackPressed() {
        goToMain();
    }

    private void goToMain(){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.none,R.anim.exit_to_right);
        ft.remove(CardPost.this);
        fragmentManager.popBackStack();
        ft.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_post_card,container,false);


        String pwd = getArguments().getString("pwd");

        txtCardPost2 = v.findViewById(R.id.txtCardPost2);
        txtCardPost3 = v.findViewById(R.id.txtCardPost3);

        btnCardPost = v.findViewById(R.id.btnCardPost);
        btnCardPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardCreateRequestDTO cardCreateRequestDTO = CardCreateRequestDTO.builder()
                        .accountId(RetrofitHandler.accountId)
                        .cardLimit(Long.valueOf(txtCardPost2.getText().toString()))
                        .cardType(txtCardPost3.getText().toString().contains("신용") || txtCardPost3.getText().toString().contains("credit")?CardType.CREDIT:CardType.DEBIT)
                        .build();

                MyApi myApi = RetrofitHandler.generateMyApi(pwd);

                Call<CardResponse> cardResponseCall =myApi.createCard(cardCreateRequestDTO);
                cardResponseCall.enqueue(new Callback<CardResponse>() {
                    @Override
                    public void onResponse(Call<CardResponse> call, Response<CardResponse> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getContext(),"카드 생성 완료",Toast.LENGTH_LONG).show();
                            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                            FragmentTransaction ft = fragmentManager.beginTransaction();
                            ft.setCustomAnimations(R.anim.none,R.anim.exit_to_right);
                            ft.remove(CardPost.this);
                            fragmentManager.popBackStack();
                            ft.commit();
                        }else{
                            Toast.makeText(getContext(),response.message(),Toast.LENGTH_LONG).show();
                            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                            FragmentTransaction ft = fragmentManager.beginTransaction();
                            ft.setCustomAnimations(R.anim.none,R.anim.exit_to_right);
                            ft.remove(CardPost.this);
                            fragmentManager.popBackStack();
                            ft.commit();
                        }
                    }

                    @Override
                    public void onFailure(Call<CardResponse> call, Throwable t) {
                        Log.d(TAG,"Fail msg : " + t.getMessage());
                        Toast.makeText(getContext(),"다시 시도 ㄱ ㄱ",Toast.LENGTH_LONG).show();
                        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                        FragmentTransaction ft = fragmentManager.beginTransaction();
                        ft.setCustomAnimations(R.anim.none,R.anim.exit_to_right);
                        ft.remove(CardPost.this);
                        fragmentManager.popBackStack();
                        ft.commit();
                    }
                });




            }
        });

        return v;
    }
}