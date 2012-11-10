package jpql;

import entity.Author;
import entity.Book;
import entity.Page;

import java.util.ArrayList;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Start 
{
	private static final String PERSISTENCE_UNIT_NAME = "jpa";
	private static EntityManagerFactory factory;
	private static EntityManager em;

	public static void main(String[] args) 
	{
	    factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	    em = factory.createEntityManager();
	    
	    //createBook();
	    makeOrphin(1527);
	    //deleteBook(1504);
	}
	
	public static void createBook()
	{
	    em.getTransaction().begin();	    
	    
	    Book book = new Book();
	    book.setName("Wuuuuuusch!");
	    
	    Author a = new Author();
	    a.setName("Tufan");
	    
	    book.setAuthor(a);
	    
	    em.persist(book);
	    
	    Author b = new Author();
	    b.setName("Samuel");
	    
	    book.setAuthor(b);
	    
	    em.persist(book);
	    
	    Vector<Page> pages = new Vector<Page>();
	    
	    for(int i=0; i<=2; i++)
	    {
	    	Page page = new Page();
	    	
	    	page.setPageNumber(i);
	    	
	    	pages.add(page);	    	
	    }
	    
	    book.setPages(pages);
	    
	    em.persist(book);
	    
	    em.getTransaction().commit();
	
	    em.close();
	}
	
	public static void makeOrphin(int id)
	{
		em.getTransaction().begin();
		
		Query q = em.createQuery("select b from Book b where b.id = " + id);
	    
	    Book book = (Book) q.getSingleResult();
	    
	    Vector<Page> pages = (Vector<Page>) book.getPages();
	    
	    pages.remove(0);
	    
	    book.setPages(pages);
	    
	    em.persist(book);
	    
	    em.getTransaction().commit();
	
	    em.close();
	}
	
	public static void deleteBook(int id)
	{
		Query q = em.createQuery("select b from Book b where b.id = " + id);
	    
	    Book book = (Book) q.getSingleResult();
	    
	    em.getTransaction().begin();	    
	    
	    em.remove(book);
	    
	    em.getTransaction().commit();
	
	    em.close();
	}
} 