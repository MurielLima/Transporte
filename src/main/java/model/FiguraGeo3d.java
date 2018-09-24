/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Muriel
 */
public abstract class FiguraGeo3d {

    double alt;
    Material material;
    boolean fragil;

    abstract public double getAreaDaBase();

    abstract public double getVolume();
    
    abstract public boolean baseCircular();

    public double getAlt() {
        return alt;
    }

    public void setAlt(double alt) {
        if (alt > 0) {
            this.alt = alt;
        } else {
            this.alt = 0.1;
        }
    }

    public boolean isFragil() {
        return fragil;
    }

    public void setFragil(boolean fragil) {
        this.fragil = fragil;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public double getPeso() {
        return this.getVolume() * this.material.getDensidade();
    }
}
