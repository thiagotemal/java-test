package br.com.temal.jdbcClientTest;

import java.util.List;
import java.util.Optional;

public interface DirectDebitEntryService {

    void create(DirectDebitEntry dde);
    Optional<DirectDebitEntry> findByid(String id);
    List<DirectDebitEntry> findAll();
    void delete(String id);
    void update(DirectDebitEntry dde);

}