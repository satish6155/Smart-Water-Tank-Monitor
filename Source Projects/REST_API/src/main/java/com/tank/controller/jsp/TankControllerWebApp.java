package com.tank.controller.jsp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tank.entity.Tank;
import com.tank.service.TankService;

@Controller
public class TankControllerWebApp {
	
	@Autowired
	private TankService tankServiceImpl;
	
	
	@RequestMapping(value="/{clientId}/getTankJspController/{tankId}",produces="application/json")
	public ModelAndView tankJspController(ModelAndView model,@PathVariable("clientId") String clientId,@PathVariable("tankId") String tankId) {
		
		System.out.println("In controller TankControllerWebApp method tankJspController, tankId: "+tankId+" clientId: "+clientId);
		Tank tank = new Tank();
		tank = tankServiceImpl.getTankByTankId(tankId,clientId);
		tank.setWaterLevel(100-tank.getWaterLevel());
		
		if(null!=tank){
			model.addObject("tank",tank);			
			model.setViewName("WaterTank");
			return model;
		}
		return new ModelAndView("ErrorPage");	    
	}
	@RequestMapping(value="/updatePumpStatus",produces="application/json",method=RequestMethod.POST)
    public String updatePumpStatus(String tankId,boolean pumpStatus)
    {
		long actualPumpStatus = 0 ;
		System.out.println("In updatePumpStatus");
		System.out.println("tankId :"+tankId);
		System.out.println("pumpStatus : "+pumpStatus);
		
		if(pumpStatus){
			actualPumpStatus = 1;
		}
		
		System.out.println("In actualPumpStatus"+actualPumpStatus);
		return Integer.toString(tankServiceImpl.updatePumpStatus(tankId,actualPumpStatus));
    }
	
	
}