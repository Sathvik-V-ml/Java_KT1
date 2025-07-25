package com.example.sathvikv;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            Customer customer = new Customer("John boy", "john12223.doe@example.com");
            product prod = new product("can", 490);
            product prod1 = new product("table", 800);

            session.save(customer);
            session.save(prod);
            session.save(prod1);

            transaction.commit();
            session.close();
            System.out.println("Customer and products saved successfully!");

            session = sessionFactory.openSession();
            product prod2 = session.get(product.class, 1);

            if (prod2 != null) {
                System.out.println("Product found!");
                System.out.println("Product ID: " + prod2.getId());
                System.out.println("Product Name: " + prod2.getName());
                System.out.println("Product Price: " + prod2.getPrice());
            } else {
                System.out.println("Product not found.");
            }
            session.close();

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            product prod3 = session.get(product.class, 2);
            if (prod3 != null) {
                prod3.setName("bike");
                prod3.setPrice(45677);
                session.update(prod3);
                System.out.println("Product with ID 2 updated.");
            } else {
                System.out.println("Product with ID 2 not found for update.");
            }

            transaction.commit();
            session.close();

            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            product prodToDelete = session.get(product.class, 3);
            if (prodToDelete != null) {
                session.delete(prodToDelete);
                System.out.println("Product with ID 3 deleted.");
            } else {
                System.out.println("Product with ID 3 not found for deletion.");
            }

            transaction.commit();
            session.close();

        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            if (session != null) session.close();
            sessionFactory.close();
        }
    }
}
