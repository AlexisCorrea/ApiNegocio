package ApiNegocio.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * DeleteRequest
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-31T15:25:59.597Z")
public class DeleteRequest   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("id_administrador")
  private String idAdministrador = null;

  @JsonProperty("token")
  private String token = null;

  public DeleteRequest id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public DeleteRequest idAdministrador(String idAdministrador) {
    this.idAdministrador = idAdministrador;
    return this;
  }

  /**
   * Get idAdministrador
   * @return idAdministrador
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getIdAdministrador() {
    return idAdministrador;
  }

  public void setIdAdministrador(String idAdministrador) {
    this.idAdministrador = idAdministrador;
  }

  public DeleteRequest token(String token) {
    this.token = token;
    return this;
  }

  /**
   * Get token
   * @return token
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeleteRequest deleteRequest = (DeleteRequest) o;
    return Objects.equals(this.id, deleteRequest.id) &&
        Objects.equals(this.idAdministrador, deleteRequest.idAdministrador) &&
        Objects.equals(this.token, deleteRequest.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, idAdministrador, token);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeleteRequest {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    idAdministrador: ").append(toIndentedString(idAdministrador)).append("\n");
    sb.append("    token: ").append(toIndentedString(token)).append("\n");
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

