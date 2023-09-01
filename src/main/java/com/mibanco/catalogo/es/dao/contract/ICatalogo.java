package com.mibanco.catalogo.es.dao.contract;

import com.mibanco.catalogo.es.dao.entity.CatalogoEntity;
import com.mibanco.catalogo.es.gen.type.CatalogoType;

import java.util.List;

public interface ICatalogo {

    void crearCatalogo(CatalogoEntity catalogo);

    CatalogoType actualizarCatalogo(CatalogoEntity catalogo);

    boolean eliminarCatalogoPorId(String id);

    CatalogoType consultarCatalogoPorId(String id);

    List<CatalogoEntity> consultarCatalogoPorNombre(String nombre);
}
