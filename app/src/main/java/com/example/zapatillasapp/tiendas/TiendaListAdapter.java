package com.example.zapatillasapp.tiendas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
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
                .inflate(R.layout.tienda_list_content2, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.itemView.setTag(itemList.get(position));
        holder.itemView.setOnClickListener(clickListener);

        holder.tiendaTxt.setText(itemList.get(position).nombre);

        holder.tiendaLogo.setTag(itemList.get(position));
        //holder.tiendaLogo.setOnClickListener(clickListener);

        //holder.itemView.setOnClickListener(clickListener);
        //holder.contentView.setText(itemList.get(position).nombre);

        loadImageFromURL(holder.tiendaLogo, itemList.get(position).direccion);

    }

    class ViewHolder extends RecyclerView.ViewHolder {

        //final TextView contentView;

        final TextView tiendaTxt;
        final ImageView tiendaLogo;

        ViewHolder(View view) {
            super(view);

            //contentView = view.findViewById(R.id.contenNombre);

            tiendaTxt = view.findViewById(R.id.tiendaTxt);
            tiendaLogo = view.findViewById(R.id.tiendaLogo);
        }
    }

    private void loadImageFromURL(ImageView imageView, String imageUrl) {
        RequestManager reqManager = Glide.with(imageView.getContext());
        RequestBuilder reqBuilder = reqManager.load(imageUrl);
        RequestOptions reqOptions = new RequestOptions();
        reqOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        reqBuilder.apply(reqOptions);
        reqBuilder.into(imageView);
    }
}
