package cn.ikyou.infrastructure.execption;

public class ServiceException extends RuntimeException{

	private static final long serialVersionUID = 7541934781202614233L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

}