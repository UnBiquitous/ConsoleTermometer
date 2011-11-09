package org.unbiquitous.app;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.unbiquitous.driver.temperature.eposmote2.TemperatureDriver;
import br.unb.unbiquitous.ubiquitos.uos.adaptabitilyEngine.SmartSpaceGateway;
import br.unb.unbiquitous.ubiquitos.uos.driverManager.RemoteDriverData;
import br.unb.unbiquitous.ubiquitos.uos.messageEngine.dataType.UpDevice;
import br.unb.unbiquitous.ubiquitos.uos.messageEngine.dataType.UpDriver;

public class _TestConsoleTermometer {

	/*@Test*/ public void shouldCallOutputTemperatureFromDriver(){
		SmartSpaceGateway gateway = mock(SmartSpaceGateway.class);
		UpDriver driver = mock(UpDriver.class);
		UpDevice device = mock(UpDevice.class);
		RemoteDriverData data = new RemoteDriverData(driver, device, "myId");
		List<RemoteDriverData> list = new ArrayList<RemoteDriverData>();
		list.add(data);
		when(gateway.listDrivers("org.unbiquitous.driver.temperature")).thenReturn(list);
		gateway.listDrivers("");
	}
	
}
