package com.example.demo.repository;


import com.example.demo.model.Encomenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncomendaRepository extends JpaRepository<Encomenda, String> {


    Encomenda findById(long id);
}
