package com.tank.clients;

import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.tank.bean.TankBean;
import com.tank.entity.Tank;

public class TankRestClient {

	public static final String REST_BASE_URI = "http://localhost:8080/SpringRestHibernate";
	
	static Logger logger = Logger.getLogger(TankRestClient.class);

	static RestTemplate restTemplate = new RestTemplate();
	
	public static void main(String args[]) {
		
		logger.debug("This is debug message");
        logger.info("This is info message");
        logger.warn("This is warn message");
        logger.fatal("This is fatal message");
        logger.error("This is error message");
        
     //   getAllTank();
        
       // Tank tank = getTankById(50000);
        
        TankBean tankBean = getTankBeanByTankId("191004001","101");
        
        System.out.println(1L);
        
        System.out.println(String.valueOf(tankBean));
		
		
		//addTank();
		//validateTank("satish","123");   
		//validateTank("chunnu","123"); 
		/* Tank tank= getTank(1);
		 
		 System.out.println("**** tank with id : " + 1 + "****");
			System.out.println(tank);*/
			/*System.out.println("Address :");
			System.out.println(tank.getAddresses());*/	
			}
	
	

	/** POST **/
	public static void addTank()
    {
    	
    	Tank tank= new Tank();
    	
    	tank.setCreatedBy(1L);
    	tank.setClientId("101");
    	tank.setTankId("191004001");
	    tank.setTankName("Mishra Ji");
	    tank.setWaterLevel(50);
	    tank.setPumpStatus(0);
	    System.out.println(tank);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        System.out.println("dfsdfsd");
        
        //headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
        HttpEntity<Tank> entity = new HttpEntity<>(tank,headers);
        System.out.println("Making request...");
        restTemplate.postForObject(REST_BASE_URI+"/addTank", entity,Tank.class);
        System.out.println("Success!!");
    }

	private static Tank getTankById(int id) {
		return restTemplate.getForObject(REST_BASE_URI + "/getTankById/" + id,
				Tank.class);
		
	}
	
	private static TankBean getTankBeanByTankId(String tankId,String clientId) {
		return restTemplate.getForObject(REST_BASE_URI + "/"+clientId+"/getTankBeanByTankId/" + tankId,
				TankBean.class);
		
	}
	
	/*	@SuppressWarnings("unused")
	private static void validateTank(String tankname, String password) {
		Tank tank = restTemplate.getForObject(REST_BASE_URI + "/login/" + tankname+"/"+password,
				Tank.class);
		if(tank==null){
			System.out.println("Wrong tankname or password!!!");
		}
		else{
			System.out.println(tank);
		}
		
	}
	public static void getAllTank() {

		System.out.println("In Tank client : getAllTanks");
		

		ResponseEntity<List<Tank>> response = restTemplate.exchange(REST_BASE_URI
				+ "/getAllTank", HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Tank>>() {
				});
		List<Tank> allTank = response.getBody();
		for (Tank tank : allTank) {
			System.out.println("TANK :" + tank.toString());

		}

	}*/
	
/*	public static void getAllStudents()
    {
       

        List<Map<String, Object>> studentList = restTemplate.getForObject(REST_BASE_URI + "/students", List.class);
        if (studentList != null)
        {
            System.out.println("**** All Students ****");
            for (Map<String, Object> map : studentList)
            {
                System.out.println("Id : id=" + map.get("id") + "   Name=" + map.get("name") + "   Age="
                        + map.get("age"));
            }
        } else
        {
            System.out.println("No Students exist!!");
        }
    }*/

	
	
}
