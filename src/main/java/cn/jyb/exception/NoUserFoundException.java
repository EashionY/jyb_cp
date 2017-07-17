package cn.jyb.exception;

public class NoUserFoundException extends RuntimeException {

	private static final long serialVersionUID = -585791529432406703L;

	public NoUserFoundException() {
		super();
	}

	public NoUserFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoUserFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoUserFoundException(String message) {
		super(message);
	}

	public NoUserFoundException(Throwable cause) {
		super(cause);
	}
	
}
