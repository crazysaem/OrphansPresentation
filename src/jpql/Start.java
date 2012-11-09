package jpql;

import entity.Book;
import entity.Page;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Start 
{
	private static final String PERSISTENCE_UNIT_NAME = "jpa";
	private static EntityManagerFactory factory;

	public static void main(String[] args) 
	{
	    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    EntityManager em = factory.createEntityManager();
	    // Read the existing entries and write to console
	    Query q = em.createQuery("select b from Book b");
	    List<Book> booklist = q.getResultList();
	    for (Book todo : booklist)
	    {
	    	System.out.println(todo);
	    }
	    System.out.println("Size: " + booklist.size());
	
	    // Create new Book
	    em.getTransaction().begin();
	    
	    Book book = new Book();
	    book.setName("The Help");
	    
	    ArrayList<Page> pages = new ArrayList<Page>();
	    
	    for(int i=0; i<150; i++)
	    {
	    	Page p = new Page();
	    	p.setPageNumber(i);
	    	p.setBook(book);
	    	pages.add(p);
	    }
	    
	    book.setPages(pages);
	    
	    em.persist(book);
	    em.getTransaction().commit();
	
	    em.close();
	}
} 