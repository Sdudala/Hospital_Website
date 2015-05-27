package com.neu.healthcare;

import java.security.Principal;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.healthcare.dao.UserDao;
import com.neu.healthcare.model.Person;
import com.neu.healthcare.model.UserAccount;
import com.neu.healthcare.model.Person.GenderType;
import com.neu.healthcare.model.UserAccount.RoleType;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
//	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/*
	 * Specify this useValidate will be injected
	 */
	@Autowired
	@Qualifier("userAccountValidator")
	private Validator validator;
	
	@Autowired
	private UserDao userDao;
	
	/*
	 * This is to initialize webDataBinder,set its
	 * validator as we specify.
	 */
	@InitBinder
	private void initBinder (WebDataBinder binder){
		binder.setValidator(validator);
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homeSecurity1(Model model, HttpServletRequest request, HttpServletResponse response, Principal principal) {

		try {
			UserAccount ua = userDao.queryUserByName(principal.getName());
			HttpSession session = request.getSession();
		    session.setAttribute("user", ua);
		    
		    String str = request.getParameter("remember");
//		    System.out.println("checkbox value is "+str);
//		    System.out.println("password is "+request.getParameter("j_password"));
		    if(str!=null){
//		    	System.out.println("I am about to set cookie");
		    	Cookie myCookie = new Cookie("username", ua.getUserName());
		    	myCookie.setMaxAge(7*86400);
		    	response.addCookie(myCookie);
		    	Cookie passCookie = new Cookie("password", ua.getPassword());
		    	passCookie.setMaxAge(7*86400);
		    	response.addCookie(passCookie);
		    }
						
			if(ua.getRole() == RoleType.ROLE_ADMIN) {
				 return "AdminHome";
			}
			else if(ua.getRole() == RoleType.ROLE_DOCTOR) {
				return "DoctorHome";
			}
			else if(ua.getRole() == RoleType.ROLE_NURSE) {
				return "NurseHome";
			}
			else if(ua.getRole() == RoleType.ROLE_PATIENT) {
				return "PatientHome";
			}
			else {
				return "BillingHome";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Login";
		}
		
	}
	
	@RequestMapping(value = "/login")
	public String homePage(Model model, HttpServletRequest request, HttpServletResponse response) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		
		Cookie loginCookie = null;
		Cookie passwordCookie = null;
        Cookie[] cookies = request.getCookies();
       
        if(cookies != null) {
        	for(Cookie cookie : cookies) {
        		if(cookie.getName().equals("username")) {
        			loginCookie = cookie;
        		}
        		if(cookie.getName().equals("password")) {
        			passwordCookie = cookie;
        		}
        	}
        }
        
        if(loginCookie != null) {
        	//loginCookie.setMaxAge();
        	response.addCookie(loginCookie);
        //	System.out.println("Found a cookie with value "+loginCookie.getValue());
        	model.addAttribute("username", loginCookie.getValue());
        	if(passwordCookie!=null){
        		response.addCookie(passwordCookie);
            	System.out.println("Found a cookie with value "+passwordCookie.getValue());
            	model.addAttribute("password", passwordCookie.getValue());
        	}
        }
//        else {
//        	System.out.println("Found no cookie in Login");
//        }
	
		return "Login";
	}
	
	@RequestMapping(value = "/registration")
	public String registration(Model model) {
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		RoleType[] roles = {RoleType.ROLE_DOCTOR, RoleType.ROLE_NURSE, RoleType.ROLE_PATIENT, RoleType.ROLE_BILLING}; 
		model.addAttribute("roles", roles);
		GenderType[] genders = Person.GenderType.values();
		model.addAttribute("genders", genders);
		return "Registration";
	}
	
	
	@RequestMapping(value = "/logout")
	public String logout(Model model,HttpServletRequest request) {	
		HttpSession session = request.getSession();
				
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		session.removeAttribute("user");
		session.invalidate();		
		return "";
	}	
	
	//for 403 access denied page

	@RequestMapping(value = "/403", method = RequestMethod.GET)

	public String accesssDenied(Model model, HttpServletRequest request, HttpServletResponse response) {

	//check if user is logged in

	HttpSession session = request.getSession();

	if (session.getAttribute("user") != null) {

	UserAccount curUser = (UserAccount)session.getAttribute("user");

	model.addAttribute("username", curUser.getUserName());

	}

	return "403";


	}
	
	
	
}
