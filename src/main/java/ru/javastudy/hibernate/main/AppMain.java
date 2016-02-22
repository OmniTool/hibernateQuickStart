package ru.javastudy.hibernate.main;

import org.hibernate.Session;
import ru.javastudy.hibernate.crud.CrudManager;
import ru.javastudy.hibernate.dao.ContactEntity;
import ru.javastudy.hibernate.utils.HibernateSessionFactory;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class AppMain {

    public static void main(String[] args) {
        System.out.println("Hibernate tutorial");

        /*

        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        session.beginTransaction();

        ContactEntity contactEntity = new ContactEntity();

        contactEntity.setBirthDate(new java.util.Date());
        contactEntity.setFirstName("Black");
        contactEntity.setLastName("SU");

        session.save(contactEntity);
        session.getTransaction().commit(); // вылетит эксепшн при попытке записать уже существующие данные

        session.close();

        */


        CrudManager crudManager = new CrudManager();
        

        // Add new user
        ContactEntity contactEntity = new ContactEntity();
        contactEntity.setFirstName("Daniel6");
        contactEntity.setLastName("NikoJdbc6");
        try {
            java.util.Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse("1986-01-02");
            contactEntity.setBirthDate(birthDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Add contact
        crudManager.addContact(contactEntity);

        // Update contact
        contactEntity.setLastName("Niko");
        //contactEntity.setId(6);
        crudManager.updateContact(contactEntity);

        // Delete contact
        //dao.deleteUser(6);

        // Get all contacts
        for (ContactEntity contact : crudManager.getAllContacts()) {
            System.out.println(contact);
        }

        // Get user by id
        System.out.println(crudManager.getContactById(1));



    }
}
