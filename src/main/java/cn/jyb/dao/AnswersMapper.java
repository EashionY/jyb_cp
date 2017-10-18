package cn.jyb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.Answers;

public interface AnswersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Answers record);

    int insertSelective(Answers record);

    Answers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Answers record);

    int updateByPrimaryKey(Answers record);
    
    Answers findByIds(@Param("userId")Integer userId,@Param("questionId")Integer questionId);
    
    List<Map<String,Object>> viewWrong(@Param("userId")Integer userId,@Param("subject")Integer subject);
}