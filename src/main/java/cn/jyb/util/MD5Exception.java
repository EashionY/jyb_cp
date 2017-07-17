package cn.jyb.util;

public class MD5Exception extends RuntimeException{

	private static final long serialVersionUID = 1498925737732496850L;

	public MD5Exception(String msg, Throwable t) {
		super(msg, t);
	}

}
