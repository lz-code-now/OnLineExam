package edu.prj.dao;

import edu.prj.entity.Question;

import java.util.List;

public interface QuestionDao {
    //插入方法
    int insert(Question question);
    //更新方法
    int update(Question question);
    //删除方法
    int delete(Long questionId);
    //查询 ID = questionId 的所有数据
    Question queryByQuestionId(Long questionId);
    //查询所有数据
    List<Question> queryAll();
    //查询 qType 对应类型的所有题目
    List<Question> queryByqType(Long qType);
    //通过创建教师的名字来查询题目
    List<Question> queryByTag(String tag);
}
