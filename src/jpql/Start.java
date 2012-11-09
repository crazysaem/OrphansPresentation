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
	    
	    createBook();
	    //deleteBook(1450);
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