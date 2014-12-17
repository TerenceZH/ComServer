package com.remote_impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import com.dao.CommodityCategoryDao;
import com.dao.CommodityDao;
import com.exception.CommodityException;
import com.func.MessageDialog;
import com.model.Commodity;
import com.model.CommodityCategory;
import com.remote_interface.ICommodityService;

public class ICommodityServiceImpl extends UnicastRemoteObject implements ICommodityService{
	private CommodityCategoryDao dao;
	private CommodityDao dao2;
	
	protected ICommodityServiceImpl() throws Exception {
		super();
		dao = new CommodityCategoryDao();
		dao2 = new CommodityDao();
		// TODO Auto-generated constructor stub
	}


	@Override
	public void addCommodityCategory(String name,
			CommodityCategory fathercc) throws RemoteException {
		// TODO Auto-generated method stub
		try{
			if(!dao.nameIsExist(name)){
				MessageDialog.tip("����������");
			}else {
				//fathercc�����null���µķ���parentid=0
				CommodityCategory cc = new CommodityCategory(dao.getLastestId()+1,name,
						fathercc==null?0:fathercc.getNode_id());
				MessageDialog.tip(dao.addCommodityCategory(cc)?"��ӳɹ���":"���ʧ�ܣ������ԣ�");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void delCommodityCategory(CommodityCategory cc)
			throws RemoteException {
		// TODO Auto-generated method stub
		try{
			if(!dao.isDeletableOrAddable(cc)){
				MessageDialog.tip("����ɾ����");
			}else {
				MessageDialog.tip(dao.delCommodityCategory(cc)?"ɾ���ɹ�":"ɾ��ʧ�ܣ������ԣ�");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<CommodityCategory> showCommodityCategory(int parentid) throws RemoteException {
		// TODO Auto-generated method stub
		try{
			return dao.queryCategoriesByParentId(parentid);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void modCmmodityCategory(CommodityCategory cc)
			throws RemoteException {
		// TODO Auto-generated method stub
		try{
			if(!dao.nameIsExist(cc.getName())){
				MessageDialog.tip("�޸�ʧ�ܣ�����������");
			}else {
				MessageDialog.tip(dao.modifyCommodityCategory(cc)?"�޸ĳɹ���":"�޸�ʧ�ܣ������ԣ�");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	@Override
	public boolean isAble(CommodityCategory cc) throws RemoteException {
		// TODO Auto-generated method stub
		try{
			return dao.isDeletableOrAddable(cc);
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public void addCommodity(Commodity c) throws RemoteException {
		// TODO Auto-generated method stub
		try{
			if(dao2.isExist(c.getName(), c.getStyle())){
				MessageDialog.tip("����������");
			}else if(dao2.isCOmmodityExist(c.getId())){
				MessageDialog.tip("��Ʒ�Ѵ��ڣ�");
			}else{
				MessageDialog.tip(dao2.addCommodity(c)?"��ӳɹ���":"���ʧ�ܣ������ԣ�");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	@Override
	public void delCommodity(Commodity c) throws RemoteException {
		// TODO Auto-generated method stub
		try{
			if(!dao2.isCOmmodityExist(c.getId())){
				MessageDialog.tip("��Ʒ������");
			}else{
				MessageDialog.tip(dao2.delCommodity(c)?"ɾ���ɹ���":"ɾ��ʧ�ܣ�");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	@Override
	public void modCommodity(Commodity c) throws RemoteException {
		// TODO Auto-generated method stub
		try{
			if(!dao2.isCOmmodityExist(c.getId())){
				MessageDialog.tip("��Ʒ�����ڣ�");
			}else{
				MessageDialog.tip(dao2.modCommodity(c)?"�޸ĳɹ�":"�޸�ʧ��");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	@Override
	public ArrayList<Commodity> queryCommodity(int type, String keyword) throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<Commodity> list = new ArrayList<Commodity>();
		try{
			switch (type) {
			case 1:
				list = dao2.queryAll();
				break;
			case 2:
				list = dao2.queryById(keyword);
				break;
			case 3:
				list = dao2.queryByKeyword(keyword);
				break;
			case 100:
				String[]temp = keyword.split("---");
				list = dao2.queryByNameAndStyle(temp[0], temp[1].equals("null")?"":temp[1]);
				break;
			case 4:
				list = dao2.queryByCateid(Integer.parseInt(keyword));
				break;
			default:
				break;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}


	@Override
	public String getCommodityId(String name) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			int cateid = dao2.getCateId(name);
			return (Integer.parseInt(dao2.getLastestId(cateid))+1)+"";
		} catch (CommodityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public boolean delIsAble(Commodity c) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			return dao2.isCommodityDeletable(c);
		} catch (CommodityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}



	@Override
	public ArrayList<String> getCategory() throws RemoteException {
		// TODO Auto-generated method stub
		ArrayList<String> lsit = new ArrayList<String>();
		try{
			ArrayList<CommodityCategory> ccList = dao.activeCategories();
			for(CommodityCategory cc:ccList){
				lsit.add(cc.getName());
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return lsit;
	}


	@Override
	public int gteCateid(String name) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			return dao2.getCateId(name);
		} catch (CommodityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}


	@Override
	public boolean isAble(int id) throws RemoteException {
		// TODO Auto-generated method stub
		try {
			return dao.isDeletableOrAddable(id);
		} catch (CommodityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public CommodityCategory queryCommodityCategory(int id)
			throws RemoteException {
		// TODO Auto-generated method stub
		try {
			return dao.queryCategoriesById(id);
		} catch (CommodityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	

}
