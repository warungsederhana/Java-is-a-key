package programmerzamannow.restful.service.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import programmerzamannow.restful.entity.Address;
import programmerzamannow.restful.entity.Contact;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.address.AddressResponse;
import programmerzamannow.restful.model.address.CreateAddressRequest;
import programmerzamannow.restful.model.address.UpdateAddressRequest;
import programmerzamannow.restful.repository.AddressRepository;
import programmerzamannow.restful.repository.ContactRepository;
import programmerzamannow.restful.service.ValidationService;

import java.util.UUID;

@Service
public class AddressService implements AddressServiceI {

  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private ContactRepository contactRepository;

  @Autowired
  private ValidationService validationService;

  @Override
  @Transactional
  public AddressResponse create(User user, CreateAddressRequest request) {
    validationService.validate(request);

    Contact contact = contactRepository.findFirstByUserAndId(user, request.getContactId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

    Address address = new Address();
    address.setId(UUID.randomUUID().toString());
    address.setContact(contact);
    address.setStreet(request.getStreet());
    address.setCity(request.getCity());
    address.setProvince(request.getProvince());
    address.setCountry(request.getCountry());
    address.setPostalCode(request.getPostalCode());
    addressRepository.save(address);

    return toAddressResponse(address);
  }

  @Override
  @Transactional(readOnly = true)
  public AddressResponse get(User user, String contactId, String addressId) {
    Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

    Address address = addressRepository.findFirstByContactAndId(contact, addressId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found"));

    return toAddressResponse(address);
  }

  @Override
  @Transactional
  public AddressResponse update(User user, UpdateAddressRequest request) {
    validationService.validate(request);

    Contact contact = contactRepository.findFirstByUserAndId(user, request.getContactId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

    Address address = addressRepository.findFirstByContactAndId(contact, request.getId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found"));

    address.setStreet(request.getStreet());
    address.setCity(request.getCity());
    address.setProvince(request.getProvince());
    address.setCountry(request.getCountry());
    address.setPostalCode(request.getPostalCode());
    addressRepository.save(address);

    return toAddressResponse(address);
  }

  @Override
  @Transactional
  public void remove(User user, String contactId, String addressId) {
    Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

    Address address = addressRepository.findFirstByContactAndId(contact, addressId)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address not found"));

    addressRepository.delete(address);
  }

  private AddressResponse toAddressResponse(Address address) {
    return AddressResponse.builder()
        .id(address.getId())
        .street(address.getStreet())
        .city(address.getCity())
        .province(address.getProvince())
        .country(address.getCountry())
        .postalCode(address.getPostalCode())
        .build();
  }
}
