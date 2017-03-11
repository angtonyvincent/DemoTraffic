/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import objet.*;

/**
 * @version 1352034
 * @author Ang Tony Vincent
 */
public class Creation extends JPanel implements MouseMotionListener {

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
     * les routes
     */
    int routes;

    /**
     * les véhicules
     */
    int motocyclettes, voitures, bus;

    /**
     * les attributs pour le glisser-déposer
     */
    int type, uX, uY;
    Point point = null;

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
     * le constructeur de la classe Creation
     *
     * @param t la taille
     * @param u nombre d'unités
     * @param r nombre de routes
     * @param m nombre de motocyclettes
     * @param v nombre de voitures
     * @param b nombre de bus
     */
    public Creation(int t, int u, int r, int m, int v, int b) {
        try {
            envi = new Environnement();
            addMouseMotionListener(this);
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    point = e.getPoint();
                    Motocyclette m;
                    Voiture v;
                    Bus b;
                    switch (type) {
                        case 0:
                            if (routes > 0) {
                                if (checkButton(52, 23, 6, 2)) {
                                    type = 1;
                                } else if (checkButton(52, 26, 2, 6)) {
                                    type = 2;
                                }
                            } else if (motocyclettes > 0) {
                                if (checkButton(52, 8, 2, 2)) {
                                    type = 3;
                                } else if (checkButton(57, 8, 2, 2)) {
                                    type = 4;
                                }
                            } else if (voitures > 0) {
                                if (checkButton(52, 13, 2, 2)) {
                                    type = 5;
                                } else if (checkButton(57, 13, 2, 2)) {
                                    type = 6;
                                }
                            } else if (bus > 0) {
                                if (checkButton(52, 18, 4, 2)) {
                                    type = 7;
                                } else if (checkButton(57, 18, 2, 4)) {
                                    type = 8;
                                }
                            } else {

                            }
                            break;

                        case 1:
                            type = 0;
                            if (checkStreet(1)) {
                                routes--;
                                for (int i = 0; i < envi.unites; i++) {
                                    setStreet(envi.carte[uX][i], 1, 3);
                                    setStreet(envi.carte[uX + 1][i], 2, 3);
                                    setStreet(envi.carte[uX + 2][i], 3, 3);
                                    setStreet(envi.carte[uX + 3][i], 3, 4);
                                    setStreet(envi.carte[uX + 4][i], 2, 4);
                                    setStreet(envi.carte[uX + 5][i], 1, 4);
                                }
                            }
                            break;

                        case 2:
                            type = 0;
                            if (checkStreet(2)) {
                                routes--;
                                for (int i = 0; i < envi.unites; i++) {
                                    setStreet(envi.carte[i][uY], 1, 2);
                                    setStreet(envi.carte[i][uY + 1], 2, 2);
                                    setStreet(envi.carte[i][uY + 2], 3, 2);
                                    setStreet(envi.carte[i][uY + 3], 3, 1);
                                    setStreet(envi.carte[i][uY + 4], 2, 1);
                                    setStreet(envi.carte[i][uY + 5], 1, 1);
                                }
                            }
                            break;

                        case 3:
                            type = 0;
                            if (envi.carte[uX][uY].getDirection() == 1) {
                                m = new Motocyclette(uX, uY, 0, 0, 1, true);
                            } else {
                                m = new Motocyclette(uX, uY, 0, 0, 2, true);
                            }
                            if (m.checkUnite(envi.carte)) {
                                motocyclettes--;
                                occupees++;
                                envi.vehicules.add(m);
                                envi.carte[uX][uY].setEtat(true);
                            }
                            break;

                        case 4:
                            type = 0;
                            if (envi.carte[uX][uY].getDirection() == 3) {
                                m = new Motocyclette(uX, uY, 0, 0, 3, true);
                            } else {
                                m = new Motocyclette(uX, uY, 0, 0, 4, true);
                            }
                            if (m.checkUnite(envi.carte)) {
                                motocyclettes--;
                                occupees++;
                                envi.vehicules.add(m);
                                envi.carte[uX][uY].setEtat(true);
                            }
                            break;

                        case 5:
                            type = 0;
                            if (envi.carte[uX][uY].getDirection() == 1) {
                                v = new Voiture(uX + 1, uY, 0, 0, 1, true);
                            } else {
                                v = new Voiture(uX, uY, 0, 0, 2, true);
                            }
                            if (v.checkUnite(envi.carte)) {
                                voitures--;
                                occupees++;
                                envi.vehicules.add(v);
                                envi.carte[uX][uY].setEtat(true);
                                envi.carte[uX + 1][uY].setEtat(true);
                            }
                            break;

                        case 6:
                            type = 0;
                            if (envi.carte[uX][uY].getDirection() == 3) {
                                v = new Voiture(uX, uY + 1, 0, 0, 3, true);
                            } else {
                                v = new Voiture(uX, uY, 0, 0, 4, true);
                            }
                            if (v.checkUnite(envi.carte)) {
                                voitures--;
                                occupees++;
                                envi.vehicules.add(v);
                                envi.carte[uX][uY].setEtat(true);
                                envi.carte[uX][uY + 1].setEtat(true);
                            }
                            break;

                        case 7:
                            type = 0;
                            if (envi.carte[uX][uY].getDirection() == 1) {
                                b = new Bus(uX + 3, uY, 0, 0, 1, true);
                            } else {
                                b = new Bus(uX, uY, 0, 0, 2, true);
                            }
                            if (b.checkUnite(envi.carte)) {
                                bus--;
                                occupees++;
                                envi.vehicules.add(b);
                                envi.carte[uX][uY].setEtat(true);
                                envi.carte[uX + 1][uY].setEtat(true);
                                envi.carte[uX + 2][uY].setEtat(true);
                                envi.carte[uX + 3][uY].setEtat(true);
                            }
                            break;

                        case 8:
                            type = 0;
                            if (envi.carte[uX][uY].getDirection() == 3) {
                                b = new Bus(uX, uY + 3, 0, 0, 3, true);
                            } else {
                                b = new Bus(uX, uY, 0, 0, 4, true);
                            }
                            if (b.checkUnite(envi.carte)) {
                                bus--;
                                occupees++;
                                envi.vehicules.add(b);
                                envi.carte[uX][uY].setEtat(true);
                                envi.carte[uX][uY + 1].setEtat(true);
                                envi.carte[uX][uY + 2].setEtat(true);
                                envi.carte[uX][uY + 3].setEtat(true);
                            }

                        default:
                            break;
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    //point = null;
                }
            });

            this.uX = 0;
            this.uY = 0;
            this.type = 0;
            this.occupees = 0;
            this.routes = r;
            this.motocyclettes = m;
            this.voitures = v;
            this.bus = b;
            this.cases = t / u;
            this.envi.unites = u;
            this.taille = t;

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

            for (int i = 0; i < this.envi.unites; i++) {
                for (int j = 0; j < this.envi.unites; j++) {
                    envi.carte[i][j] = new Unite(i, j, 0, 0, 0, false);
                }
            }
        } catch (IOException ex) {
            System.out.println("Ne peut pas trouver les images!" + ex);
        }
    }

    public void setStreet(Unite u, int type, int direction) {
        if (u.getType() == 0) {
            u.setType(type);
            u.setDirection(direction);
        } else {
            u.setType(4);
            u.setDirection(0);
        }
    }

    public boolean checkStreet(int direction) {
        int leftX, rightX, leftY, rightY;
        if (direction == 1) {
            leftX = this.uX - 1;
            leftY = this.uY;
            rightX = this.uX + 6;
            rightY = this.uY;
            if (leftX < 0 || leftY < 0) {
                if ((this.envi.carte[rightX][rightY].getType() == 0
                        || this.envi.carte[rightX][rightY].getDirection() == 1
                        || this.envi.carte[rightX][rightY].getDirection() == 2)
                        && (this.envi.carte[this.uX][this.uY].getType() == 0
                        || this.envi.carte[this.uX][this.uY].getDirection() == 1
                        || this.envi.carte[this.uX][this.uY].getDirection() == 2)) {
                    return true;
                }
            } else if (rightX > 49 || rightY > 49) {
                if ((this.envi.carte[leftX][leftY].getType() == 0
                        || this.envi.carte[leftX][leftY].getDirection() == 1
                        || this.envi.carte[leftX][leftY].getDirection() == 2)
                        && (this.envi.carte[this.uX][this.uY].getType() == 0
                        || this.envi.carte[this.uX][this.uY].getDirection() == 1
                        || this.envi.carte[this.uX][this.uY].getDirection() == 2)) {
                    return true;
                }
            } else if ((this.envi.carte[rightX][rightY].getType() == 0
                    || this.envi.carte[rightX][rightY].getDirection() == 1
                    || this.envi.carte[rightX][rightY].getDirection() == 2)
                    && (this.envi.carte[leftX][leftY].getType() == 0
                    || this.envi.carte[leftX][leftY].getDirection() == 1
                    || this.envi.carte[leftX][leftY].getDirection() == 2)
                    && (this.envi.carte[this.uX][this.uY].getType() == 0
                    || this.envi.carte[this.uX][this.uY].getDirection() == 1
                    || this.envi.carte[this.uX][this.uY].getDirection() == 2)) {
                return true;
            }
        } else {
            leftX = this.uX;
            leftY = this.uY - 1;
            rightX = this.uX;
            rightY = this.uY + 6;
            if (leftX < 0 || leftY < 0) {
                if ((this.envi.carte[rightX][rightY].getType() == 0
                        || this.envi.carte[rightX][rightY].getDirection() == 3
                        || this.envi.carte[rightX][rightY].getDirection() == 4)
                        && (this.envi.carte[this.uX][this.uY].getType() == 0
                        || this.envi.carte[this.uX][this.uY].getDirection() == 3
                        || this.envi.carte[this.uX][this.uY].getDirection() == 4)) {
                    return true;
                }
            } else if (rightX > 49 || rightY > 49) {
                if ((this.envi.carte[leftX][leftY].getType() == 0
                        || this.envi.carte[leftX][leftY].getDirection() == 3
                        || this.envi.carte[leftX][leftY].getDirection() == 4)
                        && (this.envi.carte[this.uX][this.uY].getType() == 0
                        || this.envi.carte[this.uX][this.uY].getDirection() == 3
                        || this.envi.carte[this.uX][this.uY].getDirection() == 4)) {
                    return true;
                }
            } else if ((this.envi.carte[rightX][rightY].getType() == 0
                    || this.envi.carte[rightX][rightY].getDirection() == 3
                    || this.envi.carte[rightX][rightY].getDirection() == 4)
                    && (this.envi.carte[leftX][leftY].getType() == 0
                    || this.envi.carte[leftX][leftY].getDirection() == 3
                    || this.envi.carte[leftX][leftY].getDirection() == 4)
                    && (this.envi.carte[this.uX][this.uY].getType() == 0
                    || this.envi.carte[this.uX][this.uY].getDirection() == 3
                    || this.envi.carte[this.uX][this.uY].getDirection() == 4)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkButton(int x, int y, int w, int h) {
        return x * this.cases < point.x && point.x < (x + w) * this.cases
                && y * this.cases < point.y && point.y < (y + h) * this.cases;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // les positions
        g.setColor(Color.BLACK);
        for (int i = 0; i <= this.envi.unites; i++) {
            g.drawLine(0, i * this.cases, this.taille, i * this.cases);
            g.drawLine(i * this.cases, 0, i * this.cases, this.taille);
        }

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

        // les carrefours
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
                    g.fillRect(i * cases + 1, j * cases + 1, cases - 1, cases - 1);
                }
            }
        }

        // les cadres
        g.setColor(Color.BLACK);
        g.drawLine(0, 0, 0, this.taille + this.cases);
        g.drawLine(0, 0, this.taille + this.cases, 0);

        g.setColor(Color.WHITE);
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

        g.setColor(Color.BLACK);
        g.drawString("Unités totales: " + this.envi.unites * this.envi.unites, this.taille + 2 * this.cases, 2 * this.cases);
        g.drawString("Unités occupées: " + this.occupees, this.taille + 2 * this.cases, 4 * this.cases);

        // motocyclette
        g.drawString("Encore: " + this.motocyclettes + " motocyclettes", this.taille + 2 * this.cases, 7 * this.cases);
        g.drawImage(this.motocyclette1, this.taille + 2 * this.cases, 8 * this.cases, this.cases, this.cases, this);
        g.drawImage(this.motocyclette2, this.taille + 3 * this.cases, 9 * this.cases, this.cases, this.cases, this);
        g.drawImage(this.motocyclette3, this.taille + 7 * this.cases, 8 * this.cases, this.cases, this.cases, this);
        g.drawImage(this.motocyclette4, this.taille + 8 * this.cases, 9 * this.cases, this.cases, this.cases, this);

        // voiture
        g.drawString("Encore: " + this.voitures + " voitures", this.taille + 2 * this.cases, 12 * this.cases);
        g.drawImage(this.voiture1, this.taille + 2 * this.cases, 13 * this.cases, 2 * this.cases, this.cases, this);
        g.drawImage(this.voiture2, this.taille + 2 * this.cases, 14 * this.cases, 2 * this.cases, this.cases, this);
        g.drawImage(this.voiture3, this.taille + 7 * this.cases, 13 * this.cases, this.cases, 2 * this.cases, this);
        g.drawImage(this.voiture4, this.taille + 8 * this.cases, 13 * this.cases, this.cases, 2 * this.cases, this);

        // bus
        g.drawString("Encore: " + this.bus + " bus", this.taille + 2 * this.cases, 17 * this.cases);
        g.drawImage(this.bus1, this.taille + 2 * this.cases, 18 * this.cases, 4 * this.cases, this.cases, this);
        g.drawImage(this.bus2, this.taille + 2 * this.cases, 19 * this.cases, 4 * this.cases, this.cases, this);
        g.drawImage(this.bus3, this.taille + 7 * this.cases, 18 * this.cases, this.cases, 4 * this.cases, this);
        g.drawImage(this.bus4, this.taille + 8 * this.cases, 18 * this.cases, this.cases, 4 * this.cases, this);

        // routes
        g.drawString("Encore: " + this.routes + " routes", this.taille + 2 * this.cases, 34 * this.cases);
        g.drawImage(this.routeHorizontale, this.taille + 2 * this.cases, 23 * this.cases, 6 * this.cases, this.cases, this);
        g.drawImage(this.routeHorizontale, this.taille + 2 * this.cases, 24 * this.cases, 6 * this.cases, this.cases, this);
        g.drawImage(this.routeVerticale, this.taille + 2 * this.cases, 26 * this.cases, this.cases, 6 * this.cases, this);
        g.drawImage(this.routeVerticale, this.taille + 3 * this.cases, 26 * this.cases, this.cases, 6 * this.cases, this);

        g.setColor(Color.GREEN);
        g.drawRect(this.taille + 2 * this.cases, 8 * this.cases, 2 * this.cases, 2 * this.cases);
        g.drawRect(this.taille + 7 * this.cases, 8 * this.cases, 2 * this.cases, 2 * this.cases);
        g.drawRect(this.taille + 2 * this.cases, 13 * this.cases, 2 * this.cases, 2 * this.cases);
        g.drawRect(this.taille + 7 * this.cases, 13 * this.cases, 2 * this.cases, 2 * this.cases);
        g.drawRect(this.taille + 2 * this.cases, 18 * this.cases, 4 * this.cases, 2 * this.cases);
        g.drawRect(this.taille + 7 * this.cases, 18 * this.cases, 2 * this.cases, 4 * this.cases);
        g.drawRect(this.taille + 2 * this.cases, 23 * this.cases, 6 * this.cases, 2 * this.cases);
        g.drawRect(this.taille + 2 * this.cases, 26 * this.cases, 2 * this.cases, 6 * this.cases);

        g.setColor(Color.RED);
        if (this.routes > 0) {
            g.drawString("Construction des routes en cours", this.taille + 2 * this.cases, 36 * this.cases);
        } else if (this.motocyclettes > 0) {
            g.drawString("Placement des motocyclettes en cours", this.taille + 2 * this.cases, 36 * this.cases);
        } else if (this.voitures > 0) {
            g.drawString("Placement des voitures en cours", this.taille + 2 * this.cases, 36 * this.cases);
        } else if (this.bus > 0) {
            g.drawString("Placement des bus en cours", this.taille + 2 * this.cases, 36 * this.cases);
        } else {
            this.envi.sauvegarderAFichier();
            g.drawString("Les données sont sauvegardées dans", this.taille + 2 * this.cases, 36 * this.cases);
            g.drawString("Reseau.txt et EtatCourrant.txt", this.taille + 2 * this.cases, 37 * this.cases);
            g.drawString("Fermez cette fenêtre", this.taille + 2 * this.cases, 38 * this.cases);
            g.drawString("Utilisez le bouton Charger pour vérifier", this.taille + 2 * this.cases, 39 * this.cases);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        point = e.getPoint();
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        switch (type) {
            case 1:
                this.uX = this.point.x / this.cases;
                this.uY = this.point.y / this.cases;
                if (this.uY > 49) {
                    this.uY = 49;
                }
                if (this.uX > 44) {
                    this.uX = 44;
                }
                for (int i = 0; i < this.envi.unites; i++) {
                    g.drawImage(this.routeHorizontale, this.uX * this.cases, i * this.cases, 6 * this.cases, this.cases, this);
                }
                break;

            case 2:
                this.uX = this.point.x / this.cases;
                this.uY = this.point.y / this.cases;
                if (this.uX > 49) {
                    this.uX = 49;
                }
                if (this.uY > 44) {
                    this.uY = 44;
                }
                for (int i = 0; i < this.envi.unites; i++) {
                    g.drawImage(this.routeVerticale, i * this.cases, this.uY * this.cases, this.cases, 6 * this.cases, this);
                }
                break;

            case 3:
                g.setColor(Color.GREEN);
                for (int i = 0; i < this.envi.unites; i++) {
                    for (int j = 0; j < this.envi.unites; j++) {
                        if (this.envi.carte[i][j].getType() == 1 && !this.envi.carte[i][j].isEtat()
                                && (this.envi.carte[i][j].getDirection() == 1 || this.envi.carte[i][j].getDirection() == 2)) {
                            g.fillRect(i * this.cases, j * this.cases, this.cases, this.cases);
                        }
                    }
                }
                this.uX = this.point.x / this.cases;
                this.uY = this.point.y / this.cases;
                if (this.uX > 49) {
                    this.uX = 49;
                }
                if (this.uY > 49) {
                    this.uY = 49;
                }
                g.drawImage(this.motocyclette1, this.uX * this.cases, this.uY * this.cases, this.cases, this.cases, this);
                break;

            case 4:
                g.setColor(Color.GREEN);
                for (int i = 0; i < this.envi.unites; i++) {
                    for (int j = 0; j < this.envi.unites; j++) {
                        if (this.envi.carte[i][j].getType() == 1 && !this.envi.carte[i][j].isEtat()
                                && (this.envi.carte[i][j].getDirection() == 3 || this.envi.carte[i][j].getDirection() == 4)) {
                            g.fillRect(i * this.cases, j * this.cases, this.cases, this.cases);
                        }
                    }
                }
                this.uX = this.point.x / this.cases;
                this.uY = this.point.y / this.cases;
                if (this.uX > 49) {
                    this.uX = 49;
                }
                if (this.uY > 49) {
                    this.uY = 49;
                }
                g.drawImage(this.motocyclette3, this.uX * this.cases, this.uY * this.cases, this.cases, this.cases, this);
                break;

            case 5:
                g.setColor(Color.GREEN);
                for (int i = 0; i < this.envi.unites; i++) {
                    for (int j = 0; j < this.envi.unites; j++) {
                        if (this.envi.carte[i][j].getType() == 2 && !this.envi.carte[i][j].isEtat()
                                && (this.envi.carte[i][j].getDirection() == 1 || this.envi.carte[i][j].getDirection() == 2)) {
                            g.fillRect(i * this.cases, j * this.cases, this.cases, this.cases);
                        }
                    }
                }
                this.uX = this.point.x / this.cases;
                this.uY = this.point.y / this.cases;
                if (this.uX > 48) {
                    this.uX = 48;
                }
                if (this.uY > 49) {
                    this.uY = 49;
                }
                g.drawImage(this.voiture1, this.uX * this.cases, this.uY * this.cases, 2 * this.cases, this.cases, this);
                break;

            case 6:
                g.setColor(Color.GREEN);
                for (int i = 0; i < this.envi.unites; i++) {
                    for (int j = 0; j < this.envi.unites; j++) {
                        if (this.envi.carte[i][j].getType() == 2 && !this.envi.carte[i][j].isEtat()
                                && (this.envi.carte[i][j].getDirection() == 3 || this.envi.carte[i][j].getDirection() == 4)) {
                            g.fillRect(i * this.cases, j * this.cases, this.cases, this.cases);
                        }
                    }
                }
                this.uX = this.point.x / this.cases;
                this.uY = this.point.y / this.cases;
                if (this.uX > 49) {
                    this.uX = 49;
                }
                if (this.uY > 48) {
                    this.uY = 48;
                }
                g.drawImage(this.voiture3, this.uX * this.cases, this.uY * this.cases, this.cases, 2 * this.cases, this);
                break;

            case 7:
                g.setColor(Color.GREEN);
                for (int i = 0; i < this.envi.unites; i++) {
                    for (int j = 0; j < this.envi.unites; j++) {
                        if (this.envi.carte[i][j].getType() == 3 && !this.envi.carte[i][j].isEtat()
                                && (this.envi.carte[i][j].getDirection() == 1 || this.envi.carte[i][j].getDirection() == 2)) {
                            g.fillRect(i * this.cases, j * this.cases, this.cases, this.cases);
                        }
                    }
                }
                this.uX = this.point.x / this.cases;
                this.uY = this.point.y / this.cases;
                if (this.uX > 46) {
                    this.uX = 46;
                }
                if (this.uY > 49) {
                    this.uY = 49;
                }
                g.drawImage(this.bus1, this.uX * this.cases, this.uY * this.cases, 4 * this.cases, this.cases, this);
                break;

            case 8:
                g.setColor(Color.GREEN);
                for (int i = 0; i < this.envi.unites; i++) {
                    for (int j = 0; j < this.envi.unites; j++) {
                        if (this.envi.carte[i][j].getType() == 3 && !this.envi.carte[i][j].isEtat()
                                && (this.envi.carte[i][j].getDirection() == 3 || this.envi.carte[i][j].getDirection() == 4)) {
                            g.fillRect(i * this.cases, j * this.cases, this.cases, this.cases);
                        }
                    }
                }
                this.uX = this.point.x / this.cases;
                this.uY = this.point.y / this.cases;
                if (this.uX > 49) {
                    this.uX = 49;
                }
                if (this.uY > 46) {
                    this.uY = 46;
                }
                g.drawImage(this.bus3, this.uX * this.cases, this.uY * this.cases, this.cases, 4 * this.cases, this);
                break;

            default:
                break;
        }
    }

}
