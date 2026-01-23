/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;



import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import javax.swing.JButton;

public class AddSoildata extends JFrame {

    private JFrame frame;
    private JTextField echantillon;
    private JTextField N;
    private JTextField P;
    private JTextField K;
    private JTextField Ph;
    private JTextField Temperature;
    private JTextField Humidity;
    private JTextField Rainfall;
   

   
    private JButton ajouter, accueil;



    public AddSoildata() {
        
        
    
        setBounds(100, 100, 894, 462);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        getContentPane().setLayout(null);
        this.setResizable(false);

        this.setLocation(500, 300);
        JPanel paneln = new JPanel();
        paneln.setBackground(Color.WHITE);
        paneln.setBounds(0, 0, 900, 425);
        getContentPane().add(paneln);
        paneln.setLayout(null);
        
        
        

        JLabel label_echantillon = new JLabel("Echantillon_id :");
        label_echantillon.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_echantillon.setBounds(31, 74, 120, 43);
        paneln.add(label_echantillon); 

        JLabel label_N = new JLabel("N :");
        label_N.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_N.setBounds(31, 144, 120, 43);
        paneln.add(label_N );

        JLabel label_P = new JLabel("P : ");
        label_P.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_P.setBounds(31, 209, 120, 43);
        paneln.add(label_P);

        JLabel label_K = new JLabel("K :");
        label_K.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_K.setBounds(31, 272, 120, 43);
        paneln.add(label_K);

        JLabel label_Ph = new JLabel("Ph : ");
        label_Ph.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_Ph.setBounds(317, 272, 120, 43);
        paneln.add(label_Ph);

        JLabel label_Temperature = new JLabel("Temperature :");
        label_Temperature.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_Temperature.setBounds(317, 74, 120, 43);
        paneln.add(label_Temperature); 

        JLabel label_Humidity = new JLabel("Humidity : ");
        label_Humidity.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_Humidity.setBounds(317, 144, 120, 43);
        paneln.add(label_Humidity);

        JLabel label_Rainfall = new JLabel("Rainfall :");
        label_Rainfall.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_Rainfall.setBounds(317, 209, 120, 43);
        paneln.add(label_Rainfall);
        
        


        



        echantillon = new JTextField();
        echantillon.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        echantillon.setBounds(135, 83, 164, 29);
        paneln.add(echantillon);
        echantillon.setColumns(10);

        N = new JTextField();
        N.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        N.setColumns(10);
        N.setBounds(135, 152, 164, 29);
        paneln.add(N);

        P = new JTextField();
        P.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        P.setColumns(10);
        P.setBounds(135, 221, 164, 29);
        paneln.add(P);

        K = new JTextField();
        K.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        K.setColumns(10);
        K.setBounds(135, 280, 164, 29);
        paneln.add(K);

        Ph = new JTextField();
        Ph.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        Ph.setColumns(10);
        Ph.setBounds(430, 282, 164, 29);
        paneln.add(Ph);

        Temperature = new JTextField();
        Temperature.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        Temperature.setColumns(10);
        Temperature.setBounds(430, 83, 164, 29);
        paneln.add(Temperature);

        Humidity = new JTextField();
        Humidity.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        Humidity.setColumns(10);
        Humidity.setBounds(430, 152, 164, 29);
        paneln.add(Humidity);

        Rainfall = new JTextField();
        Rainfall.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        Rainfall.setColumns(10);
        Rainfall.setBounds(430, 217, 164, 29);
        paneln.add(Rainfall);
   

       



    

        JLabel titre = new JLabel("Ajouter un echantillon ");
        titre.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD , 24));
        titre.setBounds(340, 10, 300, 30);
        //titre.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        paneln.add(titre);

        ajouter = new JButton("Ajouter");
        ajouter.setBounds(667, 260, 164, 35);
        paneln.add(ajouter);
        accueil = new JButton("Accueil");
        accueil.setBounds(667, 311, 164, 35);
        paneln.add(accueil);

        JLabel bgc = new JLabel("");
    
        bgc.setBounds(0, 62, 890, 366);
        paneln.add(bgc);
        this.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public JTextField getEchantillon() {
        return echantillon;
    }

    public void setEchantillon(JTextField echantillon) {
        this.echantillon = echantillon;
    }

    public JTextField getN() {
        return N;
    }

    public void setN(JTextField N) {
        this.N = N;
    }
    public JTextField getP() {
        return P;
    }
    public void setP(JTextField P) {
        this.P = P;
    }

    public JTextField getK () {
        return K;
    }

    public void setK(JTextField K) {
        this.K = K;
    }

    public JTextField getPh() {
        return Ph;
    }

    public void setPh(JTextField Ph) 
    {this.Ph = Ph;}

    public JTextField getTemperature() {
        return Temperature;
    }

    public void setTemperature(JTextField Temperature) {this.Temperature = Temperature;}

    public JTextField getHumidity() {
        return Humidity;
    }

    public void setHumidity(JTextField Humidity) {
        this.Humidity = Humidity;
    }

    public JTextField getRainfall() {
        return Rainfall;
    }

    public void setRainfall(JTextField Rainfall) {
        this.Rainfall = Rainfall;
    }

   

    public JButton getAccueil() {
        return accueil;
    }

    public void setAccueil(JButton accueil) {
        this.accueil = accueil;
    }


    public JButton getAjouter() {
        return ajouter;
    }

    public void setAjouter(JButton ajouter) {
        this.ajouter = ajouter;
    }




}
















