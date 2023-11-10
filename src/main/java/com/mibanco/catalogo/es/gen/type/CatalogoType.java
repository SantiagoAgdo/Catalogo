package com.mibanco.catalogo.es.gen.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import jakarta.validation.Valid;

import java.util.Objects;



@JsonTypeName("CatalogoType")
@jakarta.annotation.Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2023-11-10T14:18:30.569007-05:00[America/Bogota]")
public class CatalogoType   {
  private @Valid String codigoDetalleCatalogo;
  private @Valid String descripcionDetalleCatalogo;
  private @Valid String idCatalogo;
  private @Valid String nombreCatalogo;
  private @Valid String padreIDCatalogo;

  /**
   **/
  public CatalogoType codigoDetalleCatalogo(String codigoDetalleCatalogo) {
    this.codigoDetalleCatalogo = codigoDetalleCatalogo;
    return this;
  }

  
  @JsonProperty("codigoDetalleCatalogo")
  public String getCodigoDetalleCatalogo() {
    return codigoDetalleCatalogo;
  }

  @JsonProperty("codigoDetalleCatalogo")
  public void setCodigoDetalleCatalogo(String codigoDetalleCatalogo) {
    this.codigoDetalleCatalogo = codigoDetalleCatalogo;
  }

  /**
   **/
  public CatalogoType descripcionDetalleCatalogo(String descripcionDetalleCatalogo) {
    this.descripcionDetalleCatalogo = descripcionDetalleCatalogo;
    return this;
  }

  
  @JsonProperty("descripcionDetalleCatalogo")
  public String getDescripcionDetalleCatalogo() {
    return descripcionDetalleCatalogo;
  }

  @JsonProperty("descripcionDetalleCatalogo")
  public void setDescripcionDetalleCatalogo(String descripcionDetalleCatalogo) {
    this.descripcionDetalleCatalogo = descripcionDetalleCatalogo;
  }

  /**
   **/
  public CatalogoType idCatalogo(String idCatalogo) {
    this.idCatalogo = idCatalogo;
    return this;
  }

  
  @JsonProperty("idCatalogo")
  public String getIdCatalogo() {
    return idCatalogo;
  }

  @JsonProperty("idCatalogo")
  public void setIdCatalogo(String idCatalogo) {
    this.idCatalogo = idCatalogo;
  }

  /**
   **/
  public CatalogoType nombreCatalogo(String nombreCatalogo) {
    this.nombreCatalogo = nombreCatalogo;
    return this;
  }

  
  @JsonProperty("nombreCatalogo")
  public String getNombreCatalogo() {
    return nombreCatalogo;
  }

  @JsonProperty("nombreCatalogo")
  public void setNombreCatalogo(String nombreCatalogo) {
    this.nombreCatalogo = nombreCatalogo;
  }

  /**
   **/
  public CatalogoType padreIDCatalogo(String padreIDCatalogo) {
    this.padreIDCatalogo = padreIDCatalogo;
    return this;
  }

  
  @JsonProperty("padreIDCatalogo")
  public String getPadreIDCatalogo() {
    return padreIDCatalogo;
  }

  @JsonProperty("padreIDCatalogo")
  public void setPadreIDCatalogo(String padreIDCatalogo) {
    this.padreIDCatalogo = padreIDCatalogo;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CatalogoType catalogoType = (CatalogoType) o;
    return Objects.equals(this.codigoDetalleCatalogo, catalogoType.codigoDetalleCatalogo) &&
        Objects.equals(this.descripcionDetalleCatalogo, catalogoType.descripcionDetalleCatalogo) &&
        Objects.equals(this.idCatalogo, catalogoType.idCatalogo) &&
        Objects.equals(this.nombreCatalogo, catalogoType.nombreCatalogo) &&
        Objects.equals(this.padreIDCatalogo, catalogoType.padreIDCatalogo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigoDetalleCatalogo, descripcionDetalleCatalogo, idCatalogo, nombreCatalogo, padreIDCatalogo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CatalogoType {\n");
    
    sb.append("    codigoDetalleCatalogo: ").append(toIndentedString(codigoDetalleCatalogo)).append("\n");
    sb.append("    descripcionDetalleCatalogo: ").append(toIndentedString(descripcionDetalleCatalogo)).append("\n");
    sb.append("    idCatalogo: ").append(toIndentedString(idCatalogo)).append("\n");
    sb.append("    nombreCatalogo: ").append(toIndentedString(nombreCatalogo)).append("\n");
    sb.append("    padreIDCatalogo: ").append(toIndentedString(padreIDCatalogo)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


}

