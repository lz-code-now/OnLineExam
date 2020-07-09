package edu.prj.service.impl;

import edu.prj.dao.SubjectDao;
import edu.prj.dao.impl.SubjectDaoImpl;
import edu.prj.entity.Subject;
import edu.prj.service.SubjectService;

import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    SubjectDao subjectDao = new SubjectDaoImpl();
    @Override
    public int insert(Subject subject) {
        return subjectDao.insert(subject);
    }

    @Override
    public int update(Subject subject) {
        return subjectDao.update(subject);
    }

    @Override
    public int delete(Long subjectId) {
        return subjectDao.delete(subjectId);
    }

    @Override
    public Subject queryBySubjectId(Long subjectId) {
        return subjectDao.queryBySubjectId(subjectId);
    }

    @Override
    public List<Subject> queryAll() {
        return subjectDao.queryAll();
    }

    @Override
    public List<Subject> queryBySubjectName(String subjectName) {
        return subjectDao.queryBySubjectName(subjectName);
    }
}
