package com.server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import com.factory.ServiceFactory;
import com.remote_interface.IUserSerivce;

public class Server {
public static void main(String[] args) {
	try{
		IUserSerivce userSerivce = ServiceFactory.getUserService();
		
		LocateRegistry.createRegistry(8787);
		String url = "rmi://192.168.47.12:8787/";
		
		Naming.rebind(url+="UserService", userSerivce);
		
		System.out.println("server starts!");
	}catch(Exception e){
		e.printStackTrace();
	}
}
}
