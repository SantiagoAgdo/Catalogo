package com.mibanco.catalogo.es.utils.mappers;

import com.mibanco.catalogo.es.dao.entity.CatalogoEntity;
import com.mibanco.catalogo.es.gen.type.CatalogoType;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CatalogoMapper {

    public CatalogoEntity catalogoToEntity(CatalogoType entity) {
        return new ModelMapper().map(entity, CatalogoEntity.class);
    }

    public CatalogoType catalogoToType(CatalogoEntity entity) {
        return new ModelMapper().map(entity, CatalogoType.class);
    }

}

