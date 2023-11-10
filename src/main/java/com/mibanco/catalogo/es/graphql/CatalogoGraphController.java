package com.mibanco.catalogo.es.graphql;

import com.mibanco.catalogo.es.dao.entity.CatalogoEntity;
import com.mibanco.catalogo.es.gen.type.CatalogoType;
import com.mibanco.catalogo.es.services.impl.CatalogoServiceImpl;
import com.mibanco.catalogo.es.utils.exceptions.ApplicationException;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


@GraphQLApi
public class CatalogoGraphController {

    public static final Logger logger = LoggerFactory.getLogger(CatalogoGraphController.class);

    @Inject
    CatalogoServiceImpl service;

    @Query("consultarCatalogoPorID")
    @Description("Consulta de Catalogo por ID")
    public CatalogoType consultarCatalogoId(@Name("id") String id) {
        logger.info("Inicia consultarCatalogoId en GraphQL");
        try {
            CatalogoType catalogo = service.consultarCatalogoPorId(id);
            logger.info("Termina consultarCatalogoId en GraphQL");
            return catalogo;
        } catch (ApplicationException e) {
            logger.error("Ocurrió un error en consultarCatalogoId GraphQL");
            throw new ApplicationException(Response.Status.NOT_FOUND.getStatusCode(), "ERROR_SERVICIO: " + e.getMessage() + " en CatalogoGraphController");
        }
    }

    @Query("consultarCatalogoPorNombre")
    @Description("Consulta de Catalogo por nombre")
    public List<CatalogoEntity> consultarCatalogoNombre(@Name("nombre") String nombre) {
        logger.info("Inicia consultarCatalogoNombre en GraphQL");
        try {
            List<CatalogoEntity> catalogo = service.consultarCatalogoPorNombre(nombre);
            logger.info("Termina consultarCatalogoNombre en GraphQL");
            return catalogo;
        } catch (ApplicationException e) {
            logger.error("Ocurrió un error en consultarCatalogoNombre GraphQL");
            throw new ApplicationException(Response.Status.NOT_FOUND.getStatusCode(), "ERROR_SERVICIO: " + e.getMessage() + " en CatalogoGraphController");
        }
    }


}
