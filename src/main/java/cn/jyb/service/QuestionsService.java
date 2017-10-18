package cn.jyb.service;

import java.util.List;
import java.util.Map;

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
	
	/**
	 * 获取对应科目考试的章节考题数量
	 * @param subject
	 * @return
	 */
	public List<Map<String,Object>> getChapter(Integer subject);
	
	/**
	 * 获取对应章节的考题
	 * @param chapter
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Questions> getQuestionsByChapter(String chapter,Integer page,Integer pageSize);
	
}
