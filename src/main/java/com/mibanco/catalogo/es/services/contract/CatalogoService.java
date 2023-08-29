package com.mibanco.catalogo.es.services.contract;

import com.mibanco.catalogo.es.dao.entity.CatalogoEntity;
import com.mibanco.catalogo.es.gen.type.CatalogoType;

import java.util.List;

public interface CatalogoService {

    void crearCatalogo(CatalogoEntity catalogo);

    CatalogoType actualizarCatalogo(CatalogoEntity catalogo);

    void eliminarCatalogoPorId(String id);

    CatalogoType consultarCatalogoPorId(String id);

    List<CatalogoType> consultarCatalogoPorNombre(String nombre);
}
