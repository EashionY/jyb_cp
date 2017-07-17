package cn.jyb.exception;

public class DataBaseException extends RuntimeException {

	private static final long serialVersionUID = -7608962593577740374L;

	public DataBaseException() {
		super();
	}

	public DataBaseException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public DataBaseException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public DataBaseException(String arg0) {
		super(arg0);
	}

	public DataBaseException(Throwable arg0) {
		super(arg0);
	}

	
}
