package me.duras.piatkovemocky;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 * VazenDao
 */
public class VazenDao {

    private JdbcTemplate jdbcTemplate;

    public VazenDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Vazen> getAll() {
        String sql = "SELECT * FROM Vazen";

        return jdbcTemplate.query(sql, new RowMapper<Vazen>() {

            @Override
            public Vazen mapRow(ResultSet rs, int rowNum) throws SQLException {
                Vazen vazen = new Vazen(rs.getString("meno"), rs.getString("trest"),
                        rs.getString("trvanie"));

                vazen.setId(rs.getInt("id"));

                return vazen;
            }

        });
    }

    public Vazen create(Vazen vazen) {
        // Creating
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        simpleJdbcInsert.withTableName("vazen");
        simpleJdbcInsert.usingGeneratedKeyColumns("id");
        simpleJdbcInsert.usingColumns("meno", "trest", "trvanie");

        Map<String, Object> values = new HashMap<>();
        values.put("meno", vazen.getMeno());
        values.put("trest", vazen.getTrest());
        values.put("trvanie", vazen.getTrvanie());

        int id = simpleJdbcInsert.executeAndReturnKey(values).intValue();
        vazen.setId((int) id);

        return vazen;
    }
}
