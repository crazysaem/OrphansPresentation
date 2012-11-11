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
@Table(name="bookbi")
public class BookBi 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String name;
	
	@OneToMany(mappedBy = "bookbi", /*orphanRemoval=true,*/ cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<PageBi> pages;
	
	public BookBi()
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
	
	public Collection<PageBi> getPages() {
		return pages;
	}

	public void setPages(Collection<PageBi> pages) {
		this.pages = pages;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
