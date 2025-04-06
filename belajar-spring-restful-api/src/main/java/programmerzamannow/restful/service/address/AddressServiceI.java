package programmerzamannow.restful.service.address;

import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.address.AddressResponse;
import programmerzamannow.restful.model.address.CreateAddressRequest;

public interface AddressServiceI {

  public AddressResponse create(User user, CreateAddressRequest request);
}
