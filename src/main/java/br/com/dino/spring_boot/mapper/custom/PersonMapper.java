package br.com.dino.spring_boot.mapper.custom;

import br.com.dino.spring_boot.dto.v2.PersonDTOv2;
import br.com.dino.spring_boot.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public  class PersonMapper {

    public PersonDTOv2 converterEntityToDTO(Person person){
        PersonDTOv2 entity = new PersonDTOv2();
        entity.setId(person.getId());
        entity.setGender(person.getGender());
        entity.setAddress(person.getAddress());
        entity.setFirstName(person.getFirstName());
       // entity.setLastName(person.getLastName());
        entity.setLastName(null);
        entity.setBirthDay(new Date());
        //entity.setPhoneNumber("+55 (11) 969685-8570");
        entity.setPhoneNumber("");
        entity.setSensitiveData("For Bar");
        return  entity;
    }

    public Person converterDTOToEntity(PersonDTOv2 person){
        Person entity = new Person();
        entity.setGender(person.getGender());
        entity.setAddress(person.getAddress());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        /*entity.setBirthDay(new Date());*/
        return  entity;
    }

}
