package br.com.temal.jdbcClientTest.v1;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.temal.jdbcClientTest.DirectDebitEntry;
import br.com.temal.jdbcClientTest.DirectDebitEntryService;

@Service
public class DirectDebitEntryServiceJDBCClient implements DirectDebitEntryService {

    private final JdbcClient jdbcClient;

    public DirectDebitEntryServiceJDBCClient(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public void create(DirectDebitEntry dde) {
        String sql = "insert into Direct_Debit_Entry(ammout,expire_date,status) values (?,?,?)";
      
        var update = jdbcClient.sql(sql)
                .params( dde.ammout(), dde.expireDate(), dde.status())
                .update();
            
        Assert.state(update == 1, "Erro ao salvar create") ;
    }

    @Override
    public Optional<DirectDebitEntry> findByid(String id) {
        String sql = "SELECT * FROM Direct_Debit_Entry WHERE id=:id ";
        return jdbcClient.sql(sql)
                .param("id", id)
                .query(DirectDebitEntry.class)
                .optional();

    }

    @Override
    public List<DirectDebitEntry> findAll() {
        String sql = "SELECT  id, ammout, expire_date, status FROM  Direct_Debit_Entry ";
         return jdbcClient.sql(sql)
                .query(DirectDebitEntry.class)
                .list();
    }

    @Override
    public void update(DirectDebitEntry dde)   {
        String sql = "update Direct_Debit_Entry set ammout=?, expire_date=?, status=?,  where id=? ";
          int update = jdbcClient.sql(sql)
                .params(List.of(
                        
                       dde.ammout(), dde.expireDate(), dde.status(),dde.id()
                        ))
                .update();
        Assert.state(update == 1, "Erro ao atualizar create" + dde.id());
    }

    @Override
    public void delete(String id){
        String sql = "delete Direct_Debit_Entry where id=? ";
        int update = jdbcClient.sql(sql)
                .param(1, id)
                .update();
        Assert.state(update == 1, "Erro ao deletar create" + id);
    }

}
