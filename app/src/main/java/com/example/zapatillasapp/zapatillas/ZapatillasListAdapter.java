package com.example.zapatillasapp.zapatillas;

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
import com.example.zapatillasapp.marcas.MarcaListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ZapatillasListAdapter extends RecyclerView.Adapter<ZapatillasListAdapter.ViewHolder> {

    private List<ZapatillaItem> itemList;
    private final View.OnClickListener clickListener;

    public ZapatillasListAdapter(View.OnClickListener listener){
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
                .inflate(R.layout.zapatilla_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        //holder.contentView.setText(itemList.get(position).marcas);
        holder.itemView.setTag(itemList.get(position));
        holder.zapatillaTxt.setText(itemList.get(position).nombre);

        holder.zapatillaLogo.setTag(itemList.get(position));
        holder.zapatillaLogo.setOnClickListener(clickListener);

        loadImageFromURL(holder.zapatillaLogo, itemList.get(position).fotoZap);
    }

    @Override
    public int getItemCount(){
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        //final TextView contentView;

        final TextView zapatillaTxt;
        final ImageView zapatillaLogo;

        ViewHolder(View view){
            super(view);

            //contentView = view.findViewById(R.id.content);

            zapatillaTxt = view.findViewById(R.id.zapatillaTxt);
            zapatillaLogo = view.findViewById(R.id.zapatillaLogo);
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
