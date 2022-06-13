package com.example.banco.codigoRetiro.repository;

import android.util.Log;

import com.example.banco.api.ApiServis;
import com.example.banco.codigoRetiro.interfaces.InterfaceCodigoRetiro;
import com.example.banco.modelo.retiro.CodigoRet;

import org.json.JSONArray;
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
import retrofit2.http.Body;

public class Repository implements InterfaceCodigoRetiro.repositorio {

    InterfaceCodigoRetiro.presenter presenter;

    public Repository(InterfaceCodigoRetiro.presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void numberBill(String numeroCuenta, String token) throws JSONException {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout ( 15, TimeUnit.SECONDS)
                .writeTimeout ( 15, TimeUnit.SECONDS)
                .readTimeout (  15, TimeUnit.SECONDS)
                .build();

        JSONObject body1 = armarJson(numeroCuenta);
        Log.e("TAG", "jsonArray: "+body1.toString() );

        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient)
                .baseUrl("http://10.51.1.9:1010/transaction/")
//                .baseUrl("http://192.168.101.4:1010/transaction/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), body1.toString());

        ApiServis apiServis = retrofit.create(ApiServis.class);

        Call <CodigoRet> call = apiServis.getRetiro(token,body);
        call.enqueue(new Callback<CodigoRet>() {
            @Override
            public void onResponse(Call<CodigoRet> call, Response<CodigoRet> response) {
            if(response.isSuccessful())
                  presenter.respuesta(response.body().getMsg(), response.body().getData(), true);
            }

            @Override
            public void onFailure(Call<CodigoRet> call, Throwable t) {
                Log.e("TAG", "msg: "+t.getMessage());
            }
        });

    }

    public JSONObject armarJson(String numeroCuenta) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("numberBill", numeroCuenta);

        return jsonObject;
    }
}
