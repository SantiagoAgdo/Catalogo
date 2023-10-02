package com.mibanco.catalogo.es.catalogo.es.grpc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mibanco.catalogo.es.CatalogoServiceGrpc;
import com.mibanco.catalogo.es.CatalogoTypeGrpc;
import com.mibanco.catalogo.es.DataConsulta;
import com.mibanco.catalogo.es.dao.entity.CatalogoEntity;
import com.mibanco.catalogo.es.gen.type.CatalogoType;
import com.mibanco.catalogo.es.services.impl.CatalogoServiceImpl;
import com.mibanco.catalogo.es.utils.mappers.CatalogoGrpcMapper;
import io.quarkus.grpc.GrpcClient;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@QuarkusTest
public class CatalogoGRPCTest {

    @GrpcClient
    CatalogoServiceGrpc client;

    @Inject
    CatalogoServiceImpl serviceImpl;

    @Inject
    ObjectMapper objectMapper;

    @Mock
    private ObjectMapper mockObjectMapper;

    @Inject
    CatalogoGrpcMapper mapperGRPC;

    CatalogoServiceGrpc getClient() {
        return client;
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    public CatalogoTypeGrpc getCatalogoGrpc() throws IOException {

        String jsonString = new String(Files.readAllBytes(Paths.get("src/main/resources/json/es-Catalogo.json")), StandardCharsets.UTF_8);
        CatalogoType clienteType = objectMapper.readValue(jsonString, CatalogoType.class);

        return mapperGRPC.catalogoToGrpc(clienteType);
    }

    @Test
    public void testCrearCatalogo() throws IOException, ExecutionException, InterruptedException, TimeoutException {

        CatalogoTypeGrpc catalogo = this.getCatalogoGrpc();

        CompletableFuture<CatalogoTypeGrpc> message = new CompletableFuture<>();

        client.crearCatalogo(catalogo)
                .subscribe()
                .with(reply -> message.complete(reply.getObj()));

        Assertions.assertThat(message.get(5, TimeUnit.SECONDS)).isEqualTo(catalogo);

    }

    @Test
    public void actualizarCatalogo() throws IOException, ExecutionException, InterruptedException, TimeoutException {

        CatalogoTypeGrpc catalogo = this.getCatalogoGrpc();

        CompletableFuture<CatalogoTypeGrpc> message = new CompletableFuture<>();

        client.actualizarCatalogo(catalogo)
                .subscribe()
                .with(reply -> message.complete(reply.getObj()));

        Assertions.assertThat(message.get(5, TimeUnit.SECONDS)).isEqualTo(catalogo);

    }

    @Test
    public void eliminarCatalogo() throws IOException, ExecutionException, InterruptedException, TimeoutException {

        CompletableFuture<String> message = new CompletableFuture<>();

        DataConsulta id = DataConsulta.newBuilder().setId("500").build();

        client.eliminarCatalogoPorId(id)
                .subscribe()
                .with(reply -> message.complete(reply.getTxt()));

        Assertions.assertThat(message.get(5, TimeUnit.SECONDS)).isEqualTo("Eliminado Correctamente");

    }

    @Test
    public void consultarCatalogoID() throws IOException, ExecutionException, InterruptedException, TimeoutException {

        this.creatCatalogoMock();
        CatalogoTypeGrpc catalogo = this.getCatalogoGrpc();

        CompletableFuture<CatalogoTypeGrpc> message = new CompletableFuture<>();

        DataConsulta id = DataConsulta.newBuilder().setId("500").build();

        client.consultarCatalogoPorId(id)
                .subscribe()
                .with(reply -> message.complete(reply.getObj()));


        Assertions.assertThat(message.get(5, TimeUnit.SECONDS)).isEqualTo(catalogo);

    }

    @Test
    public void consultarCatalogoNombre() throws IOException, ExecutionException, InterruptedException, TimeoutException {

        this.creatCatalogoMock();

        CompletableFuture<List<CatalogoTypeGrpc>> message = new CompletableFuture<>();

        DataConsulta id = DataConsulta.newBuilder().setId("500").build();

        client.consultarCatalogoPorNombre(id)
                .subscribe()
                .with(reply -> message.complete(reply.getObjList()));


        Assertions.assertThat(message.get(5, TimeUnit.SECONDS)).asList();

    }


    public void creatCatalogoMock() throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get("src/main/resources/json/es-Catalogo.json")), StandardCharsets.UTF_8);
        CatalogoEntity catalogo = objectMapper.readValue(jsonString, CatalogoEntity.class);

        serviceImpl.crearCatalogo(catalogo);
    }

}
