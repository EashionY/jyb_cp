package cn.jyb.exception;

public class TeachRecordException extends RuntimeException {

	private static final long serialVersionUID = -333942472097293898L;

	public TeachRecordException() {
		super();
	}

	public TeachRecordException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public TeachRecordException(String message, Throwable cause) {
		super(message, cause);
	}

	public TeachRecordException(String message) {
		super(message);
	}

	public TeachRecordException(Throwable cause) {
		super(cause);
	}

	
}
