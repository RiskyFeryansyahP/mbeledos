package com.example.bledos.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bledos.R;

public class RecycleViewAdapterHome extends RecyclerView.Adapter<RecycleViewAdapterHome.ViewHolder> {

    private static final String TAG = "RecycleViewAdapterHome";

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: called!");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview_home, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.imageRecyclerViewHome.setImageResource(R.mipmap.ic_launcher);
        holder.txtNameBengkel.setText("Bengkel Shockbreker Motor");
        holder.txtAddress.setText("Jl. Raya Gadang, Gadang, Kec. Sukun, Kota Malang");
        holder.txtRangeDestination.setText("1KM");
        holder.cardViewItemHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: CardView List Item Recycler View Home, Position : " + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
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
