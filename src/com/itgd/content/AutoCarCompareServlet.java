package com.itgd.content;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.*;
//import com.itgd.conn.utils.DbConnection;
import com.itgd.utils.CarData;

public class AutoCarCompareServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		 	try {
		 		RequestDispatcher requestDispatcher=null;
		         		
				Map<String,ArrayList<CarData>> map=getData();
				String fun=request.getParameter("fun");
				String val=null;
				if(request.getParameter("val")!=null)
				{
				val=request.getParameter("val").toString();
				val=val.replaceAll("_", "+");
				}
				System.out.println(">>>>>>>>>>>>"+val);
				 List<String> models=null;
				 List<String> priceRange=null;
				 List<String> modelsc=null;
				 List<String> priceRangec=null;
				 
				 if(request.getParameter("fun")!=null && fun.equals("brand1"))
				 {
					 models=getModel(map,val);
					 request.setAttribute("carModels", models);
				 }
				 if(fun!=null  && fun.equals("vehicle1"))
				 {
					 String brand=request.getParameter("val1");
					 priceRange=getPriceRange(map,brand,val);
					 request.setAttribute("priceRanges", priceRange);
				 }
				 if(fun!=null  &&  fun.equals("brand2"))
				 {
					 modelsc=getModel(map,val);
					 request.setAttribute("carModelsc", modelsc);
				 }
				 if(fun!=null  && fun.equals("vehicle2"))
				 {
					 String brand=request.getParameter("val1");
					 priceRangec=getPriceRange(map,brand,val);
					 request.setAttribute("priceRangesc", priceRangec);
				 }
				 request.setAttribute("brandsDet", map);
				/*
				 String vehicle=request.getParameter("vehicle");
				 List<String> priceRange=getPriceRange(map,brand,vehicle);
				 
				 String brand1=request.getParameter("brandId1");
				 List<String> modelsc=getModel(map,brand1);
				
				String vehiclec=request.getParameter("vehiclec");
				 List<String> priceRangec=getPriceRange(map,brand1,vehiclec);
				 
				 
				 */
				 if(fun!=null && (fun.equals("brand1") || fun.equals("vehicle1") || fun.equals("brand2") || fun.equals("vehicle2") ))
				 {
					 requestDispatcher = request.getRequestDispatcher("/CarDetails.jsp");
					 
				 }
				 else
				 {
					 String brand=request.getParameter("brand");
					 String vehicle=request.getParameter("vehicle");
					 String brandc=request.getParameter("brandc");
					 String vehiclec=request.getParameter("vehiclec");
					 models=getModel(map,brand);
					 priceRange=getPriceRange(map,brand,vehicle);
					 modelsc=getModel(map,brandc);
					 priceRangec=getPriceRange(map,brandc,vehiclec);
					 request.setAttribute("carModels", models);
					 request.setAttribute("priceRanges", priceRange);
					 request.setAttribute("carModelsc", modelsc);
					 request.setAttribute("priceRangesc", priceRangec);
					 requestDispatcher = request.getRequestDispatcher("/AutoCompare.jsp");
				 }
				 requestDispatcher.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Map<String,ArrayList<CarData>> map=getData();
			String brandFirst=request.getParameter("brand");
			String brandSecond=request.getParameter("brandc");
			String vehicleFirst=request.getParameter("vehicle");
			String vehicleSecond=request.getParameter("vehiclec");
			
			Set<String> ma=map.keySet();
			List<CarData> data=null;
			List<CarData> data1=null;
			List<CarData> details1=new ArrayList<CarData>();
			List<CarData> details2=new ArrayList<CarData>();
			if(map.containsKey(brandFirst))
			{
				data= (List<CarData>) map.get(brandFirst);
				Iterator itrls=data.iterator();
				while(itrls.hasNext())
				{
					CarData obj=(CarData)itrls.next();
					if(obj.getVehicle().equals(vehicleFirst))
					{
						details1.add(obj);
					}
				}
			}
			if(map.containsKey(brandSecond))
			{
				data1= (List<CarData>) map.get(brandSecond);
				Iterator itrls=data1.iterator();
				while(itrls.hasNext())
				{
					CarData obj=(CarData)itrls.next();
					if(obj.getVehicle().equals(vehicleSecond))
					{
						details2.add(obj);
					}
				}
			}
			request.setAttribute("firstBrandDet", details1);
			request.setAttribute("secondBrandDet", details2);
			//String val=request.getParameter("val").toString();
			doGet(request,response);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public Map getData() throws SQLException
	{
		ResultSet rs=null;
		Statement stmt=null;
		String brandstatus=null;
		Connection con=null;
		CarData carObj=null;
		ArrayList<CarData> dataList=null;
		Map<String,ArrayList<CarData>> ma=new LinkedHashMap<String,ArrayList<CarData>>();
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest","root","india");
			//con=DriverManager.getConnection("jdbc:mysql://10.5.0.100:3306/itoday","itgd_intoday","!tgd_0ff111");
			stmt=con.createStatement();
			rs=stmt.executeQuery("select distinct TRIM(UPPER(brand)) AS brand,price_range,vehicle,delhi_price,mumbai_price,kolkata_price,bangalore_price,Chennai_price,bodytype,EngineDisplacement,cylinders,power,torque,gears,geartype,fueltype,runningkm,topspeed,ARAI_Claimed,Tested,AC,Climate_Control,Adjustable_Steering_rake,Adjustable_Steering_Reach,Steering_mounted_controls,Cruise_Control,Centrallock,Remotelock,Keyless,Power_windows,Driver_seat_adjust,Parking_sensor,Parking_camera,Split_foalding,Multi_function,Door_mirror,MP3_CD_Player,USB,AUXIn,MDI,Bluetooth,Foglamps,Rear_AC_vents,Rear_defogger,Wash_wipe,Airbags,ESP,ABS from autotoday order by brand");
			while(rs.next())
			{
				carObj=new CarData();
				
				String priceRange=rs.getString("price_range");
				String brand=rs.getString("brand");
				String vehicle=rs.getString("vehicle");
				//String vehicle=vehicle1.replaceAll("[^a-zA-Z0-9]", "");
				String delhi_price=rs.getString("delhi_price");
				String mumbai_price=rs.getString("mumbai_price");
				String kolkata_price=rs.getString("kolkata_price");
				String bangalore_price=rs.getString("bangalore_price");
				String chennai_price=rs.getString("Chennai_price");
				String bodytype=rs.getString("bodytype");
				String engineDisplacement=rs.getString("EngineDisplacement");
				String cylinders=rs.getString("cylinders");
				String power=rs.getString("power");
				String torque=rs.getString("torque");
				String gears=rs.getString("gears");
				String geartype=rs.getString("geartype");
				String fueltype=rs.getString("fueltype");
				String runningkm= rs.getString("runningkm");
				String topspeed= rs.getString("topspeed");
				String ARAI_Claimed= rs.getString("ARAI_Claimed");
				String Tested= rs.getString("Tested");
				String AC= rs.getString("AC");
				String Climate_Control= rs.getString("Climate_Control");
				String Adjustable_Steering_rake= rs.getString("Adjustable_Steering_rake");
				String Adjustable_Steering_Reach= rs.getString("Adjustable_Steering_Reach");
				String Steering_mounted_controls= rs.getString("Steering_mounted_controls");
				String Cruise_Control= rs.getString("Cruise_Control");
				String Centrallock= rs.getString("Centrallock");
				String Remotelock= rs.getString("Remotelock");
				String Keyless= rs.getString("Keyless");
				String Power_windows= rs.getString("Power_windows");
				String Driver_seat_adjust= rs.getString("Driver_seat_adjust");
				String Parking_sensor[]= rs.getString("Parking_sensor").split("/");
				String Parking_camera= rs.getString("Parking_camera");
				String Split_foalding= rs.getString("Split_foalding");
				String Multi_function= rs.getString("Multi_function");
				String Door_mirror= rs.getString("Door_mirror");
				String MP3_CD_Player= rs.getString("MP3_CD_Player");
				String USB= rs.getString("USB");
				String AUXIn= rs.getString("AUXIn");
				String MDI= rs.getString("MDI");
				String Bluetooth= rs.getString("Bluetooth");
				String Foglamps= rs.getString("Foglamps");
				String Rear_AC_vents= rs.getString("Rear_AC_vents");
				String Rear_defogger= rs.getString("Rear_defogger");
				String Wash_wipe= rs.getString("Wash_wipe");
				String Airbags= rs.getString("Airbags");
				String ESP= rs.getString("ESP");
				String ABS= rs.getString("ABS");
				
				carObj.setPrice_range(priceRange);
				carObj.setBrand(brand);
				carObj.setVehicle(vehicle);
				carObj.setDelhi_price(delhi_price);
				carObj.setMumbai_price(mumbai_price);
				carObj.setKolkata_price(kolkata_price);
				carObj.setBangalore_price(bangalore_price);
				carObj.setChennai_price(chennai_price);
				carObj.setBodytype(bodytype);
				carObj.setEngineDisplacement(engineDisplacement);
				carObj.setCylinders(cylinders);
				carObj.setPower(power);
				carObj.setTorque(torque);
				carObj.setGears(gears);
				carObj.setGearType(geartype);
				carObj.setFuelType(fueltype);
				carObj.setRunningkm(runningkm);
				carObj.setTopspeed(topspeed);
				carObj.setArai_Claimed(ARAI_Claimed);
				carObj.setTested(Tested);
				carObj.setAc(AC);
				carObj.setClimate_Control(Climate_Control);
				carObj.setAdjustable_Steering_rake(Adjustable_Steering_rake);
				carObj.setAdjustable_Steering_Reach(Adjustable_Steering_Reach);
				carObj.setSteering_mounted_controls(Steering_mounted_controls);
				carObj.setCruise_Control(Cruise_Control);
				carObj.setCentrallock(Centrallock);
				carObj.setRemotelock(Remotelock);
				carObj.setKeyless(Keyless);
				carObj.setPower_windows(Power_windows);
				carObj.setDriver_seat_adjust(Driver_seat_adjust);
				carObj.setParking_sensor(Parking_sensor[0]);
				carObj.setParking_camera(Parking_camera);
				carObj.setSplit_foalding(Split_foalding);
				carObj.setMulti_function(Multi_function);
				carObj.setDoor_mirror(Door_mirror);
				carObj.setMp3_CD_Player(MP3_CD_Player);
				carObj.setUsb(USB);
				carObj.setAuxin(AUXIn);
				carObj.setMdi(MDI);
				carObj.setBluetooth(Bluetooth);
				carObj.setFoglamps(Foglamps);
				carObj.setRear_AC_vents(Rear_AC_vents);
				carObj.setRear_defogger(Rear_defogger);
				carObj.setWash_wipe(Wash_wipe);
				carObj.setAirbags(Airbags);
				carObj.setEsp(ESP);
				carObj.setAbs(ABS);
				
				if(ma.containsKey(brand))
				{
					dataList=ma.get(brand);
					dataList.add(carObj);
				}
				else
				{
					dataList=new ArrayList<CarData>();
					dataList.add(carObj);
				} 
				
				ma.put(brand,dataList);
				

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			con.close();
			stmt.close();
		}
		return ma;
	}

	public List<String> getModel(Map map,String brand)
	{
		Set<String> ma=map.keySet();
		List<CarData> data=null;
		List<String> models=new ArrayList<String>();
			if(map.containsKey(brand))
			{
				data= (List<CarData>) map.get(brand);
				Iterator itrls=data.iterator();
				while(itrls.hasNext())
				{
					CarData obj=(CarData)itrls.next();
					models.add(obj.getVehicle());
				}
			}
	
		return models;
	}
	
	public List<String> getPriceRange(Map map,String brand,String vehicle)
	{
		Set<String> ma=map.keySet();
		List<CarData> data=null;
		List<String> prices=new ArrayList<String>();
			if(map.containsKey(brand))
			{
				data= (List<CarData>) map.get(brand);
				Iterator itrls=data.iterator();
				while(itrls.hasNext())
				{
					CarData obj=(CarData)itrls.next();
					if(obj.getVehicle().contains(vehicle))
					{
						if(!prices.contains(obj.getPrice_range()))
						prices.add(obj.getPrice_range());
					}
				}
			}
	
		return prices;
	}
}
