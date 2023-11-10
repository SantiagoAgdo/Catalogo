package com.mibanco.catalogo.es.controller;


import com.mibanco.catalogo.es.constants.Constants;
import com.mibanco.catalogo.es.dao.entity.CatalogoEntity;
import com.mibanco.catalogo.es.gen.contract.V1Catalogo;
import com.mibanco.catalogo.es.gen.type.CatalogoType;
import com.mibanco.catalogo.es.services.impl.CatalogoServiceImpl;
import com.mibanco.catalogo.es.utils.exceptions.ApplicationException;
import com.mibanco.catalogo.es.utils.exceptions.ApplicationExceptionValidation;
import com.mibanco.catalogo.es.utils.mappers.CatalogoMapper;
import com.mibanco.catalogo.es.utils.validator.CatalogoValidator;
import io.quarkus.runtime.annotations.RegisterForReflection;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RegisterForReflection(targets = {CatalogoType.class})
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
        LOG.info("Inicia consultarCatalogo en CatalogoController");
        try {
            catalogoValidator.validarConsulta(idCatalogo);
            CatalogoType catalogoType = catalogoService.consultarCatalogoPorId(idCatalogo);

            LOG.info("Finaliza consultarCatalogo en CatalogoController");
            return Response.status(Response.Status.OK).entity(catalogoType).build();
        } catch (ApplicationExceptionValidation e) {
            LOG.error("Error en validaciones de obtención de catálogo - CatalogoController");
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (ApplicationException e) {
            LOG.error(Constants.SERVICIO_INTERNAL + "consultarCatalogo en CatalogoServiceImpl exception: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Constants.SERVICIO_INTERNAL + "consultarCatalogo, exception: " + e.getMessage()).build();
        }
    }

    @Override
    public Response consultarCatalogoPorNombre(String nombre) {
        LOG.info("Inicia consultarCatalogoPorNombre en CatalogoController");
        try {
            catalogoValidator.validarConsulta(nombre);
            List<CatalogoEntity> listCatalogo = catalogoService.consultarCatalogoPorNombre(nombre);

            LOG.info("Finaliza consultarCatalogoPorNombre en CatalogoController");
            return Response.status(Response.Status.OK).entity(listCatalogo).build();
        } catch (ApplicationExceptionValidation e) {
            LOG.error("Error en validaciones de obtención de catálogo - CatalogoController");
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (ApplicationException e) {
            LOG.error(Constants.SERVICIO_INTERNAL + "consultarCatalogoPorNombre en CatalogoServiceImpl exception: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Constants.SERVICIO_INTERNAL + "consultarCatalogoPorNombre, exception: " + e.getMessage()).build();
        }
    }

    @Override
    public Response crearCatalogo(CatalogoType catalogoType) {
        LOG.info("Inicia crearCatalogo en CatalogoController");
        try {
            catalogoValidator.verificarCatalogo(catalogoType);
            CatalogoEntity clienteFIC = catalogoMapper.catalogoToEntity(catalogoType);
            catalogoService.crearCatalogo(clienteFIC);

            LOG.info("Finaliza crearCatalogo en CatalogoController");
            return Response.status(Response.Status.CREATED).entity(catalogoType).build();
        } catch (ApplicationExceptionValidation e) {
            LOG.error("Error en validaciones de creación de catálogo - CatalogoController");
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (ApplicationException e) {
            LOG.error(Constants.SERVICIO_INTERNAL + "crearCatalogo en CatalogoServiceImpl exception: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Constants.SERVICIO_INTERNAL + "crearCatalogo, exception: " + e.getMessage()).build();
        }
    }


    @Override
    public Response deleteCatalogo(String idCatalogo) {
        LOG.info("Inicia deleteCatalogo en CatalogoController");
        try {
            catalogoValidator.validarConsulta(idCatalogo);
            catalogoService.eliminarCatalogoPorId(idCatalogo);

            LOG.info("Finaliza deleteCatalogo en CatalogoController");
            return Response.status(Response.Status.OK).entity("Eliminado exitosamente").build();

        } catch (ApplicationExceptionValidation e) {
            LOG.error("Error en validaciones de eliminación de catálogo - CatalogoController");
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();

        } catch (ApplicationException e) {
            LOG.error(Constants.SERVICIO_INTERNAL + "deleteCatalogo en CatalogoServiceImpl exception: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Constants.SERVICIO_INTERNAL + "deleteCatalogo, exception: " + e.getMessage()).build();
        }
    }

    @Override
    public Response updateCatalogo(CatalogoType catalogoType) {
        LOG.info("Inicia updateCatalogo en CatalogoController");
        try {
            catalogoValidator.verificarCatalogo(catalogoType);
            CatalogoEntity catalogo = catalogoMapper.catalogoToEntity(catalogoType);
            catalogoService.actualizarCatalogo(catalogo);

            LOG.info("Finaliza updateCatalogo en CatalogoController");
            return Response.status(Response.Status.OK).entity("Actualizado exitosamente").build();

        } catch (ApplicationExceptionValidation e) {
            LOG.error("Error en validaciones de actualización de catálogo - CatalogoController");
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();

        } catch (ApplicationException e) {
            LOG.error(Constants.SERVICIO_INTERNAL + "updateCatalogo en CatalogoServiceImpl exception: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Constants.SERVICIO_INTERNAL + "updateCatalogo, exception: " + e.getMessage()).build();
        }
    }

}
