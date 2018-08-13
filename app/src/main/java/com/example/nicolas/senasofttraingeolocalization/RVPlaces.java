package com.example.nicolas.senasofttraingeolocalization;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nicolas.senasofttraingeolocalization.model.MyModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RVPlaces extends RecyclerView.Adapter<RVPlaces.ViewHolder> {

    List<MyModel> Places;
    String cat;

    public RVPlaces(List<MyModel> lista) {
        Places = lista;
    }

    @NonNull
    @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = ListBayCat.twoCards ? R.layout.list1 : R.layout.list;
        System.out.println(ListBayCat.twoCards);
        View holder = LayoutInflater.from(parent.getContext())
                .inflate(layoutId, parent, false);
        ViewHolder vh = new ViewHolder(holder);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final String imageURL = "http://192.168.1.9/RecursosTuristicos/ANDROID_STUDIO/img/"+Places.get(position).getImagen();
        Picasso.get().load(imageURL).into(holder.imv);
        if(!(holder.tv == null)) {
            holder.tv.setText(Places.get(position).getNombre());
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c = new Intent(view.getContext(), Detail.class);
                Detail.lat = Places.get(position).getLat();
                Detail.lon = Places.get(position).getLon();
                Detail.image = imageURL;
                Detail.name = Places.get(position).getNombre();
                Detail.description = Places.get(position).getDesc_larga();
                view.getContext().startActivity(c);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Places.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imv;
        public TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            imv = itemView.findViewById(R.id.place_image);
            tv = itemView.findViewById(R.id.place_name);
        }
    }
}
