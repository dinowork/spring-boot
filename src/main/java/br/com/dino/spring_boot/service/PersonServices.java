package br.com.dino.spring_boot.service;

import br.com.dino.spring_boot.controller.PersonController;
import br.com.dino.spring_boot.dto.v1.PersonDTO;
import br.com.dino.spring_boot.dto.v2.PersonDTOv2;
import br.com.dino.spring_boot.exception.ResourceNotFoundException;
import br.com.dino.spring_boot.mapper.custom.PersonMapper;
import br.com.dino.spring_boot.model.Person;
import br.com.dino.spring_boot.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

import static br.com.dino.spring_boot.mapper.ObjectMapper.parseListObjects;
import static br.com.dino.spring_boot.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class PersonServices {

    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;

    // V1

    public PersonDTO create(PersonDTO person){
        logger.info("Creating one person!");
        Person data = parseObject(person, Person.class);
        PersonDTO dto = parseObject(repository.save(data), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTO findById(Long id){
        logger.info("Finding one Person!");
        Person data = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        PersonDTO dto = parseObject(data, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }


    public PersonDTO update(PersonDTO person){
        logger.info("Updating one person!");
        Person data = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        data.setGender(person.getGender());
        data.setAddress(person.getAddress());
        data.setFirstName(person.getFirstName());
        data.setLastName(person.getLastName());
        PersonDTO dto = parseObject(repository.save(data), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public void delete(Long id){
        logger.info("Deleting one person!");
        repository.deleteById(id);
    }

    public List <PersonDTO> findAll(){
        logger.info("Finding all People!");
        List<PersonDTO> list = parseListObjects(repository.findAll(),PersonDTO.class);
        list.forEach(this::addHateoasLinks);
        return list;
    }



    // V2

    public PersonDTOv2 create(PersonDTOv2 person){
        logger.info("V2 - Creating one person V2!");
        Person data = converter.converterDTOToEntity(person);
        return converter.converterEntityToDTO(repository.save(data));
    }

    public PersonDTOv2 findByIdColumnBirthDay(Long id){
        logger.info("V2 -  Finding one Person !");
        Person data = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return converter.converterEntityToDTO(data);
    }

    // HETEOAS
    private void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withRel("read").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
    }

}



    