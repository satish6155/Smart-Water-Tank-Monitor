package com.tank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tank.dao.TankDao;
import com.tank.entity.Tank;

@Service("tankService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class TankServiceImpl implements TankService {

	@Autowired
	private TankDao tankDao;

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addTank(Tank tank) {
		tankDao.addTank(tank);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Tank getTankById(long id,String clientId) {
		return tankDao.getTankById(id,clientId);
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public List<Tank> getAllTank() {
		return tankDao.getAllTank();
	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void updateTank(Tank tank) {
		tankDao.updateTank(tank);

	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteTankById(long id) {
		tankDao.deleteTankById(id);

	}

	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public Tank getTankByTankId(String tankId,String clientId) {
		return tankDao.getTankByTankId(tankId,clientId);
	}

	@Override
	public int updatePumpStatus(String tankId, long actualPumpStatus) {
			System.out.println("in service :"+actualPumpStatus);
		 return tankDao.updatePumpStatus(tankId,actualPumpStatus);
		
	}


	
}
