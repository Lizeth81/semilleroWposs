package com.example.banco.transaccion.repository;

import android.util.Log;
import android.widget.Toast;

import com.example.banco.api.ApiServis;
import com.example.banco.modelo.Transaction.Transac;
import com.example.banco.modelo.Transaction.Usuario;
import com.example.banco.transaccion.interfaces.InterfaceTransaccion;
import com.example.banco.transaccion.view.Transacciones;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RepositoryTrans implements InterfaceTransaccion.repositorio {
    InterfaceTransaccion.presenter presenter;

    public RepositoryTrans(InterfaceTransaccion.presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void transaccion(String numeroCuenta, String token) throws JSONException {


        JSONObject body1 = armarJson(numeroCuenta);
        Log.e("TAG", "jsonArray: "+body1.toString() );

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.51.1.9:1010/transaction/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), body1.toString());

        ApiServis apiServis = retrofit.create(ApiServis.class);


        Call<Transac> call = apiServis.setTransaccion(token, body);
        call.enqueue(new Callback<Transac>() {
            @Override
            public void onResponse(Call<Transac> call, Response<Transac> response) {
                if(response.isSuccessful()){
                    presenter.respuesta(response.body().getMsg(), response.body().getData(), response.body().isstatus());
                    Log.e("TAG", "Respuesta erronea: "+response.body());
                    Transacciones.listDatos = response.body();
                }

                Log.e("TAG", "Respuesta : "+response.isSuccessful());
            }

            @Override
            public void onFailure(Call<Transac> call, Throwable t) {

            }
        });
    }
    public JSONObject armarJson(String numeroCuenta) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("numberBill", numeroCuenta);

        return jsonObject;
    }
}
