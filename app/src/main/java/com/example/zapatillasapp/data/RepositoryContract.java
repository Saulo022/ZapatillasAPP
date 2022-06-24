package com.example.zapatillasapp.data;

import java.util.List;

public interface RepositoryContract {

    void getZapatillaList(ZapatillaItem zapatilla, GetZapatillaListCallback callback);

    void getZapatillaList(final int fk_tiendaId,final String marca, GetZapatillaListCallback callback);

    interface FetchTiendaCatalogDataCallback {
        void onCatalogDataFetched(boolean error);
    }

     interface GetTiendaListCallback {
         void setTiendaList(List<Tiendaitem> tiendas);
    }

    interface GetMarcaListCallback {
        void setMarcaList(List<ZapatillaItem> marcas);
    }
    
    interface GetZapatillaListCallback {
        void setZapatillaList(List<ZapatillaItem> zapatillas);
    }

    void getMarcaList(
            Tiendaitem tienda, RepositoryContract.GetMarcaListCallback callback
    );

    void getMarcaList(final int fk_tiendaID, GetMarcaListCallback callback);

    interface GetTiendaCallback {
        void setTienda(Tiendaitem tienda);
    }

    void loadCatalog(
            boolean clearFirst, ZapatillaAppRepository.FetchTiendaCatalogDataCallback callback);

    void getTiendaList(ZapatillaAppRepository.GetTiendaListCallback callback);
}
