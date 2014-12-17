package com.remote_impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import com.dao.CustomerDao;
import com.exception.CustomerException;
import com.func.MessageDialog;
import com.model.Customer;
import com.remote_interface.ICustomerService;

public class ICustomerServiceImpl extends UnicastRemoteObject implements ICustomerService {
	private CustomerDao dao;
	
	public ICustomerServiceImpl()throws Exception{
		super();
		dao = new CustomerDao();
	}

	@Override
	public void addCustomer(Customer c) throws RemoteException {
		// TODO Auto-generated method stub
		try{
		if(dao.isExist(c.getId())){
			MessageDialog.tip("客户已经存在");
		}else {
			dao.addCustomer(c);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void modCustomer(Customer c) throws RemoteException {
		// TODO Auto-generated method stub
		try{
			if(!dao.isExist(c.getId())){
				MessageDialog.tip("客户不存在");
			}else {
				dao.modCustomer(c);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Customer> queryAllCustomer() throws RemoteException {
		// TODO Auto-generated method stub
		try {
			return dao.queryAllCustomer();
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Customer> queryCustomerByKeyword(String s)
			throws RemoteException {
		// TODO Auto-generated method stub
		try {
			return dao.queryByKeyword(s);
		} catch (CustomerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
