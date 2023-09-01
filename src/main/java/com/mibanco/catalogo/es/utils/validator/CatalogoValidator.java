package com.mibanco.catalogo.es.utils.validator;

import com.mibanco.catalogo.es.constans.Constans;
import com.mibanco.catalogo.es.gen.type.CatalogoType;
import com.mibanco.catalogo.es.utils.exceptions.ApplicationExceptionValidation;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

@ApplicationScoped
public class CatalogoValidator {

    public static final Logger logger = LoggerFactory.getLogger(CatalogoValidator.class);

    private String msmError = "";

    public void verificarCatalogo(CatalogoType prestamoType) throws ApplicationExceptionValidation {

//        if (validarObjeto()) {
//            throw new ApplicationExceptionValidation(
//                    Response.Status.BAD_REQUEST.getStatusCode(), Constans.SERVICIO_INTERNAL + msmError + " obligatorios"
//            );
//        }
    }

    public void validarConsulta(String idCatalogo) {
        if (idCatalogo == null) {
            throw new ApplicationExceptionValidation(
                    Response.Status.BAD_REQUEST.getStatusCode(), Constans.VALIDACION + " Id no debe ser nulo."
            );
        }
    }
}
