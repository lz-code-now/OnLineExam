package edu.prj.service.impl;

import edu.prj.dao.TeacherDao;
import edu.prj.dao.impl.TeacherDaoImpl;
import edu.prj.entity.Teacher;
import edu.prj.service.TeacherService;

import java.util.List;

public class TeacherServiceImpl implements TeacherService {
    TeacherDao teacherDao = new TeacherDaoImpl();
    @Override
    public boolean login(Teacher teacher) {
        return teacherDao.login(teacher);
    }

    @Override
    public int insert(Teacher teacher) {
        return teacherDao.insert(teacher);
    }

    @Override
    public int update(Teacher teacher) {
        return teacherDao.update(teacher);
    }

    @Override
    public int delete(Long teacherId) {
        return teacherDao.delete(teacherId);
    }

    @Override
    public Teacher queryByTeacherId(Long teacherId) {
        return teacherDao.queryByTeacherId(teacherId);
    }

    @Override
    public Teacher queryByLoginName(String loginName) {
        return teacherDao.queryByLoginName(loginName);
    }

    @Override
    public List<Teacher> queryAll() {
        return teacherDao.queryAll();
    }

    @Override
    public List<Teacher> queryByTeacherName(String teacherName) {
        return teacherDao.queryByTeacherName(teacherName);
    }
}
