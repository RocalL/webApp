package service;

import javax.jws.WebService;

@WebService(endpointInterface = "service.WebServiceInterface")
public class WebServiceImpl implements WebServiceInterface{

	@Override
	public String printMessage() {
		return "Hello from Java Code Geeks Server";
	}

}