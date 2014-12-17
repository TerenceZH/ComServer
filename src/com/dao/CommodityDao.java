package com.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.database.DBService;
import com.database.DBServiceImpl;
import com.exception.CommodityException;
import com.model.Commodity;

public class CommodityDao {
	private DBService service;
	
	public CommodityDao()throws Exception{
		try{
			service = new DBServiceImpl();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * name,style是否存在同样组合
	 * @param name
	 * @param style
	 * @return
	 * @throws CommodityException
	 */
	public boolean isExist(String name,String style)throws CommodityException{
		PreparedStatement pstm = null;
		try{
			String sql = "select * from commodity where name=? and style=?";
			Object[]params = new Object[]{name,style};
			pstm = service.setPrepareStatementParams( sql, params);
			ResultSet rs = pstm.executeQuery();
			return rs.next();
		}catch(Exception e){
			throw new CommodityException(this.getClass().getName()+".isExist");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	public boolean isCOmmodityExist(String id)throws CommodityException{
		PreparedStatement pstm = null;
		try{
			String sql = "select * from commodity where id=?";
			Object[]params = new Object[]{id};
			pstm = service.setPrepareStatementParams( sql, params);
			ResultSet rs = pstm.executeQuery();
			return rs.next();
		}catch(Exception e){
			throw new CommodityException(this.getClass().getName()+".isCommodityExist");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	/**
	 * 添加商品
	 * @param commodity
	 * @return
	 * @throws CommodityException
	 */
	public boolean addCommodity(Commodity commodity)throws CommodityException{
		PreparedStatement pstm = null;
		try{
			String sql = "insert into commodity values(?,?,?,?,?,?,?,?,?,?,?,?)";
			Object[]params = new Object[]{commodity.getId(),commodity.getName(),commodity.getStyle(),
					commodity.getStockQuantity(),commodity.getInPrice(),commodity.getSalePrice(),
					commodity.getLastInPrice(),commodity.getLastSalePrice(),commodity.getStockAvgPrice(),
					commodity.getWarningQuantity(),commodity.getTime(),commodity.getCategoryId()};
			pstm = service.setPrepareStatementParams( sql, params);
			pstm.executeUpdate();
			return true;
		}catch(Exception e){
			throw new CommodityException(this.getClass().getName()+".addCommodity");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	/**
	 * 删除
	 * @param c
	 * @return
	 * @throws CommodityException
	 */
	public boolean delCommodity(Commodity c)throws CommodityException{
		PreparedStatement pstm = null;
		try{
			String sql = "delete from commodity where id=?";
			Object[]params = new Object[]{c.getId()};
			pstm = service.setPrepareStatementParams( sql, params);
			pstm.executeUpdate();
			return true;
		}catch(Exception e){
			throw new CommodityException(this.getClass().getName()+".delCommodity");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	/**
	 * 修改属性
	 * @param commodity
	 * @return
	 * @throws CommodityException
	 */
	public boolean modCommodity(Commodity commodity)throws CommodityException{
		PreparedStatement pstm = null;
		try{
			String sql = "update commodity set stock_quantity=?,last_in_price=?,last_sale_price=?,stock_avg_price=?,"
					+ "warning_quantity=? where id=?";
			Object[]params = new Object[]{commodity.getStockQuantity(),commodity.getLastInPrice(),commodity.getSalePrice(),
					commodity.getStockAvgPrice(),commodity.getWarningQuantity(),commodity.getId()};
			pstm = service.setPrepareStatementParams( sql, params);
			pstm.executeUpdate();
			return true;
		}catch(Exception e){
			throw new CommodityException(this.getClass().getName()+".modCommodity");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	/**
	 * 查找所有
	 * @return
	 * @throws CommodityException
	 */
	public ArrayList<Commodity> queryAll()throws CommodityException{
		PreparedStatement pstm = null;
		ArrayList<Commodity> list = new ArrayList<Commodity>();
		try{
			String sql = "select * from commodity";
			pstm = service.setPrepareStatementParams( sql,null);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				Commodity commodity = new Commodity(rs.getString(1), rs.getString(2), rs.getString(3),rs.getInt(4),rs.getDouble(5),rs.getDouble(6),
						rs.getDouble(7),rs.getDouble(8),rs.getDouble(9),rs.getInt(10),rs.getString(11),rs.getInt(12));
				list.add(commodity);
			}
		}catch(Exception e){
			throw new CommodityException(this.getClass().getName()+".queryAll");
		}finally{
			service.closeDB(pstm);
		}
		return list;
	}
	
	/**
	 * id精确查找
	 * @param id
	 * @return
	 * @throws CommodityException
	 */
	public ArrayList<Commodity> queryById(String id)throws CommodityException{
		PreparedStatement pstm = null;
		ArrayList<Commodity> list = new ArrayList<Commodity>();
		try{
			String sql = "select * from commodity where id=";
			Object[]params = new Object[]{id};
			pstm = service.setPrepareStatementParams( sql,params);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				Commodity commodity = new Commodity(rs.getString(1), rs.getString(2), rs.getString(3),rs.getInt(4),rs.getDouble(5),rs.getDouble(6),
						rs.getDouble(7),rs.getDouble(8),rs.getDouble(9),rs.getInt(10),rs.getString(11),rs.getInt(12));
				list.add(commodity);
			}
		}catch(Exception e){
			throw new CommodityException(this.getClass().getName()+".queryById");
		}finally{
			service.closeDB(pstm);
		}
		return list;
	}
	
	/**
	 * 类别查找
	 * @param id
	 * @return
	 * @throws CommodityException
	 */
	public ArrayList<Commodity> queryByCateid(int id)throws CommodityException{
		PreparedStatement pstm = null;
		ArrayList<Commodity> list = new ArrayList<Commodity>();
		try{
			String sql = "select * from commodity where category_id=";
			Object[]params = new Object[]{id};
			pstm = service.setPrepareStatementParams( sql,params);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				Commodity commodity = new Commodity(rs.getString(1), rs.getString(2), rs.getString(3),rs.getInt(4),rs.getDouble(5),rs.getDouble(6),
						rs.getDouble(7),rs.getDouble(8),rs.getDouble(9),rs.getInt(10),rs.getString(11),rs.getInt(12));
				list.add(commodity);
			}
		}catch(Exception e){
			throw new CommodityException(this.getClass().getName()+".queryBycateId");
		}finally{
			service.closeDB(pstm);
		}
		return list;
	}
	
	
	/**
	 * 关键字模糊查找
	 * @param word
	 * @return
	 * @throws CommodityException
	 */
	public ArrayList<Commodity> queryByKeyword(String word)throws CommodityException{
		PreparedStatement pstm = null;
		ArrayList<Commodity> list = new ArrayList<Commodity>();
		try{
			String sql = "select * from commodity where name like ? or style like ?";
			Object[]params = new Object[]{"%"+word+"%","%"+word+"%"};
			pstm = service.setPrepareStatementParams(sql,params);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				Commodity commodity = new Commodity(rs.getString(1), rs.getString(2), rs.getString(3),rs.getInt(4),rs.getDouble(5),rs.getDouble(6),
						rs.getDouble(7),rs.getDouble(8),rs.getDouble(9),rs.getInt(10),rs.getString(11),rs.getInt(12));
				list.add(commodity);
			}
		}catch(Exception e){
			throw new CommodityException(this.getClass().getName()+".queryByKeyword");
		}finally{
			service.closeDB(pstm);
		}
		return list;
	}
	
	/**
	 * name 和  style 查找
	 * @param name
	 * @param style
	 * @return
	 * @throws CommodityException
	 */
	public ArrayList<Commodity> queryByNameAndStyle(String name,String style)throws CommodityException{
		PreparedStatement pstm = null;
		ArrayList<Commodity> list = new ArrayList<Commodity>();
		try{
			String sql = "select * from commodity where name like ? ";
			Object[]params ;
			if(!style.equals("")){
				sql+="and style like ?";
				params = new Object[]{"%"+name+"%","%"+style+"%"};
			}else {
				params = new Object[]{"%"+name+"%"};
			}
			pstm = service.setPrepareStatementParams( sql,params);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()){
				Commodity commodity = new Commodity(rs.getString(1), rs.getString(2), rs.getString(3),rs.getInt(4),rs.getDouble(5),rs.getDouble(6),
						rs.getDouble(7),rs.getDouble(8),rs.getDouble(9),rs.getInt(10),rs.getString(11),rs.getInt(12));
				list.add(commodity);
			}
		}catch(Exception e){
			throw new CommodityException(this.getClass().getName()+".queryByNameAndStyle");
		}finally{
			service.closeDB(pstm);
		}
		return list;
	}
	
	/**
	 * 获取最新id
	 * @return
	 * @throws CommodityException
	 */
	public String getLastestId(int cateId)throws CommodityException{
		PreparedStatement pstm = null;
		try{
			String sql = "select * from (select * from commodity where cate_id = ? order by id desc) where row_num<=1";
			Object[]params = new Object[]{cateId};
			pstm = service.setPrepareStatementParams( sql, params);
			ResultSet rs = pstm.executeQuery();
			return rs.getString(1);
		}catch(Exception e){
			e.printStackTrace();
			throw new CommodityException(this.getClass().getName()+".getLastestId");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	/**
	 * 能否删除商品
	 * @param c
	 * @return
	 * @throws CommodityException
	 */
	public boolean isCommodityDeletable(Commodity c)throws CommodityException{
		PreparedStatement pstm = null;
		try{
			String sql  = "select * from portlist where commodity_id=? ";
			Object[]params = new Object[]{c.getId()};
			pstm = service.setPrepareStatementParams( sql, params);
			ResultSet rs = pstm.executeQuery();
			return !rs.next();
		}catch(Exception e){
			e.printStackTrace();
			throw new CommodityException(this.getClass().getName()+".isCommodityDeletable");
		}finally{
			service.closeDB(pstm);
		}
	}
	
	
	public int getCateId(String name)throws CommodityException{
		PreparedStatement pstm = null;
		try{
			String sql  = "select * from commodity_category where name=?";
			Object[]params = new Object[]{name};
			pstm = service.setPrepareStatementParams( sql, params);
			ResultSet rs = pstm.executeQuery();
			return rs.getInt(1);
		}catch(Exception e){
			e.printStackTrace();
			throw new CommodityException(this.getClass().getName()+".isCommodityDeletable");
		}finally{
			service.closeDB(pstm);
		}
	}

}
