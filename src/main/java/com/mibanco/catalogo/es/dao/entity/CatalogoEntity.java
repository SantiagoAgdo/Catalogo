package com.mibanco.catalogo.es.dao.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RegisterForReflection
public class CatalogoEntity {

    private String codigoDetalleCatalogo;
    private String descripcionDetalleCatalogo;
    private String idCatalogo;
    private String nombreCatalogo;
    private String padreIDCatalogo;
}
