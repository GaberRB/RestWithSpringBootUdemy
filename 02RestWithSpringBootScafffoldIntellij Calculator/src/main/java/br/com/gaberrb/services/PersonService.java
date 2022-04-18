package br.com.gaberrb.services;

import br.com.gaberrb.converter.DozerConverter;
import br.com.gaberrb.converter.custom.PersonConverter;
import br.com.gaberrb.data.model.Person;
import br.com.gaberrb.data.vo.v1.PersonVO;
import br.com.gaberrb.data.vo.v2.PersonVOV2;
import br.com.gaberrb.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    PersonRepository repository;

    @Autowired
    PersonConverter converter;

    public PersonVO create(PersonVO person) {
        var entity = DozerConverter.parseObject(person, Person.class);
        var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);
        return vo;
    }

    public PersonVOV2 createV2(PersonVOV2 person) {
        var entity = converter.convertVOToEntity(person);
        var vo = converter.convertEntityToVO(repository.save(entity));
        return vo;
    }

    public List<PersonVO> findAll() {

        return DozerConverter.parseListObjects(repository.findAll(), PersonVO.class);
    }

    public PersonVO findById(Long id){

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        return DozerConverter.parseObject(entity, PersonVO.class);

    }

    public PersonVO update(PersonVO person){
        var entity = repository.findById(person.getKey())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var vo = DozerConverter.parseObject(repository.save(entity), PersonVO.class);

        return vo;
    }

    public void delete(Long id){
        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
        repository.delete(entity);
    }



}
