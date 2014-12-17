package com.remote_impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import com.dao.AccountDao;
import com.exception.AccountException;
import com.func.MessageDialog;
import com.model.Account;
import com.remote_interface.IAccountService;

public class IAccountServiceImpl extends UnicastRemoteObject implements IAccountService{
	private AccountDao dao;

	protected IAccountServiceImpl() throws RemoteException {
		super();
		dao = new AccountDao();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addAccount(Account a) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			if(dao.isExist(a.getName())){
				MessageDialog.tip("�뻻������");
			}else {
				MessageDialog.tip(dao.addAccount(a)?"��ӳɹ�":"���ʧ�ܣ�������");
			}
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void modAccount(Account a) throws RemoteException {
		// TODO Auto-generated method stub
		try{
			MessageDialog.tip(dao.modAccount(a)?"�޸ĳɹ�":"�޸�ʧ�ܣ�������");
		}catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Account> queryAccount(String name) throws RemoteException {
		// TODO Auto-generated method stub
		try{
			return dao.getAccount(name);
		}catch(Exception e){
			e.printStackTrace();
		}
		return new ArrayList<Account>();
	}

}
