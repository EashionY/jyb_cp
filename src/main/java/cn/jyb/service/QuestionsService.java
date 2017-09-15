package cn.jyb.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.jyb.entity.Questions;

@Service("questionsService")
public interface QuestionsService {
	
	/**
	 * 保存考题
	 * @param pagenum
	 */
	public void addQuestsion(Integer pagenum);
	
	/**
	 * 获取考题
	 * @param subject
	 * @param page
	 * @param pageSize
	 * @param sort
	 * @return
	 */
	public List<Questions> getQuestions(Integer subject,Integer page,Integer pageSize,String sort);
	
}
