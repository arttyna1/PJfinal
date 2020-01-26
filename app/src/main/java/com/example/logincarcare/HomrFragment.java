package com.example.logincarcare;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomrFragment extends Fragment {
private View view;
private RecyclerView recyclerView;
private List<modelstore> car;
private LinearLayoutManager linearLayoutManager;

    public HomrFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        car = new ArrayList<>();
        linearLayoutManager = new LinearLayoutManager(getActivity());
        view = inflater.inflate(R.layout.fragment_homr, container, false);
        recyclerView = view.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadstore();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),

                recyclerView, new RecyclerTouchListener.ClickListener() {

            @Override
            public void onClick(View view, int position) {
                modelstore md = car.get(position);
                Intent intent = new Intent(getActivity(), Storecarcare.class);
                intent.putExtra("namei",md.getNamestore());
                intent.putExtra("urli",md.getPath());
                intent.putExtra("couni",md.getCounty());
                intent.putExtra("lati",md.getLatitude());
                intent.putExtra("longei",md.getLongitude());
                startActivity(intent);

            }
            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    private void loadstore(){
        String url = "https://borirat.000webhostapp.com/loaddata.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //lToast.makeText(view.getContext(),response,Toast.LENGTH_LONG).show();
                try {

                    JSONArray array = new JSONArray(response);
                    for(int i =0;i<array.length();i++){
                        JSONObject storena = array.getJSONObject(i);
                        car.add(new modelstore(
                                storena.getString("id"),
                                storena.getString("name"),
                                storena.getString("path"),
                                storena.getString("country"),
                                storena.getString("lati"),
                                storena.getString("longi")

                        ));
                    }
                    StoreAdapter aspt = new StoreAdapter(car,getActivity());
                    recyclerView.setAdapter(aspt);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

}
