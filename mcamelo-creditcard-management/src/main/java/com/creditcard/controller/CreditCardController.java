package com.creditcard.controller;

import java.security.Principal;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import com.creditcard.form.CredicardForm;
import com.creditcard.model.Creditcard;
import com.creditcard.service.CreditCardService;

@Controller
public class CreditCardController extends BaseController implements ServletContextAware{

	ServletContext servletContext;
	@Autowired private CreditCardService creditCardService;
	

	
	@RequestMapping(value = "/processCreditCard", method = {RequestMethod.GET,RequestMethod.POST})
	public String processCreditCard(@ModelAttribute("creditCardForm") CredicardForm credicardForm, HttpServletRequest request, Model model) throws Exception {
	
		String redirect = "";
		
		try {
			creditCardService.saveCreditCard(credicardForm, request);
			session().setAttribute("cardProcessed", "ok");
			redirect = "redirect:/";
		} catch (Exception e) {
			redirect = "redirect:/duplicatedCreditNumber";
		}
		
		return redirect;

	}
	
	@RequestMapping(value = "/duplicatedCreditNumber", method = RequestMethod.GET)
	public ModelAndView register() throws Exception {

		ModelAndView model = new ModelAndView();
		model.setViewName("duplicatedCreditNumber");

		return model;

	}
	

	@RequestMapping(value = "/findCreditCard", method = RequestMethod.GET)
	public ModelAndView findCreditCard(HttpServletRequest request) throws Exception {

		ModelAndView model = new ModelAndView();
		
		Principal principal = request.getUserPrincipal();
		String view = "";
		
		if(principal != null){
			view = "findCreditCard";
		}else{
			view = "/login";
		}
			
		model.setViewName(view);

		return model;

	}
	
	@RequestMapping(value = "/displayCreditCard")
	public String displayCreditCard(@RequestParam("creditCardnumber") String creditCardnumber, Model model) throws Exception {
		
		Creditcard creditcard = creditCardService.getCreditCardInfo(creditCardnumber);
		
		model.addAttribute("cardNumber", creditcard.getId().getNumber());
		model.addAttribute("cvv", creditcard.getCvv());
		model.addAttribute("cardName", creditcard.getCardName());
		model.addAttribute("expirationDate", creditcard.getExpiryDate());
		
		return "/findCreditCard";

	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;	
	}
	
}