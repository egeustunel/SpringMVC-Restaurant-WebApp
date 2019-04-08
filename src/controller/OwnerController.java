package controller;

import javax.servlet.http.HttpServletRequest;

import model.Customer;
import model.Order;
import model.Product;
import model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class OwnerController {
	@RequestMapping(value="/ownerPage", method = RequestMethod.GET)
	public String showHomePage(ModelMap map, HttpServletRequest request){	
//		if(request.getSession().getAttribute("currentOwner")==null)
//			return "login";
		
		map.addAttribute("customerList",User.customerList);
		map.addAttribute("productList",Product.productList);
		map.addAttribute("orderList",Order.orderList);
		map.addAttribute("currentOwner",request.getSession().getAttribute("currentOwner"));
		//map.addAttribute("message", "");
		System.out.println("in");
		return "ownerPage";
		
	}
	@RequestMapping(value="/ownerPage", method = RequestMethod.POST)
	public String handleCustomer (@ModelAttribute("customer") Customer customer, ModelMap map,HttpServletRequest request){	
//		if(request.getSession().getAttribute("currentOwner")==null)
//			return "login";
		String uname = request.getParameter("username");
		Customer c=User.getCustomer(uname);
		
		if(request.getParameter("Delete")!=null)
		{
			for(int i=0;i<User.customerList.size();i++)
			{
				if(User.customerList.get(i).getUsername().equalsIgnoreCase(c.getUsername()))
					User.customerList.remove(i);
			}
			Customer.delCusDB(c);
			c.writeOnText();
			return "redirect:/ownerPage";
		}
			
		if(request.getParameter("Update")!=null)
		{
			map.addAttribute("customerForUp",c);
			return "CustomerUpdate";
		}
						
//		map.addAttribute("customerList",User.customerList);
//		map.addAttribute("currentOwner",request.getSession().getAttribute("currentOwner"));
//		//map.addAttribute("message", "");
//		System.out.println("in");
//		return "ownerPage";
		return "redirect:/ownerPage";
		
	}

}
