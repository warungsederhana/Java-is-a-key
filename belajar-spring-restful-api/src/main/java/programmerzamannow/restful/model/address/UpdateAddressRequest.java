package programmerzamannow.restful.model.address;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAddressRequest {

  @JsonIgnore
  @NotBlank
  private String contactId;

  @JsonIgnore
  @NotBlank
  private String id;

  @Size(max = 100)
  private String street;

  @Size(max = 100)
  private String city;

  @Size(max = 100)
  private String province;

  @NotBlank
  @Size(max = 100)
  private String country;

  @Size(max = 100)
  private String postalCode;
}
