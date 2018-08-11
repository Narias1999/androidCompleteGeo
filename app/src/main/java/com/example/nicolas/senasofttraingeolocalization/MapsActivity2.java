package com.example.nicolas.senasofttraingeolocalization;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.maps.android.PolyUtil;


import org.json.JSONObject;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback {

    public GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        System.out.println("sakdhaksd");



        String URL = "https://maps.googleapis.com/maps/api/directions/json" +
                "?origin=6.5526427,-73.1536311" +
                "&destination=" + Detail.lat + "," + Detail.lon +
                "&sensor=false&mode=driving&alternatives=true";



        StringRequest sr = new StringRequest(URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new GsonBuilder().create();

                JsonObject objeto = gson.fromJson(response, JsonElement.class).getAsJsonObject();
                String points  = objeto.get("routes").getAsJsonArray().get(0).getAsJsonObject().get("overview_polyline").getAsJsonObject().get("points").getAsString();
                PolylineOptions opts = new PolylineOptions().color(Color.BLUE).addAll(PolyUtil.decode(points));

                mMap.addPolyline(opts);
                LatLng sydney = new LatLng(6.5526427, -73.1536311);
                mMap.addMarker(new MarkerOptions().position(sydney).title("Estás aquí"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MapsActivity2.this, "Error!", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue cola = Volley.newRequestQueue(this);
        cola.add(sr);
    }
}
