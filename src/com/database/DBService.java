package com.database;

import java.sql.PreparedStatement;

public interface DBService {
	public void closeDB(PreparedStatement pstm);
	public void setPrepareStatementParams(PreparedStatement pstm,String sql,Object[]params);
	public PreparedStatement setPrepareStatementParams(String sql,Object[]params);

}
