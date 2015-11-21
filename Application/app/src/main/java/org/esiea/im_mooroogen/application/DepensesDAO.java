package org.esiea.im_mooroogen.application;

import android.content.Context;

/**
 * Created by Daren on 20/11/2015.
 */
public class DepensesDAO extends DAOBase{
    public static final String TABLE_NAME = "depenses";
    public static final String KEY = "id";
    public static final String INTITULE = "depense";
    public static final String MONTANT = "montant";

    public static final String CREATE_TABLE = "CREATE_TABLE"+TABLE_NAME+" ("+KEY+" INTEGER PRIMARY KEY AUTOINCREMENT, "+INTITULE+" TEXT, "+MONTANT+" REAL);";

    public static final String TABLE_DROP = "DROP TABLE IF EXISTS "+TABLE_NAME+";";

    public DepensesDAO(Context pContext) {
        super(pContext);
    }

    /**
     *
     * @param d la depense à ajouter à la base
     */
    public void ajouter(Depenses d) {

    }

    /**
     *
     * @param id l'identifiant de la dépense à supprimer
     */
    public void supprimer(long id) {

    }

    /**
     *
     * @param d la dépense à modifier
     */
    public void modifier(Depenses d) {

    }

    /**
     *
     * @param id l'identifiant de la dépense à sélectionner
     */
    public void selectionner(long id) {

    }
}
