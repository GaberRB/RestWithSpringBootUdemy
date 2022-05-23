package br.com.gaberrb.services;

import br.com.gaberrb.converter.DozerConverter;
import br.com.gaberrb.data.model.Book;
import br.com.gaberrb.data.vo.v1.BookVO;
import br.com.gaberrb.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    public BookVO create(BookVO book){
        var entity = DozerConverter.parseObject(book, Book.class);
        var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);
        return vo;
    }

    public Page<BookVO> findAll(Pageable pageable) {

       var page = repository.findAll(pageable);

       return page.map(this :: convertToBookVO);
    }

    private BookVO convertToBookVO(Book entity){
        return DozerConverter.parseObject(repository.save(entity), BookVO.class);
    }


    public BookVO findById(Long id){

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        return DozerConverter.parseObject(entity, BookVO.class);

    }

    public BookVO update(BookVO book){
        var entity = repository.findById(book.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setTitle(entity.getTitle());
        entity.setDescription(entity.getDescription());
        entity.setPrice(entity.getPrice());
        entity.setStars(entity.getStars());

        var vo = DozerConverter.parseObject(repository.save(entity), BookVO.class);

        return vo;

    }

    public void delete(Long id){
        Book entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }

}
