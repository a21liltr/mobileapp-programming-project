package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private final IRecyclerView rv_interface;
    private List<Duck> ducks;
    private LayoutInflater inflater;

    public RecyclerViewAdapter(List<Duck> ducks, Context context, IRecyclerView rv_interface) {
        this.ducks = ducks;
        this.inflater = inflater.from(context);
        this.rv_interface = rv_interface;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new RecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        holder.tvName.setText(ducks.get(position).getName());
        holder.tvCharacteristics.setText(ducks.get(position).getCharacteristics().toUpperCase());
        holder.tvOrigin.setText(ducks.get(position).getOrigin());
        holder.tvCuriosity.setText(ducks.get(position).getCuriosity());
    }

    @Override
    public int getItemCount() {
        return ducks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCharacteristics, tvOrigin, tvCuriosity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCharacteristics = itemView.findViewById(R.id.tv_characteristics);
            tvOrigin = itemView.findViewById(R.id.tv_origin);
            tvCuriosity = itemView.findViewById(R.id.tv_curiosity);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (rv_interface != null) {
                       int position = getAdapterPosition();

                       if (position != RecyclerView.NO_POSITION) {
                           rv_interface.onItemClick(position);
                       }
                    }
                }
            });
        }
    }
}
