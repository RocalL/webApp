package service;

import javax.jws.WebMethod;
import javax.jws.WebService;

import factory.ProjetFactory;
import factory.ProjetFactoryImpl;

@WebService
public class ProjetServiceImpl implements ProjetService {
    private ProjetFactory projetFactory;
    
    public ProjetServiceImpl(){
    	this.projetFactory = new ProjetFactoryImpl();
    }
    @WebMethod
    public String getProjets() {
        return projetFactory.getAll().toString();
    }
}