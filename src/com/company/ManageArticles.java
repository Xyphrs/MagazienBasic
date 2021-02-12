package com.company;

import Entities.Article;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.List;

public class ManageArticles {

    private static SessionFactory factory;

    public static void main(String[] args) throws IOException, ParseException {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        ManageArticles MA = new ManageArticles();
        FileAccessor fa;
        fa = new FileAccessor();
        fa.readArticlesFile("articles.csv");
        System.out.println("Articles llegits des del fitxer");
        for (int i = 0; i < fa.llistaArticle.size(); i++) {

            System.out.println(fa.llistaArticle.get(i).toString());
            MA.addArticle(fa.llistaArticle.get(i));

        }
        System.out.println("Articles llegits de la base de dades");
        MA.listArticles();
        System.out
                .println("Articles llegits de la base de dades després de des actualitzacions");
        MA.listArticles();

    }

    /* Method to CREATE an Article in the database */
    public void addArticle(Article Article) {
        Session session = factory.openSession();
        Transaction tx = null;
        Integer ArticleID = null;
        try {
            tx = session.beginTransaction();

            ArticleID = (Integer) session.save(Article);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    /* Method to READ all Articles */
    public void listArticles() {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List Articles = session.createQuery("FROM Article").list();

            for (Iterator iterator = Articles.iterator(); iterator.hasNext();) {
                Article Article = (Article) iterator.next();
                System.out.println(Article.toString());
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

    /* Method to DELETE an Article from the records */
    public void deleteArticle(Integer ArticleID) {
        Session session = factory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Article Article = (Article) session.get(Article.class, ArticleID);
            session.delete(Article);
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
