package com.coupon.system.entity.dao.implementation;
/*
 * ibrahim shweiki
 */
import java.sql.Date;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.coupon.system.entities.Company;
import com.coupon.system.entities.Coupon;
import com.coupon.system.entities.CouponType;
import com.coupon.system.entities.daoInt.CouponDAOIn;
@Transactional
@Repository
public class CouponDAOImp implements CouponDAOIn{
	
	@PersistenceContext
	private EntityManager entityMngr;//manager entity jpa to create,delete,update,add,get data from sql table  some times using sql query
	
	@Override
	public void createCoupon(Coupon coup) {
		Company comp = new  Company();
		comp =entityMngr.find(Company.class, 1);
		coup.setCompany(comp);
		entityMngr.persist(coup);
		
	}

	@Override
	public void removeCoupon(Coupon coup) {
		Query query = entityMngr.createNativeQuery("DELETE FROM All_coupons where title=?").setParameter(1, coup.getTitle().trim());
		query.executeUpdate();
		
		
		//entityMngr.remove(coup);
		
	}

	@Override
	public void updatecoupon(Coupon coup) {
		
		Query q = entityMngr.createNativeQuery("UPDATE all_coupons SET title=?,startDate=?,endDate=?,"
				+ "ctype=?,price=?,amount=?,message=?,image=? WHERE ID=?")
				.setParameter(1, coup.getTitle())
				.setParameter(2,(Date)coup.getStartDate()).setParameter(3,(Date)coup.getEndDate())
				.setParameter(4,coup.getCtyp()).setParameter(5,coup.getPrice()).setParameter(6,coup.getAmount())
				.setParameter(7, coup.getMessage()).setParameter(8, coup.getImage()).setParameter(9, coup.getId());
		//emngr.flush();
		//emngr.merge(com);
		int updating = q.executeUpdate();
		if(updating ==1)
		{
			System.out.println("the company updated");
		}
		entityMngr.flush();
	}

	@Override
	public Coupon getCoupon(int l) {
		return entityMngr.find(Coupon.class,l);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Coupon> getAllCoupon() {
		String query = "from Coupons as coup order by coup.id";
		return (Collection<Coupon>) entityMngr.createQuery(query).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Coupon> getCouponByType(CouponType coupt) {
		String q = "from Coupons as coup order by coup.CTYPE =?" ;
		
		return (Collection<Coupon>) entityMngr.createQuery(q).setParameter(1, coupt).getResultList();
	}

	@Override
	public long CouponId(Coupon coup) {
		// TODO Auto-generated method stub
		return 0;
	}

}
