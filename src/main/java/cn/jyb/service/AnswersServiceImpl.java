package cn.jyb.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jyb.dao.AnswersMapper;
import cn.jyb.entity.Answers;
import cn.jyb.exception.AnswersException;

@Service("answersService")
public class AnswersServiceImpl implements AnswersService {

	@Resource
	private AnswersMapper answersMapper;
	
	public void saveAnswer(Integer userId, Integer questionId) {
		Answers answers = answersMapper.findByIds(userId, questionId);
		int i;
		if(answers == null){//֮ǰû�г��ֹ��Ĵ���
			answers = new Answers();
			answers.setUserId(userId);
			answers.setQuestionId(questionId);
			//��һ�γ��֣��������Ϊ1
			answers.setWrongTimes(1);
			i = answersMapper.insertSelective(answers);
		}else{//֮ǰ���ֹ��Ĵ���
			int wrongTimes = answers.getWrongTimes();
			//�ڶ��γ��֣��������+1
			wrongTimes++;
			answers.setWrongTimes(wrongTimes);
			i = answersMapper.updateByPrimaryKeySelective(answers);
		}
		if(i != 1){
			throw new AnswersException("�������ʧ��");
		}
	}

	
}
