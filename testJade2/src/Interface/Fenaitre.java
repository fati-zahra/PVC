package Interface;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import AlgoritmeGenetique.CourtChemin;
import AlgoritmeGenetique.Ville;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import jade.gui.GuiEvent;
import voyageur.AgentVoyageur;

public class Fenaitre extends JFrame {
	private AgentVoyageur agentVoyageur;
	private JPanel contentPane;
	public JPanel Panel;
	private Panel pan;
	DefaultTableModel model  ;
	private CourtChemin courtChemin = new CourtChemin();
	public List<Ville> villes = new ArrayList<Ville>();
	public List<Ville> villesAjouté = new ArrayList<Ville>();
	public JComboBox comboBox;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					Fenaitre frame = new Fenaitre();
					frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	
		
	}
	
	public Fenaitre() {
		
		setTitle("Voyageur de Commerce");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 934, 610);
		setResizable(false);
		contentPane = new JPanel();
//		contentPane.setBackground(new Color(165, 214, 247));

		contentPane.setBackground(new Color(192,192,192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		Panel = new JPanel();
		Panel.setBackground(new Color(255, 245, 238));
		Panel.setBounds(10, 10, 551, 554);
		contentPane.add(Panel);
		Panel.setLayout(null);
		
				pan = new Panel();
				pan.setBounds(0, 0, 551, 554);
				Panel.add(pan);
				pan.setBorder(BorderFactory.createLineBorder(new Color(63, 54, 151), 2));
				pan.repaint();
				villes.add(new Ville(villes.size()+1,"Rabat", 322,  98, false));
				villes.add(new Ville(villes.size()+1, "Kenitra",  339,  72, false));
				villes.add(new Ville(villes.size()+1, "Asilah", 353, 36, false));
				villes.add(new Ville(villes.size()+1, "Tanger",356,15, false));
				villes.add(new Ville(villes.size()+1, "Tetouan", 373, 20, false));
				villes.add(new Ville(villes.size()+1, "Chefchaouen", 374,34, false));
				villes.add(new Ville(villes.size()+1, "Ouajda", 495, 58, false));
				villes.add(new Ville(villes.size()+1, "Taza", 421, 76, false));
				villes.add(new Ville(villes.size()+1, "Volubilis", 363,106, false));
				villes.add(new Ville(villes.size()+1, "Fés", 390, 108, false));
				villes.add(new Ville(villes.size()+1, "Meknés", 364, 123, false));
				villes.add(new Ville(villes.size()+1, "Bouarfa", 483, 150, false));
				villes.add(new Ville(villes.size()+1, "Casa Blanca", 289, 117, false));
				villes.add(new Ville(villes.size()+1, "Settat", 297, 146, false));
				villes.add(new Ville(villes.size()+1, "Safi", 238, 175, false));
				villes.add(new Ville(villes.size()+1, "Beni Mellal", 347, 166, false));
				villes.add(new Ville(villes.size()+1, "Errachidia", 389, 187, false));
				villes.add(new Ville(villes.size()+1, "Marrakich", 273, 208, false));
				villes.add(new Ville(villes.size()+1, "Arfoud", 403, 210, false));
				villes.add(new Ville(villes.size()+1, "Rissani", 400, 223, false));
				villes.add(new Ville(villes.size()+1, "Ouarzazate", 318, 225, false));
				villes.add(new Ville(villes.size()+1, "Essaouira", 218, 213, false));
				villes.add(new Ville(villes.size()+1, "Agadir", 222, 250, false));
				villes.add(new Ville(villes.size()+1, "Tan-tan", 153, 346, false));
				villes.add(new Ville(villes.size()+1, "Laayoune", 90, 396, false));

				
				  
			comboBox =new JComboBox();
				for (int i = 0; i <villes.size(); i++) {
					
					System.out.println(villes.get(i).getNomVille());
					comboBox.addItem(villes.get(i).getNomVille());
			}
				comboBox.setBounds(600, 10, 137, 39);
				contentPane.add(comboBox);
				comboBox.setSelectedIndex(-1);
				 //comboBox.addItemListener(this);
				 
				 //scrollPanell
		        JScrollPane scroll =new JScrollPane();
		        scroll.setBounds(600, 100,200, 100);
		        
				JTable jTable1 = new JTable(new DefaultTableModel(new Object[]{"Id", "Ville"}, 0));
		        jTable1.setBackground(new Color(253, 245, 230));
		        jTable1.setFont(new Font("Tahoma", Font.BOLD, 10));
			    scroll.setViewportView(jTable1);
			    contentPane.add(scroll);
			    model   = (DefaultTableModel) jTable1.getModel();
				// camboBox
		       comboBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent event) {
						
					int k =1;
						String value = event.getItem().toString();
						for (int i = 0; i <villesAjouté.size(); i++) {
						
							if(value == villesAjouté.get(i).getNomVille()) {
								k=0;
								}
						}       
						if(k==1) {
							
								for (int j = 0; j <villes.size(); j++) {
									
									if(value == villes.get(j).getNomVille()) {
										villesAjouté.add(villes.get(j));
										
										model.addRow(new String[] { String.valueOf(villes.get(j).getIdVille()), villes.get(j).getNomVille()});
										
										System.out.println(model.getRowCount());
										
									}
								}
						}
							}		
					});
		       
		       
				JButton Btn = new JButton("Court Chemin");
				
				Btn.setFont(new Font("Tahoma", Font.BOLD, 13));
				Btn.setForeground(new Color(128,128,128));
				Btn.setBackground(Color.black);
				
			     Btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("calcul de chemin:)");
						GuiEvent gev = new GuiEvent(contentPane.getUI(), 1);
						
						java.util.Map<String, Object> params = new HashMap<>();
						params.put("v1", villesAjouté);

						gev.addParameter(params);
						agentVoyageur.onGuiEvent(gev);
					}
				});
			     Btn.setBounds(790, 10,125, 40);
					contentPane.add(Btn);
				 
				/*JButton BtnSup = new JButton("SupprimerVille");
					
					BtnSup.setFont(new Font("Tahoma", Font.BOLD, 13));
					BtnSup.setForeground(new Color(255, 255, 255));
					BtnSup.setBackground(new Color(205, 133, 63));
								
					BtnSup.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										System.out.println("1 ville est sup:)");
				                        
										
										
										for(int i=0; i<model.getRowCount();i++)
										model.removeRow(model.getRowCount()-1);
										villesAjouté.remove(villesAjouté.size()-1);
										comboBox.setSelectedIndex(-1);
										pan.repaint();
										
										
									}
								});
					BtnSup.setBounds(790, 200,125,40);
								contentPane.add(BtnSup); 
				
JButton BtnReset = new JButton("Annuler");
				
     BtnReset.setFont(new Font("Tahoma", Font.BOLD, 13));
     BtnReset.setForeground(new Color(255, 255, 255));
     BtnReset.setBackground(new Color(205, 133, 63));
				
     BtnReset.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println("rset:)");
                        
						
						pan.setAnnuler(100);
						for(int i=0; i<model.getRowCount();i++)
						model.removeRow(i);
						for(int i=0; i<villesAjouté.size();i++)
							villesAjouté.remove(i);
						comboBox.setSelectedIndex(-1);
						pan.repaint();
						
						
					}
				});
    
     BtnReset.setBounds(790, 250,125,40);
		contentPane.add(BtnReset);*/
	}
	

	/* ----- Getters & Setters ----- */
	public List<Ville> getVilles() {
		return villesAjouté;
	}

	public void setVilles(Ville[] villes) {
		this.villes = villesAjouté;
	}

	public CourtChemin getCourtChemin() {
		return courtChemin;
	}

	public void setCourtChemin(CourtChemin courtChemin) {
		this.courtChemin = courtChemin;
	}

	public AgentVoyageur getAgentVoyageur() {
		return agentVoyageur;
	}

	public void setAgentVoyageur(AgentVoyageur agentVoyageur) {
		this.agentVoyageur = agentVoyageur;
	}

	public Panel getPan() {
		return pan;
	}

	public void setPan(Panel pan) {
		this.pan = pan;
	}

}
