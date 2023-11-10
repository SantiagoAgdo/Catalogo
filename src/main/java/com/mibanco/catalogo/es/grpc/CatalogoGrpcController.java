package com.mibanco.catalogo.es.grpc;

import com.mibanco.catalogo.es.*;
import com.mibanco.catalogo.es.dao.entity.CatalogoEntity;
import com.mibanco.catalogo.es.gen.type.CatalogoType;
import com.mibanco.catalogo.es.services.impl.CatalogoServiceImpl;
import com.mibanco.catalogo.es.utils.exceptions.ApplicationExceptionValidation;
import com.mibanco.catalogo.es.utils.mappers.CatalogoGrpcMapper;
import com.mibanco.catalogo.es.utils.validator.CatalogoValidator;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
public class CatalogoGrpcController extends CatalogoServiceGrpcGrpc.CatalogoServiceGrpcImplBase {

    public static final Logger LOG = LoggerFactory.getLogger(CatalogoGrpcController.class);

    @Inject
    CatalogoServiceImpl catalogoService;

    @Inject
    CatalogoGrpcMapper mapper;

    @Inject
    CatalogoValidator catalogoValidator;

    @Override
    @Blocking
    public void crearCatalogo(CatalogoTypeGrpc request, StreamObserver<ResponseCatalogo> responseObs) {
        LOG.info("Inicia Creación de Catálogo por gRPC");

        try {
            CatalogoEntity entity = mapper.catalogoGrpcToEntity(request);
            catalogoService.crearCatalogo(entity);

            ResponseCatalogo response = ResponseCatalogo.newBuilder().setObj(request.toBuilder()).build();
            LOG.info("Finaliza Creación de Catálogo por gRPC");

            responseObs.onNext(response);
            responseObs.onCompleted();

        } catch (Exception e) {
            StatusException statusException = responseExceptionGrpc(Status.INVALID_ARGUMENT, e.getMessage());
            responseObs.onError(statusException);
        }
    }

    @Override
    @Blocking
    public void actualizarCatalogo(CatalogoTypeGrpc request, StreamObserver<ResponseCatalogo> responseObs) {
        LOG.info("Inicia Actualización de Catálogo por gRPC");

        try {
            CatalogoEntity entity = mapper.catalogoGrpcToEntity(request);
            catalogoService.actualizarCatalogo(entity);

            ResponseCatalogo response = ResponseCatalogo.newBuilder().setObj(request.toBuilder()).build();
            LOG.info("Finaliza Actualización de Catálogo por gRPC");

            responseObs.onNext(response);
            responseObs.onCompleted();

        } catch (Exception e) {
            StatusException statusException = responseExceptionGrpc(Status.INVALID_ARGUMENT, e.getMessage());
            responseObs.onError(statusException);
        }
    }

    @Override
    @Blocking
    public void eliminarCatalogoPorId(DataConsulta request, StreamObserver<ResponseTxt> responseObs) {
        LOG.info("Inicia Eliminación de Catálogo por gRPC");

        try {
            catalogoService.eliminarCatalogoPorId(request.getId());
            ResponseTxt responseTexto = ResponseTxt.newBuilder().setTxt("Eliminado Correctamente").build();
            responseObs.onNext(responseTexto);

            LOG.info("Finaliza Eliminación de Catálogo por gRPC");
            responseObs.onCompleted();

        } catch (Exception e) {
            StatusException statusException = responseExceptionGrpc(Status.INVALID_ARGUMENT, e.getMessage());
            responseObs.onError(statusException);
        }
    }


    @Override
    @Blocking
    public void consultarCatalogoPorId(DataConsulta request, StreamObserver<ResponseCatalogo> responseObs) {
        LOG.info("Inicia consultarCatalogoPorId por gRPC");

        try {
            catalogoValidator.validarConsulta(request.getId());
            CatalogoType catalogo = catalogoService.consultarCatalogoPorId(request.getId());

            ResponseCatalogo response = ResponseCatalogo.newBuilder().setObj(
                    CatalogoTypeGrpc.newBuilder()
                            .setIdCatalogo(catalogo.getIdCatalogo())
                            .setNombreCatalogo(catalogo.getNombreCatalogo())
                            .setCodigoDetalleCatalogo(catalogo.getCodigoDetalleCatalogo())
                            .setDescripcionDetalleCatalogo(catalogo.getDescripcionDetalleCatalogo())
                            .setPadreIDCatalogo(catalogo.getPadreIDCatalogo())
                            .build()
            ).build();

            LOG.info("Finaliza consultarCatalogoPorId por gRPC");
            responseObs.onNext(response);
            responseObs.onCompleted();

        } catch (ApplicationExceptionValidation e) {
            StatusException statusException = responseExceptionGrpc(Status.INVALID_ARGUMENT, e.getMessage());
            responseObs.onError(statusException);
        } catch (Exception e) {
            StatusException statusException = responseExceptionGrpc(Status.INTERNAL, e.getMessage());
            responseObs.onError(statusException);
        }
    }

    @Override
    @Blocking
    public void consultarCatalogoPorNombre(DataConsulta request, StreamObserver<ResponseCatalogoList> responseObs) {
        LOG.info("Inicia consultarCatalogoPorNombre por gRPC");

        try {
            catalogoValidator.validarConsulta(request.getId());
            List<CatalogoEntity> catalogoList = catalogoService.consultarCatalogoPorNombre(request.getId());

            List<com.mibanco.catalogo.es.CatalogoTypeGrpc> catalogoResponseList = catalogoList.stream()
                    .map(catalogo -> com.mibanco.catalogo.es.CatalogoTypeGrpc.newBuilder()
                            .setIdCatalogo(catalogo.getIdCatalogo())
                            .setCodigoDetalleCatalogo(catalogo.getCodigoDetalleCatalogo())
                            .setNombreCatalogo(catalogo.getNombreCatalogo())
                            .setDescripcionDetalleCatalogo(catalogo.getDescripcionDetalleCatalogo())
                            .setPadreIDCatalogo(catalogo.getPadreIDCatalogo())
                            .build())
                    .collect(Collectors.toList());

            ResponseCatalogoList response = ResponseCatalogoList.newBuilder().addAllObj(catalogoResponseList).build();
            LOG.info("Finaliza consultarCatalogoPorNombre por gRPC");

            responseObs.onNext(response);
            responseObs.onCompleted();

        } catch (ApplicationExceptionValidation e) {
            StatusException statusException = responseExceptionGrpc(Status.INVALID_ARGUMENT, e.getMessage());
            responseObs.onError(statusException);
        } catch (Exception e) {
            StatusException statusException = responseExceptionGrpc(Status.INTERNAL, e.getMessage());
            responseObs.onError(statusException);
        }
    }

    private StatusException responseExceptionGrpc(Status statusCode, String exceptionMessage) {
        LOG.error(exceptionMessage + " Exception: " + exceptionMessage);

        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("Error: ", Metadata.ASCII_STRING_MARSHALLER), exceptionMessage);

        return statusCode.asException(metadata);
    }

}
