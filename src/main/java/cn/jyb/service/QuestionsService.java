package cn.jyb.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import cn.jyb.entity.Questions;

@Service("questionsService")
public interface QuestionsService {
	
	/**
	 * ���濼��
	 * @param pagenum
	 */
	public void addQuestsion(Integer pagenum);
	
	/**
	 * ��ȡ����
	 * @param subject
	 * @param page
	 * @param pageSize
	 * @param sort
	 * @return
	 */
	public List<Questions> getQuestions(Integer subject,Integer page,Integer pageSize,String sort);
	
	/**
	 * ��ȡ��Ӧ��Ŀ���Ե��½ڿ�������
	 * @param subject
	 * @return
	 */
	public List<Map<String,Object>> getChapter(Integer subject);
	
	/**
	 * ��ȡ��Ӧ�½ڵĿ���
	 * @param chapter
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Questions> getQuestionsByChapter(String chapter,Integer page,Integer pageSize);
	
}
