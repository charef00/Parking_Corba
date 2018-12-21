import java.awt.TextArea;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.JFrame;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

import GarageCorba.Voiture;
import serveur.ServiceImp;


public class ServeurCorba {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		try {
			
			
			
			//_______________ publier la reference ___________________
			ORB orb=ORB.init(args,null);
			POA rootPOA= POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPOA.the_POAManager().activate();
			ServiceImp bs=new ServiceImp();
			bs.mesVoitures.put("G1_Voiture1",new Voiture("Garage1","G1_Voiture1"));
			bs.mesVoitures.put("G1_Voiture2",new Voiture("Garage1","G1_Voiture2"));
			bs.mesVoitures.put("G1_Voiture3",new Voiture("Garage1","G1_Voiture3"));
			
			
			//----jndi
			Context ctx=new InitialContext();
			
			//__publier lq reference 
			Object ref=rootPOA.servant_to_reference(bs);
			System.out.println(ref);
			ctx.rebind("Garage1", ref);
			//_________________ affichage ____________
			bs.interfaceGraphique();

			//___________ demarrer serveur _____________
			orb.run();
			System.out.println("serveurs");
			
			
			
		} catch (Exception  e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 }
	
	
	
	
}


