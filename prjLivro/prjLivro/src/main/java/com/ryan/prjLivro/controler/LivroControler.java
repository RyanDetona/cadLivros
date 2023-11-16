package com.ryan.prjLivro.controler;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ryan.prjLivro.entities.Livro;
import com.ryan.prjLivro.services.LivroService;


@RestController
@RequestMapping("/livros")
public class LivroControler {
	
	private LivroService service;

	public LivroControler(LivroService service) {
		this.service = service;
	}

	@PostMapping
	public Livro createLivro(@RequestBody Livro livro) {
		return service.savelivro(livro);
	}
	
	@GetMapping("/home")
			public String paginalnicial () {
			return "index";
}

	@GetMapping("/{id}")
	public ResponseEntity<Livro> getLivro(@PathVariable Long id) {
		Livro livro = service.getlivroById(id);
		if (livro != null) {
			return ResponseEntity.ok(livro);
		} else {
			return ResponseEntity.notFound().build();
		}

	}

	@DeleteMapping("/{id}")
	public void deleteProduto(@PathVariable Long id) {
		service.deletelivro(id);
	}
	

	@GetMapping
	public ResponseEntity<List<Livro>> getAllLivros(RequestEntity<Void> requestEntity) {
		String method = requestEntity.getMethod().name();
		String contentType = requestEntity.getHeaders().getContentType().toString();
		List<Livro> livros = service.getAlllivro();
		return ResponseEntity.status(HttpStatus.OK).header("Method", method).header("Content-Type", contentType)
				.body(livros);
	}

	@PutMapping("/{id}")
	public Livro updateLivro(@PathVariable Long id, @RequestBody Livro livro) {
		return service.updatelivro(id, livro);
	}

}
