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
        LOG.info("Inicia Creación de catálogo en CatalogoServiceImpl");
        catalogoDao.crearCatalogo(catalogo);
        LOG.info("Termina Creación de catálogo en CatalogoServiceImpl");
    }

    @Override
    public CatalogoType actualizarCatalogo(CatalogoEntity catalogo) {
        LOG.info("Inicia Actualización de catálogo en CatalogoServiceImpl");
        catalogoDao.actualizarCatalogo(catalogo);
        LOG.info("Termina Actualización de catálogo en CatalogoServiceImpl");
        return mapper.catalogoToType(catalogo);
    }

    @Override
    public void eliminarCatalogoPorId(String id) {
        LOG.info("Inicia Eliminación de catálogo por ID en CatalogoServiceImpl");
        catalogoDao.eliminarCatalogoPorId(id);
        LOG.info("Termina Eliminación de catálogo por ID en CatalogoServiceImpl");
    }

    @Override
    public CatalogoType consultarCatalogoPorId(String id) {
        LOG.info("Inicia Consulta de catálogo por ID en CatalogoServiceImpl");
        CatalogoType catalogo = catalogoDao.consultarCatalogoPorId(id);
        LOG.info("Termina Consulta de catálogo por ID en CatalogoServiceImpl");
        return catalogo;
    }

    @Override
    public List<CatalogoEntity> consultarCatalogoPorNombre(String nombre) {
        LOG.info("Inicia Consulta de catálogo por nombre en CatalogoServiceImpl");
        List<CatalogoEntity> catalogoList = catalogoDao.consultarCatalogoPorNombre(nombre);
        LOG.info("Termina Consulta de catálogo por nombre en CatalogoServiceImpl");
        return catalogoList;
    }

}
