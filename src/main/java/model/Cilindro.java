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
public class Cilindro extends FiguraGeo3d {

    double diametro;

    public Cilindro(double diametro,double alt,Material material,boolean fragil) {
        setDiametro(diametro);
        setAlt(alt);       
        setMaterial(material);
        setFragil(fragil);
    }

    public double getDiametro() {
        return diametro;
    }

    public void setDiametro(double diametro) {
        if (diametro > 0) {
            this.diametro = diametro;
        } else {
            this.diametro = 0.1;
        }

    }

    @Override
    public double getAreaDaBase() {
        //TODO
        return Math.PI * ((getDiametro()/2)*getDiametro()/2);
    }

    @Override
    public double getVolume() {
        //TODO
        return getAreaDaBase() * getAlt();
    }
        @Override
    public String toString() {
        return "Cilindro";
    }
    @Override
    public boolean baseCircular() {
        return true;
    }
    
}
