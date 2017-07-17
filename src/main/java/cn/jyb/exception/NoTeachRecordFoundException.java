package cn.jyb.exception;

public class NoTeachRecordFoundException extends RuntimeException {

	private static final long serialVersionUID = 5207653171340754841L;

	public NoTeachRecordFoundException() {
		super();
	}

	public NoTeachRecordFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public NoTeachRecordFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public NoTeachRecordFoundException(String arg0) {
		super(arg0);
	}

	public NoTeachRecordFoundException(Throwable arg0) {
		super(arg0);
	}

	
}
