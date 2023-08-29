package com.mibanco.catalogo.es.services.impl;

import com.mibanco.catalogo.es.dao.entity.CatalogoEntity;
import com.mibanco.catalogo.es.gen.type.CatalogoType;
import com.mibanco.catalogo.es.services.contract.CatalogoService;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CatalogoServiceImpl implements CatalogoService {
    @Override
    public void crearCatalogo(CatalogoEntity catalogo) {

    }

    @Override
    public CatalogoType actualizarCatalogo(CatalogoEntity catalogo) {
        return null;
    }

    @Override
    public void eliminarCatalogoPorId(String id) {

    }

    @Override
    public CatalogoType consultarCatalogoPorId(String id) {
        return null;
    }

    @Override
    public List<CatalogoType> consultarCatalogoPorNombre(String nombre) {
        return null;
    }
}
