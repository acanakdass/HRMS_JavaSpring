package javaProjects.HRMS.core.adapters.concretes;

import java.rmi.RemoteException;


import javaProjects.HRMS.core.adapters.abstracts.MernisService;
import javaProjects.HRMS.entities.concretes.Users.Candidate;

public class MernisServiceAdapter implements MernisService {

	@Override
	public boolean checkIfRealPerson(Candidate candidate) {
        //KPSPublicSoapProxy kpsPublic = new KPSPublicSoapProxy();
        boolean result=true;
//        try {
//			result = kpsPublic.TCKimlikNoDogrula(Long.parseLong(candidate.getIdentityNumber()), candidate.getFirstName(), candidate.getLastName(), candidate.getBirthYear());
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
        		//return new KPSPublicLocator().getKPSPublicSoap().TCKimlikNoDogrula(Long.parseLong(tcNo),firstName,lastName,birthYear);
        if( candidate.getIdentityNumber().length()==11 ) {
        	result=true;
        }else {        	
        result=false;
        }
        return result;

	}

}
