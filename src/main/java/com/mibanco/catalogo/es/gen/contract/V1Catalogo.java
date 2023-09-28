package com.mibanco.catalogo.es.gen.contract;

import com.mibanco.catalogo.es.gen.type.CatalogoType;
import com.mibanco.catalogo.es.gen.type.Error;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;



import java.io.InputStream;
import java.util.Map;
import java.util.List;
import jakarta.validation.constraints.*;
import jakarta.validation.Valid;

@Path("/v1/es/catalogo")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2023-09-08T10:41:34.236119300-05:00[America/Bogota]")
public interface V1Catalogo {

    @GET
    @Path("/{idCatalogo}")
    @Produces({ "application/json" })
    Response consultarCatalogo(@PathParam("idCatalogo") String idCatalogo);

    @GET
    @Path("/nombre/{nombre}")
    @Produces({ "application/json" })
    Response consultarCatalogoPorNombre(@PathParam("nombre") String nombre);

    @POST
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    Response crearCatalogo(@Valid CatalogoType catalogoType);

    @DELETE
    @Path("/{idCatalogo}")
    @Produces({ "application/json" })
    Response deleteCatalogo(@PathParam("idCatalogo") String idCatalogo);

    @PUT
    @Consumes({ "application/json" })
    @Produces({ "application/json" })
    Response updateCatalogo(@Valid CatalogoType catalogoType);
}
