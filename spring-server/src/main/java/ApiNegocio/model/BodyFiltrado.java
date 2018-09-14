package ApiNegocio.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * BodyFiltrado
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-10T17:07:18.833Z")

public class BodyFiltrado   {
  @JsonProperty("tipos")
  @Valid
  private List<String> tipos = new ArrayList<String>();

  

  public BodyFiltrado tipos(List<String> tipos) {
    this.tipos = tipos;
    return this;
  }

  public BodyFiltrado addIdesItem(String tiposItem) {
    this.tipos.add(tiposItem);
    return this;
  }

  /**
   * Get ides
   * @return ides
  **/
  @ApiModelProperty(required = true, value = "")
  @NotNull


  public List<String> getTipos() {
    return tipos;
  }

  public void setIdes(List<String> tipos) {
    this.tipos = tipos;
  }

 



  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BodyFiltrado bodyFiltrado = (BodyFiltrado) o;
    return Objects.equals(this.tipos, bodyFiltrado.tipos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(tipos);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BodyFiltrado {\n");
    
    sb.append("    tipos: ").append(toIndentedString(tipos)).append("\n");
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

