package com.example.bledos.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bledos.R;

import java.util.ArrayList;

public class RecyclerViewAdapterRiwayat extends RecyclerView.Adapter<RecyclerViewAdapterRiwayat.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapterRiwa";

    public ArrayList<String> notelp = new ArrayList<>();
    public ArrayList<String> bengkelorder = new ArrayList<>();
    private Context context;

    public RecyclerViewAdapterRiwayat(ArrayList<String> notelp, ArrayList<String> bengkelorder, Context context) {
        this.notelp = notelp;
        this.bengkelorder = bengkelorder;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: Called");

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_riwayat, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: " + notelp.get(position));
        holder.txtNoTelp.setText(notelp.get(position));
        holder.txtBengkelOrder.setText(bengkelorder.get(position));
    }

    @Override
    public int getItemCount() {
        return notelp.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtBengkelOrder, txtNoTelp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNoTelp = itemView.findViewById(R.id.txtNoTelp);
            txtBengkelOrder = itemView.findViewById(R.id.txtBengkelOrder);
        }
    }
}
