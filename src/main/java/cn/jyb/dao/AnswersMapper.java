package cn.jyb.dao;

import cn.jyb.entity.Answers;

public interface AnswersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Answers record);

    int insertSelective(Answers record);

    Answers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Answers record);

    int updateByPrimaryKey(Answers record);
    
    Answers findByIds(Integer userId,Integer questionId);
}