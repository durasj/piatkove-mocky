package me.duras.piatkovemocky;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Imaginarna logika aplikacie
 */
public class Logika {

    private VazenDao dao;

    Logika(VazenDao dao) {
        this.dao = dao;
    }

    public Vazen novyVazen(String meno, String trest, String trvanie) {
        Vazen vazen = new Vazen("Aladin", "oholenie", "1r");
        this.dao.create(vazen);

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
