package edu.prj.service;

import edu.prj.entity.Question;

import java.util.List;

public interface QuestionService {
    //插入方法
    int insert(Question question);
    //更新方法
    int update(Question question);
    //删除方法
    int delete(Long questionId);
    //查询所有数据
    List<Question> queryAll();
    //查询 qType 对应类型的所有题目
    List<Question> queryByqType(Long qType);
    //通过创建教师的名字来查询题目
    List<Question> queryByTag(String tag);
    //查询Tag 不包含该字符的 所有题目
    List<Question> queryByNoTag(String tag);
    //通过SubjectID 查询数据
    List<Question> queryBySubject(Long subjectID);

    List<Question> queryByTypeAndSubject(Long qType, Long subjectID);
    Question queryByQuestionID(Long questionID);

}
