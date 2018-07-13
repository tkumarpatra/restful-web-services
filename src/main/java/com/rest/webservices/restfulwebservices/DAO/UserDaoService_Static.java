package com.rest.webservices.restfulwebservices.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rest.webservices.restfulwebservices.bean.User;

@Component
public class UserDaoService_Static {
	
	private static List<User> users = new ArrayList<>();

	public static Integer userCount = 4;
	
	static {
		users.add(new User(1, "Tanmay", new Date()));
		users.add(new User(2, "Swagatika", new Date()));
		users.add(new User(3, "Tanmay Patra", new Date()));
		users.add(new User(4, "Swagatika Jena", new Date()));
	}
	
	public List<User> findAll() {
		return users;
	}
	
	public User save(User user) {
		
		if(user.getId()==null) {
			userCount++;
			user.setId(userCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id) {
		for(User user:users) {
			if(user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
	public User deleteById(int id) {
		Iterator<User> itr = users.iterator();
		while(itr.hasNext()) {
			User user = itr.next();
				if(user.getId() == id) {
					itr.remove();
					return user;
				}
		}
		return null;
	}
}
