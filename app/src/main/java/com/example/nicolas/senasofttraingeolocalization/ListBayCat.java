package com.example.nicolas.senasofttraingeolocalization;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nicolas.senasofttraingeolocalization.model.MyModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

public class ListBayCat extends Menus {
    RecyclerView rv;
    String cat;
    static List<MyModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout fl = findViewById(R.id.fl);
        getLayoutInflater().inflate(R.layout.activity_list_bay_cat, fl);
        this.setTitle("Sitios");
        ((NavigationView) findViewById(R.id.nav_view)).getMenu().getItem(0).setChecked(true);
        rv = findViewById(R.id.myRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        cat =getIntent().getStringExtra("cat");
        getData();
        MapsActivity.cat = "1";
    }

    private void getData() {
        String url = "http://192.168.1.9/RecursosTuristicos/ANDROID_STUDIO/wsCliente.php";
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new GsonBuilder().create();
                data = Arrays.asList(gson.fromJson(response, MyModel[].class));
                RVPlaces adapter = new RVPlaces(Menus.filter(data, "1"));
                rv.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListBayCat.this, "Asegurate de estar conectadoa una red", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue cola = Volley.newRequestQueue(this);
        cola.add(request);
    }
}
