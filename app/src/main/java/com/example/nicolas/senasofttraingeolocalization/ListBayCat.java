package com.example.nicolas.senasofttraingeolocalization;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.nicolas.senasofttraingeolocalization.model.MyModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListBayCat extends Menus {
    RecyclerView rv;
    static boolean twoCards;
    NavigationView nv;
    String cat;
    LinearLayout viewInicio;
    Button btn;
    static List<MyModel> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout fl = findViewById(R.id.fl);
        getLayoutInflater().inflate(R.layout.activity_list_bay_cat, fl);
        nv = findViewById(R.id.nav_view);
        rv = findViewById(R.id.myRecycler);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        cat = "1";
        rv.setVisibility(View.GONE);
        initializeButtonMethod();
        twoCards = false;
        menuActions("Inicio", 0);
        getData();
    }

    public void initializeButtonMethod () {
        viewInicio = (LinearLayout) findViewById(R.id.view_inicio);
        btn = (Button) findViewById(R.id.buttonView);
        btn.setVisibility(View.GONE);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                twoCards = !twoCards;
                adapter();
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        btn.setVisibility(View.GONE);
        viewInicio.setVisibility(View.GONE);
        rv.setVisibility(View.VISIBLE);
        if (id == R.id.nav_camera) {
            btn.setVisibility(View.VISIBLE);
            cat = "1";
            menuActions("Sitios", 1);
            adapter();
        } else if (id == R.id.nav_gallery) {
            cat = "2";
            menuActions("Hoteles", 2);
            adapter();
        } else if (id == R.id.nav_slideshow) {
            cat = "3";
            menuActions("Restaurantes", 3);
            adapter();
        } else if (id == R.id.nav_inicio) {
            viewInicio.setVisibility(View.VISIBLE);
            rv.setVisibility(View.GONE);
            menuActions("Inicio", 0);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void adapter () {
        System.out.println("Hola");
        RVPlaces adapter = new RVPlaces(filter(data, cat));
        rv.setAdapter(adapter);
    }
    public void menuActions(String title, int cat) {
        this.setTitle(title);
        nv.getMenu().getItem(cat).setChecked(true);
    }

    public List<MyModel> filter(List<MyModel> listWithoutFilter, String cat) {
        List<MyModel> filteredList = new ArrayList<MyModel>();
        for (int i = 0; i < listWithoutFilter.size(); i++) {
            if(cat.equals(listWithoutFilter.get(i).getCat())) {
                filteredList.add(listWithoutFilter.get(i));
            }
        }
        return filteredList;
    }

    private void getData() {
        String url = "http://10.98.6.206/RecursosTuristicos/ANDROID_STUDIO/wsCliente.php";
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new GsonBuilder().create();
                data = Arrays.asList(gson.fromJson(response, MyModel[].class));
                adapter();
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
