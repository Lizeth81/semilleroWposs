package com.example.banco.codigoQR.repository;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import com.example.banco.api.ApiServis;
import com.example.banco.codigoQR.interfaces.InterfaceQr;
import com.example.banco.modelo.qr.Codigoqr;

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

public class RepositoryQr implements InterfaceQr.repositorio {
    InterfaceQr.presenter presenter;

    public RepositoryQr(InterfaceQr.presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void codigoQr(String numeroCuenta, String token) throws JSONException {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout ( 15, TimeUnit.SECONDS)
                .writeTimeout ( 15, TimeUnit.SECONDS)
                .readTimeout (  15, TimeUnit.SECONDS)
                .build();

        JSONObject body1 = armarJson(numeroCuenta);
        Log.e("TAG", "jsonArray: "+body1.toString() );

        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient)
                .baseUrl("http://10.51.1.9:1010/QR/")
//                .baseUrl("http://192.168.101.4:1010/QR/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), body1.toString());

        ApiServis apiServis = retrofit.create(ApiServis.class);

        Call<Codigoqr> call = apiServis.getQr(token, body);
        call.enqueue(new Callback<Codigoqr>() {
            @Override
            public void onResponse(Call<Codigoqr> call, Response<Codigoqr> response) {
                if(response.isSuccessful()){
                    presenter.respuesta(response.body().getMng(), response.body().getImg(), true);
//                    byte[] imageBytes = Base64.decode(response.body().getImg(), Base64.DEFAULT);
//                    Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
//                    return;
                }
//                    presenter.respuesta(response.body().getMng(), response.body().getImg(), true);
            }

            @Override
            public void onFailure(Call<Codigoqr> call, Throwable t) {

            }
        });

    }

    public JSONObject armarJson(String numeroCuenta) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("numberBill", numeroCuenta);

        return jsonObject;
    }
}
