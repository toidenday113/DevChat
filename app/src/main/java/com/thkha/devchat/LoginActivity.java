package com.thkha.devchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private final String BASE_URL ="http://192.168.1.253:3000";
    private Button btn_Login;
    private EditText et_UsernameEmail, et_Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initControl();

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    HashMap<String, Object> hmLogin = new HashMap<String, Object>();
                    hmLogin.put("username", et_UsernameEmail.getText().toString());
                    hmLogin.put("password", et_Password.getText().toString());
                    JSONObject para = new JSONObject(hmLogin);
                    RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);

                    JsonObjectRequest postRequest = new JsonObjectRequest (Request.Method.POST, BASE_URL + "/login", para,new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("Response", response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Response", error.toString());
                        }
                    });

                    requestQueue.add(postRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }

    private void initControl(){
        btn_Login = findViewById(R.id.button_Login);
        et_UsernameEmail = findViewById(R.id.editText_UsernameEmail);
        et_Password = findViewById(R.id.editText_Password);
    }
}