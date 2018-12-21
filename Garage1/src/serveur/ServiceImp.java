package serveur;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;

import GarageCorba.IGarageRemotePOA;
import GarageCorba.Voiture;

public class ServiceImp extends IGarageRemotePOA{

	
	private int place=3;
	public TextArea txt=new TextArea();
    public Map<String, Voiture> mesVoitures = new HashMap<String, Voiture>();
	@Override
	public Voiture[] afficheVoitures() 
	{
		if (mesVoitures.size()>0) 
		{
			Voiture[] tab=new Voiture[mesVoitures.size()];
			
			int i=0;
			for(Map.Entry<String, Voiture> entry : mesVoitures.entrySet()) 
			{
			    tab[i] = entry.getValue();
			    i++;
			}
			return tab;
		} 
		else
		{
			Voiture[] tab=new Voiture[1];
			tab[0]=new Voiture("default","default");
			return tab;
		}
		
	}
	@Override
	public void Restituer(Voiture v) 
	{
		mesVoitures.put(v.name, v);
		settext();
	}
	@Override
	public void Louer(Voiture v)
	{
		mesVoitures.remove(v.name);
		settext();
	
	}
	public void interfaceGraphique()
	{
		//_______________ interface graphique___________________
		JFrame Garage0 = new JFrame("Garage numero 1");
		Garage0.setBounds(500, 10, 300, 200);
		Garage0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Garage0.add(txt);
		
		txt.setEditable(false);
		settext();
		Garage0.setVisible(true);
		
	}
	private void settext()
	{
		Voiture[] vts=afficheVoitures();
		txt.setText("");
		txt.append("\n");
		if (vts[0].name.equals("default")) 
		{
			
		} 
		else
		{
			for (Voiture voiture : vts) 
			{
					txt.append(voiture.name+"\n");
					txt.append("\n");
					
			}
		}
	}
	@Override
	public Voiture maVoiture(String name) 
	{
		// TODO Auto-generated method stub
		return mesVoitures.get(name);
	}
	

	

}
