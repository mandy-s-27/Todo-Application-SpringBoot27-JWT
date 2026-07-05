package com.mandy.Todo.service;
import java.util.*;
import com.mandy.Todo.Models.Appclass;
import com.mandy.Todo.Respository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class service {
    @Autowired
    Repository ob1;
    public Appclass createTodo(Appclass pair)
    {
        return ob1.save(pair);
    }

    public Appclass searchid(Long id) {
        return ob1.findById(id).orElseThrow(()->new RuntimeException("Not found"));
    }
    public Page<Appclass> pagedisp(int page, int size)
    {
        Pageable pageob= PageRequest.of(page,size);
        return ob1.findAll(pageob);
    }
    public List<Appclass> gettodoss()
    {
        return ob1.findAll();
    }
    public Appclass update(Appclass pair)
    {
        return ob1.save(pair);
    }

    public void delete(Long id)  {
        ob1.delete(searchid(id));
    }
    void deleteall(Appclass del)
    {
        ob1.delete(del);
    }
    public List<Appclass> gettodos() {
        return ob1.findAll();
    }
}
