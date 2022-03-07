package models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "listtags")
public class Listtags implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tag;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tags")
    @Cascade({ CascadeType.MERGE})

    private Set<Notebook> notebook;

    public Listtags(){}

    public Listtags( String tag)
    {
        this.tag = tag;
    }

    public int getId()
    {
        return this.id;
    }
    public void setId(int id)
    {
        this.id = id;
    }

    public int getTagId()
    {
        return this.id;
    }
    public void setTagId(int id)
    {
        this.id = id;
    }

    public String getTag()
    {
        return tag;
    }
    public void setTag(String tag)
    {
        this.tag = tag;
    }

    @Override
    public String toString()
    {
        return tag + " ";
    }
}