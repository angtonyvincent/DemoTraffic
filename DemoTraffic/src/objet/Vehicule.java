/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objet;

import java.util.Random;
import java.util.Vector;

/**
 * @version 1352034
 * @author Ang Tony Vincent
 */
public abstract class Vehicule {

    /**
     * la position : valeur de 0 à 49
     */
    protected int x;

    /**
     * la position : valeur de 0 à 49
     */
    protected int y;

    /**
     * la localisation dans voie commune
     */
    protected int localisation;

    /**
     * la vitesse : bas (0), moyenne (1) et haute (2)
     */
    protected int vitesse;

    /**
     * Est (1), Ouest (2), Sud (3), Nord (4)
     */
    protected int direction;

    /**
     * l'état : arrêter (0) et déplacer (1)
     */
    protected boolean etat;

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the debit
     */
    public int getVitesse() {
        return vitesse;
    }

    /**
     * @param vitesse the debit to set
     */
    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    /**
     * @return the etat
     */
    public boolean isEtat() {
        return etat;
    }

    /**
     * @param etat the etat to set
     */
    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    /**
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * calculer les unités occupés par le véhicule
     *
     * @param v liste de unites occupés
     */
    public abstract void getUniteOccupe(Vector<Unite> v);

    /**
     * changer l'état des unités occupés par le véhicule
     *
     * @param carte la carte
     * @param etat l'état
     */
    public final void setUniteOccupe(Unite[][] carte, boolean etat) {
        try {
            Vector<Unite> unites_occupes = new Vector();
            this.getUniteOccupe(unites_occupes);
            for (int i = 0; i < unites_occupes.size(); i++) {
                carte[unites_occupes.elementAt(i).getX()][unites_occupes.elementAt(i).getY()].setEtat(etat);
            }
        } catch (Exception e) {
            System.out.println("Exception capturée dans Véhicule: " + e);
        }
    }

    /**
     * vérifier la position de véhicule sur la carte
     *
     * @param carte la carte
     * @return vrai si la position est libre ou faux si la position est occupée
     */
    public abstract boolean checkUnite(Unite[][] carte);

    /**
     * vérifier si la position est valide sur la carte de 50*50
     *
     * @param x la position x
     * @param y la position y
     * @return vrai si oui ou faux si non
     */
    public boolean checkExistence(int x, int y) {
        return !(x < 0 || y < 0 || x > 49 || y > 49);
    }

    /**
     * le véhicule peut avancer d'un pas en avant sur la carte
     *
     * @param carte la carte
     */
    public void avancer(Unite[][] carte) {
        try {
            this.setUniteOccupe(carte, false);
            int nX = this.x;
            int nY = this.y;
            switch (this.direction) {
                case 1:
                    this.x += 1;
                    break;

                case 2:
                    this.x -= 1;
                    break;

                case 3:
                    this.y += 1;
                    break;

                case 4:
                    this.y -= 1;
                    break;

                default:
                    break;
            }
            switch (this.localisation) {
                case 0:
                    if (carte[this.x][this.y].getType() != carte[nX][nY].getType()) {
                        this.localisation = 1;
                    }
                    break;
                case 6:
                    this.localisation = 0;
                    break;
                default:
                    this.localisation++;
                    break;
            }
        } catch (Exception e) {
            System.out.println("Exception capturée dans Véhicule: " + e);
        } finally {
            this.setUniteOccupe(carte, true);
        }
    }

    /**
     * le véhicule peut se tourner à droite de 90° sur la carte
     *
     * @param carte la carte
     */
    public abstract void tournerDroite(Unite[][] carte);

    /**
     * le véhicule peut se tourner à gauche de 90° sur la carte
     *
     * @param carte la carte
     */
    public abstract void tournerGauche(Unite[][] carte);

    /**
     * le véhicule peut faire un demi tour sur la carte
     *
     * @param carte la carte
     */
    public abstract void faireDemiTour(Unite[][] carte);

    /**
     * le véhicule peut se déplacer sur la carte
     *
     * @param carte la carte
     */
    public abstract void deplacer(Unite[][] carte);

    /**
     * vérifier l'unite d'en face s'il existe
     *
     * @param carte la carte
     * @return vrai si oui ou faux si non
     */
    public abstract boolean verifierEnFace(Unite[][] carte);

    /**
     * vérifier l'unite à droite s'il existe
     *
     * @param carte la carte
     * @return vrai si oui ou faux si non
     */
    public abstract boolean verifierADroite(Unite[][] carte);

    /**
     * vérifier l'unite à gauche s'il existe
     *
     * @param carte la carte
     * @return vrai si oui ou faux si non
     */
    public abstract boolean verifierAGauche(Unite[][] carte);

    /**
     * vérifier l'unite à gauche s'il existe
     *
     * @param carte la carte
     * @return vrai si oui ou faux si non
     */
    public abstract boolean verifierDerriere(Unite[][] carte);

    /**
     * afficher toutes les informations dé véhicule
     *
     * @return la chaîne de caractères correspondante
     */
    public String display() {
        if (this.etat) {
            return this.x + "\t" + this.y + "\t" + this.localisation + "\t" + this.vitesse + "\t" + this.direction + "\t1\r\n";
        } else {
            return this.x + "\t" + this.y + "\t" + this.localisation +"\t" + this.vitesse + "\t" + this.direction + "\t0\r\n";
        }
    }

    /**
     * le contructeur par les attributs
     *
     * @param x la position x
     * @param y la position y
     * @param localisation la localisation
     * @param vitesse la vitesse
     * @param direction la direction
     * @param etat l'état
     */
    public Vehicule(int x, int y, int localisation, int vitesse, int direction, boolean etat) {
        this.x = x;
        this.y = y;
        this.localisation = localisation;
        this.vitesse = vitesse;
        this.direction = direction;
        this.etat = etat;
    }

    /**
     * le constructeur créer aléatoirement un nouveau véhicule sur la carte
     *
     * @param carte la carte
     * @param unites le nombre des unites
     */
    public Vehicule(Unite[][] carte, int unites) {
        try {
            Random ran = new Random();
            this.x = ran.nextInt(unites) + 0;
            this.y = ran.nextInt(unites) + 0;
            this.localisation = 0;
            this.direction = ran.nextInt(4) + 1;
            while (!this.checkUnite(carte)) {
                this.x = ran.nextInt(unites) + 0;
                this.y = ran.nextInt(unites) + 0;
                this.direction = ran.nextInt(4) + 1;
            }
            this.vitesse = carte[x][y].getDebit();
            this.etat = true;
            this.setUniteOccupe(carte, true);
        } catch (Exception e) {
            System.out.println("Exception capturée dans Véhicule: " + e);
        }
    }

    /**
     * @return the localisation
     */
    public int getLocalisation() {
        return localisation;
    }

    /**
     * @param localisation the localisation to set
     */
    public void setLocalisation(int localisation) {
        this.localisation = localisation;
    }

}
