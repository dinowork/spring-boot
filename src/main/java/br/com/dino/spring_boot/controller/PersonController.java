package br.com.dino.spring_boot.controller;
import br.com.dino.spring_boot.dto.v1.PersonDTO;
import br.com.dino.spring_boot.dto.v2.PersonDTOv2;
import br.com.dino.spring_boot.mapper.custom.PersonMapper;
import br.com.dino.spring_boot.model.Person;
import br.com.dino.spring_boot.service.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    @Autowired
    private PersonServices service;

    //V1

        //CREATE
        @PostMapping ( value = "/v1",
                consumes = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE},
                produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
        public PersonDTO create(@RequestBody PersonDTO person){
            return service.create(person);
        }

        //READ
        @RequestMapping(value = "/v1/{id}", method = RequestMethod.GET,
                produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
        public PersonDTO findById(@PathVariable("id") Long id){
            return service.findById(id);
        }

        //UPDATE
        @PutMapping (value = "/v1", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        // Quando utilizado o PostMapping retira a definição "method"
        public PersonDTO update(@RequestBody PersonDTO person){
            return service.update(person);
        }

        //DELETE
        @DeleteMapping(value = "/v1/{id}")
        public ResponseEntity<?> delete(@PathVariable("id") Long id){
            service.delete(id);
            return ResponseEntity.noContent().build();
        }

        // LISTAR
        @GetMapping (value = "/v1", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
        public List<PersonDTO> findAll(){
            return service.findAll();
        }


    //V2

        //SALVAR
        @PostMapping (value = "/v2", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
        public PersonDTOv2 create(@RequestBody PersonDTOv2 person){
            return service.create(person);
        }

        //BUSCAR
        @RequestMapping(value = "/v2/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
        public PersonDTOv2 findByIdColumnBirthDay(@PathVariable("id") Long id){
            return service.findByIdColumnBirthDay(id);
        }

}
