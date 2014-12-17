package com.database;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public   class   DBPool{       
	   private   static   DBPool   dbPool;       
	   private   ComboPooledDataSource   dataSource;     

	   static   {       
	           dbPool=new   DBPool();       
	   }       
	   
	   public   DBPool(){       
	           try   {       
	                   dataSource=new   ComboPooledDataSource();       
	                   dataSource.setUser( "zhangxin");       
	                   dataSource.setPassword( "1033281892");       
	                   dataSource.setJdbcUrl( "jdbc:oracle:thin:@192.168.47.12:1521:XE"); 
	                   dataSource.setDriverClass( "oracle.jdbc.OracleDriver"); 
	                   dataSource.setInitialPoolSize(2); 
	                   dataSource.setMinPoolSize(1); 
	                   dataSource.setMaxPoolSize(10); 
	                   dataSource.setMaxStatements(50); 
	                   dataSource.setMaxIdleTime(60);       
	           }   catch   (PropertyVetoException   e)   {       
	               throw   new   RuntimeException(e);       
	           }       
	   }       

	   public   final   static   DBPool   getInstance(){       
	           return   dbPool;       
	   }       

	   public   final   Connection   getConnection()   {       
	           try   {       
	                   return   dataSource.getConnection();       
	           }   catch   (SQLException   e)   {       
	                   throw   new   RuntimeException( "�޷�������Դ��ȡ���� ",e);       
	           }       
	   }     
	   
	   public   static   void   main(String[]   args)   throws   SQLException   { 
	Connection   con   =   null; 
	try   { 
	con   =   DBPool.getInstance().getConnection(); 
	}   catch   (Exception   e){ 
	}   finally   { 
	if   (con   !=   null) 
	con.close(); 
	} 
	} 
	}