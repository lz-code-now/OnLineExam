package edu.prj.service.impl;

import edu.prj.dao.TeacherInformationDao;
import edu.prj.dao.impl.TeacherInformationDaoImpl;
import edu.prj.entity.TeacherInformation;
import edu.prj.service.TeacherInformationService;

import java.util.List;

public class TeacherInformationServiceImpl implements TeacherInformationService {
    TeacherInformationDao informationDao = new TeacherInformationDaoImpl();
    @Override
    public int insert(TeacherInformation teacherInformation) {
        return informationDao.insert(teacherInformation);
    }

    @Override
    public int update(TeacherInformation teacherInformation) {
        return informationDao.update(teacherInformation);
    }

    @Override
    public int delete(Long teacherInformationId) {
        return informationDao.delete(teacherInformationId);
    }

    @Override
    public List<TeacherInformation> queryAll() {
        return informationDao.queryAll();
    }

    @Override
    public List<TeacherInformation> queryByTeacherName(String teacherName) {
        return informationDao.queryByTeacherName(teacherName);
    }
}
