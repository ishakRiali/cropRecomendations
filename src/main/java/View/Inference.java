/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Winsido
 */
package View;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import javax.swing.JButton;

public class Inference extends JFrame {

    private JFrame frame;
    private JTextField N;
    private JTextField P;
    private JTextField K;
    private JTextField echantillon;
    private JTextField Ph;
    private JTextField Temperature;
    private JTextField Humidity;
    private JTextField Rainfall;
    private JButton inferer,accueil;


    public Inference() {

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

        JLabel label_nom = new JLabel("Echantillon id  :");
        label_nom.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_nom.setBounds(31, 74, 120, 43);
        paneln.add(label_nom);

        JLabel label_age = new JLabel("N :");
        label_age.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_age.setBounds(31, 144, 120, 43);
        paneln.add(label_age);

        JLabel label_sexe = new JLabel("P : ");
        label_sexe.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_sexe.setBounds(31, 209, 120, 43);
        paneln.add(label_sexe);

        JLabel label_chol = new JLabel("K :");
        label_chol.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_chol.setBounds(31, 272, 120, 43);
        paneln.add(label_chol);

        JLabel label_glu = new JLabel("Ph : ");
        label_glu.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_glu.setBounds(335, 274, 120, 43);
        paneln.add(label_glu);

        JLabel label_tas = new JLabel("Temperature :");
        label_tas.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_tas.setBounds(335, 74, 120, 43);
        paneln.add(label_tas);

        JLabel label_tad = new JLabel("Himidity : ");
        label_tad.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_tad.setBounds(335, 144, 120, 43);
        paneln.add(label_tad);

        JLabel label_taille = new JLabel("Rainfall :");
        label_taille.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 14));
        label_taille.setBounds(335, 209, 120, 43);
        paneln.add(label_taille);

        
        echantillon = new JTextField();
        echantillon.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        echantillon.setBounds(135, 83, 164, 29);
        echantillon.setEditable(true);
        paneln.add(echantillon);
        echantillon.setColumns(10);

        N = new JTextField();
        N.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        N.setColumns(10);
        N.setBounds(135, 152, 164, 29);
        N.setEditable(true);
        paneln.add(N);
        
        P = new JTextField();
        P.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        P.setColumns(10);
        P.setBounds(135, 217, 164, 29);
        P.setEditable(true);
        paneln.add(P);

        

        K = new JTextField();
        K.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        K.setColumns(10);
        K.setBounds(135, 280, 164, 29);
        K.setEditable(true);
        paneln.add(K);

        Ph = new JTextField();
        Ph.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        Ph.setColumns(10);
        Ph.setBounds(435, 282, 164, 29);
        Ph.setEditable(true);
        paneln.add(Ph);

        Temperature = new JTextField();
        Temperature.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        Temperature.setColumns(10);
        Temperature.setBounds(435, 83, 164, 29);
        Temperature.setEditable(true);
        paneln.add(Temperature);

        Humidity = new JTextField();
        Humidity.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        Humidity.setColumns(10);
        Humidity.setBounds(435, 152, 164, 29);
        Humidity.setEditable(true);
        paneln.add(Humidity);

        Rainfall = new JTextField();
        Rainfall.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 12));
        Rainfall.setColumns(10);
        Rainfall.setBounds(435, 217, 164, 29);
        Rainfall.setEditable(true);
        paneln.add(Rainfall);

        

        JLabel titre = new JLabel("Inference_Echantillon");
        titre.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD , 24));
        titre.setBounds(340, 10, 300, 30);
        //titre.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        paneln.add(titre);

        inferer = new JButton("Prédire");
        inferer.setBounds(667, 298, 164, 35);
        paneln.add(inferer);
        accueil = new JButton("Accueil");
        accueil.setBounds(667, 349, 164, 35);
        paneln.add(accueil);
        JLabel bgc = new JLabel("");
     
        bgc.setBounds(0, 62, 890, 366);
        paneln.add(bgc);
        this.setVisible(true);
    }

    public JFrame getFrame() {return frame;}
    public void setFrame(JFrame frame) {this.frame = frame;}
    //Getters & Setters des facteurs
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

    public JButton getInferer() {
        return inferer;
    }

    public void setInferer(JButton ajouter) {
        this.inferer = ajouter;
    }

    public JButton getAccueil() {
        return accueil;
    }

    public void setAccueil(JButton accueil) {
        this.accueil = accueil;
    }




}
