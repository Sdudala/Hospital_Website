package com.neu.healthcare;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.neu.healthcare.dao.UserDao;
import com.neu.healthcare.model.UserAccount;

@Controller
public class AdminController {
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value = "/adminNewRequests")
	public String adminNewRequests(Locale locale, Model model) {
		try {
			ArrayList<UserAccount> uaList = userDao.queryNewUserRequests();
			model.addAttribute("uaList", uaList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "AdminNewRequests";
	}
	
	@RequestMapping(value = "/admin")
	public String adminHome(Locale locale, Model model) {
		return "AdminHome";
	}
	
	@RequestMapping(value = "/adminUpdateUsers")
	public String adminUpdateUsers(Model model, HttpServletRequest request) {
		String[] userId = request.getParameterValues("userId");
		String act = request.getParameter("submit");
		if(userId != null) {
			if(userId.length > 0) {
				if(act.equals("Accept")) {
					try {
						userDao.activateUserAccountQuery(userId);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						//	return "loginError";
					}
				}
				else {
					try {
						userDao.deleteUserAccountQuery(userId);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						//	return "loginError";
					}
				}
			}
		}
		try {
			ArrayList<UserAccount> uaList = userDao.queryNewUserRequests();
			model.addAttribute("uaList", uaList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "AdminNewRequests";
	}
	
	
}
