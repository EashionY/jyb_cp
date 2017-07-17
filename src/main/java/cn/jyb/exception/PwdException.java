package cn.jyb.exception;

public class PwdException extends RuntimeException {

	private static final long serialVersionUID = 884667775388313010L;

	public PwdException() {
		super();
	}

	public PwdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PwdException(String message, Throwable cause) {
		super(message, cause);
	}

	public PwdException(String message) {
		super(message);
	}

	public PwdException(Throwable cause) {
		super(cause);
	}

	
}
