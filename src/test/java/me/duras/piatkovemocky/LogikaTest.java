package me.duras.piatkovemocky;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.mashape.unirest.http.exceptions.UnirestException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LogikaTest {
    @Mock
    private VazenDao vazniDao;

    @Mock
    private Ministerstvo ministerstvoApi;

    @Test
    @DisplayName("Test pridavania novych")
    void novyVazen() throws UnirestException {
        when(vazniDao.create(any(Vazen.class))).thenAnswer((invocation) -> {
            Vazen vazen = (Vazen) invocation.getArguments()[0];
            vazen.setId(1);
            return vazen;
        });

        Logika logika = new Logika(vazniDao, ministerstvoApi);

        Vazen vazen = logika.novyVazen("Jano Tester", "odobratie počítača", "1x");

        assertEquals("Jano Tester", vazen.getMeno());
        assertEquals("odobratie počítača", vazen.getTrest());
        assertEquals("1x", vazen.getTrvanie());
        assertTrue(vazen.getId() > 0);
        verify(ministerstvoApi).nahrajVazna(vazen);
    }

    @Test
    @DisplayName("Test listovania")
    void vypisVaznov() throws UnirestException {
        List<Vazen> list = new ArrayList<Vazen>();
        Vazen jano = new Vazen("Jano Tester", "odobratie počítača", "1x");
        list.add(jano);
        when(vazniDao.getAll()).thenReturn(list);

        Logika logika = new Logika(vazniDao, ministerstvoApi);

        String vypis = logika.vypisVaznov();

        assertTrue(vypis.contains(jano.vypis()));
    }

}
