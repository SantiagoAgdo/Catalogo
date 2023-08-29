package com.mibanco.catalogo.es.utils.mappers;

import com.mibanco.catalogo.es.dao.entity.CatalogoEntity;
import com.mibanco.catalogo.es.gen.type.CatalogoType;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class CatalogoMapper {

    public CatalogoEntity catalogoToEntity(CatalogoType entity) {
        return new ModelMapper().map(entity, CatalogoEntity.class);
    }

}

