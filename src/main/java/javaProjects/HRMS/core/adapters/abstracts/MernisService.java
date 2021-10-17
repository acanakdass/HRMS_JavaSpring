package javaProjects.HRMS.core.adapters.abstracts;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

public interface MernisService {
    public boolean checkIfRealPerson(String tcNo, String firstName, String lastName, int birthYear) throws NumberFormatException, RemoteException;

}
