package cn.jyb.service;

import java.util.List;
import java.util.Map;

public interface AnswersService {

	/**
	 * �����û��Ĵ���
	 * @param userId
	 * @param questionId
	 * @param subject
	 */
	public void saveAnswer(Integer userId,Integer questionId,Integer subject);
	
	/**
	 * �鿴����
	 * @param userId
	 * @param subject
	 * @return
	 */
	public List<Map<String,Object>> viewWrong(Integer userId,Integer subject);
	
}
