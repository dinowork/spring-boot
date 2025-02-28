package br.com.dino.spring_boot.service;

import static br.com.dino.spring_boot.mapper.ObjectMapper.parseObject;
import static br.com.dino.spring_boot.mapper.ObjectMapper.parseListObjects;
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



@Service
public class PersonServices {

    private final Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonMapper converter;

    public PersonDTO findById(Long id){
        logger.info("Finding one Person!");
        Person data = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        return parseObject(data, PersonDTO.class);
    }

    public List <PersonDTO> findAll(){
        logger.info("Finding all People!");
        return parseListObjects(repository.findAll(),PersonDTO.class) ;
    }

    public PersonDTO create(PersonDTO person){
        logger.info("Creating one person!");
        Person data = parseObject(person, Person.class);
        return parseObject(repository.save(data), PersonDTO.class);
    }

    public PersonDTOv2 create(PersonDTOv2 person){
        logger.info("Creating one person V2!");
        Person data = converter.converterDTOToEntity(person);
        return converter.converterEntityToDTO(repository.save(data));
    }

    public PersonDTO update(PersonDTO person){
        logger.info("Updating one person!");
        Person data = repository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        data.setGender(person.getGender());
        data.setAddress(person.getAddress());
        data.setFirstName(person.getFirstName());
        data.setLastName(person.getLastName());
        return parseObject(repository.save(data), PersonDTO.class);
    }

    public void delete(Long id){
        logger.info("Deleting one person!");
        repository.deleteById(id);
    }

}
