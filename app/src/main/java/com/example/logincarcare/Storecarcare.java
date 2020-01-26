package com.example.logincarcare;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Storecarcare extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private String lati,lonti,namest,countryst,urlst;
    private ImageView avatars;
    private TextView names,countrys;





    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storecarcare);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Bundle bd = getIntent().getExtras();
        if(bd != null){
            namest = bd.getString("namei");
            urlst = bd.getString("urli");
            countryst = bd.getString("couni");
            lati = bd.getString("lati");
            lonti = bd.getString("longei");
        }
        avatars = findViewById(R.id.imageStore);
        names = findViewById(R.id.carestore);
        countrys = findViewById(R.id.storecountry);
        GlideApp.with(getApplicationContext()).load(urlst).into(avatars);
        names.setText(namest);
        countrys.setText(countryst);


        ///dialog
        Button mShowDialog = (Button) findViewById(R.id.btnShowDialog);
        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(Storecarcare.this);
                View mView = getLayoutInflater().inflate(R.layout.dialog_login, null);
                final EditText mEmail = (EditText) mView.findViewById(R.id.etEmail);
                final EditText mPassword = (EditText) mView.findViewById(R.id.etPassword);
                Button mLogin = (Button) mView.findViewById(R.id.btnLogin);


//                mBuilder.setPositiveButton("Login", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//
//                    }
//                });
//
//                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                });
                mBuilder.setView(mView);
                final AlertDialog dialog = mBuilder.create();
                dialog.show();
                mLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!mEmail.getText().toString().isEmpty() && !mPassword.getText().toString().isEmpty()){
                            Toast.makeText(Storecarcare.this,
                                    R.string.success_login_msg,
                                    Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }else{
                            Toast.makeText(Storecarcare.this,
                                    R.string.error_login_msg,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
//                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (!mEmail.getText().toString().isEmpty() && !mPassword.getText().toString().isEmpty()) {
//                            Toast.makeText(MainActivity.this,
//                                    R.string.success_login_msg,
//                                    Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(MainActivity.this, Main2Activity.class));
//                            dialog.dismiss();
//                        } else {
//                            Toast.makeText(MainActivity.this,
//                                    R.string.error_login_msg,
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
            }
        });

        Button buttonIntent = (Button)findViewById(R.id.btnQueq);
        buttonIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Storecarcare.this, insertQueq.class);
                startActivity(intent);
            }
        });




    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(Float.valueOf(lati), Float.valueOf(lonti));
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,16));
    }
}
