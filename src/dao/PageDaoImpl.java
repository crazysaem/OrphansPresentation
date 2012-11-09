package dao;

import entity.Page;

public class PageDaoImpl extends GenericDaoImpl<Page> implements PageDao {

	@Override
	protected Class<Page> getEntityClass() {
		return Page.class;
	}

}
