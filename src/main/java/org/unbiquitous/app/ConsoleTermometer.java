package org.unbiquitous.app;

import java.util.List;

import org.apache.log4j.Logger;

import br.unb.unbiquitous.ubiquitos.uos.adaptabitilyEngine.Gateway;
import br.unb.unbiquitous.ubiquitos.uos.adaptabitilyEngine.UosEventListener;
import br.unb.unbiquitous.ubiquitos.uos.application.UosApplication;
import br.unb.unbiquitous.ubiquitos.uos.driverManager.RemoteDriverData;
import br.unb.unbiquitous.ubiquitos.uos.messageEngine.messages.Notify;
import br.unb.unbiquitous.ubiquitos.uos.messageEngine.messages.ServiceCall;
import br.unb.unbiquitous.ubiquitos.uos.messageEngine.messages.ServiceResponse;
import br.unb.unbiquitous.ubiquitos.uos.ontology.OntologyDeploy;
import br.unb.unbiquitous.ubiquitos.uos.ontology.OntologyStart;
import br.unb.unbiquitous.ubiquitos.uos.ontology.OntologyUndeploy;

public class ConsoleTermometer implements UosApplication, UosEventListener{

	private static Logger logger = Logger.getLogger(ConsoleTermometer.class);
	
	public void start(Gateway gateway, OntologyStart ontology){
		try {
			String driverName = "org.unbiquitous.driver.temperature";
			List<RemoteDriverData> drivers = gateway
					.listDrivers(driverName);
			if (drivers != null && !drivers.isEmpty()) {
				RemoteDriverData data = drivers.get(0);
				ServiceCall call = new ServiceCall(driverName, "sense");
				gateway.registerForEvent(this, data.getDevice(), driverName, data.getInstanceID(), "temperature_change");
				ServiceResponse response = gateway.callService(data.getDevice(), call);
				System.out.println("Now is "+response.getResponseData("temperature")+"°C");
			}else{
				System.out.println("No driver available.");
			}
		} catch (Exception e) {
			logger.error("Dammit",e);
		}
	}

	public void handleEvent(Notify event) {
		if (event.getEventKey().equals("temperature_change")){
			System.out.println("Temperature changed to "+event.getParameter("temperature")+"°C");
		}
		
	}
	
	public void stop() throws Exception {}

	public void init(OntologyDeploy ontology) {}

	public void tearDown(OntologyUndeploy ontology) throws Exception {}

}
