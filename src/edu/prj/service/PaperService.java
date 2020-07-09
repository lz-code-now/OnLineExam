package edu.prj.service;

import edu.prj.entity.Paper;

import java.util.List;

public interface PaperService {
    //插入方法
    int insert(Paper paper);
    //更新方法
    int update(Paper paper);
    //删除方法
    int delete(Long paperId);
    //查询 ID = paperId 的所有数据
    Paper queryByPaperId(Long paperId);
    //查询所有数据
    List<Paper> queryAll();
    //查询 name 包含 paperName 的所有数据
    List<Paper> queryByPaperName(String paperName);
    //通过创建教师的姓名查数据
    List<Paper> queryByCreateTeacher(String createTeacher);
    //通过创建教师 和 是否已生成 查数据
    List<Paper> queryByCreateTeacherAndHasGenerate(String createTeacher, Long hasGenerate);
}
