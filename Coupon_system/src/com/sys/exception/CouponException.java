package com.sys.exception;

/**
 * 
 * @author MatrixComp
 * @version 1.0v
 * <br>
 * this Class include Coupon Exceptions
 *
 */
public class CouponException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @message - the message that send to user where generated with throw 
	 */
	private String message;
	/**
	 * <p>Constructor </b>
	 * <br> 
	 * - empty Constructor because   the  serialization and  super() main class inherits Exception
	 */
	public CouponException ()
	{
		super();
	}
	/**
	 * Constructor
	 * @param message
	 * the  message  that  generated  with the  throw Exception
	 */
	public CouponException(String message)
	{
		super(message);
		this.message = message ;
	}
	/**
	 * this  function find  the exception type and pass it  as parameter then with respect  
	 * the exception type  will send the  The appropriate message to a client 
	 * @param e - Exception type 
	 * @param throwable
	 * @param - message 
	 */
	  public CouponException(String message, Throwable throwable) {
	        super(message, throwable);
	    }
	public static void CouponExceptionHandler(Exception e)
	{
		
		String exceptions[] = e.getClass().toString().split( "\\." );
		String exceptionClass = exceptions[exceptions.length - 1];
		ExceptionType exceptionType = ExceptionType.valueOf(exceptionClass);

		switch(exceptionType)
		{
		case ClassNotFoundException :
				System.out.println(e.getCause());
				System.out.println("Coupon class does not exist");
				break;
		case SQLException :
				System.out.println(e.getCause());
				System.out.println("cannot connect to mysql");   
				break;
		case InterruptedException :
				System.out.println(e.getCause());
				System.out.println("the thread has been interrupted - the system might be shutting down");
				break;
		case DuplicateEntryException :
				System.out.println(e.getMessage());
				System.out.println("can't create Coupon, databease already contains this Coupon");
				break;
		case WrongDataInputException :
				System.out.println(e.getMessage());
				System.out.println("Coupon information is wrong try again");
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
				e.getMessage();
				break;
		}
	}

}
