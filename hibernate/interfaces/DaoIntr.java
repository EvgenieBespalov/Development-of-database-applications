package hibernate.interfaces;

import java.util.List;

public interface DaoIntr<Type>
{
    Type findById(long id);
    List<Type> findAll();
    void delete(Type object);
    void update(Type object);
    void save(Type object);

}