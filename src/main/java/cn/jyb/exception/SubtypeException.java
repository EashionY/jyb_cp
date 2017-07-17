package cn.jyb.exception;

public class SubtypeException extends RuntimeException {

	private static final long serialVersionUID = 2433538877206791801L;

	public SubtypeException() {
		super();
	}

	public SubtypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SubtypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public SubtypeException(String message) {
		super(message);
	}

	public SubtypeException(Throwable cause) {
		super(cause);
	}

	
}
