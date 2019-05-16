package me.duras.piatkovemocky;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * App
 */
public final class App {
    private App() {
    }

    /**
     * Aplikacia, ktora vždy pridá väzňov a vypíše ich
     *
     * @param args The arguments of the program.
     * @throws UnirestException
     */
    public static void main(String[] args) throws UnirestException {
        Databaza db = new Databaza();
        VazenDao vazniDao = new VazenDao(db.getJdbcTemplate());
        Ministerstvo ministerstvoApi = new Ministerstvo();

        Logika logika = new Logika(vazniDao, ministerstvoApi);

        logika.novyVazen("Aladeen", "oholenie a odňatie slobody", "1r");
        logika.novyVazen("Kid", "šteklenie", "1x");

        System.out.println(logika.vypisVaznov());

        db.close();
    }
}
