package jpql;

import entity.Book;
import entity.Page;

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
	    
	    //Long id = createBook();
	    //makeOrphin(1574);
	    resetTables();
	    
	    em.close();
	}
	
	public static long createBook()
	{
	    em.getTransaction().begin();	    
	    
	    Book book = new Book();
	    book.setName("Harry Potter!");
	    
	    em.persist(book);
	    
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
	    
	    Long id = book.getId();	    
	    
	    return id;
	}
	
	public static void makeOrphin(long id)
	{
		em.getTransaction().begin();
		
		Query q = em.createQuery("select b from Book b where b.id = " + id);
	    
	    Book book = (Book) q.getSingleResult();
	    
	    Vector<Page> pages = (Vector<Page>) book.getPages();
	    
	    pages.remove(0);
	    
	    book.setPages(pages);
	    
	    em.persist(book);
	    
	    em.getTransaction().commit();
	}
	
	public static void resetTables()
	{
		em.getTransaction().begin();
		
		Query deleteBookPage = em.createNativeQuery("delete from Book_Page");
		deleteBookPage.executeUpdate();
		
		Query deleteBook = em.createNativeQuery("delete from Book");
		deleteBook.executeUpdate();
		
		Query deletePage = em.createNativeQuery("delete from Page");
		deletePage.executeUpdate();
	    
	    em.getTransaction().commit();
	}
} 