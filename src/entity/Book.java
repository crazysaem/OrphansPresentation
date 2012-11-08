package entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book 
{
	@Id
    private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "book", cascade=CascadeType.ALL)
    private Collection<Page> pages;
}
