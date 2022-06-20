package com.example.zapatillasapp.tiendas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.zapatillasapp.R;
import com.example.zapatillasapp.data.Tiendaitem;

import java.util.ArrayList;
import java.util.List;

public class TiendaListAdapter extends RecyclerView.Adapter<TiendaListAdapter.ViewHolder> {

    private List<Tiendaitem> itemList;
    private final View.OnClickListener clickListener;

    public TiendaListAdapter(View.OnClickListener listener){

        itemList = new ArrayList<>();
        clickListener = listener;
    }

    public void addItem(Tiendaitem item){
        itemList.add(item);
        notifyDataSetChanged();
    }

    public void addItems(List<Tiendaitem> items){
        itemList.addAll(items);
        notifyDataSetChanged();
    }

    public void setItems(List<Tiendaitem> items){
        itemList = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tienda_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setTag(itemList.get(position));
        holder.itemView.setOnClickListener(clickListener);

        holder.contentView.setText(itemList.get(position).nombre);

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView contentView;

        ViewHolder(View view) {
            super(view);
            contentView = view.findViewById(R.id.contenNombre);
        }
    }
}
