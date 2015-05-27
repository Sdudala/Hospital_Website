package com.neu.healthcare.dao;

import java.util.ArrayList;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.neu.healthcare.model.UserAccount;
import com.neu.healthcare.model.UserAccount.RoleType;


public class UserDao extends DAO{
	
	/*
	    * Since its a query, it is not necessary to wrap code
	    * inside the begin transaction and commit part, unlike
	    * update, create, and delete. Since there is no object
	    * in the session yet, nothing will be committed.  After
	    * Adding these, "transaction is not successfully started'
	    * Exception will be thrown.
	    */
		public UserAccount queryUserByNameAndPassword(String name, String password) throws Exception {
			try {
				Query q = getSession().createQuery("from UserAccount where userName = :username and password = :password and enabled = 1");
	            q.setString("username", name);
	            q.setString("password", password);
	            UserAccount ua = (UserAccount) q.uniqueResult();
	            close();
	            return ua;
			} catch (HibernateException e) {
				throw new Exception("Could not get user "+name+" due to "+e);
			}
	    }
		
		public UserAccount queryUserByName(String userName) throws Exception {
			try {
				Query q = getSession().createQuery("from UserAccount where userName = :username");
				q.setString("username", userName);
				UserAccount ua = (UserAccount) q.uniqueResult();
				close();
				return ua;
			} catch (HibernateException e) {
				throw new Exception("Could not get user "+userName+" due to "+e);
			}
		}
		
		public UserAccount queryUserById(int userId) throws Exception {
			try {
				Query q = getSession().createQuery("from UserAccount where userId = :userId");
				q.setInteger("userId", userId);
				UserAccount ua = (UserAccount) q.uniqueResult();
				close();
				return ua;
			} catch (HibernateException e) {
				throw new Exception("Could not get user due to "+e);
			}
		}
		
		public ArrayList<UserAccount> queryNewUserRequests() throws Exception{
			try {
				ArrayList<UserAccount> uaList = new ArrayList<UserAccount>();
				Query q = getSession().createQuery("from UserAccount where enabled = 0");
				uaList = (ArrayList<UserAccount>)q.list();
				close();
				return uaList;
			} catch (HibernateException e) {
				throw new Exception("Could not get new user requests due to "+e);
			}
		}
		
		public void addUserAccountDB(UserAccount ua) throws Exception{
			try {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				//Transaction transaction = getSession().beginTransaction();
				session.save(ua);
				tx.commit();
				close();
			} catch (HibernateException e) {
				throw new Exception("Could not add User due to "+e);
			}	
			
		}
		
		public void activateUserAccountQuery(String[] userId) throws Exception {
			try {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				for(String uid : userId) {
					Transaction tx = session.beginTransaction();
					String hql = "update UserAccount SET enabled = 1  where userId = :uid";
					Query query = session.createQuery(hql);
					query.setString("uid",uid);
					int rowCount = query.executeUpdate();
					System.out.println("Rows affected: " + rowCount);
				    tx.commit();
				}
				close();
				
			} catch (HibernateException e) {
				throw new Exception("Could not activate User due to "+e);
			}	
			
		}
		
		public void deleteUserAccountQuery(String[] userId) throws Exception {
			try {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				for(String uid : userId) {
					Transaction tx = session.beginTransaction();
									
					String hql = "delete from  UserAccount where userId = :uid";
					Query query = session.createQuery(hql);
					query.setString("uid",uid);
					int rowCount = query.executeUpdate();
					tx.commit();
				}
				close();
			} catch (HibernateException e) {
				throw new Exception("Could not delete User due to "+e);
			}	
		}
		
		public void updateUserAccount(UserAccount ua) throws Exception{
			try {
				SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
				Session session = sessionFactory.openSession();
				Transaction tx = session.beginTransaction();
				System.out.println("Updating useraccount");
				UserAccount merged = (UserAccount)session.merge(ua);
				session.saveOrUpdate(merged);
				tx.commit();
				close();
				
			} catch (HibernateException e) {
				throw new Exception("Could not update userAccount due to "+e);
			}	
		}
		
		public ArrayList<UserAccount> getAllDoctors() throws Exception{
			try {
				ArrayList<UserAccount> doctorList = new ArrayList<UserAccount>();
				Query q = getSession().createQuery("from UserAccount where enabled = 1 and role = :role");
				q.setString("role", RoleType.ROLE_DOCTOR.getValue());
				doctorList = (ArrayList<UserAccount>)q.list();
				System.out.println("Number of messages is "+doctorList.size());
				close();
				return doctorList;
			} catch (HibernateException e) {
				throw new Exception("Could not get all doctors due "+e);
			}
		}
				
}
