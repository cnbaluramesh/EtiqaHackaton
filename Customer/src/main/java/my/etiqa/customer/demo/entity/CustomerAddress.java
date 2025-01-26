package my.etiqa.customer.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Objects;

@JsonTypeName("Customer_address")
public class CustomerAddress {
  private String street;
  
  private String city;
  
  private String state;
  
  private String postalCode;
  
  public CustomerAddress street(String street) {
    this.street = street;
    return this;
  }
  
  @Schema(name = "street", example = "123 Main St", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("street")
  public String getStreet() {
    return this.street;
  }
  
  public void setStreet(String street) {
    this.street = street;
  }
  
  public CustomerAddress city(String city) {
    this.city = city;
    return this;
  }
  
  @Schema(name = "city", example = "Springfield", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("city")
  public String getCity() {
    return this.city;
  }
  
  public void setCity(String city) {
    this.city = city;
  }
  
  public CustomerAddress state(String state) {
    this.state = state;
    return this;
  }
  
  @Schema(name = "state", example = "IL", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("state")
  public String getState() {
    return this.state;
  }
  
  public void setState(String state) {
    this.state = state;
  }
  
  public CustomerAddress postalCode(String postalCode) {
    this.postalCode = postalCode;
    return this;
  }
  
  @Schema(name = "postalCode", example = "62701", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("postalCode")
  public String getPostalCode() {
    return this.postalCode;
  }
  
  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }
  
  public boolean equals(Object o) {
    if (this == o)
      return true; 
    if (o == null || getClass() != o.getClass())
      return false; 
    CustomerAddress customerAddress = (CustomerAddress)o;
    return (Objects.equals(this.street, customerAddress.street) && 
      Objects.equals(this.city, customerAddress.city) && 
      Objects.equals(this.state, customerAddress.state) && 
      Objects.equals(this.postalCode, customerAddress.postalCode));
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.street, this.city, this.state, this.postalCode });
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CustomerAddress {\n");
    sb.append("    street: ").append(toIndentedString(this.street)).append("\n");
    sb.append("    city: ").append(toIndentedString(this.city)).append("\n");
    sb.append("    state: ").append(toIndentedString(this.state)).append("\n");
    sb.append("    postalCode: ").append(toIndentedString(this.postalCode)).append("\n");
    sb.append("}");
    return sb.toString();
  }
  
  private String toIndentedString(Object o) {
    if (o == null)
      return "null"; 
    return o.toString().replace("\n", "\n    ");
  }
}