package cn.jyb.service;

import java.util.List;
import java.util.Map;

public interface AnswersService {

	/**
	 * 保存用户的错题
	 * @param userId
	 * @param questionId
	 * @param subject
	 */
	public void saveAnswer(Integer userId,Integer questionId,Integer subject);
	
	/**
	 * 查看错题
	 * @param userId
	 * @param subject
	 * @param chapter
	 * @return
	 */
	public List<Map<String,Object>> viewWrong(Integer userId,Integer subject,String chapter);
	
	/**
	 * 取得用户的错题数
	 * @param userId
	 * @param subject
	 * @return
	 */
	public List<Map<String,Object>> getWrongNum(Integer userId,Integer subject);
	
}
