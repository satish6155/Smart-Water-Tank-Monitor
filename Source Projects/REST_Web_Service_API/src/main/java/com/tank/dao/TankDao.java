package com.tank.dao;

import java.util.List;

import com.tank.entity.Tank;


public interface TankDao {
	
	public void addTank(Tank Tank);
	
	public Tank getTankById(long id, String clientId);
	
	public Tank getTankByTankId(String tankId,String clientId);
	
	public List<Tank> getAllTank();
	
	public void updateTank(Tank Tank);
	
	public void deleteTankById(long id);

	public int updatePumpStatus(String tankId, long actualPumpStatus);


}
