/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objet;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import main.DBConnection;

/**
 * @version 1352034
 * @author Ang Tony Vincent
 */
public class Environnement {

    public int unites = 50;
    public Unite[][] carte = new Unite[unites][unites];
    public Vector<Vehicule> vehicules = new Vector();

    /**
     * le constructeur par les nombres de véhicules
     *
     * @param nMoto le nombre de motocyclettes
     * @param nVoiture le nombre de voitures
     * @param nBus le nombre de bus
     */
    public Environnement(int nMoto, int nVoiture, int nBus) {
        creerCarteParDefaut();
        creerVehiculeAleatoire(nMoto, nVoiture, nBus);
    }

    /**
     * le constructeur par défaut de la classe Environnement
     */
    public Environnement() {

    }

    /**
     * créer par défaut une carte pour tester
     */
    public final void creerCarteParDefaut() {
        try {
            // pas route (obstacle)
            for (int i = 6; i < 44; i++) {
                for (int j = 6; j < 44; j++) {
                    carte[i][j] = new Unite(i, j, 0, 0, 0, false);
                }
            }

            // voie pour bus
            for (int i = 0; i < 50; i++) {
                carte[i][2] = new Unite(i, 2, 3, 0, 2, false);
                carte[i][3] = new Unite(i, 3, 3, 0, 1, false);
                carte[i][24] = new Unite(i, 24, 3, 0, 2, false);
                carte[i][25] = new Unite(i, 25, 3, 0, 1, false);
                carte[i][46] = new Unite(i, 46, 3, 0, 2, false);
                carte[i][47] = new Unite(i, 47, 3, 0, 1, false);
                carte[2][i] = new Unite(2, i, 3, 0, 3, false);
                carte[3][i] = new Unite(3, i, 3, 0, 4, false);
                carte[24][i] = new Unite(24, i, 3, 0, 3, false);
                carte[25][i] = new Unite(25, i, 3, 0, 4, false);
                carte[46][i] = new Unite(46, i, 3, 0, 3, false);
                carte[47][i] = new Unite(47, i, 3, 0, 4, false);
            }

            // voie pour voiture
            for (int i = 0; i < 50; i++) {
                carte[i][1] = new Unite(i, 1, 2, 0, 2, false);
                carte[i][4] = new Unite(i, 4, 2, 0, 1, false);
                carte[i][23] = new Unite(i, 23, 2, 0, 2, false);
                carte[i][26] = new Unite(i, 26, 2, 0, 1, false);
                carte[i][45] = new Unite(i, 45, 2, 0, 2, false);
                carte[i][48] = new Unite(i, 48, 2, 0, 1, false);
                carte[1][i] = new Unite(1, i, 2, 0, 3, false);
                carte[4][i] = new Unite(4, i, 2, 0, 4, false);
                carte[23][i] = new Unite(23, i, 2, 0, 3, false);
                carte[26][i] = new Unite(26, i, 2, 0, 4, false);
                carte[45][i] = new Unite(45, i, 2, 0, 3, false);
                carte[48][i] = new Unite(48, i, 2, 0, 4, false);
            }

            // voie pour motocyclette
            for (int i = 0; i < 50; i++) {
                carte[i][0] = new Unite(i, 0, 1, 0, 2, false);
                carte[i][5] = new Unite(i, 5, 1, 1, 1, false);
                carte[i][22] = new Unite(i, 22, 1, 0, 2, false);
                carte[i][27] = new Unite(i, 27, 1, 1, 1, false);
                carte[i][44] = new Unite(i, 44, 1, 1, 2, false);
                carte[i][49] = new Unite(i, 49, 1, 0, 1, false);
                carte[0][i] = new Unite(0, i, 1, 0, 3, false);
                carte[5][i] = new Unite(5, i, 1, 1, 4, false);
                carte[22][i] = new Unite(22, i, 1, 0, 3, false);
                carte[27][i] = new Unite(27, i, 1, 1, 4, false);
                carte[44][i] = new Unite(44, i, 1, 1, 3, false);
                carte[49][i] = new Unite(49, i, 1, 0, 4, false);
            }

            // voie commune (Nord-ouest)
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    carte[i][j] = new Unite(i, j, 4, 2, 0, false);
                }
            }

            // voie commune (Nord-est et Sud-ouest)
            for (int i = 44; i < 50; i++) {
                for (int j = 0; j < 6; j++) {
                    carte[i][j] = new Unite(i, j, 4, 2, 0, false);
                    carte[j][i] = new Unite(j, i, 4, 2, 0, false);
                }
            }

            // voie commune (Sud-est)
            for (int i = 44; i < 50; i++) {
                for (int j = 44; j < 50; j++) {
                    carte[i][j] = new Unite(i, j, 4, 2, 0, false);
                }
            }

            // voie commune (Centre)
            for (int i = 22; i < 28; i++) {
                for (int j = 22; j < 28; j++) {
                    carte[i][j] = new Unite(i, j, 4, 2, 0, false);
                }
            }

            // voie commune (Est)
            for (int i = 44; i < 50; i++) {
                for (int j = 22; j < 28; j++) {
                    carte[i][j] = new Unite(i, j, 4, 2, 0, false);
                }
            }

            // voie commune (Ouest)
            for (int i = 0; i < 6; i++) {
                for (int j = 22; j < 28; j++) {
                    carte[i][j] = new Unite(i, j, 4, 2, 0, false);
                }
            }

            // voie commune (Sud)
            for (int i = 22; i < 28; i++) {
                for (int j = 44; j < 50; j++) {
                    carte[i][j] = new Unite(i, j, 4, 2, 0, false);
                }
            }

            // voie commune (Nord)
            for (int i = 22; i < 28; i++) {
                for (int j = 0; j < 6; j++) {
                    carte[i][j] = new Unite(i, j, 4, 2, 0, false);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception capturée dans Environnement: " + e);
        }
    }

    /**
     * créer aléatoirement une liste des véhicules pour tester
     *
     * @param nMoto le nombre de motocyclettes
     * @param nVoiture le nombre de voitures
     * @param nBus le nombre de bus
     */
    public final void creerVehiculeAleatoire(int nMoto, int nVoiture, int nBus) {
        try {
            for (int i = 0; i < nBus; i++) {
                Bus b = new Bus(carte, unites);
                vehicules.add(b);
            }
            for (int i = 0; i < nVoiture; i++) {
                Voiture v = new Voiture(carte, unites);
                vehicules.add(v);
            }
            for (int i = 0; i < nMoto; i++) {
                Motocyclette m = new Motocyclette(carte, unites);
                vehicules.add(m);
            }
        } catch (Exception e) {
            System.out.println("Exception capturée dans Environnement: " + e);
        }
    }

    /**
     * charger l'environnement à partir d'un fichier
     *
     * @return vrai si réussite ou faux si erreur
     */
    public boolean chargerDeFichier() {
        boolean rs = false;
        try {
            Path filePath = Paths.get("Reseau.txt");
            Scanner scanner = new Scanner(filePath);
            for (int i = 0; i < unites; i++) {
                for (int j = 0; j < unites; j++) {
                    if (scanner.hasNextInt()) {
                        carte[i][j].setType(scanner.nextInt());
                    } else {
                        scanner.next();
                    }
                }
            }
            for (int i = 0; i < unites; i++) {
                for (int j = 0; j < unites; j++) {
                    if (scanner.hasNextInt()) {
                        carte[i][j].setDirection(scanner.nextInt());
                    } else {
                        scanner.next();
                    }
                }
            }

            // reset the map
            for (int i = 0; i < this.carte.length; i++) {
                for (int j = 0; j < this.carte[i].length; j++) {
                    this.carte[i][j].setEtat(false);
                }
            }
            vehicules.removeAllElements();

            filePath = Paths.get("EtatCourrant.txt");
            scanner = new Scanner(filePath);
            while (scanner.hasNext()) {
                if (scanner.hasNextInt()) {
                    int type = scanner.nextInt();
                    int x = scanner.nextInt();
                    int y = scanner.nextInt();
                    int l = scanner.nextInt();
                    int v = scanner.nextInt();
                    int d = scanner.nextInt();
                    int e = scanner.nextInt();
                    switch (type) {
                        case 1:
                            if (e == 1) {
                                Motocyclette m = new Motocyclette(x, y, l, v, d, true);
                                vehicules.add(m);
                            } else {
                                Motocyclette m = new Motocyclette(x, y, l, v, d, false);
                                vehicules.add(m);
                            }
                            break;

                        case 2:
                            if (e == 1) {
                                Voiture vt = new Voiture(x, y, l, v, d, true);
                                vehicules.add(vt);
                            } else {
                                Voiture vt = new Voiture(x, y, l, v, d, false);
                                vehicules.add(vt);
                            }
                            break;

                        default:
                            if (e == 1) {
                                Bus b = new Bus(x, y, l, v, d, true);
                                vehicules.add(b);
                            } else {
                                Bus b = new Bus(x, y, l, v, d, false);
                                vehicules.add(b);
                            }
                            break;
                    }
                } else {
                    scanner.next();
                }
            }

            // set new map
            for (int l = 0; l < this.vehicules.size(); l++) {
                this.vehicules.elementAt(l).setUniteOccupe(carte, true);
            }

            rs = true;
        } catch (IOException ex) {
            Logger.getLogger(Environnement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return rs;
        }
    }

    /**
     * sauvegarder l'état actuel de l'environment dans un fichier
     *
     * @return vrai si réussite ou faux si erreur
     */
    public boolean sauvegarderAFichier() {
        boolean rs = false;
        FileWriter fw = null;
        try {
            fw = new FileWriter("Reseau.txt");
            for (int i = 0; i < unites; i++) {
                for (int j = 0; j < unites; j++) {
                    fw.write(carte[i][j].getType() + "\t");
                }
                fw.write("\r\n");
            }
            for (int i = 0; i < unites; i++) {
                for (int j = 0; j < unites; j++) {
                    fw.write(carte[i][j].getDirection() + "\t");
                }
                fw.write("\r\n");
            }
            fw.close();
            fw = new FileWriter("EtatCourrant.txt");
            for (int i = 0; i < vehicules.size(); i++) {
                if (vehicules.elementAt(i) instanceof Motocyclette) {
                    fw.write("1\t");
                } else if (vehicules.elementAt(i) instanceof Voiture) {
                    fw.write("2\t");
                } else {
                    fw.write("3\t");
                }
                fw.write(vehicules.elementAt(i).display());
            }
            fw.close();
            rs = true;
        } catch (IOException ex) {
            Logger.getLogger(Environnement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fw.close();
            } catch (IOException ex) {
                Logger.getLogger(Environnement.class.getName()).log(Level.SEVERE, null, ex);
            }
            return rs;
        }
    }

    /**
     * charger l'environnement à partir d'une base de données
     *
     * @return vrai si réussite ou faux si erreur
     */
    public boolean chargerDeBaseDeDonees() {
        boolean rs = false;
        Connection c = DBConnection.getConnection();
        Statement stm = null;
        ResultSet rss = null;
        String sql;
        try {
            stm = c.createStatement();
            sql = "SELECT * FROM route";
            rss = stm.executeQuery(sql);

            while (rss.next()) {
                int x = rss.getInt(1);
                int y = rss.getInt(2);
                int t = rss.getInt(3);
                int d = rss.getInt(4);
                this.carte[x][y].setType(t);
                this.carte[x][y].setDirection(d);
            }

            // reset the map
            for (int i = 0; i < this.carte.length; i++) {
                for (int j = 0; j < this.carte[i].length; j++) {
                    this.carte[i][j].setEtat(false);
                }
            }
            this.vehicules.removeAllElements();

            stm = c.createStatement();
            sql = "SELECT * FROM vehicule";
            rss = stm.executeQuery(sql);

            while (rss.next()) {
                int x = rss.getInt(1);
                int y = rss.getInt(2);
                int l = rss.getInt(3);
                int d = rss.getInt(4);
                int t = rss.getInt(5);
                switch (t) {
                    case 1:
                        Motocyclette m = new Motocyclette(x, y, l, 0, d, false);
                        this.vehicules.add(m);
                        break;

                    case 2:
                        Voiture v = new Voiture(x, y, l, 0, d, true);
                        this.vehicules.add(v);
                        break;

                    case 3:
                        Bus b = new Bus(x, y, l, 0, d, true);
                        vehicules.add(b);
                        break;

                    default:
                        break;
                }
            }

            // set new map
            for (int i = 0; i < this.vehicules.size(); i++) {
                this.vehicules.elementAt(i).setUniteOccupe(carte, true);
            }

            rs = true;
        } catch (SQLException ex) {
            Logger.getLogger(Environnement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Environnement.class.getName()).log(Level.SEVERE, null, ex);
            }
            return rs;
        }
    }

    /**
     * sauvegarder l'état actuel de l'environment dans une base de données
     *
     * @return vrai si réussite ou faux si erreur
     */
    public boolean sauvegarderABaseDeDonnees() {
        boolean rs = false;
        Connection c = DBConnection.getConnection();
        Statement stm = null;
        String sql;
        try {
            // delete old database
            stm = c.createStatement();
            sql = "TRUNCATE route";
            stm.executeUpdate(sql);
            stm = c.createStatement();
            sql = "TRUNCATE vehicule";
            stm.executeUpdate(sql);

            for (int i = 0; i < this.carte.length; i++) {
                for (int j = 0; j < this.carte[i].length; j++) {
                    sql = "INSERT INTO route "
                            + "VALUES (" + i + "," + j + "," + this.carte[i][j].getType() + "," + this.carte[i][j].getDirection() + ")";
                    stm.executeUpdate(sql);
                }
            }
            
            for (int i = 0; i < this.vehicules.size(); i++) {
                int type = 0;
                if (this.vehicules.elementAt(i) instanceof Motocyclette) {
                    type = 1;
                } else if (this.vehicules.elementAt(i) instanceof Voiture) {
                    type = 2;
                } else {
                    type = 3;
                }
                sql = "INSERT INTO vehicule "
                        + "VALUES (" + this.vehicules.elementAt(i).x + "," + this.vehicules.elementAt(i).y + ","
                        + this.vehicules.elementAt(i).localisation + "," + this.vehicules.elementAt(i).direction + ","
                        + type + ")";
                stm.executeUpdate(sql);
            }

            rs = true;
        } catch (SQLException ex) {
            Logger.getLogger(Environnement.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(Environnement.class.getName()).log(Level.SEVERE, null, ex);
            }
            return rs;
        }
    }

}
