package cn.ikyou.infrastructure.execption;

public class ServiceCheckException extends RuntimeException{

	private static final long serialVersionUID = -8230857598243800400L;

	public ServiceCheckException() {
		
	}
	
	public ServiceCheckException(String msg) {
		super(msg);
	}
	
}