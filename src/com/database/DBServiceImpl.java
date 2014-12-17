package com.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBServiceImpl implements DBService{
	
	private DBPool pool;
	
	public DBServiceImpl()throws Exception{
		pool = DBPool.getInstance();
	}

	@Override
	public void closeDB(PreparedStatement pstm) {
		// TODO Auto-generated method stub
		try{
			
		}finally{
			try{
				if(null!=pstm){
					pstm.getConnection().close();
					pstm.close();
				}
			}catch(Exception e){
				System.err.println("closeDB Error!");
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setPrepareStatementParams(PreparedStatement pstm,String sql,
			Object[] params) {
		// TODO Auto-generated method stub
		try{
			pstm = pool.getConnection().prepareStatement(sql); 
			if (params != null){
				for (int i = 0; i < params.length; i++) {
					pstm.setObject(i + 1, params[i]);
				}
			}
		}
		catch (SQLException e){
			System.err.println("setPrepareStatementParams Error!");
			e.printStackTrace();
		}
	}
	
	public PreparedStatement setPrepareStatementParams(String sql,
			Object[] params) {
		// TODO Auto-generated method stub
		PreparedStatement pstm = null;
		try{
			pstm = pool.getConnection().prepareStatement(sql); 
			if (params != null){
				for (int i = 0; i < params.length; i++) {
					pstm.setObject(i + 1, params[i]);
				}
			}
		}
		catch (SQLException e){
			System.err.println("setPrepareStatementParams Error!");
			e.printStackTrace();
		}
		return pstm;
	}
	
	

}
