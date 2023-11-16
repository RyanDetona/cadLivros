package com.ryan.prjLivro.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ryan.prjLivro.entities.Livro;
import com.ryan.prjLivro.repositories.LivroRepository;


@Service
public class LivroService {	
	
private final LivroRepository livroRepository;
	
	public LivroService(LivroRepository livroRepository) {
		this.livroRepository = livroRepository;
	}

	public Livro savelivro(Livro livro) {
		return livroRepository.save(livro);
	}
	
	public  List<Livro> getAlllivro() {
		return livroRepository.findAll();
	}

	public Livro getlivroById(Long id) {
		return livroRepository.findById(id).orElse(null);
	}

	public void deletelivro(Long id) {
		livroRepository.deleteById(id);
	}

	public Livro updatelivro(Long id, Livro novolivro) {
		Optional<Livro> livroOptional = livroRepository.findById(id);
		if (livroOptional.isPresent()) {
			Livro livroExistente = livroOptional.get();
			livroExistente.setDescicao(novolivro.getDescicao());
			livroExistente.setIsbn(novolivro.getIsbn());
			return livroRepository.save(livroExistente);
		} else {
			return null;
		}
	}

}
