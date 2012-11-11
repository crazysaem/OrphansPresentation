package jpql;

import entity.BookBi;
import entity.BookUni;
import entity.PageBi;
import entity.PageUni;

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
	    
	    //createBookBi();
	    //makeOrphinBi(1626);
	    
	    //createBookUni();
	    //makeOrphinUni(1646);
	    
	    resetTables();
	    
	    em.close();
	}
	
	public static long createBookUni()
	{
	    em.getTransaction().begin();	    
	    
	    BookUni book = new BookUni();
	    book.setName("Harry Potter!");
	    
	    Vector<PageUni> pages = new Vector<PageUni>();
	    
	    for(int i=0; i<=2; i++)
	    {
	    	PageUni page = new PageUni();
	    	
	    	page.setPageNumber(i);
	    	
	    	pages.add(page);	    	
	    }
	    
	    book.setPages(pages);
	    
	    em.persist(book);
	    
	    em.getTransaction().commit();
	    
	    Long id = book.getId();	    
	    
	    return id;
	}
	
	public static long createBookBi()
	{
	    em.getTransaction().begin();	    
	    
	    BookBi book = new BookBi();
	    book.setName("Harry Potter!");
	    
	    Vector<PageBi> pages = new Vector<PageBi>();
	    
	    for(int i=0; i<=2; i++)
	    {
	    	PageBi page = new PageBi();
	    	
	    	page.setPageNumber(i);
	    	
	    	page.setBookbi(book);
	    	
	    	pages.add(page);	    	
	    }
	    
	    book.setPages(pages);
	    
	    em.persist(book);
	    
	    em.getTransaction().commit();
	    
	    Long id = book.getId();	    
	    
	    return id;
	}
	
	public static void makeOrphinUni(long id)
	{
		em.getTransaction().begin();
		
		Query q = em.createQuery("select b from BookUni b where b.id = " + id);
	    
	    BookUni book = (BookUni) q.getSingleResult();
	    
	    Vector<PageUni> pages = (Vector<PageUni>) book.getPages();
	    
	    pages.remove(0);
	    
	    book.setPages(pages);
	    
	    em.persist(book);
	    
	    em.getTransaction().commit();
	}
	
	public static void makeOrphinBi(long id)
	{
		em.getTransaction().begin();
		
		Query q = em.createQuery("select b from BookBi b where b.id = " + id);
	    
	    BookBi book = (BookBi) q.getSingleResult();
	    
	    Vector<PageBi> pages = (Vector<PageBi>) book.getPages();
	    
	    PageBi page = pages.get(0);
	    
	    page.setBookbi(null);
	    
	    pages.remove(0);
	    
	    book.setPages(pages);
	    
	    em.persist(book);
	    
	    em.getTransaction().commit();
	}
	
	public static void resetTables()
	{
		em.getTransaction().begin();
		
		Query delete = null;
		
		delete = em.createNativeQuery("delete from Bookuni_Pageuni");
		delete.executeUpdate();
		
		delete = em.createNativeQuery("delete from Bookuni");
		delete.executeUpdate();
		
		delete = em.createNativeQuery("delete from Pageuni");
		delete.executeUpdate();
		
		delete = em.createNativeQuery("delete from Pagebi");
		delete.executeUpdate();
		
		delete = em.createNativeQuery("delete from Bookbi");
		delete.executeUpdate();
	    
	    em.getTransaction().commit();
	}
} 