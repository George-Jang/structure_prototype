package com.db.structure.retrofit;

import com.db.structure.requestDTO.AccountCreateRequestDTO;
import com.db.structure.requestDTO.AccountPasswordUpdateDTO;
import com.db.structure.requestDTO.CardCreateRequestDTO;
import com.db.structure.responseDTO.AccountResponse;
import com.db.structure.responseDTO.CardListResponse;
import com.db.structure.responseDTO.CardResponse;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyApi {

    // account
    @POST("/v1/account")
    Call<AccountResponse> createAccount(@Body AccountCreateRequestDTO accountCreateRequestDTO);

    @PATCH("v1/account/{accountId}")
    Call<AccountResponse> updateAccountPassword(@Path("accountId") String accountId, @Body AccountPasswordUpdateDTO accountPasswordUpdateDTO);

    @DELETE("/v1/account/{accountId}")
    Call<ResponseBody> deleteAccount(@Path("accountId") String accountId);

    @GET("/v1/account/{accountId}")
    Call<AccountResponse> getAccount(@Path("accountId") String accountId);


    //card
    @POST("/v1/card")
    Call<CardResponse> createCard(@Body CardCreateRequestDTO cardCreateRequestDTO);

    @DELETE("/v1/card/{cardId}")
    Call<ResponseBody> deleteCard(@Path("cardId") String cardId);

    @GET("/v1/cards/{accountId}")
    Call<CardListResponse> getCardList(@Path("accountId") String accountId);
}
