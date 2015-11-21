package org.esiea.im_mooroogen.application;

/**
 * Created by Daren on 20/11/2015.
 */
public class Depenses {
    private long id;
    private float montant;

    public Depenses() {
    }

    public Depenses(long id, float montant) {
        super();
        this.id = id;
        this.montant = montant;
    }

    public long getId() {
        return id;
    }

    public float getMontant() {
        return  montant;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }
}
