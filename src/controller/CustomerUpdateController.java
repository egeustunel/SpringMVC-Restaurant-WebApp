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
public class CustomerUpdateController {
	
	@RequestMapping(value="/CustomerUpdate", method = RequestMethod.GET)
	public String showCustomerUpdate(ModelMap map, HttpServletRequest request){	

		return "CustomerUpdate";
		
	}

	
	@RequestMapping(value="/CustomerUpdate", method = RequestMethod.POST)
	public String handleCustomer (@ModelAttribute("customerForUp") Customer customer, ModelMap map,HttpServletRequest request){	
		
		String uname=request.getParameter("id");

		for(int i=0;i<User.customerList.size();i++)
		{
			if(User.customerList.get(i).getUsername().equals( uname))
			{
				
				Customer c=User.customerList.get(i);
				Customer.updateCusDB(c, customer, false);
				c.setUsername(customer.getUsername());
				c.setPassword(customer.getPassword());
				c.setName(customer.getName());
				c.setSurname(customer.getSurname());
				c.setAddress(customer.getAddress());
				c.writeOnText();
				break;				
			}

		}
		
		return "redirect:/ownerPage";
		
	}

}
