package cn.jyb.exception;

public class NoAppointmentException extends RuntimeException {

	private static final long serialVersionUID = -1518005235099738788L;

	public NoAppointmentException() {
		super();
	}

	public NoAppointmentException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NoAppointmentException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoAppointmentException(String message) {
		super(message);
	}

	public NoAppointmentException(Throwable cause) {
		super(cause);
	}

	
}
