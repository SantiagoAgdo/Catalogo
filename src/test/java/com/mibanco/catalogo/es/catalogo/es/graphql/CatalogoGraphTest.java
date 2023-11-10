package com.mibanco.catalogo.es.catalogo.es.graphql;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class CatalogoGraphTest {



    @Test
    void consultaPorIdGraphTest(){
        given()
                .when()
                .contentType(ContentType.JSON)
                .body("{\"query\": \"query ConsultarCatalogoPorID { consultarCatalogoPorID(id: '10002') { codigoDetalleCatalogo descripcionDetalleCatalogo idCatalogo nombreCatalogo padreIDCatalogo } }\"}")
                .post("/graphql")
                .then()
                .statusCode(200);
    }

    @Test
    void consultaPorNombreGraphTest(){
        given()
                .when()
                .contentType(ContentType.JSON)
                .body("{\"query\": \"query ConsultarCatalogoPorID { consultarCatalogoPorID(id: '10002') { codigoDetalleCatalogo descripcionDetalleCatalogo idCatalogo nombreCatalogo padreIDCatalogo } }\"}")
                .post("/graphql")
                .then()
                .statusCode(200)
                .body("data.consultarCatalogoPorID[0].padreIDCatalogo", equalTo(null));

    }
}
