package com.creditcard.controller;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import com.creditcard.model.User;
import com.creditcard.service.CreditCardService;
import com.creditcard.service.UserService;
import com.creditcard.utility.CreditCardUtility;

@Controller
public class HomeController extends BaseController implements ServletContextAware{

	ServletContext servletContext;
	@Autowired private UserService userService;
	@Autowired private CreditCardUtility creditCardUtility;
	@Autowired private CreditCardService creditCardService;	
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView defaultPage(HttpServletRequest request) throws Exception{

		ModelAndView model = new ModelAndView("redirect:/login");
		
		Principal principal = request.getUserPrincipal();
		
		if(principal != null){
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				if(auth.getAuthorities().toString().contains("ROLE_ADMIN")){
					if(session().getAttribute("creditCardNumbers") == null || session().getAttribute("cardProcessed") != null){
						List<String> creditCardNumbers = creditCardService.findAllCreditCardNumber();
						session().setAttribute("creditCardNumbers", creditCardNumbers);
						session().setAttribute("userNameTitle", "Administrator : ");
					}
				}else{
					if(session().getAttribute("creditCardNumbers") == null || session().getAttribute("cardProcessed") != null){
						List<String> creditCardNumbers = creditCardService.findCreditCardNumberByUsername(principal.getName());
						session().setAttribute("creditCardNumbers", creditCardNumbers);
						session().setAttribute("userNameTitle", "User : ");
					}
				}
				
			model.setViewName("home");
		
		}	
		
		return model;
	}
	
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView register() throws Exception {

		ModelAndView model = new ModelAndView();
		model.setViewName("registration");

		return model;

	}
	
	@RequestMapping(value = "/processRegistration", method = {RequestMethod.GET,RequestMethod.POST})
	public String processRegistration(@ModelAttribute("userForm") User user, HttpServletRequest request, Map<String, Object> model) throws Exception {

		user.setPassword(creditCardUtility.passwordEncoder().encode(user.getPassword()));
		userService.saveNewUser(user);
		
		return "registrationSuccess";

	}
	

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout, HttpServletRequest request) throws Exception {
		
		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		
		String author = "- webApp author : Mauro Camelo -";
		model.addObject("author", author);
		model.setViewName("login");
		
		return model;

	}

	
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Username and password not valid";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Username and password not valid";
		}

		return error;
	}

	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;	
	}
	
}