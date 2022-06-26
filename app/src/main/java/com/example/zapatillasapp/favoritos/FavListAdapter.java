package com.example.zapatillasapp.favoritos;

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
import com.example.zapatillasapp.data.ZapatillaItem;
import com.example.zapatillasapp.zapatillas.ZapatillasListAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavListAdapter extends RecyclerView.Adapter<FavListAdapter.ViewHolder> {

    private List<ZapatillaItem> itemList;
    private final View.OnClickListener clickListener;

    public FavListAdapter(View.OnClickListener listener){
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
                .inflate(R.layout.fav_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        holder.itemView.setTag(itemList.get(position));
        holder.itemView.setOnClickListener(clickListener);

        holder.itemView.setTag(itemList.get(position));
        holder.favTiendaTxt.setText(itemList.get(position).tienda1);

        holder.itemView.setTag(itemList.get(position));
        holder.favNombreTxt.setText(itemList.get(position).nombre);

        holder.itemView.setTag(itemList.get(position));
        holder.favPrecioTxt.setText(itemList.get(position).precio);

        holder.favLogo.setTag(itemList.get(position));
        //holder.favLogo.setOnClickListener(clickListener);

        loadImageFromURL(holder.favLogo, itemList.get(position).fotoZap);
    }

    @Override
    public int getItemCount(){
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        //final TextView contentView;

        final TextView favTiendaTxt, favNombreTxt, favPrecioTxt;
        final ImageView favLogo;

        ViewHolder(View view){
            super(view);

            favLogo = view.findViewById(R.id.favLogo);
            favTiendaTxt = view.findViewById(R.id.favTiendaTxt);
            favNombreTxt = view.findViewById(R.id.favNombreTxt);
            favPrecioTxt = view.findViewById(R.id.favPrecioTxt);
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
