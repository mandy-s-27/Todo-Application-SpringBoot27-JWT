package com.mandy.Todo.service;

import com.mandy.Todo.Models.user;
import com.mandy.Todo.Respository.Repository;
import com.mandy.Todo.Respository.userrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userservice {
    @Autowired
    userrepository ob1;
    public user createTodo(user pair)
    {
        return ob1.save(pair);
    }

    public user searchid(Long id) {
        return ob1.findById(id).orElseThrow(()->new RuntimeException("Not found"));
    }


}
