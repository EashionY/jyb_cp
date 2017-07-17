package cn.jyb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 朋友圈说说的评论
 * @author Eashion
 *
 */
public class Comments implements Serializable{

	private static final long serialVersionUID = -8090579177979698681L;

	private int id;
	private int talk_id;
	private int user_id;
	//评论
	private String comment;
	//0未点赞，1已点赞
	private String likes;
	//回复id
	private int reply;
	//创建时间
	private Timestamp creatime;
	
	public Comments() {	}

	public Comments(int talk_id, int user_id, String comment, String likes, int reply) {
		this.talk_id = talk_id;
		this.user_id = user_id;
		this.comment = comment;
		this.likes = likes;
		this.reply = reply;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTalk_id() {
		return talk_id;
	}

	public void setTalk_id(int talk_id) {
		this.talk_id = talk_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getLikes() {
		return likes;
	}

	public void setLikes(String likes) {
		this.likes = likes;
	}

	public int getReply() {
		return reply;
	}

	public void setReply(int reply) {
		this.reply = reply;
	}

	
	public Timestamp getCreatime() {
		return creatime;
	}
	
	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}
	
	@Override
	public String toString() {
		return "Comments [id=" + id + ", talk_id=" + talk_id + ", user_id=" + user_id + ", comment=" + comment
				+ ", likes=" + likes + ", reply=" + reply + ", creatime=" + creatime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comments other = (Comments) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
