package com.mandy.Todo.Controller;

import com.mandy.Todo.Models.Appclass;
import com.mandy.Todo.service.service;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Todo/api")
@Slf4j

public class TodoController {
    @Autowired
    service ob2;
    @GetMapping("/{id}")
    ResponseEntity<Appclass> findid(@PathVariable long id)
    {
        try
        {
            Appclass isfound=ob2.searchid(id);
            return new ResponseEntity<>(isfound, HttpStatus.ACCEPTED);
        }
        catch(RuntimeException e)
        {
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }
    @ApiResponses(value={
            @ApiResponse(responseCode = "200",description = "Todo success"),
            @ApiResponse(responseCode = "201",description = "Todo success create"),
            @ApiResponse(responseCode = "400",description = "Todo not found")
    })
    @GetMapping
    ResponseEntity<List<Appclass>> getAll()
    {
        return new ResponseEntity<>(ob2.gettodoss(), HttpStatus.OK);
    }


    @GetMapping("/page")
    ResponseEntity<Page<Appclass>> getpage(@RequestParam int page,@RequestParam int size)
    {
        return new ResponseEntity<>(ob2.pagedisp(page, size),HttpStatus.OK);
    }
    @PostMapping("/usercreate")
    ResponseEntity<Appclass> usercreate(@RequestBody Appclass inf)
    {
        return new ResponseEntity<>(ob2.createTodo(inf),HttpStatus.CREATED);
    }
    @PutMapping("/update")
    ResponseEntity<Appclass> updatei(@RequestBody Appclass up)
    {
        return new ResponseEntity<>(ob2.update(up),HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    String dd(@PathVariable long id)
    {
        ob2.delete(id);
        return "Deleted";
    }




}
