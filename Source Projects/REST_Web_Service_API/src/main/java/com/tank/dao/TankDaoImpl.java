package com.tank.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tank.entity.Tank;

@Repository("tankDao")
public class TankDaoImpl implements TankDao {

	static Logger logger = Logger.getLogger(TankDaoImpl.class);
	
	private static String PUMP_STATUS= "UPDATE Tank SET pumpStatus =:actualPumpStatus WHERE tankId = :tankId";
	
	@PersistenceContext
	private EntityManager entityManager;

	public TankDaoImpl() {
		super();
	}

	@Override
	public void addTank(Tank tank) {
		entityManager.persist(tank);
	}

	@Override
	public Tank getTankById(long id,String clientId) {
		//return entityManager.find(Tank.class, id);
		Tank tank = null;
		try {
			tank = (Tank) entityManager.createQuery(
							"select tanks from Tank tanks where tanks.id= :id and tanks.clientId= :clientId")
					.setParameter("id", id).setParameter("clientId", clientId).getSingleResult();
			
		} catch (Exception e) {
			System.out.println("WATER TANK : Exception in daoimpl getTankByTankId method: "+e);
			logger.error("WATER TANK : Exception in daoimpl getTankById method: for clientId "+clientId+" and Id "+id+" : "+e);
			
		}
		return tank;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tank> getAllTank() {
		return entityManager.createNativeQuery("select * from Tank")
				.getResultList();
	}

	@Override
	public void updateTank(Tank tank) {
		System.out.println("In Tank DAO : updateTank"+ tank.toString());
		entityManager.merge(tank);

	}

	@Override
	public void deleteTankById(long id) {
		Tank tank = entityManager.find(Tank.class, id);
		entityManager.remove(tank);

	}

	@Override
	public Tank getTankByTankId(String tankId,String clientId) {
		
		Tank tank = null;
		try {
			tank = (Tank) entityManager.createQuery(
							"select tanks from Tank tanks where tanks.tankId= :tankId and tanks.clientId= :clientId")
					.setParameter("tankId", tankId).setParameter("clientId", clientId).getSingleResult();
			
		} catch (Exception e) {
			System.out.println("WATER TANK : Exception in daoimpl getTankByTankId method: "+e);
			logger.error("WATER TANK : Exception in daoimpl getTankByTankId method: for clientId "+clientId+" and tankId "+tankId+" : "+e);
			
		}
		return tank;
	}

	@Override
	@Transactional
	public int updatePumpStatus(String tankId, long actualPumpStatus) {
		
		System.out.println("in dao "+actualPumpStatus);
		try {
			System.out.println("in truy");
			return entityManager.createQuery(PUMP_STATUS).setParameter("actualPumpStatus", actualPumpStatus).setParameter("tankId", tankId).executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("excepton a gyi"+e.getStackTrace());
			logger.error("WATER TANK : Exception in updatePumpStatus method of daoimpl class : for tankId "+tankId+" : "+e);
			return 0;
		}
	}

}
