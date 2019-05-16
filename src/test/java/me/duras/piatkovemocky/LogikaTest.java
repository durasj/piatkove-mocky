package me.duras.piatkovemocky;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.mashape.unirest.http.exceptions.UnirestException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LogikaTest {
    private Databaza db;
    private VazenDao vazniDao;
    private Ministerstvo ministerstvoApi;

    @BeforeEach
    void setUp() {
        this.db = new Databaza();
        this.vazniDao = new VazenDao(db.getJdbcTemplate());
        this.ministerstvoApi = new Ministerstvo();
    }

    @AfterEach
    void tearDown() {
        db.close();
    }

    @Test
    @DisplayName("Test pridavania novych")
    void novyVazen() throws UnirestException {
        Logika logika = new Logika(vazniDao, ministerstvoApi);

        Vazen vazen = logika.novyVazen("Jano Tester", "odobratie počítača", "1x");

        assertEquals("Jano Tester", vazen.getMeno());
        assertEquals("odobratie počítača", vazen.getTrest());
        assertEquals("1x", vazen.getTrvanie());
        assertTrue(vazen.getId() > 0);
    }

    @Test
    @DisplayName("Test listovania")
    void vypisVaznov() throws UnirestException {
        Logika logika = new Logika(vazniDao, ministerstvoApi);

        Vazen vazen = logika.novyVazen("Jano Tester", "odobratie počítača", "1x");

        String vypis = logika.vypisVaznov();

        assertTrue(vypis.contains(vazen.vypis()));
    }

}
