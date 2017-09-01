package test;

import org.junit.Before;
import org.junit.Test;

import cn.jyb.service.QuestionsService;

public class TestQuestionsService extends TestBase {
	
	private QuestionsService questionsService;
	
	@Before
	public void init(){
		questionsService = super.getContext().getBean("questionsService", QuestionsService.class);
	}
	
	@Test
	public void add(){
		for(Integer pagenum=113;pagenum<114;pagenum++){
			questionsService.addQuestsion(pagenum);
		}
		System.out.println("´¢´æÍê±Ï");
	}
}
