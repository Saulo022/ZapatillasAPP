package com.example.zapatillasapp.data;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.room.Room;

import com.example.zapatillasapp.database.MarcaDao;
import com.example.zapatillasapp.database.TiendaDao;
import com.example.zapatillasapp.database.ZapatillaDao;
import com.example.zapatillasapp.database.ZapatillasAppDatabase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class ZapatillaAppRepository implements RepositoryContract{

    public static String TAG = ZapatillaAppRepository.class.getSimpleName();

    public static final String DB_FILE = "zapatillas.db";
    public static final String JSON_FILE = "zapatillas.json";
    public static final String JSON_ROOT = "zapatillas";

    private static ZapatillaAppRepository INSTANCE;

    private ZapatillasAppDatabase database;
    private Context context;

    public static RepositoryContract getInstance(Context context) {
        if(INSTANCE == null){
            INSTANCE = new ZapatillaAppRepository(context);
        }
        return INSTANCE;
    }

    private ZapatillaAppRepository(Context context){
        this.context = context;

        database = Room.databaseBuilder(
                context, ZapatillasAppDatabase.class, DB_FILE
        )
                .fallbackToDestructiveMigration()
                .build();
    }


    @Override
    public void getZapatillaList(ZapatillaItem zapatilla, GetZapatillaListCallback callback) {
        getZapatillaList(zapatilla.fk_tiendaId, zapatilla.marcas, callback);

    }

    @Override
    public void getZapatillaList(final int fk_tiendaId,final String marca, GetZapatillaListCallback callback){

        AsyncTask.execute(() -> {
            if (callback != null){
                callback.setZapatillaList(getZapatillaDao().loadsZapatillasMarca(fk_tiendaId, marca));
            }
        });
    }

    @Override
    public void getMarcaList(final Tiendaitem tienda, final GetMarcaListCallback callback){

        getMarcaList(tienda.pk_tienda, callback);
    }

    @Override
    public void getMarcaList(final int fk_tiendaId, GetMarcaListCallback callback) {

        AsyncTask.execute(() -> {
            if(callback != null){
                callback.setMarcaList(getZapatillaDao().loadProducts(fk_tiendaId));
            }
        });
    }

    @Override
    public void loadCatalog(
            final boolean clearFirst, final FetchTiendaCatalogDataCallback callback) {

        AsyncTask.execute(() -> {
            if (clearFirst){

                database.clearAllTables();
            }
            boolean error = false;
            if (getTiendaDao().loadTiendas().size() == 0){
                error = !loadCatalogFromJSON(loadJSONFromAsset());
            }
            if(callback != null) callback.onCatalogDataFetched(error);
        });
    }

    /*
    @Override
    public void getTienda(final int id, final GetTiendaCallback callback) {

        AsyncTask.execute(new Runnable() {

            @Override
            public void run() {
                if(callback != null) {
                    callback.setTienda(getTiendaDao().loadTienda(id));
                }
            }
        });

    }*/

    @Override
    public void getTiendaList(final GetTiendaListCallback callback){
        AsyncTask.execute(() -> {
            if (callback != null){
                callback.setTiendaList(getTiendaDao().loadTiendas());
            }
        });
    }

    private TiendaDao getTiendaDao(){
        return database.tiendaDao();
    }

    private ZapatillaDao getZapatillaDao(){
        return database.zapatillaDao();
    }

    private MarcaDao getMarcaDao(){
        return database.marcaDao();
    }

    private boolean loadCatalogFromJSON(String json){

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        try {

            JSONObject jsonObject = new JSONObject(json);
            JSONArray jsonArray = jsonObject.getJSONArray(JSON_ROOT);

            if (jsonArray.length() > 0){

                final List<Tiendaitem> tiendas = Arrays.asList(
                        gson.fromJson(jsonArray.toString(), Tiendaitem[].class)
                );

                for (Tiendaitem tienda: tiendas){
                    getTiendaDao().insertTienda(tienda);
                }

                for (Tiendaitem tienda: tiendas){
                    for (ZapatillaItem zapatilla: tienda.items){
                        zapatilla.fk_tiendaId = tienda.pk_tienda;
                        getZapatillaDao().insertZapatilla(zapatilla);
                    }
                }
                return true;
            }
        } catch (JSONException error){
            Log.e(TAG, "error: " + error);
        }
        return false;
    }

    private String loadJSONFromAsset(){

        String json = null;

        try {

            InputStream is = context.getAssets().open(JSON_FILE);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException error){
            Log.e(TAG, "error: " + error);
        }
        return json;
    }
}
