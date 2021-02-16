package Entities;

import java.io.Serializable;
import java.util.Date;

public class Article implements Serializable {
    int id_article;
    int id_revista;
    public Autor id_autor;
    String titol;
    Date data_creacio;
    boolean publicable;

    public Article() {
    }

    public Article(int id_article, String titol, Date data_creacio, boolean publicable, int id_revista, Autor id_autor) {
        this.id_article = id_article;
        this.titol = titol;
        this.data_creacio = data_creacio;
        this.publicable = publicable;
        this.id_revista = id_revista;
        this.id_autor = id_autor;
    }

    public int getId_article() {
        return id_article;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public Date getData_creacio() {
        return data_creacio;
    }

    public void setData_creacio(Date data_creacio) {
        this.data_creacio = data_creacio;
    }

    public boolean isPublicable() {
        return publicable;
    }

    public void setPublicable(boolean publicable) {
        this.publicable = publicable;
    }

    public int getId_revista() {
        return id_revista;
    }

    public void setId_revista(int id_revista) {
        this.id_revista = id_revista;
    }

    public Autor getId_autor() {
        return id_autor;
    }

    public void setId_autor(Autor autor) {
        this.id_autor = autor;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id_article=" + id_article +
                ", titol='" + titol + '\'' +
                ", data_creacio=" + data_creacio +
                ", publicable=" + publicable +
                ", id_revista=" + id_revista +
                ", autor=" + id_autor +
                '}';
    }
}
