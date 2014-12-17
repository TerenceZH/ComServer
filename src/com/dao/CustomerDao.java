package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.database.DBService;
import com.database.DBServiceImpl;
import com.exception.CustomerException;
import com.model.Customer;

public class CustomerDao {
	private DBService service;
	
	public CustomerDao()throws Exception{
		try{
			service = new DBServiceImpl();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 是否存在
	 * @param id
	 * @return
	 * @throws CustomerException
	 */
	public boolean isExist(String id)throws CustomerException{
		PreparedStatement pstm = null;
		try{
			String sql = "select * from customer where id=?";
			Object[]params = new Object[]{id};
			pstm = service.setPrepareStatementParams( sql, params);
			ResultSet rs = pstm.executeQuery();
			return rs.next();
		}catch(Exception e){
			throw new CustomerException(this.getClass().getName()+".isExist");
		}finally{
			service.closeDB(pstm);
		}
	}

	/**
	 * 添加客户
	 * @param c
	 * @return
	 * @throws CustomerException
	 */
	public boolean addCustomer(Customer c)throws CustomerException{
		PreparedStatement pstm = null;
		try{
			String sql = "insert into customer values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			Object[]params = new Object[]{c.getId(),c.getType(),c.getLevel(),c.getName(),c.getPhone(),c.getAddress(),
					c.getZip(),c.getMail(),c.getMax_to_get(),c.getTo_pay(),c.getTo_get(),c.getDefault_businessman(),c.getPoint()};
			pstm = service.setPrepareStatementParams( sql, params);
			pstm.executeUpdate();
			return true;
		}catch(Exception e){
			throw new CustomerException(this.getClass().getName()+".addCustomer");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	/**
	 * 删除客户
	 * @param c
	 * @return
	 * @throws CustomerException
	 */
	public boolean delCustomer(Customer c)throws CustomerException{
		PreparedStatement pstm = null;
		try{
			String sql = "delete from customer where id=?";
			Object[]params = new Object[]{c.getId()};
			pstm = service.setPrepareStatementParams( sql, params);
			pstm.executeUpdate();
			return true;
		}catch(Exception e){
			throw new CustomerException(this.getClass().getName()+".delCustomer");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	/**
	 * 修改
	 * @param c
	 * @return
	 * @throws CustomerException
	 */
	public boolean modCustomer(Customer c)throws CustomerException{
		PreparedStatement pstm = null;
		try{
			String sql  = "update customer set level=?,phone=?,address=?,zip=?,mail=?,max_to_get=?,to_pay=?,to_get=?,point=? where id=?";
			Object[]params = new Object[]{c.getLevel(),c.getPhone(),c.getAddress(),c.getZip(),c.getMail(),c.getMax_to_get(),c.getTo_pay(),c.getTo_get(),c.getPoint(),c.getId()};
			pstm = service.setPrepareStatementParams( sql, params);
			pstm.executeUpdate();
			return true;
		}catch(Exception e){
			throw new CustomerException(this.getClass().getName()+".modCustomer");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	public ArrayList<Customer> queryAllCustomer()throws CustomerException{
		PreparedStatement pstm = null;
		ArrayList<Customer> list = new ArrayList<Customer>();
		try{
			String sql = "select * from customer ";
			pstm = service.setPrepareStatementParams(sql, null);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				Customer c = new Customer(rs.getString(1),rs.getInt(2),rs.getString(4),rs.getInt(13),rs.getInt(3),rs.getString(5)
						,rs.getString(6),rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getDouble(10),rs.getDouble(11),rs.getString(12));
				list.add(c);
			}
		}catch(Exception e){
			throw new CustomerException(this.getClass().getName()+".queryAllCustomer");
		}
		return list;
	}
	
	public ArrayList<Customer> queryByKeyword(String s)throws CustomerException{
		PreparedStatement pstm = null;
		ArrayList<Customer> list = new ArrayList<Customer>();
		try{
			String sql = "select * from customer where id like ?";
			Object[]params  = new Object[]{"%"+s+"%"};
			pstm = service.setPrepareStatementParams(sql, params);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				Customer c = new Customer(rs.getString(1),rs.getInt(2),rs.getString(4),rs.getInt(13),rs.getInt(3),rs.getString(5)
						,rs.getString(6),rs.getString(7),rs.getString(8),rs.getDouble(9),rs.getDouble(10),rs.getDouble(11),rs.getString(12));
				list.add(c);
			}
		}catch(Exception e){
			throw new CustomerException(this.getClass().getName()+".queryAllCustomer");
		}
		return list;
	}
	
	
}
