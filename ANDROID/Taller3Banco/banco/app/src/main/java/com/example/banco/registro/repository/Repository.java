package com.example.banco.registro.repository;

import android.util.Log;
import com.example.banco.api.ApiServis;
import com.example.banco.modelo.registro.RegistroModel;
import com.example.banco.registro.interfaces.InterfaceRegistro;
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

public class Repository implements InterfaceRegistro.repositorio {

    InterfaceRegistro.presenter presenter;

    public Repository(InterfaceRegistro.presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void guardar(String nombre, String identificacion, String email, String pass, String estate) throws JSONException {

        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout ( 15, TimeUnit.SECONDS)
                .writeTimeout ( 15, TimeUnit.SECONDS)
                .readTimeout (  15, TimeUnit.SECONDS)
                .build();

        JSONObject body1 = armarJson(nombre, identificacion, email, pass, estate);

        Log.e("TAG", "jsonArray: "+body1.toString() );

        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient)
                .baseUrl("http://10.51.1.9:1010/user/")
//                .baseUrl("http://192.168.101.4:1010/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), body1.toString());

        ApiServis apiServis = retrofit.create(ApiServis.class);
        Log.e("TAG", "jsonArray: "+body );

        Call <RegistroModel> call = apiServis.registrar(body);
        call.enqueue(new Callback<RegistroModel>() {
            @Override
            public void onResponse(Call<RegistroModel> call, Response<RegistroModel> response) {
                if (response.isSuccessful())
                presenter.respuesta(response.body().getMsg(),true);
            }

            @Override
            public void onFailure(Call<RegistroModel> call, Throwable t) {
                presenter.respuesta(t.getMessage(),false);
            }
        });
    }

    public JSONObject armarJson(String nombre, String identificacion, String email, String pass, String estate) throws JSONException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("user_name", nombre);
        jsonObject.put("user_identification", identificacion);
        jsonObject.put("user_email", email);
        jsonObject.put("user_password", pass);
        jsonObject.put("user_estate", estate);

        return jsonObject;
    }
}
