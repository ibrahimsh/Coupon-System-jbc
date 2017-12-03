package com.sys.exception;

import java.io.Serializable;

/**
 * 
 * @author MatrixComp
 * @version 1.0v
 * @see 
 * <b> Company Exception inherits fromm Exception Class </b>
 *
 */
public class companyException extends Exception  implements Serializable
{

	/**
	 * Serializable - version
	 * message - get  from throw
	 */
	private String message ;
	private static final long serialVersionUID = 1L;
	/**
	 * Constructor  
	 */
	public companyException ()
	{
		super();
	}
	public companyException(String message)
	{
		super(message);
		this.message = message;
	}
	 public companyException(String message, Throwable throwable) {
	        super(message, throwable);
	    }
	public static void CompanyExceptionHandler(Exception e)
	{
		
		String exceptions[] = e.getClass().toString().split( "\\." );
		String exceptionClass = exceptions[exceptions.length - 1];
		ExceptionType exceptionType =ExceptionType.valueOf(exceptionClass);//= ExceptionType.valueOf(exceptionClass);

		switch(exceptionType)
		{
		case ClassNotFoundException :
				System.out.println(e.getCause());
				System.out.println("Company class does not exist");
				break;
		case SQLException :
				System.out.println(e.getCause());
				System.out.println("cannot connect to mysql Company table");   
				break;
		case InterruptedException :
				System.out.println(e.getCause());
				System.out.println("the thread has been interrupted - the system might be shutting down");
				break;
		case DuplicateEntryException :
				System.out.println(e.getMessage());
				System.out.println("can't create Company, databease already contains this Company");
				break;
		case WrongDataInputException :
				System.out.println(e.getMessage());
				System.out.println("either the Company name or the password is wrong - can't login!");
				break;
		case DuplicateCouponTypeException :
				System.out.println(e.getMessage());
				System.out.println("can't purchase coupon - same coupon type already exist!");
				break;
		case UnAvailableCouponException :
				System.out.println(e.getMessage());
				System.out.println("can't purchase coupon - no more available coupons or coupon is expired");
				break;
		case NullConnectionException :
				System.out.println(e.getMessage());
				System.out.println("your connection is null - the system might be shutting down!");
		case IllegalArgumentException:
			System.out.println(e.getMessage());
			System.out.println("error in parameter or argument that you try to add");
			break;
		default:
				e.printStackTrace();;
		}
	}
	

}
