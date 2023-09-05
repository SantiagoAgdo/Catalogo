package com.mibanco.catalogo.es.utils.validator;

import com.mibanco.catalogo.es.constans.Constans;
import com.mibanco.catalogo.es.gen.type.CatalogoType;
import com.mibanco.catalogo.es.utils.exceptions.ApplicationExceptionValidation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class CatalogoValidator {

    public static final Logger logger = LoggerFactory.getLogger(CatalogoValidator.class);

    public void verificarCatalogo(CatalogoType catalogo) throws ApplicationExceptionValidation {

        if (catalogo.getIdCatalogo() != null) {
            throw new ApplicationExceptionValidation(
                    Response.Status.BAD_REQUEST.getStatusCode(), Constans.SERVICIO_INTERNAL + "Id " + " obligatorio"
            );
        }
        if (catalogo.getNombreCatalogo() != null) {
            throw new ApplicationExceptionValidation(
                    Response.Status.BAD_REQUEST.getStatusCode(), Constans.SERVICIO_INTERNAL + "Nombre " + " obligatorio"
            );
        }
        if (catalogo.getPadreIDCatalogo() != null) {
            throw new ApplicationExceptionValidation(
                    Response.Status.BAD_REQUEST.getStatusCode(), Constans.SERVICIO_INTERNAL + "IDPadre " + " obligatorio"
            );
        }
        if (catalogo.getDescripcionDetalleCatalogo() != null) {
            throw new ApplicationExceptionValidation(
                    Response.Status.BAD_REQUEST.getStatusCode(), Constans.SERVICIO_INTERNAL + "Descripcion " + " obligatorio"
            );
        }
        if (catalogo.getCodigoDetalleCatalogo() != null) {
            throw new ApplicationExceptionValidation(
                    Response.Status.BAD_REQUEST.getStatusCode(), Constans.SERVICIO_INTERNAL + "Codigo " + " obligatorio"
            );
        }
    }

    public void validarConsulta(String idCatalogo) {
        if (idCatalogo == null) {
            throw new ApplicationExceptionValidation(
                    Response.Status.BAD_REQUEST.getStatusCode(), Constans.VALIDACION + " Id no debe ser nulo."
            );
        }
    }
}
