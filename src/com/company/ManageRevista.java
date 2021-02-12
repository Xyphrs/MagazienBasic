package com.company;

import Entities.Revista;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ManageRevista {

    private static SessionFactory factory;

    public static void main(String[] args) throws IOException {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        ManageRevista MA = new ManageRevista();
        FileAccessor fa;
        fa = new FileAccessor();
        fa.readMagazinesFile("revistes.csv");
        System.out.println("Revistas llegits des del fitxer");
        for (int i = 0; i < fa.llistaRevistes.size(); i++) {

            System.out.println(fa.llistaRevistes.get(i).toString());
            MA.addRevista(fa.llistaRevistes.get(i));

        }
        System.out.println("Revistas llegits de la base de dades");
        MA.listRevistas();
        System.out
                .println("Revistas llegits de la base de dades després de des actualitzacions");
        MA.listRevistas();

    }

    /* Method to CREATE an Revista in the database */
    public void addRevista(Revista revista) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer RevistaID = null;
        try {
            tx = session.beginTransaction();

            RevistaID = (Integer) session.save(revista);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    /* Method to READ all Revistas */
    public void listRevistas() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List Revistas = session.createQuery("FROM Revista").list();

            for (Iterator iterator = Revistas.iterator(); iterator.hasNext();) {
                Revista Revista = (Revista) iterator.next();
                System.out.println(Revista.toString());
            }

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    /* Method to DELETE an revista from the records */
    public void deleteRevista(Integer RevistaID) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Revista Revista = (Revista) session.get(Revista.class, RevistaID);
            session.delete(Revista);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
