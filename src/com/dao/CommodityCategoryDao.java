package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.database.DBService;
import com.database.DBServiceImpl;
import com.exception.CommodityException;
import com.model.CommodityCategory;

public class CommodityCategoryDao {
	private DBService service;
	
	public CommodityCategoryDao(){
		try {
			service = new DBServiceImpl();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 可否删除或者添加，商品编号前三位是分类编号
	 * @param cc
	 * @return
	 * @throws CommodityException 
	 */
	public boolean isDeletableOrAddable(CommodityCategory cc) throws CommodityException{
		PreparedStatement pstm = null;
		try{ 
			String sql = "select * from commodity where category_id= ?";
			Object[]params = new Object[]{cc.getNode_id()};
			pstm = service.setPrepareStatementParams( sql, params);
			ResultSet rs = pstm.executeQuery();
			return !rs.next();
		}catch(Exception e){
			e.printStackTrace();
			throw new CommodityException(this.getClass().getName()+".isDeletable");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	public boolean isDeletableOrAddable(int id) throws CommodityException{
		PreparedStatement pstm = null;
		try{ 
			String sql = "select * from commodity where category_id= ?";
			Object[]params = new Object[]{id};
			pstm = service.setPrepareStatementParams(sql, params);
			ResultSet rs = pstm.executeQuery();
			return !rs.next();
		}catch(Exception e){
			e.printStackTrace();
			throw new CommodityException(this.getClass().getName()+".isDeletable");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	/**
	 * 删除
	 * @param cc
	 * @return
	 * @throws CommodityException
	 */
	public boolean delCommodityCategory(CommodityCategory cc)throws CommodityException{
		PreparedStatement pstm = null;
		try{
			String sql = "delete from comodity_category where node_id=?";
			Object[]params = new Object[]{cc.getNode_id()};
			pstm = service.setPrepareStatementParams( sql, params);
			pstm.executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			throw new CommodityException(this.getClass().getName()+".delCommodityCategory");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	/**
	 *增加
	 * @param cc
	 * @return
	 * @throws CommodityException
	 */
	public boolean addCommodityCategory(CommodityCategory cc)throws CommodityException{
		PreparedStatement pstm = null;
		try{
			String sql = "insert into commodity_category values(?,?,?)";
			Object[]params = new Object[]{cc.getNode_id(),cc.getName(),cc.getParent_id()};
			pstm = service.setPrepareStatementParams(sql, params);
			pstm.executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			throw new CommodityException(this.getClass().getName()+".addCommodityCategory");
		}finally{
			service.closeDB(pstm);
		}
	}

	/**
	 * 判断条件是name
	 * @param name
	 * @return
	 * @throws CommodityException
	 */
	public boolean nameIsExist(String name)throws CommodityException{
		PreparedStatement pstm = null;
		try{
			String sql = "select * from commodity_category where name= ?";
			Object[]params = new Object[]{name};
			pstm = service.setPrepareStatementParams( sql, params);
			ResultSet rs = pstm.executeQuery();
			return !rs.next();
		}catch(Exception e){
			e.printStackTrace();
			throw new CommodityException(this.getClass().getName()+".nameIsExist");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	
	/**
	 * 修改,只能修改name
	 * @param cc
	 * @return
	 * @throws CommodityException
	 */
	public boolean modifyCommodityCategory(CommodityCategory cc)throws CommodityException{
		PreparedStatement pstm = null;
		try{
			String sql = "update commodity_category set name=? where node_id=?";
			Object[]params = new Object[]{cc.getName(),cc.getNode_id()};
			pstm = service.setPrepareStatementParams(sql, params);
			pstm.executeUpdate();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			throw new CommodityException(this.getClass().getName()+".modifyCommodityCategory");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	/**
	 * 获取最新id
	 * @return
	 * @throws CommodityException
	 */
	public int getLastestId()throws CommodityException{
		PreparedStatement pstm = null;
		try{
			String sql = "select * from (select * from commodity_category order by node_id desc) where row_num<=1";
			pstm = service.setPrepareStatementParams(sql, null);
			ResultSet rs = pstm.executeQuery();
			return rs.getInt(1);
		}catch(Exception e){
			e.printStackTrace();
			throw new CommodityException(this.getClass().getName()+".getLastestId");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	/**
	 * 根据父节点查找相应类别
	 * @param parentId
	 * @return
	 * @throws CommodityException
	 */
	public ArrayList<CommodityCategory> queryCategoriesByParentId(int parentId)throws CommodityException{
		PreparedStatement pstm  = null;
		ArrayList<CommodityCategory> list = new ArrayList<CommodityCategory>();
		try{
			String sql = "select * from commodity_category where parent_id=?";
			Object[]params  = new Object[]{parentId};
			pstm = service.setPrepareStatementParams( sql, params);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				CommodityCategory cc = new CommodityCategory(rs.getInt(1), rs.getString(2),rs.getInt(3));
				list.add(cc);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new CommodityException(this.getClass().getName()+".queryCategoriesByParentId");
		}finally{
			service.closeDB(pstm);
		}
		return list;
	}
	
	public CommodityCategory queryCategoriesById(int id)throws CommodityException{
		PreparedStatement pstm  = null;
		CommodityCategory cc = null;
		try{
			String sql = "select * from commodity_category where node_id=?";
			Object[]params  = new Object[]{id};
			pstm = service.setPrepareStatementParams(sql, params);
			ResultSet rs = pstm.executeQuery();
			cc = new CommodityCategory(rs.getInt(1), rs.getString(2),rs.getInt(3));
		}catch(Exception e){
			e.printStackTrace();
			throw new CommodityException(this.getClass().getName()+".queryCategoriesById");
		}finally{
			service.closeDB(pstm);
		}
		return cc;
	}
	
	/**
	 * 返回可以添加商品的类别
	 * @return
	 * @throws CommodityException
	 */
	public ArrayList<CommodityCategory> activeCategories()throws CommodityException{
		PreparedStatement pstm  = null;
		ArrayList<CommodityCategory> list = new ArrayList<CommodityCategory>();
		try{
			String sql = "select * from commodity_category where node_id not in (select distinct parent_id from commodity_category)";
			pstm = service.setPrepareStatementParams( sql, null);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				CommodityCategory cc = new CommodityCategory(rs.getInt(1), rs.getString(2),rs.getInt(3));
				list.add(cc);
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new CommodityException(this.getClass().getName()+".activeCategories");
		}finally{
			service.closeDB(pstm);
		}
		return list;
	}
}
