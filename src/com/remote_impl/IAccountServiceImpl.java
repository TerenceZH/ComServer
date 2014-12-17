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
				MessageDialog.tip("请换个名字");
			}else {
				MessageDialog.tip(dao.addAccount(a)?"添加成功":"添加失败，请重试");
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
			MessageDialog.tip(dao.modAccount(a)?"修改成功":"修改失败，请重试");
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
