package me.duras.piatkovemocky;

import java.util.List;
import java.util.stream.Collectors;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * Imaginarna logika aplikacie
 */
public class Logika {

    private VazenDao dao;
    private Ministerstvo api;

    Logika(VazenDao dao, Ministerstvo api) {
        this.dao = dao;
        this.api = api;
    }

    public Vazen novyVazen(String meno, String trest, String trvanie) throws UnirestException {
        Vazen vazen = new Vazen(meno, trest, trvanie);
        this.dao.create(vazen);
        this.api.nahrajVazna(vazen);

        return vazen;
    }

    public String vypisVaznov() {
        List<Vazen> vazni = this.dao.getAll();

        return vazni.stream().map((Vazen vzn) ->
            "ID " + vzn.getId()
            + "\nMeno: " + vzn.getMeno()
            + "\nTrest: " + vzn.getTrest()
            + "\nTrvanie: " + vzn.getTrvanie()
        ).collect(Collectors.joining("\n---------\n"));
    }
}
