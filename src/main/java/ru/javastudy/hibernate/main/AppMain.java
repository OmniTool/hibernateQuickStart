package ru.javastudy.hibernate.main;

import org.hibernate.Session;
import ru.javastudy.hibernate.dao.ContactEntity;
import ru.javastudy.hibernate.utils.HibernateSessionFactory;

public class AppMain {

    public static void main(String[] args) {
        System.out.println("Hibernate tutorial");

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        ContactEntity contactEntity = new ContactEntity();

        contactEntity.setBirthDate(new java.util.Date());
        contactEntity.setFirstName("Black");
        contactEntity.setLastName("SU");

        session.save(contactEntity);
        session.getTransaction().commit(); // вылетит эксепшн при попытке записать уже существующие данные

        session.close();


    }
}
