package com.mibanco.catalogo.es.catalogo.es.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mibanco.catalogo.es.gen.type.CatalogoType;
import com.mibanco.catalogo.es.services.impl.CatalogoServiceImpl;
import com.mibanco.catalogo.es.utils.validator.CatalogoValidator;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CatalogoControllerTest {

    @Inject
    ObjectMapper objectMapper;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearCatalogoTest() throws IOException {

        byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/json/es-Catalogo.json"));
        String jsonString = new String(jsonData);

        CatalogoType catalogo = objectMapper.readValue(jsonString, CatalogoType.class);

        given()
                .contentType(ContentType.JSON)
                .body(catalogo)
                .when()
                .post("v1/es/catalogo")
                .then()
                .statusCode(201);
    }

    @Test
    void obtenerCatalogoTest() throws IOException {

        byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/json/es-catalogoGet.json"));
        String jsonString = new String(jsonData);

        CatalogoType catalogo = objectMapper.readValue(jsonString, CatalogoType.class);

        given()
                .contentType(ContentType.JSON)
                .body(catalogo)
                .when()
                .get("v1/es/catalogo/500")
                .then()
                .statusCode(200);
    }

    @Test
    void eliminarCatalogoTest() throws IOException {
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("v1/es/catalogo/500")
                .then()
                .statusCode(200);
    }

    @Test
    void obtenerCatalogoPorNombreTest() throws IOException {

        byte[] jsonData = Files.readAllBytes(Paths.get("src/main/resources/json/es-catalogoGet.json"));
        String jsonString = new String(jsonData);

        CatalogoType catalogo = objectMapper.readValue(jsonString, CatalogoType.class);

        given()
                .contentType(ContentType.JSON)
                .body(catalogo)
                .when()
                .get("v1/es/catalogo/nombre/500")
                .then()
                .statusCode(200);
    }

}
