package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.database.DBService;
import com.database.DBServiceImpl;
import com.exception.ImportException;
import com.model.ImportBill;

public class ImportDao {
private DBService service;
	
	public ImportDao(){
		try {
			service = new DBServiceImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public boolean addBill(ImportBill bill)throws ImportException{
		PreparedStatement pstm = null;
		try{
			String sql = "insert into import_bill values(?,?,?,?,?,?,?,?,?,?,?)";
			Object[]params = new Object[]{bill.getId(),bill.getType(),bill.getSupplier(),bill.getBusinessman(),
					bill.getOperator(),bill.getWarehouse(),bill.getPortListIdList(),bill.getDesc(),bill.getTotal(),
					bill.getTime(),bill.getIsApproved()};
			pstm =  service.setPrepareStatementParams(sql, params);
			pstm.executeUpdate();
			return true;
		}catch(Exception e){
			throw new ImportException(this.getClass().getName()+"¡£addBill");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	public String getLastId()throws ImportException{
		PreparedStatement pstm = null;
		try{
			String sql = "select id from import_bill where id like ? order by id desc";
			java.util.Date date = new java.util.Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
			String s = format.format(date);
			Object[]params = new Object[]{"%"+s+"%"};
			pstm = service.setPrepareStatementParams(sql, params);
			ResultSet rs = pstm.executeQuery();
			if(rs.next()){
				return rs.getString(1);
			}else {
				return "0";
			}
		}catch(Exception e){
			throw new ImportException(this.getClass().getName()+"¡£addBill");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	public ArrayList<ImportBill> queryAllImportBills(String time1,String time2) throws ImportException{
		PreparedStatement pstm = null;
		ArrayList<ImportBill> bills = new ArrayList<ImportBill>();
		try{
			String sql = "select * from import_bill where time between ? and ?";
			Object[]params = new Object[]{time1,time2};
			pstm = service.setPrepareStatementParams(sql, params);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				ArrayList<String> list = new ArrayList<String>();
				String temp = rs.getString(7);
				if(temp.contains(",")){
					String[]t = temp.split(",");
					for(int i=0;i<t.length;i++){
						list.add(t[i]);
					}
				}else {
					list.add(temp);
				}
				ImportBill bill = new ImportBill(rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getString(6),list,rs.getString(8),rs.getDouble(9),rs.getString(10),rs.getInt(11));
				bills.add(bill);
			}
		}catch(Exception e){
			throw new ImportException(this.getClass().getName()+"¡£queryAllImportBills");
		}finally{
			service.closeDB(pstm);
		}
		return bills;
	}

}
