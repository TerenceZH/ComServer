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
				MessageDialog.tip("请重命名！");
			}else {
				//fathercc如果是null，新的分类parentid=0
				CommodityCategory cc = new CommodityCategory(dao.getLastestId()+1,name,
						fathercc==null?0:fathercc.getNode_id());
				MessageDialog.tip(dao.addCommodityCategory(cc)?"添加成功！":"添加失败，请重试！");
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
				MessageDialog.tip("不能删除！");
			}else {
				MessageDialog.tip(dao.delCommodityCategory(cc)?"删除成功":"删除失败，请重试！");
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
				MessageDialog.tip("修改失败，请重命名！");
			}else {
				MessageDialog.tip(dao.modifyCommodityCategory(cc)?"修改成功！":"修改失败，请重试！");
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
				MessageDialog.tip("请重命名！");
			}else if(dao2.isCOmmodityExist(c.getId())){
				MessageDialog.tip("商品已存在！");
			}else{
				MessageDialog.tip(dao2.addCommodity(c)?"添加成功！":"添加失败，请重试！");
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
				MessageDialog.tip("商品不存在");
			}else{
				MessageDialog.tip(dao2.delCommodity(c)?"删除成功！":"删除失败！");
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
				MessageDialog.tip("商品不存在！");
			}else{
				MessageDialog.tip(dao2.modCommodity(c)?"修改成功":"修改失败");
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
