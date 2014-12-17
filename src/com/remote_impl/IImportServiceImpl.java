package com.remote_impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import com.dao.ImportDao;
import com.exception.ImportException;
import com.func.CommonFunc;
import com.model.ImportBill;
import com.remote_interface.IImportService;

public class IImportServiceImpl extends UnicastRemoteObject implements IImportService{
	private ImportDao dao;

	protected IImportServiceImpl() throws RemoteException {
		super();
		dao = new ImportDao();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addImportBill(ImportBill bill) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			dao.addBill(bill);
		} catch (ImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<ImportBill> queryImportBill(String time1, String time2)
			throws RemoteException {
		// TODO Auto-generated method stub
		try {
			return dao.queryAllImportBills(time1, time2);
		} catch (ImportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<ImportBill>();
	}

	@Override
	public String getNewId() throws RemoteException {
		// TODO Auto-generated method stub
		try{
			String t = dao.getLastId();
			if(t.equals("0")){
				return "00001";
			}else {
				return CommonFunc.billidToString(t);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

}
