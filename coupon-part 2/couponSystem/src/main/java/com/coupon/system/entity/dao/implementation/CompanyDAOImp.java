package com.coupon.system.entity.dao.implementation;
/*
 * ibrahim shweiki
 */
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import com.coupon.system.entities.Company;
import com.coupon.system.entities.Coupon;
import com.coupon.system.entities.CouponType;
import com.coupon.system.entities.daoInt.CompanyDAOIn;
import com.coupon.system.queries.companyQuerys;
@Repository
@Transactional
public class CompanyDAOImp implements CompanyDAOIn {

	private static final Logger log = LoggerFactory.getLogger(CompanyDAOImp.class);
	@PersistenceContext
	EntityManager emngr;//Manger entity to create ,remove ,add,update table in mysql some times using sql query
	@Override
	public void createCompany(Company comp) {
		Company c1 = new  Company();
		c1.setCompName(comp.getCompName());
		c1.setEmail(comp.getEmail());
		c1.setPassword(comp.getPassword());
		emngr.persist(c1);
		log.info("the company created and  added succeffuly, company is :"+comp);
		
		
	}

	@Override
	public void removeCompany(Company comp) {
		Query query = emngr.createNativeQuery("DELETE FROM companies where name=?").setParameter(1, comp.getCompName().trim());
		query.executeUpdate();
		//emngr.remove(comp);
		log.info("the company ,"+comp +" "+"removed succeffuly");
		
	}

	@Override
	public void updateCompany(Company comp) {
		
		//Company com = getCompany(comp.getId());
		//com.setCompName(comp.getCompName());
		//com.setPassword(comp.getPassword());
		//com.setEmail(comp.getEmail());
		Query q = emngr.createNativeQuery("UPDATE Companies SET name=?,password=?,email=? WHERE ID=?").setParameter(1, comp.getCompName())
				.setParameter(2,comp.getCompName()).setParameter(3,comp.getEmail()).setParameter(4,comp.getId());
		//emngr.flush();
		//emngr.merge(com);
		int updating = q.executeUpdate();
		if(updating ==1)
		{
			log.info("the company updated");
		}
	}

	@Override
	public Company getCompany(int l) {
		
		return emngr.find(Company.class,l);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Company> getAllCompanies() {
		String qu = "FROM Company as comp";
		return (Collection<Company>) emngr.createQuery(qu).getResultList();
	}

	@Override
	public boolean login(String compName, String password) {
		boolean exist =true;
		String q = "select from companies as comp Where comp.COMP_NAME = "+compName+"AND"+"comp.PASSWORD="+password ;
		List<?> list = emngr.createQuery("SELECT u FROM Company u WHERE COMP_NAME=? and PASSWORD=?")
				.setParameter(1, compName).setParameter(2, password).getResultList();
		if(list.isEmpty()) {
			exist = false;
		}
		return exist;
	}

	@Override
	public long getID(String company) {
		//Query q = emngr.createQuery("select compName")
		return 0;
	}

	@Override
	public void addCouponToCompany(Coupon c, Company com) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Collection<Coupon> getCoupons(Company comp) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Coupon> getCouponsByType(CouponType typ, Company comp) {
		return (Collection<Coupon>) emngr.createNativeQuery("select from all_coupons where ctyp=?").setParameter(1, typ).getResultList();
		
	}

}
