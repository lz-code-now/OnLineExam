package edu.prj.service;

import edu.prj.entity.Subject;

import java.util.List;

public interface SubjectService {
    //插入方法
    int insert(Subject subject);
    //更新方法
    int update(Subject subject);
    //删除方法
    int delete(Long subjectId);
    //查询 ID = subjectId 的所有数据
    Subject queryBySubjectId(Long subjectId);
    //查询所有数据
    List<Subject> queryAll();
    //查询 name 包含 subjectName 的所有数据
    List<Subject> queryBySubjectName(String subjectName);
}
