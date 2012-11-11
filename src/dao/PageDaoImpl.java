package dao;

import entity.PageUni;

public class PageDaoImpl extends GenericDaoImpl<PageUni> implements PageDao {

	@Override
	protected Class<PageUni> getEntityClass() {
		return PageUni.class;
	}

}
