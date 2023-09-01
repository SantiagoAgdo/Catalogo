package com.mibanco.catalogo.es.utils.mappers;

import com.mibanco.catalogo.es.CatalogoTypeGrpc;
import com.mibanco.catalogo.es.dao.entity.CatalogoEntity;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

@ApplicationScoped
public class CatalogoGrpcMapper {
    public CatalogoEntity catalogoGrpcToEntity(CatalogoTypeGrpc grpc) {
        return new ModelMapper().map(grpc, CatalogoEntity.class);
    }

}
