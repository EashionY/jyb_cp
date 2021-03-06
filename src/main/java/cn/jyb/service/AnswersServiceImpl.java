package cn.jyb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jyb.dao.AnswersMapper;
import cn.jyb.entity.Answers;
import cn.jyb.exception.AnswersException;

@Service("answersService")
public class AnswersServiceImpl implements AnswersService {

	@Resource
	private AnswersMapper answersMapper;
	
	public void saveAnswer(Integer userId, Integer questionId, Integer subject) {
		Answers answers = answersMapper.findByIds(userId, questionId);
		int i;
		if(answers == null){//之前没有出现过的错题
			answers = new Answers();
			answers.setUserId(userId);
			answers.setQuestionId(questionId);
			//第一次出现，错误次数为1
			answers.setWrongTimes(1);
			answers.setSubject(subject);
			i = answersMapper.insertSelective(answers);
		}else{//之前出现过的错题
			int wrongTimes = answers.getWrongTimes();
			//第二次出现，错误次数+1
			wrongTimes++;
			answers.setWrongTimes(wrongTimes);
			i = answersMapper.updateByPrimaryKeySelective(answers);
		}
		if(i != 1){
			throw new AnswersException("保存错题失败");
		}
	}

	public List<Map<String, Object>> viewWrong(Integer userId, Integer subject, String chapter) {
		return answersMapper.viewWrong(userId, subject, chapter);
	}

	public List<Map<String, Object>> getWrongNum(Integer userId, Integer subject) {
		//取得错题总条数
		Integer totalNum = answersMapper.wrongTotalNum(userId, subject);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("totalNum", totalNum);
		//分章节的错题数
		List<Map<String,Object>> result = answersMapper.wrongChapterNum(userId, subject);
		result.add(map);
		return result;
	}

	
}
