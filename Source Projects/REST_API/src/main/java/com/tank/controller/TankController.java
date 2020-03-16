package com.tank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tank.bean.TankBean;
import com.tank.entity.Tank;
import com.tank.service.TankService;


@RestController
public class TankController {
	
	@Autowired
	private TankService tankServiceImpl;	
	
	@RequestMapping(value="/addTank", method=RequestMethod.POST, 
            produces="application/json", consumes="application/json")
    public Tank createTank(@RequestBody Tank tank)
    {
		System.out.println("In controller");
		System.out.println(tank);
		tankServiceImpl.addTank(tank);
		System.out.println("In controller after persisting");
		System.out.println(tank);
		return tank;
    }
/*	@RequestMapping(value = "/getAllTanks", produces = "application/json", method = RequestMethod.GET)
	public List<Tank> getAllTanks() {

		List<Tank> tanks = new ArrayList<>();

		tanks = tankServiceImpl.getAllTank();
		System.out.println("In USER controller : getAllTanks ");
		for (Tank tank : tanks){
			System.out.println("tank"+tank.toString());
		}
		
		return tanks;
	}*/
		
	@RequestMapping(value="/{clientId}/getTankById/{id}",produces="application/json",
            method=RequestMethod.GET)
    public @ResponseBody TankBean getTankById(@PathVariable("clientId") String clientId,@PathVariable("id") long id)
    {
		System.out.println("In controller getTank ID: "+id+" clientId: "+clientId);
		Tank tank = tankServiceImpl.getTankById(id,clientId);
		System.out.println("In controller getTank, Tank : "+ tank );
		TankBean tankBean = null;
		if(null!=tank){
			System.out.println("tank"+tank.toString());
			tankBean = new TankBean(tank);
			tankBean.setWaterLevel(100-tankBean.getWaterLevel());
			System.out.println("tankBean"+tankBean.toString());
		}	
        return tankBean;
    }
	
	@RequestMapping(value="/{clientId}/getTankBeanByTankId/{tankId}",produces="application/json",
            method=RequestMethod.GET)
    public TankBean getTankBeanByTankId(@PathVariable("clientId") String clientId,@PathVariable("tankId") String tankId)
    {
		System.out.println("In controller getTankByTankId tankId: "+tankId+" clientId: "+clientId);	
		
		Tank tank = new Tank();
		tank = tankServiceImpl.getTankByTankId(tankId,clientId);
		TankBean tankBean = null;
		if(null!=tank){
			System.out.println("tank"+tank.toString());
			tankBean = new TankBean(tank);
			System.out.println("tankBean"+tankBean.toString());
		}	
		//tankBean.setWaterLevel(100-tankBean.getWaterLevel());
        return tankBean;
    }
	@RequestMapping(value="/{clientId}/updateTankByTankId/{tankId}/{pumpStatus}/{waterDepth}",produces="application/json",
            method=RequestMethod.GET)
    public String updateTankByTankId(@PathVariable("clientId") String clientId,@PathVariable("tankId") String tankId,
    							@PathVariable("pumpStatus") int pumpStatus,@PathVariable("waterDepth") int waterDepth)
    {
		System.out.println("In controller updateTankByTankId tankId: "+tankId+" clientId: "+clientId+" pumpStatus: "+pumpStatus+" waterDepth: "+waterDepth);	
		
		Tank tank = new Tank();
		tank = tankServiceImpl.getTankByTankId(tankId,clientId);
		
		if(null!=tank){
			System.out.println("tank"+tank.toString());
			tank.setPumpStatus(pumpStatus);
			tank.setWaterLevel(waterDepth);
			tankServiceImpl.updateTank(tank);
		}	
        return "1";
    }
	/*@RequestMapping(value = "/{clientId}/updateTank/", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public Tank updateTank(@PathVariable("clientId") String clientId,@RequestBody Tank tank) {

		System.out.println("In Tank controller : updateTank ");
		tankServiceImpl.updateTank(tank);
		return tank;
	}*/
	
/*	@RequestMapping(value="/updatePumpStatus",produces="application/json",method=RequestMethod.POST)
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
    }*/
	
	//////////////////////////Not used methods below------------------------------------------
	/*@RequestMapping(value="/addTankApp", method=RequestMethod.POST, 
            produces="application/json", consumes="application/json")
    public Tank createTankApp(@RequestBody Tank tank)
    {
		System.out.println("In request :"+tank.toString());
		tankServiceImpl.addTank(tank);
		
		System.out.println("Out response :"+tank.toString());
		return tank;		
    }
	@RequestMapping(value="/appDummy", method=RequestMethod.POST, 
            produces="application/json", consumes="application/json")
    public String createTankApp(@RequestBody String request)
    {
		System.out.println("In request :"+request);
		
		return "yo yo";		
    }
	@RequestMapping(value="/test",produces="application/json",
            method=RequestMethod.GET)
    public String testConnection()
    {
		System.out.println("Request is successfull");
		return "yo";
    }*/
	
	
	
	/////////////////////////////////////////////////////////////
	
	

}
