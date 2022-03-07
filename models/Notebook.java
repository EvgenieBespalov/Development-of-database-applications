package models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table (name = "notebook")
public class Notebook implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private LocalDate date;
    private String text;

    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade({ CascadeType.MERGE})
    @JoinTable(name="tags",
            joinColumns = @JoinColumn(name="id_nb"),
            inverseJoinColumns=@JoinColumn(name="tag_id"))
    private List<Listtags> tags;

    public Notebook(){}

    public Notebook(String name, LocalDate date, String text, List<Listtags> tags)
    {
        this.name = name;
        this.date = date;
        this.text = text;
        this.tags = tags;
    }

    public Notebook(Notebook notebook)
    {
        this.id = notebook.id;
        this.name = notebook.name;
        this.date = notebook.date;
        this.text = notebook.text;
        this.tags = notebook.tags;
    }

    public long getId()
    {
        return this.id;
    }
    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return this.name;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public LocalDate getDate()
    {
        return this.date;
    }
    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public String getText()
    {
        return this.text;
    }
    public void setText(String text)
    {
        this.text = text;
    }

    public List<Listtags> getTags()
    {
        return tags;
    }
    public void setTags(List<Listtags> tagsList)
    {
        this.tags = tagsList;
    }

    public String getNotebook() {
        return name;
    }

    @Override
    public String toString() {
        return "Имя: " + name + "\nДата: " + date + "\nТекст: " + text + "\nТеги: " + tags + "\n";
    }
}
