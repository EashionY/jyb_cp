package cn.jyb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.Questions;

public interface QuestionsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Questions record);

    int insertSelective(Questions record);

    Questions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Questions record);

    int updateByPrimaryKey(Questions record);
    
    int saveQuestion(Questions record);
    
    /**
     * 顺序获取考题
     * @param subject
     * @param offset
     * @param pageSize
     * @return
     */
    List<Questions> getNormalQuestions(@Param("subject")Integer subject,@Param("offset")Integer offset,
    		@Param("pageSize")Integer pageSize);

    /**
     * 随机获取考题
     * @param subject
     * @param pageSize
     * @return
     */
	List<Questions> getRandQuestions(@Param("subject")Integer subject,@Param("pageSize")Integer pageSize);
    
	/**
	 * 获取科目4考试时的多选题
	 * @param pageSize 
	 * @return
	 */
    List<Questions> getMultSelection(@Param("pageSize")Integer pageSize);
}