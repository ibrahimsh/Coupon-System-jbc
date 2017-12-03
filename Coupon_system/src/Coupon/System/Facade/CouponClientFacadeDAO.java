package Coupon.System.Facade;

import java.sql.SQLException;

import com.sys.exception.CouponSystemException;

/**
 * 
 * @author MatrixComp
 * main interface contain login method  and adminFacade ,Customer Facade, CompanyFacade implement form this  interface  to  use login method 
 * as agate to access the system 
 *
 */
public interface CouponClientFacadeDAO {
	/**
	 * Main login interface for All Client types Administrator ,Customer , or  Company
	 * @param UserName
	 * @param password
	 * @param cliType
	 * @return true ,or  false 
	 * @throws CouponSystemException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public boolean login(String UserName,String password ,clientType cliType) throws CouponSystemException, ClassNotFoundException, SQLException;

}
