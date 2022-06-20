package com.example.zapatillasapp.data;

import java.util.List;

public interface RepositoryContract {

    interface FetchTiendaCatalogDataCallback {
        void onCatalogDataFetched(boolean error);
    }

     interface GetTiendaListCallback {
         void setTiendaList(List<Tiendaitem> tiendas);
    }

    void loadCatalog(
            boolean clearFirst, ZapatillaAppRepository.FetchTiendaCatalogDataCallback callback);

    void getTiendaList(ZapatillaAppRepository.GetTiendaListCallback callback);
}
