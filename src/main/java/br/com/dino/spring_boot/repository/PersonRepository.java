package br.com.dino.spring_boot.repository;

import br.com.dino.spring_boot.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

// @Repository    versões antigas precisam dessa anotação
public interface PersonRepository extends JpaRepository<Person, Long> { }
