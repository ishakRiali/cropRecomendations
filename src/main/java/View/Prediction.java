package View;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.EventQueue;
import java.awt.Image;
import java.text.DecimalFormat;
import javax.swing.*;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.Font;

public class Prediction extends JFrame{

    private double[] resultatPrediction;
    JTextArea monaffichage ,monaffichage2;
    JButton retour;
    JScrollPane scrollText;
    
    
    



    public Prediction(double[] resultatPrediction) {
        this.resultatPrediction = resultatPrediction;
        //dessinViewPrediction;
        this.setBackground(Color.WHITE);
        this.setBounds(100, 100, 1010, 554);
        this.getContentPane().setLayout(null);
        this.setResizable(false);
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 996, 517);
        panel.setLayout(null);
        getContentPane().add(panel);
        getContentPane().setBackground(Color.WHITE);
        this.setBackground(Color.WHITE);
        JLabel lblNewLabel = new JLabel("");
    //    lblNewLabel.setIcon(new ImageIcon(prediction.class.getResource("/images/Health.png")));
        lblNewLabel.setBounds(462, 40, 566, 356);
        panel.add(lblNewLabel);

        monaffichage = new JTextArea();
        monaffichage.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 24));
        monaffichage.setBounds(30, 100, 920, 171);
        
         monaffichage2 = new JTextArea();
         monaffichage2.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 18));
          monaffichage2.setBounds(30, 200, 920, 300);
        
        
        int m = 0 ; 
double maxVal = resultatPrediction[0];
        for (int i = 1; i <resultatPrediction.length ; i++) {
            if (resultatPrediction[i]>maxVal){
                maxVal = resultatPrediction[i];
                 m=i ;
            }
           
        }
        
        
         String bestCrop="";
       

    switch (m) {
        case 0:
            bestCrop = "apple";
            break;
        case 1:
            bestCrop = "banana";
            break;
        case 2:
            bestCrop = "blackgram";
            break;
        case 3:
            bestCrop = "chickpea";
            break;
        case 4:
            bestCrop = "coconut";
            break;
        case 5:
            bestCrop = "coffe";
            break;
        case 6:
            bestCrop = "cotton";
            break;
        case 7:
            bestCrop = "grapes";
            break;
        
        case 8 :
            bestCrop = "jute"  ;
            break;
        case 9 :
             bestCrop = "kidneybeans" ; 
             break;
        case 10 :
             bestCrop = "lentil" ; 
             break;
        case 11 :
             bestCrop = "maize" ; 
             break;
        case 12 :
             bestCrop = "mango" ; 
             break;
        case 13 :
             bestCrop = "mothbeans" ; 
             break;
        case 14 :
             bestCrop = "mungbean" ; 
             break; 
        case 15 :
             bestCrop = "muskmelon" ; 
             break;
        case 16 :
             bestCrop = "orang" ; 
             break;
        case 17 :
             bestCrop = "papaya" ; 
             break;
        case 18 :
             bestCrop = "pigeonpeas" ; 
             break;
        case 19 :
             bestCrop = "pomegranate" ; 
             break;
        case 20 :
             bestCrop = "rice" ; 
             break;
             case 21 :
             bestCrop = "watermelon" ; 
             break;
        
    }
        
    String[] valeurs = {
            "apple", "banana", "blackgram", "chickpea", "coconut", "coffee", "cotton",
            "grapes", "jute", "kidneybeans", "lentil", "maize", "mango", "mothbeans",
            "mungbean", "muskmelon", "orange", "papaya", "pigeonpeas", "pomegranate",
            "rice", "watermelon"
        };
    
        DecimalFormat df = new DecimalFormat("#0.00");
        
   
       if (resultatPrediction != null)
        {
            monaffichage.append("La meilleur plante pour votre sol  est : "+bestCrop +"\n Avec une probabilité de : "+df.format(resultatPrediction[m]*100)+"%");
            for (int i = 0; i < valeurs.length; i++) {
            monaffichage2.append(valeurs[i] + ": " + df.format(resultatPrediction[i]*100) + "  " + ((i + 1) % 3 == 0 ? "\n" : ""));
        }
        }
       
//        JScrollPane scrollText = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
//        scrollText.add(monaffichage);


        panel.add(monaffichage);
        panel.add(monaffichage2);
//        panel.add(scrollText);


        JLabel titre = new JLabel("Résultat");
        titre.setFont(new Font("/Fonts/Roboto-Medium.ttf", Font.BOLD, 24));

        //titre.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
        titre.setBounds(309, 10, 300, 44);
        panel.add(titre);

        retour = new JButton("Retour");
        retour.setBounds(55, 437, 133, 44);
        panel.add(retour);

        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    public JTextArea getMonaffichage() {
        return monaffichage;
    }

    public void setMonaffichage(JTextArea monaffichage) {
        this.monaffichage = monaffichage;
    }

    public JButton getRetour() {
        return retour;
    }

    public void setRetour(JButton retour) {
        this.retour = retour;
    }

    public double[] getresultatPrediction() {
        return resultatPrediction;
    }
    public void setresultatPrediction(double[] resultatPrediction) {
        this.resultatPrediction = resultatPrediction;
    }

}
