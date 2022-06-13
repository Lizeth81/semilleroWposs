package com.example.banco.transferencia.repository;

import android.util.Log;

import com.example.banco.api.ApiServis;
import com.example.banco.modelo.Transferenciaa.Tranferir;
import com.example.banco.transferencia.interfaces.InterfaceTransferencia;

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

public class Repository implements InterfaceTransferencia.repositorio {
    InterfaceTransferencia.presenter presenter;

    public Repository(InterfaceTransferencia.presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void transfer(String numeroCuenta, String tipoTranferencia, int monto, String cuentaDestiny, String token) throws JSONException {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout ( 15, TimeUnit.SECONDS)
                .writeTimeout ( 15, TimeUnit.SECONDS)
                .readTimeout (  15, TimeUnit.SECONDS)
                .build();

        JSONObject body1 = armarJson(numeroCuenta, tipoTranferencia, monto, cuentaDestiny);
        Log.e("TAG", "jsonArray: "+body1.toString() );

        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient)
                .baseUrl("http://10.51.1.9:1010/transaction/")
//                .baseUrl("http://192.168.101.4:1010/transaction/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), body1.toString());

        ApiServis apiServis = retrofit.create(ApiServis.class);

        Call<Tranferir> call = apiServis.trasferencia(token, body);
        call.enqueue(new Callback<Tranferir>() {
            @Override
            public void onResponse(Call<Tranferir> call, Response<Tranferir> response) {
                Log.e("", "estado"+response.body().isStatus());
                if(response.body().isStatus()){
                    presenter.respuesta(response.body().getMsg(), true);
                }else{
                    presenter.respuesta(response.body().getMsg(), false);
                }
            }

            @Override
            public void onFailure(Call<Tranferir> call, Throwable t) {
                Log.e("", "Error");
            }
        });
    }
    public JSONObject armarJson(String numeroCuenta, String tipoTranferencia, int monto, String cuentaDestiny) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("numberBill", numeroCuenta);
        jsonObject.put("typeTransation", tipoTranferencia);
        jsonObject.put("amount", monto);
        jsonObject.put("numberBillDestiny", cuentaDestiny);

        return jsonObject;
    }
}
