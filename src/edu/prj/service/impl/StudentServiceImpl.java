package edu.prj.service.impl;

import edu.prj.dao.StudentDao;
import edu.prj.dao.impl.StudentDaoImpl;
import edu.prj.entity.Student;
import edu.prj.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    StudentDao studentDao = new StudentDaoImpl();
    @Override
    public boolean login(Student student) {
        return studentDao.login(student);
    }

    @Override
    public int insert(Student student) {
        return studentDao.insert(student);
    }

    @Override
    public int update(Student student) {
        return studentDao.update(student);
    }

    @Override
    public int delete(Long studentId) {
        return studentDao.delete(studentId);
    }

    @Override
    public Student queryByStudentId(Long studentId) {
        return studentDao.queryByStudentId(studentId);
    }

    @Override
    public Student queryByLoginName(String loginName) {
        return studentDao.queryByLoginName(loginName);
    }

    @Override
    public List<Student> queryAll() {
        return studentDao.queryAll();
    }

    @Override
    public List<Student> queryByStudentName(String studentName) {
        return studentDao.queryByStudentName(studentName);
    }
}
