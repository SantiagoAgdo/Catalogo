package com.mibanco.catalogo.es.catalogo.es.grpc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mibanco.catalogo.es.CatalogoServiceGrpc;
import com.mibanco.catalogo.es.CatalogoTypeGrpc;
import com.mibanco.catalogo.es.DataConsulta;
import com.mibanco.catalogo.es.gen.type.CatalogoType;
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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@QuarkusTest
public class CatalogoGRPCTest {

    @GrpcClient
    CatalogoServiceGrpc client;

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

        // Preparación de datos
        String jsonString = new String(Files.readAllBytes(Paths.get("src/main/resources/json/es-Catalogo.json")), StandardCharsets.UTF_8);
        CatalogoType clienteType = objectMapper.readValue(jsonString, CatalogoType.class);

        //Se retorna la data dataType a protobuf
        return mapperGRPC.catalogoToGrpc(clienteType);
    }

    @Test
    public void testCrearCatalogo() throws IOException, ExecutionException, InterruptedException, TimeoutException {

        CatalogoTypeGrpc catalogo = this.getCatalogoGrpc();

        //Variable para guargar la respuesta de gRPC
        CompletableFuture<CatalogoTypeGrpc> message = new CompletableFuture<>();

        //Llamado del servicio
        client.crearCatalogo(catalogo)
                .subscribe()
                .with(reply -> message.complete(reply.getObj()));


        //Test del servicio
        Assertions.assertThat(message.get(5, TimeUnit.SECONDS)).isEqualTo(catalogo);

    }

    @Test
    public void actualizarCatalogo() throws IOException, ExecutionException, InterruptedException, TimeoutException {

        CatalogoTypeGrpc catalogo = this.getCatalogoGrpc();

        //Variable para guargar la respuesta de gRPC
        CompletableFuture<CatalogoTypeGrpc> message = new CompletableFuture<>();

        //Llamado del servicio
        client.actualizarCatalogo(catalogo)
                .subscribe()
                .with(reply -> message.complete(reply.getObj()));

        System.out.println("Lo esperado..." + message.get(5, TimeUnit.SECONDS));
        System.out.println("Lo comparado..." + catalogo);

        //Test del servicio
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

        CatalogoTypeGrpc catalogo = this.getCatalogoGrpc();

        CompletableFuture<CatalogoTypeGrpc> message = new CompletableFuture<>();

        DataConsulta id = DataConsulta.newBuilder().setId("500").build();

        client.consultarCatalogoPorId(id)
                .subscribe()
                .with(reply -> message.complete(reply.getObj()));


        System.out.println("Lo esperado..." + message.get(5, TimeUnit.SECONDS));
        System.out.println("Lo comparado..." + catalogo);

        Assertions.assertThat(message.get(5, TimeUnit.SECONDS)).isEqualTo(catalogo);

    }

    public CatalogoTypeGrpc getCatalogoGrpcNl() throws IOException {

        // Preparación de datos
        String jsonString = new String(Files.readAllBytes(Paths.get("src/main/resources/json/es-CatalogoGet.json")), StandardCharsets.UTF_8);
        CatalogoType clienteType = objectMapper.readValue(jsonString, CatalogoType.class);

        //Se retorna la data dataType a protobuf
        return mapperGRPC.catalogoToGrpc(clienteType);
    }

}
