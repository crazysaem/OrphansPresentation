package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="page")
public class Page 
{
	@Id
    private Long id;
	
	private int pageNumber;
	
	@ManyToOne
    private Book book;
}
