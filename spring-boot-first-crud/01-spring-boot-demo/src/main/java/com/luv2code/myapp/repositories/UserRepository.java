package com.luv2code.myapp.repositories;

import com.luv2code.myapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

// extends JpaRepository representa a classe pai da ORM e spring data,
// logo sua extensão traz todos os métodos padrões como findById() para a interface UserRepository
// essencial por se tratar de @Repository, modulo responsavel por comandos de DML - Data Manipulation Language

public interface UserRepository extends JpaRepository<User, Integer> {
    public boolean existsByEmail(String email);
}
