package hibernate.dao;

import hibernate.HibernateSessionFactoryUtil;
import hibernate.interfaces.DaoIntr;
import models.Listtags;
import models.Notebook;
import org.hibernate.*;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NotebookDao implements DaoIntr<Notebook>
{
    private SessionFactory sessionFactory;

    @Override
    public Notebook findById(long id)
    {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Notebook.class, id);
    }

    public List<Notebook> findByName(String name)
    {
        List<Notebook> Notebooks = (List<Notebook>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                "select nb from Notebook as nb where nb.name='" + name + "'").list();

        return Notebooks;
    }

    public List<Notebook> findByDate(LocalDate date1, LocalDate date2)
    {
        List<Notebook> Notebooks = (List<Notebook>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                "select nb from Notebook as nb where nb.date between '" + date1 + "' and '" + date2 + "'").list();

        return Notebooks;
    }

    public List<Notebook> findByText(String text)
    {
        List<Notebook> Notebooks = (List<Notebook>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery(
                "select nb from Notebook as nb where (fts(nb.text," + text + ") = true )").list();
        return Notebooks;
    }

    public List<Notebook> findByTags(String tag)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Query query = session.createQuery(
                "select nb from Notebook as nb join nb.tags cs where cs.tag='" + tag +"'");
        List<Notebook> list = query.list();

        tx1.commit();
        session.close();
        return list;
    }

    public void appendTags(long notebookId, Listtags tags)
    {
        Notebook notebook= HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Notebook.class, notebookId);
        if(notebook!=null){
            notebook.getTags().add(tags);
        }
    }

    private Session getCurrentSession()
    {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Notebook> findAll() {
        List<Notebook> Notebooks = (List<Notebook>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Notebook").list();
        return Notebooks;
    }

    @Override
    public void delete(Notebook notebook) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Query query = session.createQuery(
                "select nb from Notebook as nb where nb.id = '" + notebook.getId() + "'");
        Notebook note = (Notebook) query.list().get(0);

        System.out.println(note);
        session.delete(note);

        tx1.commit();
        session.close();
    }

    @Override
    public void update(Notebook notebook) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        Query query = session.createQuery(
                "select nb from Notebook as nb where nb.id = '" + notebook.getId() + "'");
        Notebook note = (Notebook) query.list().get(0);

        ArrayList<Listtags> tags1 = new ArrayList<Listtags>();
        for (Listtags tag : notebook.getTags()) {
            query = session.createQuery(
                    "select lt from Listtags as lt where lt.tag = '" + tag.getTag() + "'");
            List<Listtags> listtag = query.list();
            if (listtag.size() != 0) {
                tags1.add(listtag.get(0));
            }
            else {
                tags1.add(tag);
            }
        }

        note.setName(notebook.getName());
        note.setDate(notebook.getDate());
        note.setText(notebook.getText());
        note.setTags(tags1);

        session.update(note);
        tx1.commit();
        session.close();
    }

    @Override
    public void save(Notebook note1)
    {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();

        ArrayList<Listtags> tags1 = new ArrayList<Listtags>();
        for (Listtags tag : note1.getTags()) {
            Query query = session.createQuery(
                    "select lt from Listtags as lt where lt.tag = '" + tag.getTag() + "'");// tags2.get(1).getTag()
            List<Listtags> list = query.list();
            if (list.size() != 0)
            {
                tags1.add(list.get(0));
            }
            else
            {
                tags1.add(tag);
            }
        }
        note1.setTags(tags1);

        session.saveOrUpdate(note1);

        tx1.commit();
        session.close();
    }

}
