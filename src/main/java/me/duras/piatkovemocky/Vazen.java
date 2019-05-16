package me.duras.piatkovemocky;

/**
 * Vazen
 */
public class Vazen {

    private int id;
    private String meno;
    private String trest;
    private String trvanie;

    public Vazen(String meno, String trest, String trvanie) {
        this.meno = meno;
        this.trest = trest;
        this.trvanie = trvanie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeno() {
        return this.meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getTrest() {
        return this.trest;
    }

    public void setTrest(String trest) {
        this.trest = trest;
    }

    public String getTrvanie() {
        return this.trvanie;
    }

    public void setTrvanie(String trvanie) {
        this.trvanie = trvanie;
    }
}
