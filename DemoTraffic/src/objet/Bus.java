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
public class Bus extends Vehicule {

    public Bus(Unite[][] carte, int unites) {
        super(carte, unites);
    }

    public Bus(int x, int y, int localisation, int vitesse, int direction, boolean etat) {
        super(x, y, localisation, vitesse, direction, etat);
    }

    @Override
    public void getUniteOccupe(Vector<Unite> v) {
        try {
            switch (this.direction) {
                case 1: {
                    Unite u1 = new Unite(this.x, this.y, 3, this.vitesse, this.direction, this.etat);
                    Unite u2 = new Unite(this.x - 1, this.y, 3, this.vitesse, this.direction, this.etat);
                    Unite u3 = new Unite(this.x - 2, this.y, 3, this.vitesse, this.direction, this.etat);
                    Unite u4 = new Unite(this.x - 3, this.y, 3, this.vitesse, this.direction, this.etat);
                    v.add(u1);
                    v.add(u2);
                    v.add(u3);
                    v.add(u4);
                }
                break;

                case 2: {
                    Unite u1 = new Unite(this.x, this.y, 3, this.vitesse, this.direction, this.etat);
                    Unite u2 = new Unite(this.x + 1, this.y, 3, this.vitesse, this.direction, this.etat);
                    Unite u3 = new Unite(this.x + 2, this.y, 3, this.vitesse, this.direction, this.etat);
                    Unite u4 = new Unite(this.x + 3, this.y, 3, this.vitesse, this.direction, this.etat);
                    v.add(u1);
                    v.add(u2);
                    v.add(u3);
                    v.add(u4);
                }
                break;

                case 3: {
                    Unite u1 = new Unite(this.x, this.y, 3, this.vitesse, this.direction, this.etat);
                    Unite u2 = new Unite(this.x, this.y - 1, 3, this.vitesse, this.direction, this.etat);
                    Unite u3 = new Unite(this.x, this.y - 2, 3, this.vitesse, this.direction, this.etat);
                    Unite u4 = new Unite(this.x, this.y - 3, 3, this.vitesse, this.direction, this.etat);
                    v.add(u1);
                    v.add(u2);
                    v.add(u3);
                    v.add(u4);
                }
                break;

                case 4: {
                    Unite u1 = new Unite(this.x, this.y, 3, this.vitesse, this.direction, this.etat);
                    Unite u2 = new Unite(this.x, this.y + 1, 3, this.vitesse, this.direction, this.etat);
                    Unite u3 = new Unite(this.x, this.y + 2, 3, this.vitesse, this.direction, this.etat);
                    Unite u4 = new Unite(this.x, this.y + 3, 3, this.vitesse, this.direction, this.etat);
                    v.add(u1);
                    v.add(u2);
                    v.add(u3);
                    v.add(u4);
                }
                break;

                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Exception capturée dans Bus: " + e);
        }
    }

    @Override
    public boolean checkUnite(Unite[][] carte) {
        boolean rs = true;
        try {
            Vector<Unite> unites = new Vector();
            this.getUniteOccupe(unites);
            for (int i = 0; i < unites.size(); i++) {
                if (!this.checkExistence(unites.elementAt(i).getX(), unites.elementAt(i).getY())) {
                    rs = false;
                } else if (carte[unites.elementAt(i).getX()][unites.elementAt(i).getY()].isEtat()) {
                    rs = false; // occupé
                } else if (carte[unites.elementAt(i).getX()][unites.elementAt(i).getY()].getType() != 3) {
                    rs = false; // pas voie pour bus
                } else if (carte[unites.elementAt(i).getX()][unites.elementAt(i).getY()].getDirection() != 0
                        && carte[unites.elementAt(i).getX()][unites.elementAt(i).getY()].getDirection() != this.direction) {
                    rs = false; // fausse direction
                }
            }
        } catch (Exception e) {
            System.out.println("Exception capturée dans Bus: " + e);
        } finally {
            return rs;
        }
    }

     @Override
    public void tournerDroite(Unite[][] carte) {
        try {
            this.setUniteOccupe(carte, false);
            switch (this.getDirection()) {
                case 1:
                    this.setY(this.y + 3);
                    this.setDirection(3);
                    break;

                case 2:
                    this.setY(this.y - 3);
                    this.setDirection(4);
                    break;

                case 3:
                    this.setX(this.x - 3);
                    this.setDirection(2);
                    break;

                case 4:
                    this.setX(this.x + 3);
                    this.setDirection(1);
                    break;

                default:
                    break;
            }
            this.setLocalisation(0);
        } catch (Exception e) {
            System.out.println("Exception capturée dans Bus: " + e);
        } finally {
            this.setUniteOccupe(carte, true);
        }
    }

    @Override
    public void tournerGauche(Unite[][] carte) {
        try {
            this.setUniteOccupe(carte, false);
            switch (this.getDirection()) {
                case 1:
                    this.setY(this.y - 3);
                    this.setDirection(4);
                    break;

                case 2:
                    this.setY(this.y + 3);
                    this.setDirection(3);
                    break;

                case 3:
                    this.setX(this.x + 3);
                    this.setDirection(1);
                    break;

                case 4:
                    this.setX(this.x - 3);
                    this.setDirection(2);
                    break;

                default:
                    break;
            }
            this.setLocalisation(6);
        } catch (Exception e) {
            System.out.println("Exception capturée dans Bus: " + e);
        } finally {
            this.setUniteOccupe(carte, true);
        }
    }

    @Override
    public boolean verifierEnFace(Unite[][] carte) {
        boolean rs = false;
        int nX = this.x;
        int nY = this.y;
        try {
            switch (this.localisation) {
                case 3:
                    switch (this.direction) {
                        case 1:
                            nX = this.x + 4;
                            nY = this.y;
                            if (this.checkExistence(nX, nY)) {
                                if (carte[nX][nY].getType() == 3
                                        && carte[nX][nY].getDirection() == this.direction
                                        && !carte[this.x+1][this.y].isEtat()) {
                                    rs = true;
                                }
                            }
                            break;

                        case 2:
                            nX = this.x - 4;
                            nY = this.y;
                            if (this.checkExistence(nX, nY)) {
                                if (carte[nX][nY].getType() == 3
                                        && carte[nX][nY].getDirection() == this.direction
                                        && !carte[this.x-1][this.y].isEtat()) {
                                    rs = true;
                                }
                            }
                            break;

                        case 3:
                            nX = this.x;
                            nY = this.y + 4;
                            if (this.checkExistence(nX, nY)) {
                                if (carte[nX][nY].getType() == 3
                                        && carte[nX][nY].getDirection() == this.direction
                                        && !carte[this.x][this.y+1].isEtat()) {
                                    rs = true;
                                }
                            }
                            break;

                        case 4:
                            nX = this.x;
                            nY = this.y - 4;
                            if (this.checkExistence(nX, nY)) {
                                if (carte[nX][nY].getType() == 3
                                        && carte[nX][nY].getDirection() == this.direction
                                        && !carte[this.x][this.y-1].isEtat()) {
                                    rs = true;
                                }
                            }
                            break;

                        default:
                            break;
                    }
                    break;

                case 4:
                    switch (this.direction) {
                        case 1:
                            nX = this.x + 3;
                            nY = this.y;
                            if (this.checkExistence(nX, nY)) {
                                if (carte[nX][nY].getType() == 3
                                        && carte[nX][nY].getDirection() == this.direction
                                        && !carte[this.x+1][this.y].isEtat()) {
                                    rs = true;
                                }
                            }
                            break;

                        case 2:
                            nX = this.x - 3;
                            nY = this.y;
                            if (this.checkExistence(nX, nY)) {
                                if (carte[nX][nY].getType() == 3
                                        && carte[nX][nY].getDirection() == this.direction
                                        && !carte[this.x-1][this.y].isEtat()) {
                                    rs = true;
                                }
                            }
                            break;

                        case 3:
                            nX = this.x;
                            nY = this.y + 3;
                            if (this.checkExistence(nX, nY)) {
                                if (carte[nX][nY].getType() == 3
                                        && carte[nX][nY].getDirection() == this.direction
                                        && !carte[this.x][this.y+1].isEtat()) {
                                    rs = true;
                                }
                            }
                            break;

                        case 4:
                            nX = this.x;
                            nY = this.y - 3;
                            if (this.checkExistence(nX, nY)) {
                                if (carte[nX][nY].getType() == 3
                                        && carte[nX][nY].getDirection() == this.direction
                                        && !carte[this.x][this.y-1].isEtat()) {
                                    rs = true;
                                }
                            }
                            break;

                        default:
                            break;
                    }
                    break;

                default: // case 0, 1, 2, 5, 6
                    switch (this.direction) {
                        case 1:
                            nX = this.x + 1;
                            nY = this.y;
                            break;

                        case 2:
                            nX = this.x - 1;
                            nY = this.y;
                            break;

                        case 3:
                            nX = this.x;
                            nY = this.y + 1;
                            break;

                        case 4:
                            nX = this.x;
                            nY = this.y - 1;
                            break;

                        default:
                            break;
                    }
                    if (this.checkExistence(nX, nY)) {
                        if (!carte[nX][nY].isEtat()) {
                            rs = true;
                        }
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println("Exception capturée dans Bus: " + e);
        } finally {
            return rs;
        }
    }

    @Override
    public boolean verifierADroite(Unite[][] carte) {
        boolean rs = false;
        try {
            if (this.localisation != 3) {
                return rs;
            } else {
                int nX, nY;
                switch (this.direction) {
                    case 1:
                        nX = this.x;
                        nY = this.y + 3;
                        if (this.checkExistence(nX, nY)) {
                            if (carte[nX][nY].getType() == 3
                                    && carte[nX][nY].getDirection() == 3
                                    && !carte[this.x][this.y+1].isEtat()
                                    && !carte[this.x][this.y+2].isEtat()
                                    && !carte[this.x][this.y+3].isEtat()) {
                                rs = true;
                            }
                        }
                        break;

                    case 2:
                        nX = this.x;
                        nY = this.y - 3;
                        if (this.checkExistence(nX, nY)) {
                            if (carte[nX][nY].getType() == 3
                                    && carte[nX][nY].getDirection() == 4
                                    && !carte[this.x][this.y-1].isEtat()
                                    && !carte[this.x][this.y-2].isEtat()
                                    && !carte[this.x][this.y-3].isEtat()) {
                                rs = true;
                            }
                        }
                        break;

                    case 3:
                        nX = this.x - 3;
                        nY = this.y;
                        if (this.checkExistence(nX, nY)) {
                            if (carte[nX][nY].getType() == 3
                                    && carte[nX][nY].getDirection() == 2
                                    && !carte[this.x-1][this.y].isEtat()
                                    && !carte[this.x-2][this.y].isEtat()
                                    && !carte[this.x-3][this.y].isEtat()) {
                                rs = true;
                            }
                        }
                        break;

                    case 4:
                        nX = this.x + 3;
                        nY = this.y;
                        if (this.checkExistence(nX, nY)) {
                            if (carte[nX][nY].getType() == 3
                                    && carte[nX][nY].getDirection() == 1
                                    && !carte[this.x+1][this.y].isEtat()
                                    && !carte[this.x+2][this.y].isEtat()
                                    && !carte[this.x+3][this.y].isEtat()) {
                                rs = true;
                            }
                        }
                        break;

                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception capturée dans Bus: " + e);
        } finally {
            return rs;
        }
    }

    @Override
    public boolean verifierAGauche(Unite[][] carte) {
        boolean rs = false;
        try {
            if (this.localisation != 4) {
                return rs;
            } else {
                int nX, nY;
                switch (this.direction) {
                    case 1:
                        nX = this.x;
                        nY = this.y - 4;
                        if (this.checkExistence(nX, nY)) {
                            if (carte[nX][nY].getType() == 3
                                    && carte[nX][nY].getDirection() == 4
                                    && !carte[this.x][this.y-1].isEtat()
                                    && !carte[this.x][this.y-2].isEtat()
                                    && !carte[this.x][this.y-3].isEtat()) {
                                rs = true;
                            }
                        }
                        break;

                    case 2:
                        nX = this.x;
                        nY = this.y + 4;
                        if (this.checkExistence(nX, nY)) {
                            if (carte[nX][nY].getType() == 3
                                    && carte[nX][nY].getDirection() == 3
                                    && !carte[this.x][this.y+1].isEtat()
                                    && !carte[this.x][this.y+2].isEtat()
                                    && !carte[this.x][this.y+3].isEtat()) {
                                rs = true;
                            }
                        }
                        break;

                    case 3:
                        nX = this.x + 4;
                        nY = this.y;
                        if (this.checkExistence(nX, nY)) {
                            if (carte[nX][nY].getType() == 3
                                    && carte[nX][nY].getDirection() == 1
                                    && !carte[this.x+1][this.y].isEtat()
                                    && !carte[this.x+2][this.y].isEtat()
                                    && !carte[this.x+3][this.y].isEtat()) {
                                rs = true;
                            }
                        }
                        break;

                    case 4:
                        nX = this.x - 4;
                        nY = this.y;
                        if (this.checkExistence(nX, nY)) {
                            if (carte[nX][nY].getType() == 3
                                    && carte[nX][nY].getDirection() == 2
                                    && !carte[this.x-1][this.y].isEtat()
                                    && !carte[this.x-2][this.y].isEtat()
                                    && !carte[this.x-3][this.y].isEtat()) {
                                rs = true;
                            }
                        }
                        break;

                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception capturée dans Bus: " + e);
        } finally {
            return rs;
        }
    }

    private boolean verifierTournerGauche(Unite[][] carte) {
        boolean rs = false;
        try {
            if (this.localisation != 3) {
                return rs;
            } else {
                int nX, nY;
                switch (this.direction) {
                    case 1:
                        nX = this.x + 1;
                        nY = this.y - 4;
                        if (this.checkExistence(nX, nY)) {
                            if (carte[nX][nY].getType() == 3
                                    && carte[nX][nY].getDirection() == 4
                                    && !carte[this.x + 1][this.y].isEtat()) {
                                rs = true;
                            }
                        }
                        break;

                    case 2:
                        nX = this.x - 1;
                        nY = this.y + 4;
                        if (this.checkExistence(nX, nY)) {
                            if (carte[nX][nY].getType() == 3
                                    && carte[nX][nY].getDirection() == 3
                                    && !carte[this.x - 1][this.y].isEtat()) {
                                rs = true;
                            }
                        }
                        break;

                    case 3:
                        nX = this.x + 4;
                        nY = this.y + 1;
                        if (this.checkExistence(nX, nY)) {
                            if (carte[nX][nY].getType() == 3
                                    && carte[nX][nY].getDirection() == 1
                                    && !carte[this.x][this.y + 1].isEtat()) {
                                rs = true;
                            }
                        }
                        break;

                    case 4:
                        nX = this.x - 4;
                        nY = this.y - 1;
                        if (this.checkExistence(nX, nY)) {
                            if (carte[nX][nY].getType() == 3
                                    && carte[nX][nY].getDirection() == 2
                                    && !carte[this.x][this.y - 1].isEtat()) {
                                rs = true;
                            }
                        }
                        break;

                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception capturée dans Bus: " + e);
        } finally {
            return rs;
        }
    }

    @Override
    public void deplacer(Unite[][] carte) {
        try {
            Random r = new Random();
            switch (this.localisation) {
                case 3:
                    if ((this.verifierTournerGauche(carte) || this.verifierEnFace(carte))
                            && this.verifierADroite(carte)) {
                        boolean b = r.nextBoolean();
                        if (b) {
                            this.avancer(carte);
                        } else {
                            this.tournerDroite(carte);
                        }
                    } else if (this.verifierTournerGauche(carte) || this.verifierEnFace(carte)) {
                        this.avancer(carte);
                    } else if (this.verifierADroite(carte)) {
                        this.tournerDroite(carte);
                    } else {

                    }
                    break;

                case 4:
                    if (this.verifierAGauche(carte) && this.verifierEnFace(carte)) {
                        boolean b = r.nextBoolean();
                        if (b) {
                            this.avancer(carte);
                        } else {
                            this.tournerGauche(carte);
                        }
                    } else if (this.verifierAGauche(carte)) {
                        this.tournerGauche(carte);
                    } else if (this.verifierEnFace(carte)) {
                        this.avancer(carte);
                    } else {

                    }
                    break;

                default: // case 0, 1, 2, 5, 6
                    if (this.verifierEnFace(carte)) {
                        this.avancer(carte);
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println("Exception capturée dans Bus: " + e);
        }
    }

    @Override
    public void faireDemiTour(Unite[][] carte) {
        this.setUniteOccupe(carte, false);
        try {
            switch (this.getDirection()) {
                case 1:
                    this.setDirection(2);
                    this.setX(this.x - 4);
                    break;

                case 2:
                    this.setDirection(1);
                    this.setX(this.x + 4);
                    break;

                case 3:
                    this.setDirection(4);
                    this.setY(this.y - 4);
                    break;

                case 4:
                    this.setDirection(3);
                    this.setY(this.y + 4);
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Exception capturée dans Bus: " + e);
        } finally {
            this.setUniteOccupe(carte, true);
        }
    }

    @Override
    public boolean verifierDerriere(Unite[][] carte) {
        boolean rs = false;
        int nX, nY;
        try {
            switch (this.direction) {
                case 1:
                    nX = this.x - 4;
                    nY = this.y;
                    if (this.checkExistence(nX, nY)) {
                        if (carte[nX][nY].getType() == 4 && carte[nX][nY].getDirection() == 0) {
                            if (!carte[nX][nY].isEtat()) {
                                rs = true;
                            }
                        }
                    }
                    break;

                case 2:
                    nX = this.x + 4;
                    nY = this.y;
                    if (this.checkExistence(nX, nY)) {
                        if (carte[nX][nY].getType() == 4 && carte[nX][nY].getDirection() == 0) {
                            if (!carte[nX][nY].isEtat()) {
                                rs = true;
                            }
                        }
                    }
                    break;

                case 3:
                    nX = this.x;
                    nY = this.y - 4;
                    if (this.checkExistence(nX, nY)) {
                        if (carte[nX][nY].getType() == 4 && carte[nX][nY].getDirection() == 0) {
                            if (!carte[nX][nY].isEtat()) {
                                rs = true;
                            }
                        }
                    }
                    break;

                case 4:
                    nX = this.x;
                    nY = this.y + 4;
                    if (this.checkExistence(nX, nY)) {
                        if (carte[nX][nY].getType() == 4 && carte[nX][nY].getDirection() == 0) {
                            if (!carte[nX][nY].isEtat()) {
                                rs = true;
                            }
                        }
                    }
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Exception capturée dans Bus: " + e);
        } finally {
            return rs;
        }
    }

}
