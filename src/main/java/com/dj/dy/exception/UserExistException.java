package com.dj.dy.exception;

public class UserExistException extends Exception
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -60268077671551396L;

	public UserExistException(String errorMsg)
    {
        super(errorMsg);
    }
}
