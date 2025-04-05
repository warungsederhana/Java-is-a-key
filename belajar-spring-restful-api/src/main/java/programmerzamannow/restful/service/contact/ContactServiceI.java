package programmerzamannow.restful.service.contact;

import org.springframework.data.domain.Page;
import programmerzamannow.restful.entity.User;
import programmerzamannow.restful.model.contact.ContactResponse;
import programmerzamannow.restful.model.contact.CreateContactRequest;
import programmerzamannow.restful.model.contact.SearchContactRequest;
import programmerzamannow.restful.model.contact.UpdateContactRequest;

import java.util.List;

public interface ContactServiceI {

  public ContactResponse create(User user, CreateContactRequest request);

  public ContactResponse get(User user, String id);

  public ContactResponse update(User user, UpdateContactRequest request);

  public Page<ContactResponse> search(User user, SearchContactRequest request);

  public void delete(User user, String id);
}
