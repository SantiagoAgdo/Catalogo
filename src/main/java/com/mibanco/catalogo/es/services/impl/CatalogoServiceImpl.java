package com.mibanco.catalogo.es.services.impl;

import com.mibanco.catalogo.es.dao.contract.impl.CatalogoDao;
import com.mibanco.catalogo.es.dao.entity.CatalogoEntity;
import com.mibanco.catalogo.es.gen.type.CatalogoType;
import com.mibanco.catalogo.es.services.contract.CatalogoService;
import com.mibanco.catalogo.es.utils.mappers.CatalogoMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ApplicationScoped
public class CatalogoServiceImpl implements CatalogoService {

    public static final Logger LOG = LoggerFactory.getLogger(CatalogoServiceImpl.class);

    @Inject
    CatalogoDao catalogoDao;

    @Inject
    CatalogoMapper mapper;


    @Override
    public void crearCatalogo(CatalogoEntity catalogo) {

        LOG.info("Inicia Creacion de catalogo  en CatalogoServiceImpl");
        catalogoDao.crearCatalogo(catalogo);

        LOG.info("Termina creacion de catalogo en CatalogoServiceImpl");
    }

    @Override
    public CatalogoType actualizarCatalogo(CatalogoEntity catalogo) {

        LOG.info("Inicia Actualizacion de catalogo  en CatalogoServiceImpl");
        catalogoDao.actualizarCatalogo(catalogo);

        LOG.info("Termina Actualizacion de catalogo en CatalogoServiceImpl");
        return mapper.catalogoToType(catalogo);
    }

    @Override
    public void eliminarCatalogoPorId(String id) {
        LOG.info("Inicia eliminarCatalogoPorId  en CatalogoServiceImpl");
        catalogoDao.eliminarCatalogoPorId(id);

        LOG.info("Termina eliminarCatalogoPorIdo en CatalogoServiceImpl");
    }

    @Override
    public CatalogoType consultarCatalogoPorId(String id) {
        LOG.info("Inicia consulta de catalogo  en CatalogoServiceImpl");

        LOG.info("Termina consulta de catalogo en CatalogoServiceImpl");
        return catalogoDao.consultarCatalogoPorId(id);

    }

    @Override
    public List<CatalogoEntity> consultarCatalogoPorNombre(String nombre) {
        LOG.info("Inicia consulta de catalogo por nombre en CatalogoServiceImpl");

        LOG.info("Termina consulta de catalogo por nombre en CatalogoServiceImpl");
        return catalogoDao.consultarCatalogoPorNombre(nombre);
    }
}
