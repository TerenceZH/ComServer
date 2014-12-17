package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.database.DBService;
import com.database.DBServiceImpl;
import com.exception.AccountException;
import com.model.Account;

public class AccountDao {
	private DBService service;
	
	public AccountDao(){
		try {
			service = new DBServiceImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean isExist(String name)throws AccountException{
		PreparedStatement pstm = null;
		try{
			String sql = "select * from account where name=?";
			Object[]params = new Object[]{name};
			pstm = service.setPrepareStatementParams( sql, params);
			ResultSet rs = pstm.executeQuery();
			return rs.next();
		}catch(Exception e){
			throw new AccountException(this.getClass().getName()+".isExist");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	public ArrayList<Account> getAccount(String name)throws AccountException{
		PreparedStatement pstm = null;
		ArrayList<Account> list = new ArrayList<Account>();
		try{
			String sql = "select * from account where name like ?";
			Object[]params = new Object[]{"%"+name+"%"};
			pstm = service.setPrepareStatementParams( sql, params);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				Account account = new Account(rs.getString(1),rs.getDouble(2));
				list.add(account);
			}
		}catch(Exception e){
			throw new AccountException(this.getClass().getName()+".getAccount");
		}finally{
			service.closeDB(pstm);
		}
		return list;
	}
	
	public boolean modAccount(Account a)throws AccountException{
		PreparedStatement pstm  = null;
		try{
			String sql  = "update account set money=? where name=? ";
			Object[]params = new Object[]{a.getMoney(),a.getName()};
			pstm = service.setPrepareStatementParams( sql, params);
			pstm.executeUpdate();
			return true;
		}catch(Exception e){
			throw new AccountException(this.getClass().getName()+".modAccount");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	public boolean addAccount(Account a)throws AccountException{
		PreparedStatement pstm = null;
		try{
			String sql = "insert into account values(?,?);";
			Object[]params = new Object[]{a.getName(),a.getMoney()};
			pstm = service.setPrepareStatementParams(sql, params);
			pstm.executeUpdate();
			return true;
		}catch(Exception e){
			throw new AccountException(this.getClass().getName()+".addAccount");
		}finally{
			service.closeDB(pstm);
		}
	}

}
