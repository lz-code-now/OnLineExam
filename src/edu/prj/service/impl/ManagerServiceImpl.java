package edu.prj.service.impl;

import edu.prj.dao.ManagerDao;
import edu.prj.dao.impl.ManagerDaoImpl;
import edu.prj.entity.Manager;
import edu.prj.service.ManagerService;

import java.util.List;

public class ManagerServiceImpl implements ManagerService {
    ManagerDao managerDao = new ManagerDaoImpl();
    @Override
    public boolean login(Manager manager) {
        return managerDao.login(manager);
    }

    @Override
    public int insert(Manager manager) {
        return managerDao.insert(manager);
    }

    @Override
    public int update(Manager manager) {
        return managerDao.update(manager);
    }

    @Override
    public int delete(Long managerId) {
        return managerDao.delete(managerId);
    }

    @Override
    public Manager queryByManagerId(Long managerId) {
        return managerDao.queryByManagerId(managerId);
    }

    @Override
    public List<Manager> queryAll() {
        return managerDao.queryAll();
    }

    @Override
    public Manager queryByManagerName(String managerName) {
        return managerDao.queryByManagerName(managerName);
    }
}
