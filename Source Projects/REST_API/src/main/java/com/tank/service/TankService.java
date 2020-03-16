package com.tank.service;

import com.tank.entity.Tank;

public interface TankService {

	public void addTank(Tank tank);

	public Tank getTankById(long id, String clientId);
	
	public Tank getTankByTankId(String tankId,String clientId);

	//public List<User> getAllUser();

	public void updateTank(Tank tank);

	public void deleteTankById(long id);

	public int updatePumpStatus(String tankId, long actualPumpStatus);
	
	//public void deleteTankByTankId(long id);
	
	//public User validateUser(String username, String password) ;
}
