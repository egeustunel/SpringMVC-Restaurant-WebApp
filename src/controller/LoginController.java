package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import model.Customer;
import model.Order;
import model.Owner;
import model.Product;
import model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController 
{
	@RequestMapping(value={"/","/login"}, method = RequestMethod.GET)
	public String showHomePage(ModelMap map) throws IOException{
		map.addAttribute("user",new User());	
		//map.addAttribute("message", "");
		//new File("owners.txt");
		File f = new File(User.customersPath);
		f.getParentFile().mkdirs(); 
		f.createNewFile();
		File f2=new File(User.ownersPath);
		f2.getParentFile().mkdirs(); 
		f2.createNewFile();
		File f3=new File(Product.productsPath);
		f3.getParentFile().mkdirs(); 
		f3.createNewFile();
		File f4=new File(Order.ordersPath);
		f4.getParentFile().mkdirs(); 
		f4.createNewFile();
		
		
		User.readFromText(true);
		User.readFromText(false);
		Product.readFromText();
		Order.readFromText();
		return "login";
	}
	
	
	
	@RequestMapping(value = {"/login","/"}, method = RequestMethod.POST)
	public String login(@ModelAttribute("user") User user, ModelMap map,HttpServletRequest request) 
	{
	
		User u=User.getUser(user.getUsername());

		
		if(u==null)
		{
			map.addAttribute("message", "incorrect username!");
			return "redirect:/login";
		}
		//password control, if not match
		if(!user.getPassword().equals(u.getPassword()))
		{
			map.addAttribute("message", "incorrect password!");
			return "redirect:/login";
		}
		else
		{
			Owner o=User.getOwner(user.getUsername());
			Customer c=User.getCustomer(user.getUsername());
			if(o!=null)
			{
				
				map.addAttribute("owner",o);
				request.getSession().setAttribute("currentOwner", o);
				System.out.println("login owner id  : "+o.id);
				request.getSession().setAttribute("currentCustomer", null);
				return "redirect:/ownerPage";
			}
			else
			{
				request.getSession().setAttribute("currentCustomer", c);
				request.getSession().setAttribute("currentOwner", null);
				map.addAttribute("customer", c);
				return "customerPage";
			}
			
			
		}
		
		
		
		
		
	}

}
