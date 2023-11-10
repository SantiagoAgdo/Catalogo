package com.mibanco.catalogo.es.utils.mappers;

import com.google.gson.Gson;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.util.JsonFormat;
import com.mibanco.catalogo.es.CatalogoTypeGrpc;
import com.mibanco.catalogo.es.dao.entity.CatalogoEntity;
import com.mibanco.catalogo.es.gen.type.CatalogoType;
import jakarta.enterprise.context.ApplicationScoped;
import org.modelmapper.ModelMapper;

import java.io.IOException;

@ApplicationScoped
public class CatalogoGrpcMapper {

    private final Gson obj = new Gson();


    public CatalogoEntity catalogoGrpcToEntity(CatalogoTypeGrpc grpc) {
        return new ModelMapper().map(grpc, CatalogoEntity.class);
    }

    public String toJson(MessageOrBuilder messageOrBuilder) throws IOException {
        return JsonFormat.printer().print(messageOrBuilder);
    }

    public CatalogoTypeGrpc catalogoToGrpc(CatalogoType clienteCDTDigitalType) throws IOException {
        CatalogoTypeGrpc.Builder structBuilder = CatalogoTypeGrpc.newBuilder();
        JsonFormat.parser().ignoringUnknownFields().merge(this.obj.toJson(clienteCDTDigitalType), structBuilder);
        return structBuilder.build();
    }


}
