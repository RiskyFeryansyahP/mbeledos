package com.example.bledos.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bledos.OrderActivity;
import com.example.bledos.R;
import com.example.bledos.TransaksiActivity;

import java.util.ArrayList;

public class RecycleViewAdapterHome extends RecyclerView.Adapter<RecycleViewAdapterHome.ViewHolder> {

    private static final String TAG = "RecycleViewAdapterHome";

    private ArrayList<String> kodeBengkel = new ArrayList<>();
    private ArrayList<String> namaBengkel = new ArrayList<>();
    private ArrayList<String> alamatBengkel = new ArrayList<>();
    private ArrayList<Double> latitude = new ArrayList<>();
    private ArrayList<Double> longitude = new ArrayList<>();
    private Context context;

    public RecycleViewAdapterHome(Context context, ArrayList<String> kodeBengkel, ArrayList<String> namaBengkel, ArrayList<String> alamatBengkel, ArrayList<Double> latitude, ArrayList<Double> longitude) {
        this.context = context;
        this.kodeBengkel = kodeBengkel;
        this.namaBengkel = namaBengkel;
        this.alamatBengkel = alamatBengkel;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called!");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_home, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.imageRecyclerViewHome.setImageResource(R.mipmap.ic_launcher);
        holder.txtNameBengkel.setText(namaBengkel.get(position));
        holder.txtAddress.setText(alamatBengkel.get(position));
        holder.txtRangeDestination.setText("1KM");
        holder.cardViewItemHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: CardView List Item Recycler View Home, Position : " + position);
                OrderActivity.latitudePlace = latitude.get(position);
                OrderActivity.longitudePlace = longitude.get(position);
                context.startActivity(new Intent(context, OrderActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return kodeBengkel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageRecyclerViewHome;
        
        private CardView cardViewItemHome;

        private TextView txtNameBengkel, txtAddress, txtRangeDestination;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageRecyclerViewHome = itemView.findViewById(R.id.imageRecyclerViewHome);

            cardViewItemHome = itemView.findViewById(R.id.cardViewItemHome);

            txtNameBengkel = itemView.findViewById(R.id.txtNameBengkel);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtRangeDestination = itemView.findViewById(R.id.txtRangeDestination);
        }

    }

}
