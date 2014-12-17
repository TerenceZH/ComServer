package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.database.DBService;
import com.database.DBServiceImpl;
import com.exception.LogException;
import com.model.Log;

public class LogDao {
private DBService service;
	
	public LogDao()throws Exception{
		try{
			service = new DBServiceImpl();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public boolean addLog(Log l)throws LogException{
		PreparedStatement pstm = null;
		try{
			String sql = "insert into log values(?,?,?)";
			Object[]params = new Object[]{l.getType(),l.getDesc(),l.getTime()};
			pstm = service.setPrepareStatementParams( sql, params);
			pstm.executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			throw new LogException(this.getClass().getName()+".addLog");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	public ArrayList<Log> queryAll()throws LogException{
		PreparedStatement pstm  = null;
		ArrayList<Log> logs = new ArrayList<Log>();
		try{
			String sql = "select * from log ";
			pstm = service.setPrepareStatementParams( sql, null);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				Log log = new Log(rs.getInt(1),rs.getString(2),rs.getString(3));
				logs.add(log);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new LogException(this.getClass().getName()+".queryAll");
		}finally{
			service.closeDB(pstm);
		}
		return logs;
	}
	
	public ArrayList<Log> queryByTime(String t1,String t2)throws LogException{
		PreparedStatement pstm  = null;
		ArrayList<Log> logs = new ArrayList<Log>();
		try{
			String sql = "select * from log  where time between ? and ?";
			Object[]params = new Object[]{t1,t2};
			pstm = service.setPrepareStatementParams(sql, params);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				Log log = new Log(rs.getInt(1),rs.getString(2),rs.getString(3));
				logs.add(log);
			}
			
		}catch(Exception e){
			e.printStackTrace();
			throw new LogException(this.getClass().getName()+".queryByTime");
		}finally{
			service.closeDB(pstm);
		}
		return logs;
	}
	

}
