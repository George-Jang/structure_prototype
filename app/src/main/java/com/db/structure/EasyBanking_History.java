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
import android.widget.TextView;
import android.widget.Toast;

import com.db.structure.responseDTO.TransactionResponse;
import com.db.structure.retrofit.MyApi;
import com.db.structure.retrofit.RetrofitHandler;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EasyBanking_History extends Fragment implements onBackPressedListener{
    private final  String TAG = getClass().getSimpleName();

    @Override
    public void onBackPressed() {
        goToMain();
    }

    private void goToMain(){
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.none,R.anim.exit_to_right);
        ft.remove(EasyBanking_History.this);
        fragmentManager.popBackStack();
        ft.commit();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_easy_banking__history,container,false);


        String pwd = getArguments().getString("pwd");

        MyApi myApi = RetrofitHandler.generateMyApi(pwd);

        Call<List<TransactionResponse>> listCall = myApi.getAllTransactions(String.valueOf(RetrofitHandler.accountId));
        listCall.enqueue(new Callback<List<TransactionResponse>>() {
            @Override
            public void onResponse(Call<List<TransactionResponse>> call, Response<List<TransactionResponse>> response) {
                if(response.isSuccessful()) {
                    LinearLayout layout = v.findViewById(R.id.historyLinear);
                    EditText transactionDate, type, comment, transactionValue, client, balanceAfterTransaction;


                    List<TransactionResponse> transactionResponses = response.body();

                    for (TransactionResponse transaction : transactionResponses) {
                        transactionDate = new EditText(getContext());
                        transactionDate.setText("?????? : " + transaction.getTransactionDate());

                        type = new EditText(getContext());
                        type.setText("?????? : " + transaction.getType());

                        comment = new EditText(getContext());
                        comment.setText("????????? : " + transaction.getComment());

                        transactionValue = new EditText(getContext());
                        transactionValue.setText("?????? ?????? : " + transaction.getTransactionValue());

                        client = new EditText(getContext());
                        client.setText("?????? ?????? : " + transaction.getClient().getUser().getName());

                        balanceAfterTransaction = new EditText(getContext());
                        balanceAfterTransaction.setText("?????? ??? ?????? : " + transaction.getBalanceAfterTransaction());

                        layout.addView(transactionDate);
                        layout.addView(type);
                        layout.addView(comment);
                        layout.addView(transactionValue);
                        layout.addView(client);
                        layout.addView(balanceAfterTransaction);

                    }
                }else{
                    Toast.makeText(getContext(),"?????? ??????",Toast.LENGTH_LONG).show();
                    FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                    FragmentTransaction ft = fragmentManager.beginTransaction();
                    ft.setCustomAnimations(R.anim.none, R.anim.exit_to_right);
                    ft.remove(EasyBanking_History.this);
                    fragmentManager.popBackStack();
                    ft.commit();
                }
            }

            @Override
            public void onFailure(Call<List<TransactionResponse>> call, Throwable t) {
                Log.d(TAG,"Fail msg : " + t.getMessage());
                Toast.makeText(getContext(),"?????? ?????? ??? ???",Toast.LENGTH_LONG).show();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                ft.setCustomAnimations(R.anim.none, R.anim.exit_to_right);
                ft.remove(EasyBanking_History.this);
                fragmentManager.popBackStack();
                ft.commit();
            }
        });

        return v;
    }
}