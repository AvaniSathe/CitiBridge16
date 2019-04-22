package org.patriques;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import org.patriques.*;
import org.patriques.input.technicalindicators.Interval;
import org.patriques.input.timeseries.OutputSize;
import org.patriques.output.AlphaVantageException;
import org.patriques.output.timeseries.data.StockData;
import org.springframework.beans.factory.annotation.Autowired;

import com.arbitrage.domain.*;
import com.arbitrage.mapper.UserMapper;


public class App {
	@Autowired
    private UserMapper userMapper;
    public App(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
    App(){
    	
    }
	public String arbitrageCalculator(String companyName, String transaction ){ //to calculate arbitrage
		  Scanner scanner=new Scanner(System.in);
	    String apiKey = "ULPNVUVSHZ2JRJEWS";
	    int timeout = 3000;
	    AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
	    TimeSeries stockTimeSeries = new TimeSeries(apiConnector);
	    org.patriques.output.timeseries.IntraDay response ;
	    int activity;
	    if(transaction.equalsIgnoreCase("buy")) activity=0;
	    else activity=1;
	    String result = null;
	    StockData NSEsingleStockData = null;
	    StockData BSEsingleStockData = null;
	    try {
	       response = stockTimeSeries.intraDay("NSE:"+companyName, Interval.ONE_MIN, OutputSize.COMPACT);
	      ArrayList<StockData> NSEstockData = (ArrayList<StockData>) response.getStockData();
	     NSEsingleStockData=NSEstockData.get(0);
	    System.out.println("NSE High: "+NSEsingleStockData.getHigh());
	    
	    
	     response = stockTimeSeries.intraDay("BSE:"+companyName, Interval.ONE_MIN, OutputSize.COMPACT);
	      ArrayList<StockData> BSEstockData = (ArrayList<StockData>) response.getStockData();
	     BSEsingleStockData=BSEstockData.get(0);
	    System.out.println("BSE High: "+BSEsingleStockData.getHigh());
	    if(activity==0)
	        result=NSEsingleStockData.getHigh()<=BSEsingleStockData.getHigh()?"Buy on NSE stock":"Buy on BSE stock";
	     else
	        	result=NSEsingleStockData.getHigh()>=BSEsingleStockData.getHigh()?"Sell on NSE stock":"Sell on BSE stock";
	    
	    
	    
	    } catch (AlphaVantageException e) {
	      return "something went wrong";
	    }
	    return"NSE : "+NSEsingleStockData.getHigh()+" BSE : "+BSEsingleStockData.getHigh()+" "+result;
	  }
	public boolean addArbitrageToWishlist(UserWishlist userWishlist) {  //saving stock
	 
	    try {
	    LocalDate date =  LocalDate.now();
	    java.sql.Date d=java.sql.Date.valueOf(date);
        userMapper.insert(userWishlist.getUserId(), userWishlist.getcompanyName(),userWishlist.getArbitrage(),userWishlist.getQuantity(),userWishlist.getCurrentMarketPrice(),d,userWishlist.getNSE(),userWishlist.getBSE());
	    } catch (AlphaVantageException e) {
	      return false;
	    }
	    return true;
	}
	public double getCurrentNSEHigh(String companyName){  // to get Current Market Price
		 String apiKey = "ULPNVUVSHZ2JRJEWS";
		    int timeout = 3000;
		    AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
		    TimeSeries stockTimeSeries = new TimeSeries(apiConnector);
		    org.patriques.output.timeseries.IntraDay response ;
		    StockData NSEsingleStockData = null;
		    try {
		       response = stockTimeSeries.intraDay("NSE:"+companyName, Interval.ONE_MIN, OutputSize.COMPACT);
		      ArrayList<StockData> NSEstockData = (ArrayList<StockData>) response.getStockData();
		     NSEsingleStockData=NSEstockData.get(0);
		    return NSEsingleStockData.getHigh();
		    }
		    catch (AlphaVantageException e) {
			      return 0;
			    }
	}
	
	public long getCurrentNSEquantity(String companyName){    // to get volume
		 String apiKey = "ULPNVUVSHZ2JRJEWS";
		    int timeout = 3000;
		    AlphaVantageConnector apiConnector = new AlphaVantageConnector(apiKey, timeout);
		    TimeSeries stockTimeSeries = new TimeSeries(apiConnector);
		    org.patriques.output.timeseries.IntraDay response ;
		    StockData NSEsingleStockData = null;
		    try {
		       response = stockTimeSeries.intraDay("NSE:"+companyName, Interval.ONE_MIN, OutputSize.COMPACT);
		      ArrayList<StockData> NSEstockData = (ArrayList<StockData>) response.getStockData();
		     NSEsingleStockData=NSEstockData.get(0);
		    return NSEsingleStockData.getVolume();
		    }
		    catch (AlphaVantageException e) {
			      return 0;
			    }
	}
	
}