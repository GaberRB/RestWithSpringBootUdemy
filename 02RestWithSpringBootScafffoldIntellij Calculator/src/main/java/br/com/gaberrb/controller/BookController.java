package br.com.gaberrb.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import br.com.gaberrb.data.vo.v1.BookVO;
import br.com.gaberrb.data.vo.v1.PersonVO;
import br.com.gaberrb.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/book/v1")
public class BookController {

    @Autowired
    BookService service;

    @GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
    public List<BookVO> findAll(){
        var books = service.findAll();

        books.stream()
                .forEach(b -> b.add(
                        linkTo(methodOn(BookController.class).findById(b.getKey())).withSelfRel()
                        )
                );

        return books;
    }

    @GetMapping(value = "{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
    public BookVO findById(@PathVariable("id") Long id) {
        var bookVO = service.findById(id);
        bookVO.add(linkTo(methodOn(BookController.class).findById(id)).withSelfRel());

        return bookVO;
    }

    @PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, consumes ={"application/json", "application/xml", "application/x-yaml"})
    public BookVO create(@RequestBody BookVO book) {
        var bookVO = service.create(book);
        bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());

        return bookVO;
    }

    @PutMapping(produces = {"application/json", "application/xml", "application/x-yaml"}, consumes ={"application/json", "application/xml", "application/x-yaml"})
    public BookVO update(@RequestBody BookVO book) {
        var bookVO = service.update(book);
        bookVO.add(linkTo(methodOn(BookController.class).findById(bookVO.getKey())).withSelfRel());

        return bookVO;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }



}
