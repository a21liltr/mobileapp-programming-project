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

    private List<Duck> ducks;
    private LayoutInflater inflater;

    public RecyclerViewAdapter(List<Duck> ducks, Context context) {
        this.ducks = ducks;
        this.inflater = inflater.from(context);
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
        holder.tvCharacteristics.setText(ducks.get(position).getCharacteristics());
        holder.tvCharacteristics.setText(ducks.get(position).getOrigin());
    }

    @Override
    public int getItemCount() {
        return ducks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCharacteristics, tvOrigin;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCharacteristics = itemView.findViewById(R.id.tv_characteristics);
            tvOrigin = itemView.findViewById(R.id.tv_origin);

        }
    }
}
