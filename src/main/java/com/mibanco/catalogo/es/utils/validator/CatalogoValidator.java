package com.mibanco.catalogo.es.utils.validator;

import com.mibanco.catalogo.es.constants.Constants;
import com.mibanco.catalogo.es.gen.type.CatalogoType;
import com.mibanco.catalogo.es.utils.exceptions.ApplicationExceptionValidation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class CatalogoValidator {

    public void verificarCatalogo(CatalogoType catalogo) throws ApplicationExceptionValidation {
        validarCampoRequerido(catalogo.getIdCatalogo(), "Id");
        validarCampoRequerido(catalogo.getNombreCatalogo(), "Nombre");
        validarCampoRequerido(catalogo.getPadreIDCatalogo(), "IDPadre");
        validarCampoRequerido(catalogo.getDescripcionDetalleCatalogo(), "Descripción");
        validarCampoRequerido(catalogo.getCodigoDetalleCatalogo(), "Código");
    }

    public void validarConsulta(String idCatalogo) {
        if (idCatalogo == null) {
            throw new ApplicationExceptionValidation(
                    Response.Status.BAD_REQUEST.getStatusCode(), Constants.VALIDACION + " Id no debe ser nulo."
            );
        }
    }

    private void validarCampoRequerido(String valor, String nombreCampo) throws ApplicationExceptionValidation {
        if (valor == null || valor.isEmpty()) {
            throw new ApplicationExceptionValidation(
                    Response.Status.BAD_REQUEST.getStatusCode(), Constants.SERVICIO_INTERNAL + nombreCampo + " obligatorio."
            );
        }
    }

}
