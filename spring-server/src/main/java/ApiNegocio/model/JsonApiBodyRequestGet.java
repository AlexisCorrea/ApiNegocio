package ApiNegocio.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import ApiNegocio.model.GetRequest;

import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * JsonApiBodyRequestGet
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-31T15:25:59.597Z")

public class JsonApiBodyRequestGet   {
  @JsonProperty("negocio")
  @Valid
  private List<GetRequest> negocio = new ArrayList<GetRequest>();

  public JsonApiBodyRequestGet negocio(List<GetRequest> negocio) {
    this.negocio = negocio;
    return this;
  }

  public JsonApiBodyRequestGet addNegocioItem(GetRequest negocioItem) {
    this.negocio.add(negocioItem);
    return this;
  }

  /**
   * Get negocio
   * @return negocio
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull

  @Valid

  public List<GetRequest> getNegocio() {
    return negocio;
  }

  public void setNegocio(List<GetRequest> negocio) {
    this.negocio = negocio;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    JsonApiBodyRequestGet jsonApiBodyRequestGet = (JsonApiBodyRequestGet) o;
    return Objects.equals(this.negocio, jsonApiBodyRequestGet.negocio);
  }

  @Override
  public int hashCode() {
    return Objects.hash(negocio);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class JsonApiBodyRequestGet {\n");
    
    sb.append("    negocio: ").append(toIndentedString(negocio)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

