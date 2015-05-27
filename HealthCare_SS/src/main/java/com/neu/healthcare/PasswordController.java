package com.neu.healthcare;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neu.healthcare.dao.UserDao;
import com.neu.healthcare.model.UserAccount;

@Controller
public class PasswordController {
	
	@Autowired
	@Qualifier("passwordValidator")
	private Validator validator;
	
	@Autowired
	private UserDao userDao;
	
	@InitBinder
	private void initBinder (WebDataBinder binder){
		binder.setValidator(validator);
	}
	
	@RequestMapping(value = "/changePassword")
	public String changePassword(Model model, HttpServletRequest request) {	
		UserAccount userAccount = new UserAccount();
		model.addAttribute("userAccount", userAccount);
		return "ChangePassword";
	}	
	
	@RequestMapping(value = "/confirmPasswordChange")
	public String confirmPasswordChange(Model model, @Validated UserAccount userAccount, BindingResult result, HttpServletRequest request) {	
		HttpSession session = request.getSession();
		UserAccount curUser = (UserAccount)session.getAttribute("user");
		
		if (result.hasErrors()){
			model.addAttribute("userAccount", userAccount);
			return "ChangePassword";
		}
		
		String oldPassword = request.getParameter("oldPassword");
		if(oldPassword.equals(curUser.getPassword())) {
			curUser.setPassword(userAccount.getPassword());
			curUser.setConfirmPassword(userAccount.getConfirmPassword());
			try{
				userDao.updateUserAccount(curUser);
				model.addAttribute("error", 0);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//	return "loginError";
			}
		}
		else {
			model.addAttribute("error", 1);
		}
		
		return "ChangePasswordComplete";
	}	

}
