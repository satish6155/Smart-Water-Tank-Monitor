<%@ page language="java" contentType="text/html; charset=ISO-8859-1"

    pageEncoding="ISO-8859-1"%>

     <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
     <%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<html>
<head>
<title>Water Tank</title>
<link href="https://fonts.googleapis.com/css?family=Merienda+One" rel="stylesheet">
 <link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link href="https://gitcdn.github.io/bootstrap-toggle/2.2.2/css/bootstrap-toggle.min.css" rel="stylesheet">
<script src="https://gitcdn.github.io/bootstrap-toggle/2.2.2/js/bootstrap-toggle.min.js"></script> 
<style>
@import url('https://fonts.googleapis.com/css?family=Calligraffitti|Open+Sans');

html, body {
  height: 98%;
  background-color: #D0F0FF;
}

body {
   display: flex;
 /*  justify-content: center;*/
  align-items: center; 
   flex-direction: column; 
}
.header {
	color: blue;
	padding:8px;
	margin-below:10px;
	font-family: 'Merienda One', sans-serif;
	font-size: 220%;
	/* background-color: gray; */
	text-align:center;
}
 hr { background-color: #ABB2B9; height: 1px; margin:-3px; } 
h1 {
  font-family: 'Calligraffitti';
}

h3 {
  font-family: 'Open Sans';
  margin-left:170px;
  margin-top:50px;
  padding-left:40px;
  text-align:right;
}
kp{
margin:18px;
 font-size:120%;
 color:#41E741  ;
}

.sea {
  border: 3px dashed #000;
}

.LightWaves {
  animation: lightwaves 2s infinite;
  position: relative;
}

@keyframes lightwaves {
    0%,100% { transform: translate(0,0); }
    25%     { transform: translate(5px,5px); transform: scale(1.05); }
    50%     { transform: translate(25px, 5px); }
    75%     { transform: translate(12px,10px); transform: scale(1.05); }
}

.DarkWaves {
  animation: darkwaves 3.8s infinite;
}

@keyframes darkwaves {
    0%,100% { transform: translate(0,0); }
    25%     { transform: translate(25px, 5px); transform: scale(1.05); }
    50%     { transform: translate(12px,10px); }
    75%     { transform: translate(5px,5px); }
}

.WhiteWaves {
  animation: whitewaves 4s alternate ease infinite;
}

@keyframes whitewaves {
    0% { transform: translate(0,25px); }
    100%     { transform: translate(25px,35px); }
}
</style>
<script>
var toggleCurrStatus=0;
$(document).ready(function() {
	
	toggleCurrStatus = ${tank.pumpStatus};
	//alert('${tank.pumpStatus} :'+${tank.pumpStatus});
	//alert("toggleCurrStatus :"+toggleCurrStatus);
	changePumpToggle(${tank.pumpStatus});
	//alert('${tank.pumpStatus} :'+${tank.pumpStatus});
	//	alert("toggleCurrStatus :"+toggleCurrStatus);
	animateValue("waterLevel",0,${tank.waterLevel},2000);
	
	setInterval(autoRefresh, 3000);
});
  
  $(function() {
	    $('#toggle-one').change(function() {
	    	 /* ajax for chnaging status in db */
	    	// alert('Inside toggle on change');
	    	 $.ajax({
	    	     url: "${pageContext.request.contextPath}/updatePumpStatus",
	    	      type: "post",
	    	      data: {
	    			    tankId : ${tank.tankId},
	    			    pumpStatus : $(this).prop('checked')
	    		 }, 
	    	     success: function(response) {
	    	    	// alert ('toggle response: '+response);
	    	    	 if(response!="1"){
	    	    		 alert("Some error occured, toggle not updated in database, please try to refresh!!")
	    	    	 }
	    	     }
	    	
	    	     });
	    })
	  });
  
  function checkPumpToggleStatus(newStatus){
	  
	//  alert ('toggleCurrStatus : newStatus: '+toggleCurrStatus+" : "+ newStatus);	  
	  if(toggleCurrStatus!=newStatus){
		//  alert('inside if toggleCurrStatus!=newStatus')
		  changePumpToggle(newStatus);
	  } 
	  
  }
  function changePumpToggle(pumpStatus){
	 // alert('Inside changePumpToggle, pumpStatus :'+pumpStatus);
	  if(pumpStatus==0){
		 // alert('Inside changePumpToggle, if(pumpStatus==0)');
			 $('#toggle-one').bootstrapToggle('off');
			 toggleCurrStatus=0;
		}
		else if(pumpStatus==1){
			//alert('Inside changePumpToggle, if(pumpStatus==1)');
			 $('#toggle-one').bootstrapToggle('on');
	  toggleCurrStatus=1;
		}
  }
 
  function refresh() {
	 /*  alert('Refresh Button Pressed : ${tank.id} ${tank.tankId} ${tank.clientId} ${tank.waterLevel} ${tank.pumpStatus}'); */
	   /* ajax for getting latest data from db by hitting method getTankById of class TankController*/
	   
	  $.ajax({
 	     url: "${pageContext.request.contextPath}/${tank.clientId}/getTankById/${tank.id}",
 	      type: "get",
 	     success: function(response) {
 	    	changePumpToggle(response.pumpStatus); 
 	    	animateValue("waterLevel",0,response.waterLevel,2000);
 	     }
 	     });
	  }
  function autoRefresh() {
		  // alert('Inside auto Refresh '); 
		   /* ajax for getting latest data from db by hitting method getTankById of class TankController*/
		   
		  // var currToggleStatus = $('#toggle-one').bootstrapSwitch('toggleState'); ; // $(this).prop('checked');
	//	 alert ('toggleCurrStatus: '+toggleCurrStatus);
		  $.ajax({
	 	     url: "${pageContext.request.contextPath}/${tank.clientId}/getTankById/${tank.id}",
	 	      type: "get",
	 	     success: function(response) {
	 	    	 	    	
	 	    	
	 	    	checkPumpToggleStatus(response.pumpStatus); 
	 	    	
	 	    	var obj = document.getElementById("waterLevel"); 
	 	    	
	 	    	var current = response.waterLevel;
	 	    	if(current>=50){
		        	obj.innerHTML = "<span style='color:#41E741;'>"+current+"%</span>"; 
		        }
		        else if(current <50  &&  current >=30 ){
		        	obj.innerHTML = "<span style='color:#FFC300;'>"+current+"%</span>"; 
		        }
		        else {
		        	obj.innerHTML = "<span style='color:red;'>"+current+"%</span>"; 
		        }
	 	     }
	 	     });
		  }
 
  function animateValue(id, start, end, duration) {
	    var range = end - start;
	    var current = start;
	    var increment = end > start? 1 : -1;
	    var stepTime = Math.abs(Math.floor(duration / range));
	    var obj = document.getElementById(id);
	    var timer = setInterval(function() {
	        current += increment;
	        if(current>=50){
	        	obj.innerHTML = "<span style='color:#41E741;'>"+current+"%</span>"; 
	        }
	        else if(current <50  &&  current >=30 ){
	        	obj.innerHTML = "<span style='color:#FFC300;'>"+current+"%</span>"; 
	        }
	        else {
	        	obj.innerHTML = "<span style='color:red;'>"+current+"%</span>"; 
	        }
	        
	        if (current == end) {
	            clearInterval(timer);
	        }
	    }, stepTime);
	}

	
	  
</script>
</head>
<body>
<div class="header">
		<b>My Tank Status</b>
		<hr><br>
		<%-- <!-- <kp> --><c:out value="${tank.waterLevel}%" /><!-- %</kp> --> --%>
		<kp id="waterLevel" ><%-- ${tank.waterLevel}% --%></kp>
	
</div>
<svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" width="98%"
	 height="50%" viewBox="0 0 800 350" style="enable-background:new 0 0 800 400;">  <!-- xml:space="preserve" -->
	 
<style type="text/css">
<![CDATA[
	.st0{opacity:0.76;}
	.st1{fill:url(#SVGID_1_);}
	.st2{fill:url(#SVGID_2_);}
	.st3{fill:url(#SVGID_3_);}
	.st4{fill:none;stroke:#FFFFFF;stroke-width:4;stroke-miterlimit:10;}
]]>
</style>
<g id="Sky" class="sky">
	<radialGradient id="SVGID_1_" cx="400" cy="202.5" r="317.3423" gradientUnits="userSpaceOnUse">
		<stop  offset="0" style="stop-color:#E6DBFA"/>
		<stop  offset="3.659654e-002" style="stop-color:#E5DCFA"/>
		<stop  offset="0.5708" style="stop-color:#D5EBFE"/>
		<stop  offset="1" style="stop-color:#D0F0FF"/>
	</radialGradient>
	<rect y="-1" class="st1" width="800" height="407"/>
</g>
<g id="DarkWaves" class="DarkWaves">
	<g>
		<linearGradient id="SVGID_2_" gradientUnits="userSpaceOnUse" x1="400.0005" y1="602" x2="400.0005" y2="202.3545">
			<stop  offset="0" style="stop-color:#0000FF"/>
			<stop  offset="0.1789" style="stop-color:#0020EC"/>
			<stop  offset="0.4149" style="stop-color:#0043D7"/>
			<stop  offset="0.6374" style="stop-color:#005CC8"/>
			<stop  offset="0.8386" style="stop-color:#006CBF"/>
			<stop  offset="1" style="stop-color:#0071BC"/>
		</linearGradient>
		<path class="st2" d="M761.8,202.4c-24.7,0-36.7,7.2-49.5,14.7c-13.2,7.8-26.8,15.9-53.8,15.9c-27,0-40.6-8.1-53.8-15.9
			c-12.8-7.6-24.9-14.7-49.6-14.7c-24.7,0-36.8,7.2-49.6,14.7c-13.2,7.8-26.8,15.9-53.8,15.9c-27,0-40.7-8.1-53.8-15.9
			c-12.8-7.6-24.9-14.7-49.6-14.7c-24.7,0-36.8,7.2-49.6,14.7c-13.2,7.8-26.8,15.9-53.8,15.9c-27,0-40.7-8.1-53.8-15.9
			c-12.8-7.6-24.9-14.7-49.6-14.7c-24.7,0-36.8,7.2-49.6,14.7C78.7,224.9,65,232.9,38,232.9c-27,0-40.4-8.1-53.6-15.9
			c-12.1-7.1-24.3-13.9-45.4-14.6V602h922V232.9c-22.6-0.8-37.2-8.4-49.7-15.8C798.5,209.5,786.5,202.4,761.8,202.4z"/>
	</g>
</g>
<g id="LightWaves" class="LightWaves">
	<g>
		<linearGradient id="SVGID_3_" gradientUnits="userSpaceOnUse" x1="400.0005" y1="600" x2="400.0005" y2="214.3545">
			<stop  offset="0" style="stop-color:#0000FF"/>
			<stop  offset="0.1643" style="stop-color:#0B2CF7"/>
			<stop  offset="0.3574" style="stop-color:#1559F0"/>
			<stop  offset="0.5431" style="stop-color:#1E7DEA"/>
			<stop  offset="0.7168" style="stop-color:#2496E6"/>
			<stop  offset="0.874" style="stop-color:#28A6E3"/>
			<stop  offset="1" style="stop-color:#29ABE2"/>
		</linearGradient>
		<path class="st3" d="M750.9,229.8c-14.8-7.9-28.7-15.4-57.2-15.4c-28.5,0-42.4,7.5-57.2,15.4c-15.2,8.2-30.9,16.6-62.1,16.6
			s-46.9-8.4-62.1-16.6c-14.8-7.9-28.7-15.4-57.2-15.4c-28.5,0-42.4,7.5-57.2,15.4c-15.2,8.2-30.9,16.6-62.1,16.6
			c-31.2,0-46.9-8.4-62.1-16.6c-14.8-7.9-28.7-15.4-57.2-15.4c-28.5,0-42.4,7.5-57.2,15.4c-15.2,8.2-30.9,16.6-62.1,16.6
			c-31.2,0-46.9-8.4-62.1-16.6c-14.8-7.9-28.9-15.4-57.3-15.4c-16.9,0-28.8,2.6-38.8,6.4V600h922V237c-12,5.3-26,9.4-47.8,9.4
			C782.1,246.4,766.1,237.9,750.9,229.8z"/>
	</g>
</g>
<g id="WhiteWaves" class="WhiteWaves">
	<g class="st0">
		<path class="st4" d="M-71,220c94.2,0,94.2,18,188.4,18c94.2,0,94.2-18,188.4-18c94.2,0,94.2,18,188.4,18c94.2,0,94.2-18,188.4-18
			s94.2,18,188.4,18"/>
	</g>
</g>
</svg>
<div class="header">
		Pump Status:
		<input id="toggle-one" type="checkbox" checked data-toggle="toggle" data-onstyle="primary" data-size="large">
</div>
<h3>
<button class="btn btn-success" onclick="refresh()">Refresh</button>
</h3>	
<%-- <div style="align:left; border: solid 1px black; margin-left:-200;">
<c:out value="${tank.tankName}" />
				
		</div>	 --%>
				

</body>
</html>