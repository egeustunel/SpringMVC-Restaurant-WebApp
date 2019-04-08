package Service;


import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import model.Owner;
import model.User;

@RestController
public class myService {
//	@RequestMapping(value="/home", method = RequestMethod.GET)
//	public String showHomePage(ModelMap map){
//		map.addAttribute("owner",new Owner());	
//		//map.addAttribute("message", "");
//		return "home";
//	}
//
//	@RequestMapping(value = "/home", method = RequestMethod.POST)
//	public String create(@ModelAttribute("owner") Owner owner, ModelMap map) 
//	{
//		//BURADA PASS YA DA USERNAME IN BOS OLUP OLMADIGI DENETLENMIYOR
//		if(User.isUserNameValid(owner.getUsername()))
//		{
//			//create new owner with this models attr.s for add this into the DB
//			//the model is only a object not in the database(not in arraylist or owner.txt)
//			new Owner(owner.getUsername(),owner.getPassword());
//			map.addAttribute("message","done!");
//			return "redirect:/login";
//			
//		//	return "home";
//		}
//		//username is not valid
//		else
//		{
//			System.out.println(owner.getUsername()+ " is not added");
//			for(Owner o :Owner.ownerList)
//			System.out.println(o.getUsername());
//			map.addAttribute("message","try new username!");
//			return "home";
//		}
//			
//	}
	
	

}
