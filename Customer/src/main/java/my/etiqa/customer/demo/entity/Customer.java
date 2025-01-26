package my.etiqa.customer.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "first_name") // Map to the "first_name" column in the database
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email_personal")
  private String emailPersonal;

  @Column(name = "email_office")
  private String emailOffice;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Embedded
  private CustomerAddress address;

  @ElementCollection
  @CollectionTable(
          name = "customer_family_members", // Join table name
          joinColumns = @JoinColumn(name = "customer_id") // Foreign key to "customer"
  )
  private List<String> familyMembers = new ArrayList<>();
  
  public Customer id(Integer id) {
    this.id = id;
    return this;
  }
  
  @Schema(name = "id", example = "1", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public Integer getId() {
    return this.id;
  }
  
  public void setId(Integer id) {
    this.id = id;
  }
  
  public Customer firstName(String firstName) {
    this.firstName = firstName;
    return this;
  }
  
  @Schema(name = "firstName", example = "John", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("firstName")
  public String getFirstName() {
    return this.firstName;
  }
  
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  
  public Customer lastName(String lastName) {
    this.lastName = lastName;
    return this;
  }
  
  @Schema(name = "lastName", example = "Doe", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("lastName")
  public String getLastName() {
    return this.lastName;
  }
  
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
  
  public Customer emailPersonal(String emailPersonal) {
    this.emailPersonal = emailPersonal;
    return this;
  }
  
  @Schema(name = "emailPersonal", example = "john.doe@example.com", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("emailPersonal")
  public String getEmailPersonal() {
    return this.emailPersonal;
  }
  
  public void setEmailPersonal(String emailPersonal) {
    this.emailPersonal = emailPersonal;
  }
  
  public Customer emailOffice(String emailOffice) {
    this.emailOffice = emailOffice;
    return this;
  }
  
  @Schema(name = "emailOffice", example = "john.doe@work.com", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("emailOffice")
  public String getEmailOffice() {
    return this.emailOffice;
  }
  
  public void setEmailOffice(String emailOffice) {
    this.emailOffice = emailOffice;
  }
  
  public Customer phoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
    return this;
  }
  
  @Schema(name = "phoneNumber", example = "+1234567890", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("phoneNumber")
  public String getPhoneNumber() {
    return this.phoneNumber;
  }
  
  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }
  
  public Customer address(CustomerAddress address) {
    this.address = address;
    return this;
  }
  
  @Valid
  @Schema(name = "address", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("address")
  public CustomerAddress getAddress() {
    return this.address;
  }
  
  public void setAddress(CustomerAddress address) {
    this.address = address;
  }
  
  public Customer familyMembers(List<String> familyMembers) {
    this.familyMembers = familyMembers;
    return this;
  }
  
  public Customer addFamilyMembersItem(String familyMembersItem) {
    if (this.familyMembers == null)
      this.familyMembers = new ArrayList<>(); 
    this.familyMembers.add(familyMembersItem);
    return this;
  }
  
  @Schema(name = "familyMembers", example = "[\"Jane\",\"Mike\"]", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("familyMembers")
  public List<String> getFamilyMembers() {
    return this.familyMembers;
  }
  
  public void setFamilyMembers(List<String> familyMembers) {
    this.familyMembers = familyMembers;
  }
  
  public boolean equals(Object o) {
    if (this == o)
      return true; 
    if (o == null || getClass() != o.getClass())
      return false; 
    Customer customer = (Customer)o;
    return (Objects.equals(this.id, customer.id) && 
      Objects.equals(this.firstName, customer.firstName) && 
      Objects.equals(this.lastName, customer.lastName) && 
      Objects.equals(this.emailPersonal, customer.emailPersonal) && 
      Objects.equals(this.emailOffice, customer.emailOffice) && 
      Objects.equals(this.phoneNumber, customer.phoneNumber) && 
      Objects.equals(this.address, customer.address) && 
      Objects.equals(this.familyMembers, customer.familyMembers));
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { this.id, this.firstName, this.lastName, this.emailPersonal, this.emailOffice, this.phoneNumber, this.address, this.familyMembers });
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Customer {\n");
    sb.append("    id: ").append(toIndentedString(this.id)).append("\n");
    sb.append("    firstName: ").append(toIndentedString(this.firstName)).append("\n");
    sb.append("    lastName: ").append(toIndentedString(this.lastName)).append("\n");
    sb.append("    emailPersonal: ").append(toIndentedString(this.emailPersonal)).append("\n");
    sb.append("    emailOffice: ").append(toIndentedString(this.emailOffice)).append("\n");
    sb.append("    phoneNumber: ").append(toIndentedString(this.phoneNumber)).append("\n");
    sb.append("    address: ").append(toIndentedString(this.address)).append("\n");
    sb.append("    familyMembers: ").append(toIndentedString(this.familyMembers)).append("\n");
    sb.append("}");
    return sb.toString();
  }
  
  private String toIndentedString(Object o) {
    if (o == null)
      return "null"; 
    return o.toString().replace("\n", "\n    ");
  }
}
