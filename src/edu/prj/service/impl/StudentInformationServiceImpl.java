package edu.prj.service.impl;

import edu.prj.dao.StudentInformationDao;
import edu.prj.dao.impl.StudentInformationDaoImpl;
import edu.prj.entity.StudentInformation;
import edu.prj.service.StudentInformationService;

import java.util.List;

public class StudentInformationServiceImpl implements StudentInformationService {
    StudentInformationDao studentInformationDao = new StudentInformationDaoImpl();
    @Override
    public int insert(StudentInformation studentInformation) {
        return studentInformationDao.insert(studentInformation);
    }

    @Override
    public int update(StudentInformation studentInformation) {
        return studentInformationDao.update(studentInformation);
    }

    @Override
    public int delete(Long studentInformationID) {
        return studentInformationDao.delete(studentInformationID);
    }

    @Override
    public List<StudentInformation> queryAll() {
        return studentInformationDao.queryAll();
    }

    @Override
    public List<StudentInformation> queryByStudentName(String studentName) {
        return studentInformationDao.queryByStudentName(studentName);
    }
}
