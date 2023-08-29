package com.mibanco.catalogo.es.controller;


import com.mibanco.catalogo.es.constans.Constans;
import com.mibanco.catalogo.es.dao.entity.CatalogoEntity;
import com.mibanco.catalogo.es.gen.contract.V1Catalogo;
import com.mibanco.catalogo.es.gen.type.CatalogoType;
import com.mibanco.catalogo.es.services.impl.CatalogoServiceImpl;
import com.mibanco.catalogo.es.utils.exceptions.ApplicationException;
import com.mibanco.catalogo.es.utils.exceptions.ApplicationExceptionValidation;
import com.mibanco.catalogo.es.utils.mappers.CatalogoMapper;
import com.mibanco.catalogo.es.utils.validator.CatalogoValidator;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CatalogoController implements V1Catalogo {

    public static final Logger LOG = LoggerFactory.getLogger(CatalogoController.class);
    @Inject
    CatalogoServiceImpl catalogoService;

    @Inject
    CatalogoMapper catalogoMapper;

    @Inject
    CatalogoValidator catalogoValidator;

    @Override
    public Response consultarCatalogo(String idCatalogo) {
        return null;
    }

    @Override
    public Response consultarCatalogoPorNombre(String nombre) {
        return null;
    }

    @Override
    public Response crearCatalogo(CatalogoType catalogoType) {
        LOG.info("Inicia crearCatalogo en CatalogoController");
        try {
            catalogoValidator.verificarCatalogo(catalogoType);
            CatalogoEntity clienteFIC = catalogoMapper.catalogoToEntity(catalogoType);
            catalogoService.crearCatalogo(clienteFIC);

            LOG.info("Finaliza crearClienteFIC en CatalogoController");
            return Response.status(Response.Status.CREATED).entity(catalogoType).build();

        } catch (ApplicationExceptionValidation e) {

            LOG.error("Error en validaciones de creacion de catalogo - CatalogoController");
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();

        } catch (ApplicationException e) {

            LOG.error(Constans.SERVICIO_INTERNAL + "crearClienteFIC en CatalogoServiceImpl exception: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Constans.SERVICIO_INTERNAL + "crearCatalogo, exception: " + e.getMessage()).build();
        }
    }

    @Override
    public Response deleteCatalogo(String idCatalogo) {
        return null;
    }

    @Override
    public Response updateCatalogo(CatalogoType catalogoType) {
        return null;
    }
}
