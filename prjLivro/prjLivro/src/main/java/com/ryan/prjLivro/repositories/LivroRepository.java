package com.ryan.prjLivro.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ryan.prjLivro.entities.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{
	
}
