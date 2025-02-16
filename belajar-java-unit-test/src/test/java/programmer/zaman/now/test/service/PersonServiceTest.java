package programmer.zaman.now.test.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import programmer.zaman.now.test.data.Person;
import programmer.zaman.now.test.repository.PersonRepository;

@Extensions({
    @ExtendWith(MockitoExtension.class)
})
public class PersonServiceTest {

  @Mock
  private PersonRepository personRepository;

  private PersonService personService;

  @BeforeEach
  void setUp() {
    personService = new PersonService(personRepository);
  }

  @Test
  public void testGetPersonNotFound() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      personService.get("not found");
    });
  }

  @Test
  public void testGetPersonSuccess() {
    // behavior mock
    Mockito.when(personRepository.selectById("eko"))
        .thenReturn(new Person("eko", "Eko"));

    var person = personService.get("eko");

    Assertions.assertNotNull(person);
    Assertions.assertEquals("eko", person.getId());
    Assertions.assertEquals("Eko", person.getName());
  }

  @Test
  void testRegisterSuccess() {
    var person = personService.register("Eko");

    Assertions.assertNotNull(person);
    Assertions.assertEquals("Eko", person.getName());
    Assertions.assertNotNull(person.getId());

    // verify insert is called once
    Mockito.verify(personRepository, Mockito.times(1))
        .insert(new Person(person.getId(), "Eko"));
  }

}
