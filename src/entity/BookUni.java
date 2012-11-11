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
@Table(name="bookuni")
public class BookUni 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String name;
	
	@OneToMany(/*orphanRemoval=true,*/ cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    private Collection<PageUni> pages;
	
	public BookUni()
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
	
	public Collection<PageUni> getPages() {
		return pages;
	}

	public void setPages(Collection<PageUni> pages) {
		this.pages = pages;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
