package controller;

import javax.servlet.http.HttpServletRequest;

import model.Customer;
import model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class CustomerController {
	@RequestMapping(value="/customerPage", method = RequestMethod.GET)	
	public String showCustomerPage(ModelMap map, HttpServletRequest request)
	{	
		map.addAttribute("currentCustomer",(Customer)request.getSession().getAttribute("currentCustomer"));
		return "customerPage";
	}
	
	@RequestMapping(value="/updateOwnInfo", method = RequestMethod.GET)
	public String showCustomerOwnUpdate(ModelMap map, HttpServletRequest request){	
		
		Customer c=(Customer)request.getSession().getAttribute("currentCustomer");
		map.addAttribute("customerForUp",c);
		return "updateOwnInfo";
		
	}
	
	@RequestMapping(value="/customerPage", method = RequestMethod.POST)
	public String handleCustomer (ModelMap map,HttpServletRequest request){	
		System.out.println("in post");
		Customer c=(Customer)request.getSession().getAttribute("currentCustomer");
		if(request.getParameter("Update")!=null)
		{
			map.addAttribute("customerForUp",c);
			return "updateCustomerInfo";
		}
		return "updateCustomerInfo";
		
	}
	
	@RequestMapping(value="/updateOwnInfo", method = RequestMethod.POST)
	public String handleCustomerOwnUpdate (@ModelAttribute("customerForUp") Customer customer,ModelMap map,HttpServletRequest request)
	{	
		System.out.println("in update info post");
		Customer c=(Customer)request.getSession().getAttribute("currentCustomer");
		Customer.updateCusDB(c, customer, true);
		c.setPassword(customer.getPassword());
		c.setName(customer.getName());
		c.setSurname(customer.getSurname());
		c.setAddress(customer.getAddress());
		c.writeOnText();
		
		return "redirect:/customerPage";
		
	}
	

}
