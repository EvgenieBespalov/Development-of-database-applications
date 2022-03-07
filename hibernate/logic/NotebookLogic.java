package hibernate.logic;

import hibernate.HibernateSessionFactoryUtil;
import hibernate.dao.ListTagsDao;
import hibernate.dao.NotebookDao;
import hibernate.interfaces.LogicAbCl;
import models.Listtags;
import models.Notebook;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class NotebookLogic extends LogicAbCl<Notebook> {

    private NotebookDao notebookDao = new NotebookDao();


    public NotebookLogic(){}

    public void appendTags(long markId, Listtags modelEntity) {
        notebookDao.appendTags(markId,modelEntity);
    }

    @Override
    public void save(Notebook notebook) {
        notebookDao.save(notebook);
    }

    @Override
    public void delete(Notebook notebook) {
        notebookDao.delete(notebook);
    }

    @Override
    public void update(Notebook notebook) {
        notebookDao.update(notebook);
    }

    @Override
    public List<Notebook> findAll() {
        return notebookDao.findAll();
    }

    @Override
    public Notebook findById(int id) {
        return notebookDao.findById(id);
    }

    public List<Notebook> findByName(String name) {
        return notebookDao.findByName(name);
    }

    public List<Notebook> findByDate(String date1, String date2) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return notebookDao.findByDate(LocalDate.parse(date1, dtf), LocalDate.parse(date2, dtf));
    }

    public List<Notebook> findByText(String text) {
        return notebookDao.findByText(text);
    }

    public List<Notebook> findByTags(String tag) {
        return notebookDao.findByTags(tag);
    }
}
