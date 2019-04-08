package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import model.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class OrderController 
{
//	@RequestMapping(value="/addProductToOrder", method = RequestMethod.GET)
//	public String addProToOrd(ModelMap map, HttpServletRequest request) throws IOException{
//		return "addProductToOrder";
//	}
	@RequestMapping(value="/createOrder", method = RequestMethod.GET)
	public String showCreateOrderPage(ModelMap map, HttpServletRequest request) throws IOException{
		map.addAttribute("productList",Product.productList);
//		Order o=new Order(true);
//		map.addAttribute("order",o);
//		System.out.println("NEW order id : "+o.getId());
	
		return "createOrder";
	}
	
	
	@RequestMapping(value="/createOrder", method = RequestMethod.POST)
//	public String handleAddProductToOrder (@ModelAttribute("order") Order order, ModelMap map,HttpServletRequest request)
	public String handleAddProductToOrder (ModelMap map,HttpServletRequest request)
	{
		boolean isThisOwner;
		Owner ow=null;
		Customer c=null;
		Order order;
		String name = request.getParameter("productName");
		Product p=Product.getProductFromName(name);
		try {
		if(request.getSession().getAttribute("currentOwner")!=null)
		{
			ow=(Owner)request.getSession().getAttribute("currentOwner");
			isThisOwner=true;
			order = new Order(ow.id, name);
		}
			
		else
		{
			c=(Customer)request.getSession().getAttribute("currentCustomer");
			isThisOwner=false;
			order = new Order(c.id, name);

		}
			
		
		
		
		

		order.addProduct(p);
			

		
			if(isThisOwner)
			{				
				
//				System.out.println("current owner id : "+ ow.id);
//				order.setUserID(ow.id);
				return"redirect:/ownerPage";
			}
				
			else
			{
				
//				System.out.println("current cust id : "+ c.id);
				c.getOrderList().add(order);
//				order.setUserID(c.id);
				map.addAttribute("currentCustomer",c);
				return"redirect:/customerPage";
			}
			
				
			
//		}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			

				return"login";

		}
		
	}
	@RequestMapping(value = "orderHandle", method = RequestMethod.POST)
	public String orderHandle(@ModelAttribute("order") Order order, ModelMap map,HttpServletRequest request) throws IOException 
	{
//		System.out.println("order handle");
		int orderId=Integer.parseInt(request.getParameter("orderId"));
		Order o=null;
		for(int i=0;i<Order.orderList.size();i++)
		{
			if(Order.orderList.get(i).getId()==orderId)
			{
				o=Order.orderList.get(i);				
				break;
			}
		}
		if(request.getParameter("UpdateStatus")!=null)
		{
			Order.updateOrderDB(o);
			o.setStatus("sent");
			if(request.getSession().getAttribute("currentOwner")!=null)
				return"redirect:/ownerPage";
			else
				return"redirect:/customerPage";
		}
		
		if(request.getParameter("AddProduct")!=null)
		{
			map.addAttribute("order", o);
			map.addAttribute("productList",Product.productList);
			return "addProductToOrder";
		}
		if(request.getSession().getAttribute("currentOwner")!=null)
			return"redirect:/ownerPage";
		else
			return"redirect:/customerPage";
		
	}
	@RequestMapping(value = "/addProductToOrder", method = RequestMethod.POST)
	public String addProductToOrder(@ModelAttribute("order") Order order, ModelMap map,HttpServletRequest request) throws IOException 
	{
		String name = request.getParameter("productName");
		Product p=Product.getProductFromName(name);
		int orderId=Integer.parseInt(request.getParameter("orderId"));
		Order o=null;
		for(int i=0;i<Order.orderList.size();i++)
		{
			if(Order.orderList.get(i).getId()==orderId)
			{
				o=Order.orderList.get(i);				
				break;
			}
		}
		
		o.getProductList().add(p);
		if(request.getSession().getAttribute("currentOwner")!=null)
			return"redirect:/ownerPage";
		else
			return"redirect:/customerPage";
	}
	
	

}
