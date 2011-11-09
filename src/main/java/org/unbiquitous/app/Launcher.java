package org.unbiquitous.app;

import br.unb.unbiquitous.ubiquitos.uos.context.ContextException;
import br.unb.unbiquitous.ubiquitos.uos.context.UOSApplicationContext;

public class Launcher {
	public static void main(String[] args) throws ContextException {
		new UOSApplicationContext().init("termometer");
	}
}
