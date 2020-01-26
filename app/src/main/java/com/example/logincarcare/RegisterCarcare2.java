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

public class RegisterCarcare2 extends AppCompatActivity {

    private EditText fname,lname,emailCare,passwordCare,phoneCare,reg_namecarcare,reg_address,reg_city;
    private Button reg_confirm;
    private RelativeLayout regis,regis2;
    private ProgressBar progressBar;
    private TextView textload;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_carcare2);
        bind();
        regis2.setVisibility(View.GONE);
        reg_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterCare();
            }
        });


    }
    private void bind(){

        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        emailCare = findViewById(R.id.emailCare);
        passwordCare =findViewById(R.id.passwordCare);
        phoneCare = findViewById(R.id.phoneCare);
        reg_namecarcare = findViewById(R.id.reg_namecarcare);
        reg_address = findViewById(R.id.reg_address);
        reg_city = findViewById(R.id.reg_city);
        reg_confirm = findViewById(R.id.reg_confrim);
        progressBar = (ProgressBar)findViewById(R.id.spin_kit1);
        RotatingPlane doubleBounce = new RotatingPlane();
        progressBar.setIndeterminateDrawable(doubleBounce);
        textload = findViewById(R.id.textload_reg);
        regis = findViewById(R.id.mainRegis);
        regis2 = findViewById(R.id.subregis);
    }
    private void RegisterCare(){
        final Handler handler = new Handler();
        regis.setVisibility(GONE);
        regis2.setVisibility(View.VISIBLE);
        String url_reg = "https://borirat.000webhostapp.com/RegisCare.php";
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
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
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
               params.put("fname",fname.getText().toString());
               params.put("lname",lname.getText().toString());
               params.put("emailCare",emailCare.getText().toString());
               params.put("passwordCare",passwordCare.getText().toString());
               params.put("phoneCare",phoneCare.getText().toString());
               params.put("reg_namecarcare",reg_namecarcare.getText().toString());
               params.put("reg_address",reg_address.getText().toString());
               params.put("reg_city",reg_city.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


}
