package cn.jyb.service;


public interface AnswersService {

	/**
	 * 保存用户的错题
	 * @param userId
	 * @param questionId
	 */
	public void saveAnswer(Integer userId,Integer questionId);
	
}
