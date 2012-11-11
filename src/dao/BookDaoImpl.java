package dao;

import entity.BookUni;

public class BookDaoImpl extends GenericDaoImpl<BookUni> implements BookDao {

	@Override
	protected Class<BookUni> getEntityClass() {
		return BookUni.class;
	}

}
