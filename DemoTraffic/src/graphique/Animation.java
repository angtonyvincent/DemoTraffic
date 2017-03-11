/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;
import objet.*;

/**
 * @version 1352034
 * @author Ang Tony Vincent
 */
public class Animation extends JPanel implements ActionListener {

    /**
     * l'environnement concernant les routes, les véhicules
     */
    Environnement envi;

    /**
     * la taille de la carte
     */
    int taille;

    /**
     * la taille d'une unité
     */
    int cases;

    /**
     * les unités occupées
     */
    int occupees;

    /**
     * les images de véhicules
     */
    Image motocyclette1 = null;
    Image motocyclette2 = null;
    Image motocyclette3 = null;
    Image motocyclette4 = null;
    Image voiture1 = null;
    Image voiture2 = null;
    Image voiture3 = null;
    Image voiture4 = null;
    Image bus1 = null;
    Image bus2 = null;
    Image bus3 = null;
    Image bus4 = null;

    /**
     * les images de routes
     */
    Image routeVerticale = null;
    Image routeHorizontale = null;
    Image carrefour = null;

    /**
     * timer pour repeindre après un instant
     */
    Timer timer;

    /**
     * le constructeur de la classe Animation
     *
     * @param e l'environnement actuel
     * @param taille la taille de l'écran
     * @param delai le délai pour le timer
     */
    public Animation(Environnement e, int taille, int delai) {
        try {
            this.envi = e;
            this.timer = new Timer(delai, this);
            this.cases = taille / e.unites;
            this.taille = taille;
            this.occupees = 0;
            for (Unite[] carte : this.envi.carte) {
                for (Unite item : carte) {
                    if (item.isEtat()) {
                        this.occupees++;
                    }
                }
            }

            this.motocyclette1 = ImageIO.read(new File("img/m1.png"));
            this.motocyclette2 = ImageIO.read(new File("img/m2.png"));
            this.motocyclette3 = ImageIO.read(new File("img/m3.png"));
            this.motocyclette4 = ImageIO.read(new File("img/m4.png"));
            this.voiture1 = ImageIO.read(new File("img/c1.png"));
            this.voiture2 = ImageIO.read(new File("img/c2.png"));
            this.voiture3 = ImageIO.read(new File("img/c3.png"));
            this.voiture4 = ImageIO.read(new File("img/c4.png"));
            this.bus1 = ImageIO.read(new File("img/b1.png"));
            this.bus2 = ImageIO.read(new File("img/b2.png"));
            this.bus3 = ImageIO.read(new File("img/b3.png"));
            this.bus4 = ImageIO.read(new File("img/b4.png"));
            this.routeHorizontale = ImageIO.read(new File("img/rh.png"));
            this.routeVerticale = ImageIO.read(new File("img/rv.png"));
            this.carrefour = ImageIO.read(new File("img/rc.png"));
        } catch (IOException ex) {
            System.out.println("Ne peut pas trouver les images!" + ex);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // les routes
        for (int i = 0; i < envi.unites; i++) {
            for (int j = 0; j < envi.unites; j++) {
                if (envi.carte[i][j].getType() == 1 && envi.carte[i][j].getDirection() == 2) {
                    g.drawImage(this.routeVerticale, i * this.cases + 1, j * this.cases + 1, this.cases, 6 * this.cases, this);
                } else if (envi.carte[i][j].getType() == 1 && envi.carte[i][j].getDirection() == 3) {
                    g.drawImage(this.routeHorizontale, i * this.cases + 1, j * this.cases + 1, 6 * this.cases, this.cases, this);
                }
            }
        }

        // carrefour
        boolean carrefour = false;
        for (int i = 0; i < envi.unites; i++) {
            for (int j = 0; j < envi.unites; j++) {
                if (envi.carte[i][j].getType() == 4) {
                    g.drawImage(this.carrefour,
                            i * this.cases - this.cases / 2 + 1,
                            j * this.cases - this.cases / 2 + 1,
                            7 * this.cases, 7 * this.cases, this);
                    j += 6;
                    carrefour = true;
                }
            }
            if (carrefour) {
                i += 6;
                carrefour = false;
            }
        }

        // les obtacles
        for (int i = 0; i < envi.unites; i++) {
            for (int j = 0; j < envi.unites; j++) {
                if (envi.carte[i][j].getType() == 0) {
                    g.setColor(Color.WHITE); // pas route (obstacle)
                    g.fillRect(i * cases + 1, j * cases + 1, cases, cases);
                }
            }
        }

        // les cadres
        g.setColor(Color.WHITE);
        g.drawLine(0, 0, 0, this.taille + this.cases);
        g.drawLine(0, 0, this.taille + this.cases, 0);
        g.fillRect(this.taille + 1, 0, this.cases, this.taille + this.cases);
        g.fillRect(0, this.taille + 1, this.taille + this.cases, this.cases);

        // les positions
        g.setColor(Color.BLACK);
        for (int i = 0; i <= envi.unites; i++) {
            g.drawString(i + "", envi.unites * cases + 2, i * cases);
            if (i != 0) {
                g.drawString(i + "", (i - 1) * cases, (envi.unites + 1) * cases);
            }
        }

        // les véhicules
        for (int i = 0; i < envi.vehicules.size(); i++) {
            if (envi.vehicules.elementAt(i) instanceof Motocyclette) {
                switch (envi.vehicules.elementAt(i).getDirection()) {
                    case 1:
                        g.drawImage(this.motocyclette1,
                                envi.vehicules.elementAt(i).getX() * this.cases + 1,
                                envi.vehicules.elementAt(i).getY() * this.cases + 1,
                                this.cases, this.cases, this);
                        break;
                    case 2:
                        g.drawImage(this.motocyclette2,
                                envi.vehicules.elementAt(i).getX() * this.cases + 1,
                                envi.vehicules.elementAt(i).getY() * this.cases + 1,
                                this.cases, this.cases, this);
                        break;
                    case 3:
                        g.drawImage(this.motocyclette3,
                                envi.vehicules.elementAt(i).getX() * this.cases + 1,
                                envi.vehicules.elementAt(i).getY() * this.cases + 1,
                                this.cases, this.cases, this);
                        break;
                    case 4:
                        g.drawImage(this.motocyclette4,
                                envi.vehicules.elementAt(i).getX() * this.cases + 1,
                                envi.vehicules.elementAt(i).getY() * this.cases + 1,
                                this.cases, this.cases, this);
                        break;
                    default:
                        break;
                }
            } else if (envi.vehicules.elementAt(i) instanceof Voiture) {
                switch (envi.vehicules.elementAt(i).getDirection()) {
                    case 1:
                        g.drawImage(this.voiture1,
                                (envi.vehicules.elementAt(i).getX() - 1) * this.cases + 1,
                                envi.vehicules.elementAt(i).getY() * this.cases + 1,
                                2 * this.cases, this.cases, this);
                        break;
                    case 2:
                        g.drawImage(this.voiture2,
                                envi.vehicules.elementAt(i).getX() * this.cases + 1,
                                envi.vehicules.elementAt(i).getY() * this.cases + 1,
                                2 * this.cases, this.cases, this);
                        break;
                    case 3:
                        g.drawImage(this.voiture3,
                                envi.vehicules.elementAt(i).getX() * this.cases + 1,
                                (envi.vehicules.elementAt(i).getY() - 1) * this.cases + 1,
                                this.cases, 2 * this.cases, this);
                        break;
                    case 4:
                        g.drawImage(this.voiture4,
                                envi.vehicules.elementAt(i).getX() * this.cases + 1,
                                envi.vehicules.elementAt(i).getY() * this.cases + 1,
                                this.cases, 2 * this.cases, this);
                        break;
                    default:
                        break;
                }
            } else {
                switch (envi.vehicules.elementAt(i).getDirection()) {
                    case 1:
                        g.drawImage(this.bus1,
                                (envi.vehicules.elementAt(i).getX() - 3) * this.cases + 1,
                                envi.vehicules.elementAt(i).getY() * this.cases + 1,
                                4 * this.cases, this.cases, this);
                        break;
                    case 2:
                        g.drawImage(this.bus2,
                                envi.vehicules.elementAt(i).getX() * this.cases + 1,
                                envi.vehicules.elementAt(i).getY() * this.cases + 1,
                                4 * this.cases, this.cases, this);
                        break;
                    case 3:
                        g.drawImage(this.bus3,
                                envi.vehicules.elementAt(i).getX() * this.cases + 1,
                                (envi.vehicules.elementAt(i).getY() - 3) * this.cases + 1,
                                this.cases, 4 * this.cases, this);
                        break;
                    case 4:
                        g.drawImage(this.bus4,
                                envi.vehicules.elementAt(i).getX() * this.cases + 1,
                                envi.vehicules.elementAt(i).getY() * this.cases + 1,
                                this.cases, 4 * this.cases, this);
                        break;
                    default:
                        break;
                }
            }
        }

        g.drawString("Unités totales: " + this.envi.unites * this.envi.unites, this.taille + this.cases, 2 * this.cases);
        g.drawString("Unités occupées: " + this.occupees, this.taille + this.cases, 4 * this.cases);
        this.timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < envi.vehicules.size(); i++) {
            envi.vehicules.elementAt(i).deplacer(envi.carte);
        }
        repaint();
    }

}