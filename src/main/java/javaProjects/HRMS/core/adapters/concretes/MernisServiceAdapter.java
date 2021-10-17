package javaProjects.HRMS.core.adapters.concretes;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import javaProjects.HRMS.core.adapters.abstracts.MernisService;
import tr.gov.nvi.tckimlik.WS.KPSPublicLocator;
import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

public class MernisServiceAdapter implements MernisService {

	@Override
	public boolean checkIfRealPerson(String tcNo, String firstName, String lastName, int birthYear) throws NumberFormatException,RemoteException {
        KPSPublicSoapProxy kpsPublic = new KPSPublicSoapProxy();
        boolean result = kpsPublic.TCKimlikNoDogrula(Long.parseLong(tcNo), firstName, lastName, birthYear);
//		return new KPSPublicLocator().getKPSPublicSoap().TCKimlikNoDogrula(Long.parseLong(tcNo),firstName,lastName,birthYear);
        if(tcNo.length()==11) {
        	return true;
        }
        return false;
//        return result;
	}

}
