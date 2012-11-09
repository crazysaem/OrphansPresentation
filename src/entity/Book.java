package entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String name;
	
	//@OneToMany(mappedBy = "book", orphanRemoval=false, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    //private Collection<Page> pages;
	
	@OneToOne(cascade=CascadeType.ALL, /*orphanRemoval=true,*/ fetch = FetchType.EAGER)
	private Author author;
	
	public Book()
	{
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	/*
	public Collection<Page> getPages() {
		return pages;
	}

	public void setPages(Collection<Page> pages) {
		this.pages = pages;
	}*/
	
	@Override
	public String toString() {
		return this.name;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
}
