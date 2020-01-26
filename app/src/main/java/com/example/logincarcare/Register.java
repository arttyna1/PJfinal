package com.example.logincarcare;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.login.Login;
import com.github.ybq.android.spinkit.style.RotatingPlane;

import java.util.HashMap;
import java.util.Map;

import static android.view.View.GONE;

public class Register extends AppCompatActivity {
private EditText username,password,name,phone;
private Button reg;
private RelativeLayout regis,regis2;
private ProgressBar progressBar;
    private TextView textload;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bind();
        regis2.setVisibility(View.GONE);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
    }
    private void bind(){
        username = findViewById(R.id.reg_email);
        password = findViewById(R.id.reg_password);
        name = findViewById(R.id.reg_name);
        phone = findViewById(R.id.reg_phone);
        reg = findViewById(R.id.reg_confrim);
        progressBar = (ProgressBar)findViewById(R.id.spin_kit);
        RotatingPlane doubleBounce = new RotatingPlane();
        progressBar.setIndeterminateDrawable(doubleBounce);
        textload = findViewById(R.id.textload_reg);
        regis = findViewById(R.id.mainRegis);
        regis2 = findViewById(R.id.subregis);
    }

    private void Register(){
        final Handler handler = new Handler();
        regis.setVisibility(GONE);
        regis2.setVisibility(View.VISIBLE);
        String url_reg = "https://borirat.000webhostapp.com/register.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_reg
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        regis2.setVisibility(View.GONE);
                        regis.setVisibility(View.VISIBLE);
                    }
                }, 1000);
                Toast.makeText(getApplicationContext(),"สมัครสำเร็จ",Toast.LENGTH_LONG).show();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent i = new Intent(getApplicationContext(), Login.class);
                        startActivity(i);
                    }
                }, 2000);

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("email",username.getText().toString());
                params.put("name",name.getText().toString());
                params.put("phone",phone.getText().toString());
                params.put("password",password.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}
