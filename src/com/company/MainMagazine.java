package com.company;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainMagazine {

    public static void main(String[] args) {
        ArrayList<Revista> revistes = new ArrayList<>();
        FileAccessor fa;

        Menu menu = new Menu();
        int opcio;

        while (true) {
            opcio = menu.menuPral();
            switch (opcio) {
                case 1 -> {
                    System.out.println("1!!");
                    fa = new FileAccessor();
                    try {
                        fa.readAutorsFile("autors.csv");
                        fa.printAutors();
                        fa.readMagazinesFile("revistes.csv");
                        fa.printRevistes();
                        revistes = fa.readArticlesFile("articles.csv");
                        mostraRevistes(revistes);
                    } catch (NumberFormatException | IOException | ParseException e) {
                        e.printStackTrace();
                    }

                }
                case 2 -> {
                    System.out.println("2!!!");
                    System.out.println(seleccionaArticle(seleccionaRevista(revistes)).getAutor().toString());

                }
                default -> {
                    System.out.println("Adeu!!");
                    System.exit(1);
                }
            }
        }
    }

    public static void mostraRevistes(ArrayList<Revista> revistes){
        for (int i = 0; i < revistes.size(); i++) {

            System.out.println(revistes.get(i).toString());
            for (int j = 0; j < revistes.get(i).getArticles().size(); j++) {
                System.out.println("\t"+ revistes.get(i).getArticle(j).toString());
                System.out.println("\t\t"+revistes.get(i).getArticle(j).getAutor().toString());
            }

        }
    }

    public static Revista seleccionaRevista(ArrayList<Revista> revistes){
        Scanner scanner = new Scanner(System.in);
        revistes.forEach(System.out::println);
        System.out.println("Introduce un identificador de una revista");
        int seleccion = scanner.nextInt();
        System.out.println(revistes.get(seleccion - 1));

        return revistes.get(seleccion - 1);
    }

    public static Article seleccionaArticle(Revista revista){
        Scanner scanner = new Scanner(System.in);

        System.out.println("------------------");
        revista.getArticles().forEach(System.out::println);

        System.out.println("\nIntroduce un identificador del articulo");
        int seleccion = scanner.nextInt();

        Article article  = null;

        for (int i = 0; i < revista.getArticles().size(); i++) {
            if (revista.getArticle(i).getId_article() == seleccion){
                article = revista.getArticle(i);
            }
        }
        return article;
    }
}
