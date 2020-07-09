package edu.prj.service.impl;

import edu.prj.dao.GradeDao;
import edu.prj.dao.impl.GradeDaoImpl;
import edu.prj.entity.Grade;
import edu.prj.service.GradeService;

import java.util.List;

public class GradeServiceImpl implements GradeService {
    GradeDao gradeDao = new GradeDaoImpl();
    @Override
    public int insert(Grade grade) {
        return gradeDao.insert(grade);
    }

    @Override
    public int update(Grade grade) {
        return gradeDao.update(grade);
    }

    @Override
    public int delete(Long gradeId) {
        return gradeDao.delete(gradeId);
    }

    @Override
    public Grade queryByGradeId(Long gradeId) {
        return gradeDao.queryByGradeId(gradeId);
    }

    @Override
    public List<Grade> queryAll() {
        return gradeDao.queryAll();
    }

    @Override
    public List<Grade> queryByGradeName(String gradeName) {
        return gradeDao.queryByGradeName(gradeName);
    }
}
