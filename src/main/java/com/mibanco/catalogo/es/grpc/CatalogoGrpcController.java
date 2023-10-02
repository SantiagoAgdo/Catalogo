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

import java.util.ArrayList;
import java.util.List;

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

        LOG.info("Inicia Creacion Catalogo por GRPC");

        try {
            CatalogoEntity entity = mapper.catalogoGrpcToEntity(request);
            catalogoService.crearCatalogo(entity);

            ResponseCatalogo response = ResponseCatalogo.newBuilder().setObj(request.toBuilder()).build();
            LOG.info("Finaliza creacion Catalogo por GRPC");

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

        LOG.info("Inicia Actualizacion Catalogo por GRPC");

        try {
            CatalogoEntity entity = mapper.catalogoGrpcToEntity(request);
            catalogoService.actualizarCatalogo(entity);

            ResponseCatalogo response = ResponseCatalogo.newBuilder().setObj(request.toBuilder()).build();
            LOG.info("Finaliza Actualizacion Catalogo por GRPC");

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

        LOG.info("Inicia Actualizacion Catalogo por GRPC");

        try {
            catalogoService.eliminarCatalogoPorId(request.getId());
            ResponseTxt responseTexto = ResponseTxt.newBuilder().setTxt("Eliminado Correctamente").build();
            responseObs.onNext(responseTexto);

            LOG.info("Finaliza Actualizacion Catalogo por GRPC");
            responseObs.onCompleted();

        } catch (Exception e) {

            StatusException statusException = responseExceptionGrpc(Status.INVALID_ARGUMENT, e.getMessage());
            responseObs.onError(statusException);
        }
    }

    @Override
    @Blocking
    public void consultarCatalogoPorId(DataConsulta request, StreamObserver<ResponseCatalogo> responseObs) {

        LOG.info("Inicia consultarCatalogoPorId por GRPC");
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
            ).build();

            LOG.info("Finaliza consultarPasivo por GRPC");
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
        LOG.info("Inicia consultarCatalogoPorNombre por GRPC");
        try {
            catalogoValidator.validarConsulta(request.getId());
            List<CatalogoEntity> cupoList = catalogoService.consultarCatalogoPorNombre(request.getId());

            List<com.mibanco.catalogo.es.CatalogoTypeGrpc> catalogoResponse = new ArrayList<>();
            for (CatalogoEntity catalogo : cupoList) {
                catalogoResponse.add(com.mibanco.catalogo.es.CatalogoTypeGrpc.newBuilder()
                        .setIdCatalogo(catalogo.getIdCatalogo())
                        .setCodigoDetalleCatalogo(catalogo.getCodigoDetalleCatalogo().toString())
                        .setNombreCatalogo(catalogo.getNombreCatalogo())
                        .setDescripcionDetalleCatalogo(catalogo.getDescripcionDetalleCatalogo())
                        .setPadreIDCatalogo(catalogo.getPadreIDCatalogo())
                        .build());
            }
            ResponseCatalogoList response = ResponseCatalogoList.newBuilder().addAllObj(catalogoResponse).build();
            LOG.info("Finaliza consultarCatalogoPorNombre por GRPC");

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

        LOG.error(exceptionMessage + "Exception: " + exceptionMessage);

        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("Error: ", Metadata.ASCII_STRING_MARSHALLER), exceptionMessage);

        return statusCode.asException(metadata);
    }
}
