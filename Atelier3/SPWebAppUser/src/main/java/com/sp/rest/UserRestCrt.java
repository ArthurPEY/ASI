package com.sp.rest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.sp.model.User;
import com.sp.service.UserService;


@RestController
public class UserRestCrt {
	@Autowired
	UserService lService;

	
	
	public List<String> Parse(String listStr){
		listStr = listStr.replace("[", "").replace("]", "");
		listStr = listStr.replace("\"", "");
		String[] split = listStr.split(",");
		List<String> list = Arrays.asList(split);
		
		return list;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/adduser")
	public void addUser(@RequestBody User user) {
		System.out.println(user);
		lService.addUser(user);
		
	}
	
	
	@RequestMapping(method=RequestMethod.GET,value="/getuser/{id}")
	public User getUser(@PathVariable String id) {
		User l=lService.getUser(Integer.valueOf(id));
		return l;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/login")
	public int login(@RequestBody String user_pass) {
		user_pass = user_pass.replace("[", "").replace("]", "");
		user_pass = user_pass.replace("\"", "");
		String[] split = user_pass.split(",");
		List<String> list = Arrays.asList(split);
		int existUser = lService.login(list);
		System.out.println(existUser);
		return existUser;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/getmoney/{id}")
	public int getMoneyUser(@PathVariable int id) {
		int money=lService.getMoneyUser(id);
		return money;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/setmoney")
	public void setMoneyUser(@RequestBody String userId_moneyChange) {
		List<String> Changes = Parse(userId_moneyChange);
		int userId = Integer.parseInt(Changes.get(0));
		int valueMoney = Integer.parseInt(Changes.get(1));
		System.out.println(userId);
		System.out.println(valueMoney);
		lService.setMoneyUser(userId,valueMoney);
	}
	

	

	
}