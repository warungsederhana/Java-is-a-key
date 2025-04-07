package programmerzamannow.restful.service.address;

import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.address.AddressResponse;
import programmerzamannow.restful.model.address.CreateAddressRequest;
import programmerzamannow.restful.model.address.UpdateAddressRequest;

import java.util.List;

public interface AddressServiceI {

  public AddressResponse create(User user, CreateAddressRequest request);

  public AddressResponse get(User user, String contactId, String addressId);

  public AddressResponse update(User user, UpdateAddressRequest request);

  public void remove(User user, String contactId, String addressId);

  public List<AddressResponse> list(User user, String contactId);
}
