package com.example.biomarketdelivery.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.biomarketdelivery.R;

import com.example.biomarketdelivery.CommandDetailsActivity;
import com.example.biomarketdelivery.models.LinkModel;

import java.io.Serializable;
import java.util.List;

public class LinkAdapter extends RecyclerView.Adapter<LinkAdapter.ViewHolder> {
    Context context;
    List<LinkModel> list;

    public LinkAdapter(Context context, List<LinkModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.link_template, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        if(list.get(position).isValidity()){
//            holder.confirmed.setVisibility(View.VISIBLE);
//            holder.process.setVisibility(View.GONE);
//        }

        holder.dateOfCommand.setText(list.get(position).getId());

        holder.cardViewLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CommandDetailsActivity.class);
                intent.putExtra("linkModel", (Serializable) list.get(position));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewLink;
        TextView dateOfCommand;
//        TextView confirmed, process;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewLink = itemView.findViewById(R.id.cardViewLink);
            dateOfCommand = itemView.findViewById(R.id.dateOfCommand);
//            confirmed = itemView.findViewById(R.id.confirmed);
//            process = itemView.findViewById(R.id.process);
        }
    }
}
