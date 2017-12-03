package com.sys.exception;

import java.io.Serializable;

/**
 * 
 * @author MatrixComp
 * @version 1.0v
 * <br>
 * this Class include Customer Exceptions
 *
 */
public class CustomerException extends Exception implements Serializable
{

	private static final long serialVersionUID = 1L;
	//String exceptions[] = e.getClass().toString().split( "\\." );
	/**
	 * @message - the message that send to user where generated with throw 
	 */
	public String message;
	/**
	 * <p>Constructor </b>
	 * <br> 
	 * - empty Constructor because   the  serialization
	 */
	public CustomerException ()
	{
		
	}
	/**
	 * Constructor
	 * @param message
	 * the  message  that  generated  with the  throw Exception
	 * @param throwable
	 */
	public CustomerException(String message)
	{
		super(message);
		this.message = message;
	}
	public CustomerException(String message, Throwable throwable) {
	        super(message, throwable);
	    }
	/**
	 * this  function find  the exception type and pass it  as parameter then with respect  
	 * the exception type  will send the  The appropriate message to a client 
	 * @param e - Exception type  
	 */
	public static void CustomerExceptionHandler(Exception e)
	{
		
		String exceptions[] = e.getClass().toString().split( "\\." );
		String exceptionClass = exceptions[exceptions.length - 1];
		ExceptionType exceptionType = ExceptionType.valueOf(exceptionClass);
		System.out.println("the error"+exceptionType);
		switch(exceptionType)
		{
			case ClassNotFoundException :
					System.out.println(e.getCause());
					System.out.println("Customer class does not exist");
					break;
			case SQLException :
					System.out.println(e.getCause());
					System.out.println("cannot connect to mysql for  Customer table");   
					break;
			case InterruptedException :
					System.out.println(e.getCause());
					System.out.println("the thread has been interrupted - the system might be shutting down");
					break;
			case DuplicateEntryException :
					System.out.println(e.getMessage());
					System.out.println("can't create customer, databease already contains this customer");
					break;
			case WrongDataInputException :
					System.out.println(e.getMessage());
					System.out.println("either the customer name or the password is wrong - can't login!");
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
			//default:
					//break;
		default:
			e.getMessage();
			break;
		}
	}

}
