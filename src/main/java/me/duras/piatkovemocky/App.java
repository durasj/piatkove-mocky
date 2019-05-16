package me.duras.piatkovemocky;

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        Databaza db = new Databaza();
        VazenDao vazniDao = new VazenDao(db.getJdbcTemplate());

        Logika logika = new Logika(vazniDao);

        logika.novyVazen("Aladeen", "oholenie a odňatie slobody", "1r");
        logika.novyVazen("Kid", "šteklenie", "1x");

        System.out.println(logika.vypisVaznov().toString());
    }
}
