package hibernate.dao;

import hibernate.HibernateSessionFactoryUtil;
import hibernate.interfaces.DaoIntr;
import models.Listtags;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ListTagsDao implements DaoIntr<Listtags>
{
    @Override
    public Listtags findById(long id)
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Listtags.class, id);
    }

    @Override
    public List<Listtags> findAll() {
        List<Listtags> Notebooks = (List<Listtags>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Listtags").list();
        return Notebooks;
    }

    @Override
    public void delete(Listtags notebook) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(notebook);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(Listtags notebook) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery(
                "select a.id from Listtags as a ");

        List<Integer> idNotebook = query.list();

        if(idNotebook.size()!=0)
        {
            //notebook.setId(idNotebook.get((int)notebook.getId()-1));
            session.update(notebook);
        }
        else
        {
            System.out.println("Такой аудитории нет!");
        }
        tx1.commit();
        session.close();
    }

    public void update(Listtags notebookBefore, Listtags notebookAfter)
    {
        /*Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Query query = session.createQuery("select a.id from listtags as a where a.auditory = '" + notebookBefore.getNotebook() + "'");

        List<Integer> idNotebook = query.list();

        if(idNotebook.size()!=0)
        {
            //notebookAfter.setId(idNotebook.get(0));
            session.update(notebookAfter);
        }
        else
        {
            System.out.println("Такой аудитории нет!");
        }
        tx1.commit();
        session.close();*/
    }

    @Override
    public void save(Listtags newTags)
    {

        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Query query = session.createQuery("select a.tag from Listtags as a where a.tag = '"
                + newTags.getTag() + "'");
        List<String> listTags = query.list();

        if(listTags.size() == 0)
        {
            session.save(newTags);
        }
        else
        {
            System.out.println("Такой тег уже существует!");
        }

        tx1.commit();
        session.close();
    }
}
