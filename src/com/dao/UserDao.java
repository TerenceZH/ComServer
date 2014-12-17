package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.database.DBService;
import com.database.DBServiceImpl;
import com.exception.UserException;
import com.model.Power;
import com.model.Users;

public class UserDao {
	private DBService service = null;

	public UserDao(){
		try {
			service = new DBServiceImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 是否存在匹配的用户
	 * @param name
	 * @param password
	 * @return
	 * @throws UserException 
	 */
	public boolean isExist(String name,String password) throws UserException{
		PreparedStatement pstm = null;
		try{
			String sql = "select * from users where username=? and password=?";
			Object[]params = new Object[]{name,password};
			pstm = service.setPrepareStatementParams( sql, params);
			ResultSet rs = pstm.executeQuery();
			return rs.next();
		}catch(Exception e){
			throw new UserException("UserDao.isExist");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	public boolean isExist(String name)throws UserException{
		PreparedStatement pstm = null;
		try{
			String sql = "select * from users where username=? ";
			Object[]params = new Object[]{name};
			pstm = service.setPrepareStatementParams(sql, params);
			ResultSet rs = pstm.executeQuery();
			return rs.next();
		}catch(Exception e){
			throw new UserException("UserDao.isExist");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	/**
	 * 获得power
	 * @param name
	 * @return
	 */
	public int getPower(String name)throws UserException{
		PreparedStatement  pstm = null;
		try{
			String sql = "select type from users where username=? ";
			Object[]params = new Object[]{name};
			pstm = service.setPrepareStatementParams( sql, params);
			ResultSet rs = pstm.executeQuery();
			return rs.getInt(3);
		}catch(Exception e){
			throw new UserException("UserDao.getPower");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	public boolean addUser(Users u) throws UserException{
		PreparedStatement pstm = null;
		try{
			String sql = "insert into users values(?,?,?)";
			Power p = u.getType();
			int type;
			switch (p.getName()) {
			case "系统管理员":
				type = 0;
				break;
			case "库存管理员":
				type = 1;
				break;
			case "进销人员":
				type = 2;
				break;
			case "财务人员":
				type = 3;
				break;
			case "总经理":
				type = 4;
				break;
			default:
				type = 0;
				break;
			}
			Object[]params = new Object[]{u.getUsername(),u.getPassword(),type};
			pstm = service.setPrepareStatementParams(sql, params);
			pstm.executeUpdate();
			return true;
		}catch(Exception e){
			throw new UserException("UserDao.addUser");
		}finally{
			service.closeDB(pstm);
		}
	}

	
	public boolean modUser(Users u)throws UserException{
		PreparedStatement pstm = null;
		try{
			String sql = "update users set type=? where username=?";
			Power p = u.getType();
			int type;
			switch (p.getName()) {
			case "系统管理员":
				type = 0;
				break;
			case "库存管理员":
				type = 1;
				break;
			case "进销人员":
				type = 2;
				break;
			case "财务人员":
				type = 3;
				break;
			case "总经理":
				type = 4;
				break;
			default:
				type = 0;
				break;
			}
			Object[]params = new Object[]{type,u.getUsername()};
			pstm = service.setPrepareStatementParams( sql, params);
			pstm.executeUpdate();
			return true;
		}catch(Exception e){
			throw new UserException("UserDao.modUser");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	public Users getUser(String username)throws UserException{
		PreparedStatement  pstm = null;
		try{
			String sql = "select * from users where username=? ";
			Object[]params = new Object[]{username};
			pstm = service.setPrepareStatementParams( sql, params);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				int t = rs.getInt(3);
				Power p = null;
				switch (t) {
				case 0:
					p = Power.ADMINSTRATOR;
					break;
				case 1:
					p = Power.STOCKMAN;
					break;
				case 2:
					p = Power.SALESMAN;
					break;
				case 3:
					p = Power.ACCOUNT;
					break;
				case 4:
					p = Power.MANAGER;
					break;
				default:
					break;
				}
				return new Users(username, rs.getString(2), p);
			}
		}catch(Exception e){
			throw new UserException("UserDao.getPower");
		}finally{
			service.closeDB(pstm);
		}
		return null;
	}
}
