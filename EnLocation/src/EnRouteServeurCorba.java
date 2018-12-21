import javax.naming.Context;
import javax.naming.InitialContext;

import org.omg.CORBA.ORB;
import org.omg.CORBA.Object;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import serveur.ServiceImp;


public class EnRouteServeurCorba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ORB orb=ORB.init(args,null);
			POA rootPOA= POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPOA.the_POAManager().activate();
			ServiceImp bs=new ServiceImp();
			//----jndi
			Context ctx=new InitialContext();
			
			//__publier lq reference 
			Object ref=rootPOA.servant_to_reference(bs);
			System.out.println(ref);
			ctx.rebind("EnRoute", ref);
			//_________________ affichage ____________
			bs.interfaceGraphique();
			orb.run();
			
		} catch (Exception  e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
	
}


