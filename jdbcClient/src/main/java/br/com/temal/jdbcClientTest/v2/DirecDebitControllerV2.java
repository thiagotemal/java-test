package br.com.temal.jdbcClientTest.v2;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.temal.jdbcClientTest.DirectDebitEntry;
import br.com.temal.jdbcClientTest.DirectDebitEntryService;
@RestController
@RequestMapping("v2/direct-debit-entrys")
public class DirecDebitControllerV2 {

    private final DirectDebitEntryService directDebitEntryService;

    public DirecDebitControllerV2(@Qualifier("directDebitEntryServiceJDBCTemplate") DirectDebitEntryService directDebitEntryService){
        this.directDebitEntryService=directDebitEntryService;;
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody DirectDebitEntry dde){

        directDebitEntryService.create(dde);

    }

    @GetMapping("/{id}")
    public Optional<DirectDebitEntry> findById(@PathVariable String  id){

        return directDebitEntryService.findByid(id);
        
    }
    
     @GetMapping()
    public List<DirectDebitEntry> findAll(){
        return directDebitEntryService.findAll();
    }

        @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){

         directDebitEntryService.delete(id);
        
    }

        @PutMapping("/{id}")
    @ResponseStatus
    public void create(@RequestBody DirectDebitEntry dde,@PathVariable String id){

        directDebitEntryService.create(dde);

    }
}
