package com.arbitrage.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Date;
import java.util.List;
import org.apache.ibatis.annotations.Select;

import com.arbitrage.domain.*;



@Mapper
public interface UserMapper {

	@Select("select * from UserLogin where username=#{username}")  //to get the info about user
	UserLogin findAll(String username);
	
	@Select("select * from UserWishlist where userId=#{id}")  //to get the saved stock for particular user
	List<UserWishlist>find(int id);
	
	@Insert("INSERT into userWishlist(userId,companyName,arbitrage,quantity,"
			+ "currentMarketPrice,transactionDate,NSE,BSE) "
			+ "VALUES(#{userId}, #{companyName}, #{arbitrage},"
			+ " #{quantity}, #{currentMarketPrice}, #{transactionDate}, #{NSE}, #{BSE})")
	public boolean insert(int userId,String companyName,double arbitrage,int quantity,
			double currentMarketPrice,Date transactionDate,double NSE,double BSE); //to save saved sock
}
