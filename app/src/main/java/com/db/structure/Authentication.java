package com.db.structure;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.db.structure.requestDTO.TransactionCreateRequestDTO;
import com.db.structure.responseDTO.AccountResponse;
import com.db.structure.responseDTO.TransactionResponse;
import com.db.structure.retrofit.MyApi;
import com.db.structure.retrofit.RetrofitHandler;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Authentication extends Fragment implements onBackPressedListener { // 비밀번호 입력창
    String btn;
    Button btnGo;
    EditText authPw,deleteCardId;
    private final  String TAG = getClass().getSimpleName();

    public void onBackPressed() {
        goToMain();
    }

    private void goToMain(){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        //FragmentTransaction fragmentTransaction6 = getFragmentManager().beginTransaction();
        //fragmentTransaction6.setCustomAnimations(R.anim.none,R.anim.exit_to_right);

        fragmentManager.beginTransaction().remove(Authentication.this).commit();
        fragmentManager.popBackStack();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle data = getArguments();
        if(data != null) {
            btn = data.getString("BtnSelected");
        }else{
            btn = "none";
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_authentication,container,false);
        btnGo = v.findViewById(R.id.btnAuth);
        authPw = v.findViewById(R.id.auth_pw);
        deleteCardId = v.findViewById(R.id.deleteCardId);

        if(btn.equals("card_delete")){
            deleteCardId.setVisibility(View.VISIBLE);
        }

        btnGo.setOnClickListener(new View.OnClickListener() { // 전의 프래그먼트에 따라 이벤트가 달라짐
            @Override
            public void onClick(View view) {

                //TODO: pwd null 체크
                Bundle bundle = new Bundle();
                bundle.putString("pwd", authPw.getText().toString());

                MyApi myApi = RetrofitHandler.generateMyApi(authPw.getText().toString());


                switch (btn){
                    case "Trans":

                        TransactionCreateRequestDTO requestDTO = TransactionCreateRequestDTO.builder()
                                .comment(getArguments().getString("comment"))
                                .receivingAccount(getArguments().getString("account"))
                                .transactionValue(Long.valueOf(getArguments().getString("amount")))
                                .type(getArguments().getString("type"))
                                .sendingAccount(String.valueOf(RetrofitHandler.accountId)).build();

                        Call<TransactionResponse> responseCall = myApi.createTransaction(requestDTO);
                        responseCall.enqueue(new Callback<TransactionResponse>() {
                            @Override
                            public void onResponse(Call<TransactionResponse> call, Response<TransactionResponse> response) {
                                if(response.isSuccessful()) {
                                    Toast.makeText(getContext(), "송금 완료", Toast.LENGTH_LONG).show();
                                    TransactionGet trans = new TransactionGet();

                                    FragmentTransaction fragmentTransaction6 = getFragmentManager().beginTransaction();

                                    fragmentTransaction6.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                                            R.anim.none, R.anim.none);

                                    fragmentTransaction6.replace(R.id.main_frame, trans);
                                    //fragmentTransaction3.addToBackStack("cardGet");
                                    fragmentTransaction6.commit();
                                }else{
                                    Log.d(TAG,"error code : " + response.code());
                                    Toast.makeText(getContext(),"비밀번호 오류",Toast.LENGTH_LONG).show();
                                    TransactionGet trans = new TransactionGet();

                                    FragmentTransaction fragmentTransaction6 = getFragmentManager().beginTransaction();

                                    fragmentTransaction6.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                                            R.anim.none, R.anim.none);

                                    fragmentTransaction6.replace(R.id.main_frame, trans);
                                    //fragmentTransaction3.addToBackStack("cardGet");
                                    fragmentTransaction6.commit();
                                }
                            }

                            @Override
                            public void onFailure(Call<TransactionResponse> call, Throwable t) {
                                Log.d(TAG,"Fail msg : " + t.getMessage());
                                Toast.makeText(getContext(),"다시 시도 ㄱ ㄱ",Toast.LENGTH_LONG).show();
                                TransactionGet trans = new TransactionGet();

                                FragmentTransaction fragmentTransaction6 = getFragmentManager().beginTransaction();

                                fragmentTransaction6.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                                        R.anim.none, R.anim.none);

                                fragmentTransaction6.replace(R.id.main_frame, trans);
                                //fragmentTransaction3.addToBackStack("cardGet");
                                fragmentTransaction6.commit();
                            }
                        });




                        break;

                    case "account_get":

                        AccountGet account = new AccountGet();
                        account.setArguments(bundle);

                        FragmentTransaction fragmentTransaction1 = getFragmentManager().beginTransaction();

                        fragmentTransaction1.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,
                                R.anim.none,R.anim.none);

                        fragmentTransaction1.replace(R.id.main_frame,account);
                        //fragmentTransaction.addToBackStack("accountGet");
                        fragmentTransaction1.commit();

                        break;
                    case "account_delete":

                        Call<ResponseBody> accountResponseCall = myApi.deleteAccount(String.valueOf(RetrofitHandler.accountId));
                        accountResponseCall.enqueue(new Callback<ResponseBody>() {
                            //TODO: 삭제하면 홈화면으로
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if(response.isSuccessful()) {
                                    Toast.makeText(getContext(), "계정 탈퇴 완료", Toast.LENGTH_LONG).show();
                                    FragmentManager manager1 = getActivity().getSupportFragmentManager();

                                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                                    fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                                            R.anim.none, R.anim.none);

                                    manager1.beginTransaction().remove(Authentication.this).commit();
                                    manager1.popBackStack();
                                    return;
                                }else{
                                    Log.d(TAG,"Status Code : " + response.code());
                                    Toast.makeText(getContext(), "비밀번호 확인", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.d(TAG,"Fail msg : " + t.getMessage());
                                Toast.makeText(getContext(), "다시 시도 ㄱ ㄱ", Toast.LENGTH_LONG).show();
                            }
                        });

                        break;



                    case "account_patch":
                        AccountPatch account2 = new AccountPatch();
                        account2.setArguments(bundle);

                        FragmentTransaction fragmentTransaction2 = getFragmentManager().beginTransaction();

                        fragmentTransaction2.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,
                                R.anim.none,R.anim.none);

                        fragmentTransaction2.replace(R.id.main_frame,account2);
                        //fragmentTransaction2.addToBackStack("accountPatch");
                        fragmentTransaction2.commit();

                        break;
                    case "card_get":
                        CardGet card = new CardGet();
                        card.setArguments(bundle);

                        FragmentTransaction fragmentTransaction3 = getFragmentManager().beginTransaction();

                        fragmentTransaction3.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,
                                R.anim.none,R.anim.none);

                        fragmentTransaction3.replace(R.id.main_frame,card);
                        //fragmentTransaction3.addToBackStack("cardGet");
                        fragmentTransaction3.commit();

                        break;
                    case "card_delete":
                        FragmentManager manager2 = getActivity().getSupportFragmentManager();

                        Call<ResponseBody> responseBodyCall = myApi.deleteCard(deleteCardId.getText().toString());
                        responseBodyCall.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if(response.isSuccessful()){
                                    Toast.makeText(getContext(),"카드 삭제 완료",Toast.LENGTH_LONG).show();

                                    FragmentTransaction fragmentTransaction4 = getFragmentManager().beginTransaction();
                                    fragmentTransaction4.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,
                                            R.anim.none,R.anim.none);

                                }else{
                                    Toast.makeText(getContext(),"비밀번호 확인",Toast.LENGTH_LONG).show();
                                    FragmentTransaction fragmentTransaction4 = getFragmentManager().beginTransaction();
                                    fragmentTransaction4.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,
                                            R.anim.none,R.anim.none);

                                }
                                manager2.beginTransaction().remove(Authentication.this).commit();
                                manager2.popBackStack();
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Log.d(TAG,"Fail msg : " + t.getMessage());
                                Toast.makeText(getContext(),"다시 시도 ㄱ ㄱ",Toast.LENGTH_LONG).show();
                                FragmentTransaction fragmentTransaction4 = getFragmentManager().beginTransaction();
                                fragmentTransaction4.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,
                                        R.anim.none,R.anim.none);

                                manager2.beginTransaction().remove(Authentication.this).commit();
                                manager2.popBackStack();
                            }
                        });
                        break;

                    case "card_post":
                        CardPost card2 = new CardPost();
                        card2.setArguments(bundle);

                        FragmentTransaction fragmentTransaction5 = getFragmentManager().beginTransaction();

                        fragmentTransaction5.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,
                                R.anim.none,R.anim.none);

                        fragmentTransaction5.replace(R.id.main_frame,card2);
                        //fragmentTransaction5.addToBackStack("cardPost");
                        fragmentTransaction5.commit();

                        break;

                }
            }
        });
        return v;
    }

}