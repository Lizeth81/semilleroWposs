package com.example.banco.api;

import com.example.banco.modelo.Transferenciaa.Tranferir;
import com.example.banco.modelo.Transaction.Transac;
import com.example.banco.modelo.login.Login;
import com.example.banco.modelo.qr.Codigoqr;
import com.example.banco.modelo.registro.RegistroModel;
import com.example.banco.modelo.retiro.CodigoRet;
import com.example.banco.modelo.retiro.RetiroPlata;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiServis {


    @POST ("register")
    Call<RegistroModel> registrar(@Body RequestBody body);

    @POST ("login")
    Call<Login>loguear(@Body RequestBody body);

   @POST("codeRetirement")
    Call<CodigoRet> getRetiro(@Header("Authorization") String token, @Body RequestBody body);

   @PUT("retirement")
    Call<RetiroPlata> retiros(@Header("Authorization") String token, @Body RequestBody body);

   @PUT("transfer")
    Call<Tranferir> trasferencia(@Header("Authorization") String token, @Body RequestBody body);

   @POST("createCommonQRCode")
    Call<Codigoqr> getQr(@Header("Authorization") String token, @Body RequestBody body);

   @POST("getTransfers")
    Call<Transac> setTransaccion(@Header("Authorization") String token, @Body RequestBody body);
}
