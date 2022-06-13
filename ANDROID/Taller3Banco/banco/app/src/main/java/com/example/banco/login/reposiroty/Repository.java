package com.example.banco.login.reposiroty;

import android.util.Log;
import android.widget.Toast;

import com.example.banco.api.ApiServis;
import com.example.banco.login.interfaces.InterfaceLogin;
import com.example.banco.login.view.MainActivity;
import com.example.banco.modelo.login.Login;
import com.example.banco.modelo.registro.Persona;
import com.example.banco.modelo.registro.RegistroModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository implements InterfaceLogin.repositorio{

    InterfaceLogin.presenter presenter;

    public Repository(InterfaceLogin.presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void login(String correo, String pass) throws JSONException {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout ( 5, TimeUnit.SECONDS)
                .writeTimeout ( 5, TimeUnit.SECONDS)
                .readTimeout (  5, TimeUnit.SECONDS)
                .build();

        JSONObject body1 = armarJson(correo, pass);
        Log.e("", "cuerpo "+body1);

        Retrofit retrofit = new Retrofit.Builder().client(okHttpClient)
                .baseUrl("http://10.51.1.9:1010/user/")
//                .baseUrl("http://192.168.101.4:1010/user/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), body1.toString());
        ApiServis apiServis = retrofit.create(ApiServis.class);

        Call<Login> call = apiServis.loguear(body);
        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                Log.e("", "data "+response.body().getData());
                Log.e("", "token "+response.body().getToken());
                if(response.body().getData() != null){
                    presenter.respuesta(true,response.body().getData(), response.body().getToken());
                }else{
                    presenter.respuesta(false, null, null);
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Log.e("TAG", t.getMessage());
            }
        });
//        call.enqueue(new Callback<Login>() {
//           @Override
//           public void onResponse(Call<Login> call, Response<Login> response) {
//               Log.e("", "data "+response.body().getData());
//               Log.e("", "token "+response.body().getToken());
//             presenter.respuesta(true,response.body().getData(), response.body().getToken());
//           }
//           @Override
//           public void onFailure(Call<Login> call, Throwable t) {
//               Log.e("TAG", "Eroor al ingresar"+t.getMessage());
//           }
//       });
    }

    public JSONObject armarJson(String correo, String pass) throws JSONException {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray =  new JSONArray();

        jsonObject.put("user_email", correo);
        jsonObject.put("user_password", pass);


        return jsonObject;
    }
}
