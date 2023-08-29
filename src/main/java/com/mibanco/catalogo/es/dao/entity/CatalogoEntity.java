package com.mibanco.catalogo.es.dao.entity;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatalogoEntity {

    private String codigoDetalleCatalogo;
    private String descripcionDetalleCatalogo;
    private String idCatalogo;
    private String nombreCatalogo;
    private String padreIDCatalogo;
}
