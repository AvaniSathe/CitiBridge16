package com.arbitrage.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.patriques.*;
import com.arbitrage.domain.*;
import com.arbitrage.mapper.UserMapper;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("/rest/user")
public class UserResources {
	@Autowired
    private UserMapper userResources;
    public UserResources(UserMapper userResources) {
        this.userResources = userResources;
    }

    public int UserID;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/user-wishlist")                         //get the saved stock from database
    public List<UserWishlist> get() {
        List<UserWishlist> list=userResources.find(UserID);
        int size=list.size();
        for(int i=0;i<size;i++) {
        	double price=new App(userResources).getCurrentNSEHigh(list.get(i).getcompanyName());
        	long quantity=new App(userResources).getCurrentNSEquantity(list.get(i).getcompanyName());
        	System.out.println("Price: "+price);
        	if(price!=0.0) {
        	list.get(i).setCurrentMarketPrice(price);
        	}
        	list.get(i).setQuantity((int)quantity);
        }
        return list;
    }
    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/wishlist")                                     //add to saved stock
    public boolean addToWishlist(@RequestBody UserWishlist userWishlist) {
    	App app=new App(userResources);
    	userWishlist.setUserId(UserID);
        return app.addArbitrageToWishlist(userWishlist);
    }
    
    @CrossOrigin(origins = "http://localhost:4200")        //to authenticate user
    @PostMapping(path = "/post")
    public String posting(@RequestBody UserLogin user) {
    	UserLogin a;
    	a= userResources.findAll(user.getUsername());
    	if(a!=null) {
    	if(a.getPassword().equals(user.getPassword())) {
    		UserID=a.getUserId();
    		return "Authentication Successful "+UserID;
    	}else return "Authentication Not Successful";
    	}
    	else return "Authentication Not Successful";
    }
    @CrossOrigin(origins = "http://localhost:4200")        //get realtime stock values
    @PostMapping(path = "/result")
    public String resultCalculator(@RequestBody Parameters singleParameter) {
    	App app=new App(userResources);
        return app.arbitrageCalculator(singleParameter.getCompanyName(),singleParameter.getTransaction());
    }
    
}
