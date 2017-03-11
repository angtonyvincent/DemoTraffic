/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objet;

/**
 * @version 1352034
 * @author Ang Tony Vincent
 */
public class Unite {

    /**
     * la position : valeur de 0 à 49
     */
    private int x;

    /**
     * la position : valeur de 0 à 49
     */
    private int y;

    /**
     * voie d'un type de véhicule (motocyclette (1), voiture (2) et bus (3)),
     * voie commune (4) et pas route (0)
     */
    private int type;

    /**
     * bas (0), moyenne (1) et haute (2)
     */
    private int debit;

    /**
     * occupé (1) et libre (0)
     */
    private boolean etat;

    /**
     * Est (1), Ouest (2), Sud (3), Nord (4) et non-direction (0)
     */
    private int direction;

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
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the debit
     */
    public int getDebit() {
        return debit;
    }

    /**
     * @param debit the debit to set
     */
    public void setDebit(int debit) {
        this.debit = debit;
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
     * le constructeur par les attributs
     *
     * @param x la direction x
     * @param y la direction y
     * @param type le type
     * @param debit le débit
     * @param direction la direction
     * @param etat l'état
     */
    public Unite(int x, int y, int type, int debit, int direction, boolean etat) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.debit = debit;
        this.etat = etat;
        this.direction = direction;
    }

    /**
     * le constructeur par l'autre unite
     *
     * @param u l'unite à copier
     */
    public Unite(Unite u) {
        this.x = u.x;
        this.y = u.y;
        this.type = u.type;
        this.debit = u.debit;
        this.etat = u.etat;
        this.direction = u.direction;
    }

    /**
     * @param u the unite to set
     */
    public void setUnite(Unite u) {
        this.x = u.x;
        this.y = u.y;
        this.type = u.type;
        this.debit = u.debit;
        this.etat = u.etat;
        this.direction = u.direction;
    }

    /**
     * afficher l'unite sur l'écran
     *
     * @return une chaîne de caractères correspondante
     */
    public String display() {
        if (this.etat) {
            return this.x + "\t" + this.y + "\t" + this.type + "\t" + this.debit + "\t" + this.direction + "\t1\r\n";
        } else {
            return this.x + "\t" + this.y + "\t" + this.type + "\t" + this.debit + "\t" + this.direction + "\t0\r\n";
        }
    }

    /**
     * vérifier si la position est valide
     * @param x la ligne
     * @param y la colonne
     * @param n la taille de la carte
     * @return vrai si oui ou faux si non
     */
    public boolean checkValide(int x, int y, int n) {
        return (x >= 0 && x <= n - 1 && y >= 0 && y <= n - 1);
    }
}
