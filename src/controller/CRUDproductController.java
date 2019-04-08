package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import model.Customer;
import model.Product;
import model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CRUDproductController {
	@RequestMapping(value="/addProduct", method = RequestMethod.GET)
	public String showAddProductPage(ModelMap map, HttpServletRequest request)
	{
		map.addAttribute("product",new Product());	
		//map.addAttribute("message", "");
		return "addProduct";
		
	}
	
	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public String create(@ModelAttribute("product") Product product, ModelMap map,HttpServletRequest request) throws IOException 
	{
		if(product.getName()==null || product.getPrice()==null)
		{
			map.addAttribute("message", "enter name and price");
			return "addProduct";
		}
		Product p=new Product(product.getName(), product.getPrice());		
		
		
		return "redirect:/ownerPage";
	}
	@RequestMapping(value = "crud", method = RequestMethod.POST)
	public String crud(@ModelAttribute("product") Product product, ModelMap map,HttpServletRequest request) throws IOException 
	{
//		System.out.println("in crudpost");
		
		
		String name = request.getParameter("productName");

		//isaret edilen product bu
		Product p=Product.getProductFromName(name);
		if(request.getParameter("Delete")!=null)
		{
			for(int i=0;i<Product.productList.size();i++)
			{
				if(Product.productList.get(i).getName().equalsIgnoreCase(name))
					Product.productList.remove(i);
				
			}
			Product.deletePro(p);
		p.writeOnText();
			return "redirect:/ownerPage";
		}
		if(request.getParameter("Update")!=null)
		{
			map.addAttribute("product",p);
			return "productUpdate";
		}
		
		return"redirect:/ownerPage";
	}
	@RequestMapping(value = "/productUpdate", method = RequestMethod.POST)
	public String updateProduct(@ModelAttribute("product") Product product, ModelMap map,HttpServletRequest request) throws IOException 
	{	

		String name=request.getParameter("name");
		//get before element

		if(product.getName()==null || product.getPrice()==null)
		{
			map.addAttribute("message", "enter name and price");
			return "productUpdate";
		}
	//	Product p=new Product(product.getName(), product.getPrice());		
		
		
		for(int i=0;i<Product.productList.size();i++)
		{
			if(Product.productList.get(i).getName().equalsIgnoreCase(name))
			{
				Product pro=Product.productList.get(i);
//				pro.setName(product.getName());
				pro.setPrice(product.getPrice());
				Product.updatePro(pro, product.getPrice());
				pro.writeOnText();
				System.out.println("pro name: "+ pro.getName());
				break;
			}
				
		}
		
		return "redirect:/ownerPage";
	}
	

}
