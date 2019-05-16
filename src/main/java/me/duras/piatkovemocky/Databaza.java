package me.duras.piatkovemocky;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * Databaza
 */
public class Databaza {

    private Connection connection = null;
    private JdbcTemplate jdbcTemplate;

    public Databaza() {
        Path userDirpath = Paths.get(System.getProperty("user.dir"));
        Path dbPath = Paths.get(userDirpath.toString(), "sqlite.db");

        try {
            String connectionUrl = "jdbc:sqlite:" + dbPath.toString();

            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName("org.sqlite.JDBC");
            dataSource.setUrl(connectionUrl);
            connection = dataSource.getConnection();
            jdbcTemplate = new JdbcTemplate(dataSource);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Get current jdbc template
     *
     * @return the jdbcTemplate
     */
    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    /**
     * Close database connection
     */
    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
