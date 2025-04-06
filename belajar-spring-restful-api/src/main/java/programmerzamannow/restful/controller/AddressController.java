package programmerzamannow.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.WebResponse;
import programmerzamannow.restful.model.address.AddressResponse;
import programmerzamannow.restful.model.address.CreateAddressRequest;
import programmerzamannow.restful.service.address.AddressServiceI;

@RestController
public class AddressController {

  @Autowired
  private AddressServiceI addressService;

  @PostMapping(
      path = "/api/contacts/{contactId}/addresses",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE
  )
  public WebResponse<AddressResponse> create(
      User user,
      @RequestBody CreateAddressRequest request,
      @PathVariable("contactId") String contactId
  ) {
    request.setContactId(contactId);
    AddressResponse addressResponse = addressService.create(user, request);
    return WebResponse.<AddressResponse>builder()
        .data(addressResponse)
        .build();
  }
}
