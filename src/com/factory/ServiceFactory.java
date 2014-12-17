package com.factory;

import com.func.Constants;
import com.remote_impl.IAccountServiceImpl;
import com.remote_impl.ICommodityServiceImpl;
import com.remote_impl.ICustomerServiceImpl;
import com.remote_impl.IImportServiceImpl;
import com.remote_impl.ILogServiceImpl;
import com.remote_impl.IUserServiceImpl;
import com.remote_interface.IAccountService;
import com.remote_interface.ICommodityService;
import com.remote_interface.ICustomerService;
import com.remote_interface.IImportService;
import com.remote_interface.ILogService;
import com.remote_interface.IUserSerivce;

public class ServiceFactory {
	
	public static IUserSerivce getUserService(){
		try{
			return (IUserServiceImpl)Class.forName(Constants.USER_SERVICE_CLASS).newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static ICommodityService getCommodityService(){
		try{
			return (ICommodityServiceImpl)Class.forName(Constants.COMMODITY_SERVICE_CLASS).newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static ILogService getLogService(){
		try{
			return (ILogServiceImpl) Class.forName(Constants.LOG_SERVICE_CLASS).newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static ICustomerService getCustomerService(){
		try{
			return (ICustomerServiceImpl)Class.forName(Constants.CUSTOMER_SERVICE_CLASS).newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static IAccountService getAccountService(){
		try{
			return (IAccountServiceImpl)Class.forName(Constants.ACCOUNT_SERVICE_CLASS).newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static IImportService getImportService(){
		try{
			return (IImportServiceImpl)Class.forName(Constants.IMPORT_SERVICE_CLASS).newInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
