package hibernate.interfaces;

import java.util.List;

public abstract class LogicAbCl<Type>
{

    abstract protected void save(Type object);
    abstract protected void delete(Type object);
    abstract protected void update(Type object);
    abstract protected List<Type> findAll();
    abstract protected Type findById(int id);

}
