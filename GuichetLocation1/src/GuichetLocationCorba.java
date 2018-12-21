

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import GarageCorba.IGarageRemote;
import GarageCorba.IGarageRemoteHelper;
import GarageCorba.Voiture;

public class GuichetLocationCorba {

	public static void main(String[] args) {
		try 
		{
			//_______________ interface graphique___________________
			
			
			
			
			
			
			//____jndi 
			Context ctx=new InitialContext();
			//_____________ pour enRoute______________
			Object objenroute=ctx.lookup("EnRoute");
			IGarageRemote stubroute=IGarageRemoteHelper.narrow((org.omg.CORBA.Object)objenroute);
			
			//_____________ pour garage______________
			Object obj=ctx.lookup("Garage1");
			IGarageRemote stub=IGarageRemoteHelper.narrow((org.omg.CORBA.Object)obj);
			createFenetre(stub,stubroute);
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	private static void createFenetre(IGarageRemote garage,IGarageRemote enroute) 
	{
		JButton louer=new JButton("Actualiser");
		JButton ok=new JButton("Louer");
		JComboBox cb = new JComboBox();
		JTextField status=new JTextField("", 20);
		status.setEnabled(false);
		cb.setEditable(false);
		ok.setEnabled(false);
		
		JPanel pane = new JPanel();
		JPanel panerst = new JPanel();
		JPanel panecb = new JPanel();
		
		panerst.add(louer);
		panecb.add(cb);
		panecb.add(ok);
		pane.setLayout(new GridLayout(3,1));
		pane.add(panerst, BorderLayout.CENTER);
		pane.add(panecb, BorderLayout.CENTER);
		pane.add(status, BorderLayout.LINE_END);
        //__________ action ---------------
		
		
		
		
		louer.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Voiture[] voituregarer=garage.afficheVoitures();
				System.out.println(voituregarer.length);
				if(voituregarer.length==0)
				{
					status.setText("le Garage est vide !!!");
					
				}else
				{
					cb.removeAllItems();
					if (voituregarer[0].name.equals("default")) 
					{
						status.setText("le Garage est vide !!!");
					} 
					else
					{
						for (Voiture voiture : voituregarer) 
						{
							cb.addItem(voiture.name);
						}
						ok.setEnabled(true);
					}
				}
				
				
			}
		});
		
		ok.addActionListener(new ActionListener() 
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				String name=(String)cb.getSelectedItem().toString();
				Voiture vtr=garage.maVoiture(name);
				garage.Louer(vtr);
				enroute.Restituer(vtr);
				ok.setEnabled(false);
				status.setText("la voiture est louee .");
			}
			
		});
		
		
		JFrame Guichet = new JFrame("Guichet Location Garage 1");
		Guichet.setBounds(150, 10, 300, 200);
		Guichet.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Guichet.add(pane);
		Guichet.setVisible(true);
    }

}
