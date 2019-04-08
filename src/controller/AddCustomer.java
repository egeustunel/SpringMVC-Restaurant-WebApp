package controller;

import javax.servlet.http.HttpServletRequest;

import model.Customer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AddCustomer 
{
	@RequestMapping(value="/addCustomer", method = RequestMethod.GET)
	public String showHomePage(ModelMap map){
		map.addAttribute("customer",new Customer());	
		//map.addAttribute("message", "");
		return "addCustomer";
	}
	
	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	public String create(@ModelAttribute("customer") Customer customer, ModelMap map,HttpServletRequest request) 
	{
		if(customer.getUsername()==null || customer.getPassword()==null)
		{
			map.addAttribute("message", "enter username and password");
			return "addCustomer";
		}
		
		Customer c=new Customer(customer.getUsername(), customer.getPassword(), customer.getName(), customer.getSurname(), customer.getAddress());
		
		 map.addAttribute("currentOwner",request.getSession().getAttribute("currentOwner"));
		return "redirect:/ownerPage";
	}
	

}
