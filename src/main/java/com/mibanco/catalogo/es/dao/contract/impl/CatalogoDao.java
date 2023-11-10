package com.mibanco.catalogo.es.dao.contract.impl;

import com.mibanco.catalogo.es.dao.contract.ICatalogo;
import com.mibanco.catalogo.es.dao.entity.CatalogoEntity;
import com.mibanco.catalogo.es.gen.type.CatalogoType;
import com.mibanco.catalogo.es.utils.mappers.CatalogoMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@ApplicationScoped
public class CatalogoDao implements ICatalogo {


    @Inject
    CatalogoMapper mapper;


    List<CatalogoEntity> list = new ArrayList<>();

    @Override
    public void crearCatalogo(CatalogoEntity catalogo) {
        list.add(catalogo);
    }

    @Override
    public CatalogoType actualizarCatalogo(CatalogoEntity catalogo) {
        int index = catalogoExiste(catalogo.getIdCatalogo());
        if (index == -1) {
            return null;
        }
        list.set(index, new CatalogoEntity(
                catalogo.getCodigoDetalleCatalogo(),
                catalogo.getDescripcionDetalleCatalogo(),
                catalogo.getIdCatalogo(),
                catalogo.getNombreCatalogo(),
                catalogo.getPadreIDCatalogo()
        ));
        return mapper.catalogoToType(catalogo);
    }

    @Override
    public boolean eliminarCatalogoPorId(String id) {
        int index = catalogoExiste(id);
        if (index == -1) {
            return false;
        }
        list.remove(list.get(index));
        return true;
    }

    @Override
    public CatalogoType consultarCatalogoPorId(String id) {
        CatalogoEntity catalogo = list.stream()
                .filter(x -> x.getIdCatalogo().equals(id))
                .findFirst()
                .orElse(null);

        return (catalogo != null) ? mapper.catalogoToType(catalogo) : new CatalogoType();
    }

    @Override
    public List<CatalogoEntity> consultarCatalogoPorNombre(String nombre) {
        List<CatalogoEntity> catalogo = list.stream()
                .filter(x -> x.getNombreCatalogo().equals(nombre))
                .collect(Collectors.toList());

        return (catalogo != null) ? catalogo : new ArrayList<>();
    }

    int catalogoExiste(String id) {
        for (CatalogoEntity catalogo : list) {
            if (catalogo.getIdCatalogo().equals(id)) {
                return list.indexOf(catalogo);
            }
        }
        return -1;
    }

}
