package cn.jyb.service;


public interface AnswersService {

	/**
	 * �����û��Ĵ���
	 * @param userId
	 * @param questionId
	 */
	public void saveAnswer(Integer userId,Integer questionId);
	
}
