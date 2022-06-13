package com.example.banco.retiro.repository;

import android.util.Log;

import com.example.banco.api.ApiServis;
import com.example.banco.modelo.retiro.RetiroPlata;
import com.example.banco.retiro.interfaces.InterfaceRetiro;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository implements InterfaceRetiro.repositorio {

    InterfaceRetiro.presenter presenter;

    public Repository(InterfaceRetiro.presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void retiro(String codeAut, String numeroCuenta, String tipoTrans, int monto, String token) throws JSONException {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout ( 15, TimeUnit.SECONDS)
                .writeTimeout ( 15, TimeUnit.SECONDS)
                .readTimeout (  15, TimeUnit.SECONDS)
                .build();

        JSONObject body1 = armarJson(codeAut, numeroCuenta, tipoTrans, monto);
        Log.e("TAG", "jsonArray: "+body1.toString());

        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient)
                .baseUrl("http://10.51.1.9:1010/transaction/")
//                .baseUrl("http://192.168.101.4:1010/transaction/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), body1.toString());

        ApiServis apiServis = retrofit.create(ApiServis.class);

       Call<RetiroPlata> call = apiServis.retiros(token, body);
       call.enqueue(new Callback<RetiroPlata>() {
           @Override
           public void onResponse(Call<RetiroPlata> call, Response<RetiroPlata> response) {
               Log.e("", "estado"+response.body().isStatus());
               if(response.body().isStatus()){
                   presenter.respuesta(response.body().getMsg(), true);
               }else{
                   presenter.respuesta(response.body().getMsg(), false);
               }
//               if(response.isSuccessful())
//                   presenter.respuesta("Retiro exitoso", true);
           }

           @Override
           public void onFailure(Call<RetiroPlata> call, Throwable t) {
               presenter.respuesta(t.getMessage(), false);
           }
       });


    }
    public JSONObject armarJson(String codiAut, String numeroCuenta, String tipoTrans, int monto) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("codeAut", codiAut);
        jsonObject.put("numberBill", numeroCuenta);
        jsonObject.put("typeTransation", tipoTrans);
        jsonObject.put("amount", monto);

        return jsonObject;
    }
}
