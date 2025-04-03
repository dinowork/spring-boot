package br.com.dino.spring_boot.service;

import br.com.dino.spring_boot.dto.v1.PersonDTO;
import br.com.dino.spring_boot.exception.RequiredObjectIsNullException;
import br.com.dino.spring_boot.model.Person;
import br.com.dino.spring_boot.repository.PersonRepository;
import br.com.dino.spring_boot.unitetests.mapper.mocks.MockPerson;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    MockPerson input;

    @InjectMocks
    PersonServices services;

    @Mock
    PersonRepository repository;



    @BeforeEach
    void setUp() {
        input = new MockPerson();
        MockitoAnnotations.openMocks(this);  // sem MockitoAnnotations não ha @InjectMocks e @Mock
    }

    @Test
    void findById() {

        // person do mock retornado com valores
        Person person = input.mockEntity(1);
        // quando for no banco banco retorna o person acima la no service.findById
        when(repository.findById(1L)).thenReturn(Optional.of(person));

        PersonDTO dto = services.findById(1L);

        //validar dados
        assertNotNull(dto);
        assertNotNull(dto.getId());
        assertNotNull(dto.getLinks());

        assertEquals("Address Test1",dto.getAddress());
        assertEquals("First Name Test1",dto.getFirstName());
        assertEquals("Female",dto.getGender());
        assertEquals(1L,dto.getId());
        assertEquals("Last Name Test1",dto.getLastName());

        assertNotNull(dto.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")  && link.getHref().endsWith("/api/person/v1/1") && link.getType().equals("GET")));
        assertNotNull(dto.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")  && link.getHref().endsWith("/api/person/v1") && link.getType().equals("GET")));
        assertNotNull(dto.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")  && link.getHref().endsWith("/api/person/v1") && link.getType().equals("POST")));
        assertNotNull(dto.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")  && link.getHref().endsWith("/api/person/v1") && link.getType().equals("PUT")));
        assertNotNull(dto.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")  && link.getHref().endsWith("/api/person/v1/1") && link.getType().equals("DELETE")));

    }

    @Test
    void create() {

        // personDTO
        PersonDTO dtoSave = input.mockDTO(1);

        // person
        Person personSave = input.mockEntity(1);
        Person personReturn = input.mockEntity(1);

        when(repository.save(personSave)).thenReturn(personReturn);
        PersonDTO dto = services.create(dtoSave);

        assertNotNull(dto);
        assertNotNull(dto.getId());
        assertNotNull(dto.getLinks());

        assertEquals("Address Test1",dto.getAddress());
        assertEquals("First Name Test1",dto.getFirstName());
        assertEquals("Female",dto.getGender());
        assertEquals(1L,dto.getId());
        assertEquals("Last Name Test1",dto.getLastName());

        assertNotNull(dto.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")  && link.getHref().endsWith("/api/person/v1/1") && link.getType().equals("GET")));
        assertNotNull(dto.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")  && link.getHref().endsWith("/api/person/v1") && link.getType().equals("GET")));
        assertNotNull(dto.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")  && link.getHref().endsWith("/api/person/v1") && link.getType().equals("POST")));
        assertNotNull(dto.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")  && link.getHref().endsWith("/api/person/v1") && link.getType().equals("PUT")));
        assertNotNull(dto.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")  && link.getHref().endsWith("/api/person/v1/1") && link.getType().equals("DELETE")));

    }

    @Test
    void createException() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () ->{
            services.create((PersonDTO) null);
        });

        String expectedMessage =  "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void update() {
        Person person = input.mockEntity(1);
        Person personReturn = input.mockEntity(1);

        PersonDTO personDTO  = input.mockDTO(1);

        when(repository.findById(1L)).thenReturn(Optional.of(person));
        when(repository.save(person)).thenReturn(personReturn);

        PersonDTO dto = services.update(personDTO);

        //validar dados
        assertNotNull(dto);
        assertNotNull(dto.getId());
        assertNotNull(dto.getLinks());

        assertEquals("Address Test1",dto.getAddress());
        assertEquals("First Name Test1",dto.getFirstName());
        assertEquals("Female",dto.getGender());
        assertEquals(1L,dto.getId());
        assertEquals("Last Name Test1",dto.getLastName());

        assertNotNull(dto.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")  && link.getHref().endsWith("/api/person/v1/1") && link.getType().equals("GET")));
        assertNotNull(dto.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")  && link.getHref().endsWith("/api/person/v1") && link.getType().equals("GET")));
        assertNotNull(dto.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")  && link.getHref().endsWith("/api/person/v1") && link.getType().equals("POST")));
        assertNotNull(dto.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")  && link.getHref().endsWith("/api/person/v1") && link.getType().equals("PUT")));
        assertNotNull(dto.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")  && link.getHref().endsWith("/api/person/v1/1") && link.getType().equals("DELETE")));

    }


    @Test
    void updateException() {
        Exception exception = assertThrows(RequiredObjectIsNullException.class, () ->{
            services.update(null);
        });

        String expectedMessage =  "It is not allowed to persist a null object!";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete() {
        services.delete(1L);
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void findAll() {
        List<Person> list = input.mockEntityList();
        when(repository.findAll()).thenReturn(list);
        List<PersonDTO> people = services.findAll();

        assertNotNull(people);
        assertEquals(14, people.size());

        PersonDTO personOne = people.get(1);

        assertEquals("Address Test1",personOne.getAddress());
        assertEquals("First Name Test1",personOne.getFirstName());
        assertEquals("Female",personOne.getGender());
        assertEquals(1L,personOne.getId());
        assertEquals("Last Name Test1",personOne.getLastName());

        assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("self")  && link.getHref().endsWith("/api/person/v1/1") && link.getType().equals("GET")));
        assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("findAll")  && link.getHref().endsWith("/api/person/v1") && link.getType().equals("GET")));
        assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("create")  && link.getHref().endsWith("/api/person/v1") && link.getType().equals("POST")));
        assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("update")  && link.getHref().endsWith("/api/person/v1") && link.getType().equals("PUT")));
        assertNotNull(personOne.getLinks().stream().anyMatch(link -> link.getRel().value().equals("delete")  && link.getHref().endsWith("/api/person/v1/1") && link.getType().equals("DELETE")));

    }
}