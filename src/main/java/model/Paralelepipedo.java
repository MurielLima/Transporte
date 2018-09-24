/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Murie
 */
public class Paralelepipedo extends FiguraGeo3d {

    double larg;
    double prof;

    public Paralelepipedo() {
    }

    public Paralelepipedo(double larg, double prof, double alt, Material material, boolean fragil) {
        setLarg(larg);
        setProf(prof);
        setAlt(alt);
        setMaterial(material);
        setFragil(fragil);
    }

    public double getLarg() {
        return larg;
    }

    public void setLarg(double larg) {
        if (larg > 0) {
            this.larg = larg;
        } else {
            this.larg = 0.1;
        }
    }

    public double getProf() {
        return prof;
    }

    public void setProf(double prof) {
        if (prof > 0) {
            this.prof = prof;
        } else {
            this.prof = 0.1;
        }
    }

    @Override
    public double getAreaDaBase() {
        return getLarg() * getProf();
    }

    @Override
    public double getVolume() {
        return getAreaDaBase() * getAlt();
    }

    @Override
    public String toString() {
        return "Paralelepipedo";
    }

    @Override
    public boolean baseCircular() {
        return false;
    }
    
}
