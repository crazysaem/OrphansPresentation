package dao;

import entity.Lecture;

public class LectureDaoImpl extends GenericDaoImpl<Lecture> implements LectureDao {

	@Override
	protected Class<Lecture> getEntityClass() {
		return Lecture.class;
	}

}
