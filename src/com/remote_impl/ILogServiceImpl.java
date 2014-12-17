package com.remote_impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import com.dao.LogDao;
import com.exception.LogException;
import com.model.Log;
import com.remote_interface.ILogService;

public class ILogServiceImpl extends UnicastRemoteObject implements ILogService{
	private LogDao dao;

	protected ILogServiceImpl() throws Exception {
		super();
		dao = new LogDao();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addLog(Log log) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			dao.addLog(log);
		} catch (LogException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@Override
	public ArrayList<Log> queryAllLogs() throws RemoteException {
		// TODO Auto-generated method stub
		try {
			return dao.queryAll();
		} catch (LogException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ArrayList<Log> queryByTime(String t1, String t2)
			throws RemoteException {
		// TODO Auto-generated method stub
		try {
			return dao.queryByTime(t1, t2);
		} catch (LogException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
