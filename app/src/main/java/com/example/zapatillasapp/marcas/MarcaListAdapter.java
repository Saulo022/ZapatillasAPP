package com.example.zapatillasapp.marcas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zapatillasapp.R;
import com.example.zapatillasapp.data.MarcaItem;
import com.example.zapatillasapp.data.ZapatillaItem;
import com.example.zapatillasapp.tiendas.TiendaListAdapter;

import java.util.ArrayList;
import java.util.List;

public class MarcaListAdapter extends RecyclerView.Adapter<MarcaListAdapter.ViewHolder> {

    private List<ZapatillaItem> itemList;
    private final View.OnClickListener clickListener;

    public MarcaListAdapter(View.OnClickListener listener){
        itemList = new ArrayList<>();
        clickListener = listener;
    }

    public void addItem(ZapatillaItem item){
        itemList.add(item);
        notifyDataSetChanged();
    }

    public void addItems(List<ZapatillaItem> items){
        itemList.addAll(items);
        notifyDataSetChanged();
    }

    public void setItems(List<ZapatillaItem> items){
        itemList = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.marca_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.contentView.setText(itemList.get(position).marcas);

        holder.itemView.setTag(itemList.get(position));
        holder.itemView.setOnClickListener(clickListener);
    }

    @Override
    public int getItemCount(){
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        final TextView contentView;

        ViewHolder(View view){
            super(view);
            contentView = view.findViewById(R.id.content);
        }
    }
}
