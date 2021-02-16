package com.company;

import java.io.IOException;
import java.text.ParseException;

public class ManageMain {
    public static void main(String[] args) throws IOException, ParseException {
        ManageAutor.start();
        ManageRevista.start();
        ManageArticles.start();
    }
}
