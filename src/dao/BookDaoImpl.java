package dao;

import entity.Book;

public class BookDaoImpl extends GenericDaoImpl<Book> implements BookDao {

	@Override
	protected Class<Book> getEntityClass() {
		return Book.class;
	}

}
