package com.db.structure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.db.structure.responseDTO.CardListResponse;
import com.db.structure.responseDTO.CardResponse;
import com.db.structure.responseDTO.CardType;
import com.db.structure.retrofit.MyApi;
import com.db.structure.retrofit.RetrofitHandler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CardGet extends Fragment implements onBackPressedListener{ // 카드 정보 조회
    private final  String TAG = getClass().getSimpleName();

    @Override
    public void onBackPressed() {
        goToMain();
    }

    private void goToMain(){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.none,R.anim.exit_to_right);
        ft.remove(CardGet.this);
        fragmentManager.popBackStack();
        ft.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_get_card, container, false);

        String pwd = getArguments().getString("pwd");

        MyApi myApi = RetrofitHandler.generateMyApi(pwd);

        Call<CardListResponse> cardListResponseCall = myApi.getCardList(String.valueOf(RetrofitHandler.accountId));
        cardListResponseCall.enqueue(new Callback<CardListResponse>() {
            @Override
            public void onResponse(Call<CardListResponse> call, Response<CardListResponse> response) {
                if(response.isSuccessful()){
                    LinearLayout layout = v.findViewById(R.id.cardLinear);
                    EditText cardId,cardIssueDate, cardLimit, cardType;

                    CardListResponse cardListResponse = response.body();


                    for (CardResponse cardResponse: cardListResponse.getCardResponses()) {
                        cardId = new EditText(getContext());
                        cardId.setText("카드 번호 : " +cardResponse.getCardId());

                        cardIssueDate = new EditText(getContext());
                        cardIssueDate.setText("카드 생성 날짜 : "+cardResponse.getCardIssueDate());

                        cardLimit = new EditText(getContext());
                        cardLimit.setText("카드 한도 : "+cardResponse.getCardLimit());

                        cardType = new EditText(getContext());
                        cardType.setText("카드 종류 : " +((cardResponse.getCardType().equals(CardType.DEBIT)) ? "직불 카드":"신용 카드"));

                        layout.addView(cardId);
                        layout.addView(cardIssueDate);
                        layout.addView(cardLimit);
                        layout.addView(cardType);
                    }


                }else{
                    Log.d(TAG,"Status Code : " + response.code());
                    Toast.makeText(getContext(),"비밀번호 오류",Toast.LENGTH_LONG).show();
                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.setCustomAnimations(R.anim.none, R.anim.exit_to_right);
                    ft.remove(CardGet.this);
                    fragmentManager.popBackStack();
                    ft.commit();
                }
            }

            @Override
            public void onFailure(Call<CardListResponse> call, Throwable t) {
                Log.d(TAG,"Fail msg : " + t.getMessage());
                Toast.makeText(getContext(),"다시 시도 ㄱ ㄱ",Toast.LENGTH_LONG).show();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.setCustomAnimations(R.anim.none, R.anim.exit_to_right);
                ft.remove(CardGet.this);
                fragmentManager.popBackStack();
                ft.commit();
            }
        });





        return v;
    }
}