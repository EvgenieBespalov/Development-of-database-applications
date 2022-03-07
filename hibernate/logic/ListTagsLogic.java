package hibernate.logic;

import hibernate.HibernateSessionFactoryUtil;
import hibernate.dao.ListTagsDao;
import hibernate.interfaces.LogicAbCl;
import models.Listtags;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ListTagsLogic extends LogicAbCl<Listtags> {

    private ListTagsDao listTagsDao = new ListTagsDao();

    public ListTagsLogic(){}

    @Override
    public void save(Listtags listtags) {
        listTagsDao.save(listtags);
    }

    @Override
    public void delete(Listtags listtags) {
        listTagsDao.delete(listtags);
    }

    @Override
    public void update(Listtags listtags) {
        listTagsDao.update(listtags);
    }

    @Override
    public List<Listtags> findAll() {
        return listTagsDao.findAll();
    }

    @Override
    public Listtags findById(int id) {
        return listTagsDao.findById(id);
    }
}