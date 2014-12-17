package com.remote_impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.omg.CORBA.OBJ_ADAPTER;

import com.dao.UserDao;
import com.exception.UserException;
import com.model.Power;
import com.model.Users;
import com.remote_interface.IUserSerivce;

public class IUserServiceImpl extends UnicastRemoteObject implements IUserSerivce {

	private UserDao dao = null;
	
	public IUserServiceImpl() throws RemoteException {
		super();
		dao = new UserDao();
		// TODO Auto-generated constructor stub
	}
	
	/**µÇÂ½*/
	public Power login(String username,String password)throws RemoteException{
		Power p = null;
		try {
			boolean isExist = dao.isExist(username, password);
			if(isExist){
				int type = dao.getPower(username);
				switch (type) {
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
					p =  Power.ACCOUNT;
					break;
				case 4:
					p =  Power.MANAGER;
				default:
					break;
				}
			}
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
		
	}
	/**µÇ³ö*/
	public void logout() throws RemoteException{
		System.exit(0);
	}
	
	@Override
	public Users getUser(String username,String password) throws RemoteException {
		// TODO Auto-generated method stub
		Users u = null;
		try{
			int t = dao.getPower(username);
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
			u  = new Users(username,password,p);
		}catch(UserException e){
			e.printStackTrace();
		}
		return u;
	}

	@Override
	public void addUser(Users u) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			dao.addUser(u);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void modUser(Users u) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			dao.modUser(u);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Users getUser(String name) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			return dao.getUser(name);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean isExist(String name) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			return dao.isExist(name);
		} catch (UserException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	

}
