package com.db.structure.retrofit;

import com.db.structure.requestDTO.AccountCreateRequestDTO;
import com.db.structure.requestDTO.AccountPasswordUpdateDTO;
import com.db.structure.responseDTO.AccountResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyApi {
    @POST("/v1/account")
    Call<AccountResponse> createAccount(@Body AccountCreateRequestDTO accountCreateRequestDTO);

    @PATCH("v1/account/{accountId}")
    Call<AccountResponse> updateAccountPassword(@Path("accountId") int accountId, @Body AccountPasswordUpdateDTO accountPasswordUpdateDTO);

    @DELETE("/v1/account/{accountId}")
    Call<AccountResponse> deleteAccount(@Path("accountId") int accountId);

    @GET("/v1/account/{accountId}")
    Call<AccountResponse> getAccount(@Path("accountId") int accountId);
}
