package com.sys.exception;
/**
 * this  class contain all Exception in general 
 * @author MatrixComp -ibrahim shweiki
 * @version 1.0v
 * 
 *
 */
public class CouponSystemException extends Exception {

	/**
	 * general system exceptions 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	/**
	 * <p>Constructor </b>
	 * <br> 
	 * - empty Constructor because   the  serialization and  super() main class inherits Exception
	 */
	public CouponSystemException ()
	{
		super();
	}
	/**
	 * Constructor
	 * @param message
	 * the  message  that  generated  with the  throw Exception
	 */
	public CouponSystemException(String message)
	{
		super(message);
		this.message = message ;
	}
	/**
	 * this  function find  the exception type and pass it  as parameter then with respect  
	 * the exception type  will send the  The appropriate message to a client 
	 * @param e - Exception type  
	 */
	public static void CouponExceptionHandler(Exception e)
	{
		
		String exceptions[] = e.getClass().toString().split( "\\." );
		String exceptionClass = exceptions[exceptions.length - 1];
		ExceptionType exceptionType = ExceptionType.valueOf(exceptionClass);

		switch(exceptionType)
		{
		case ClassNotFoundException :
				System.out.println(e.getCause());
				System.out.println("Coupon ,Company,Customer class does not exist");
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
				System.out.println("can't create Coupon or customer or company, already exist in database");
				break;
		case WrongDataInputException :
				System.out.println(e.getMessage());
				System.out.println("Coupon information is wrong try again");
				break;
		case companyException : 
				System.out.println(e.getMessage());
				System.out.println("problem with company database");
				break;
		case CouponException :
			System.out.println(e.getMessage());
			System.out.println("problem with coupon database");
			break;
		case CustomerException :
			System.out.println(e.getMessage());
			System.out.println("problem with Customer database");
		case NullConnectionException :
				System.out.println(e.getMessage());
				System.out.println("your connection is null - the system might be shutting down!");
		case IllegalArgumentException:
			System.out.println(e.getMessage());
			System.out.println("error in parameter or argument that you try to add");
			break;
		case CouponSystemException :
			System.out.println(e.getMessage());
			System.out.println("Problem with DailyCoupon  remover thread");
		default:
				e.getMessage();
				break;
		}

	}
}
