package br.com.temal.jdbcClientTest.v2;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.temal.jdbcClientTest.DirectDebitEntry;
import br.com.temal.jdbcClientTest.DirectDebitEntryService;

@Service
public class DirectDebitEntryServiceJDBCTemplate implements DirectDebitEntryService {

    private final JdbcTemplate jdbc;

    public DirectDebitEntryServiceJDBCTemplate(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    RowMapper<DirectDebitEntry> rowMapper = (rs, rowNum) -> new DirectDebitEntry(
            rs.getInt("id"), rs.getDouble("ammout"), rs.getString("expire_date"), rs.getString("status"));

    @Override
    public void create(DirectDebitEntry dde) {
        String sql = "insert into Direct_Debit_Entry(ammout,expire_date,status) values (?,?,?) ";
        var update = jdbc.update(sql,
                dde.ammout(), dde.expireDate(), dde.status());
        Assert.state(update == 1, "Erro ao salvar create" + dde.id());

    }

    @Override
    public Optional<DirectDebitEntry> findByid(String id) {
        String sql = "SELECT * FROM Direct_Debit_Entry WHERE id=:id ";
        DirectDebitEntry dde = null;
        dde = jdbc.queryForObject(sql, rowMapper, id);
   
        return Optional.ofNullable(dde);

    }

    @Override
    public List<DirectDebitEntry> findAll() {
        String sql = "SELECT  id, ammout, expire_date, status FROM  Direct_Debit_Entry ";
        return jdbc.query(sql, rowMapper);
    }

    @Override
    public void update(DirectDebitEntry dde) {
        String sql = "update Direct_Debit_Entry set ammout=?, expire_date=?, status=?,  where id=? ";
        int update = jdbc.update(sql, dde.ammout(), dde.expireDate(), dde.status(), dde.id());

        Assert.state(update == 1, "Erro ao atualizar create" + dde.id());
    }

    @Override
    public void delete(String id) {
        String sql = "delete Direct_Debit_Entry where id=:id ";
        int update = jdbc.update(sql, id);
        Assert.state(update == 1, "Erro ao deletar create" + id);
    }

}
