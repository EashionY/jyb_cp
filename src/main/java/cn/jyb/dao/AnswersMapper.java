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
    /**
     * �鿴����
     * @param userId
     * @param subject
     * @param chapter
     * @return
     */
    List<Map<String,Object>> viewWrong(@Param("userId")Integer userId,@Param("subject")Integer subject,@Param("chapter")String chapter);
    /**
     * ��������
     * @param userId
     * @param subject
     * @return
     */
    int wrongTotalNum(@Param("userId")Integer userId,@Param("subject")Integer subject);
    /**
     * ���½���ʾ������
     * @param userId
     * @param subject
     * @return
     */
    List<Map<String,Object>> wrongChapterNum(@Param("userId")Integer userId,@Param("subject")Integer subject);
}