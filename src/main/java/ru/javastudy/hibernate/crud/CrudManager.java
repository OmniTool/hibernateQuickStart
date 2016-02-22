package ru.javastudy.hibernate.crud;


    import java.util.ArrayList;
    import java.util.List;

    import org.hibernate.Query;
    import org.hibernate.Session;
    import org.hibernate.Transaction;

    import ru.javastudy.hibernate.dao.ContactEntity;
    import ru.javastudy.hibernate.utils.HibernateSessionFactory;

    public class CrudManager {

        public void addContact(ContactEntity contactEntity) {
            Transaction trns = null;
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            try {
                trns = session.beginTransaction();
                session.save(contactEntity);
                session.getTransaction().commit();
            } catch (RuntimeException e) {
                if (trns != null) {
                    trns.rollback();
                }
                e.printStackTrace();
            } finally {
                session.flush();
                session.close();
            }
        }

        public void deleteContact(int id) {
            Transaction trns = null;
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            try {
                trns = session.beginTransaction();
                ContactEntity contactEntity = (ContactEntity) session.load(ContactEntity.class, id); //id: исправлено Integer на int
                session.delete(contactEntity);
                session.getTransaction().commit();
            } catch (RuntimeException e) {
                if (trns != null) {
                    trns.rollback();
                }
                e.printStackTrace();
            } finally {
                //session.flush();
                session.close();
            }
        }

        public void updateContact(ContactEntity contactEntity) {
            Transaction trns = null;
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            try {
                trns = session.beginTransaction();
                session.update(contactEntity);
                session.getTransaction().commit();
            } catch (RuntimeException e) {
                if (trns != null) {
                    trns.rollback();
                }
                e.printStackTrace();
            } finally {
                //session.flush();
                session.close();
            }
        }

        public List<ContactEntity> getAllContacts() {
            List<ContactEntity> contactEntities = new ArrayList<ContactEntity>();
            //Transaction trns = null;
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            try {
                //trns = session.beginTransaction();
                contactEntities = session.createQuery("from ContactEntity").list();
            } catch (RuntimeException e) {
                e.printStackTrace();
            } finally {
                session.flush();
                session.close();
            }
            return contactEntities;
        }

        public ContactEntity getContactById(int id) {
            ContactEntity contactEntity = null;
            //Transaction trns = null;
            Session session = HibernateSessionFactory.getSessionFactory().openSession();
            try {
                //trns = session.beginTransaction();
                String queryString = "from ContactEntity where id = :id";
                Query query = session.createQuery(queryString);
                query.setInteger("id", id);
                contactEntity = (ContactEntity) query.uniqueResult();
            } catch (RuntimeException e) {
                e.printStackTrace();
            } finally {
                session.flush();
                session.close();
            }
            return contactEntity;
        }
    }

