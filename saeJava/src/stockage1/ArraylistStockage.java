/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stockage1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 * @param <T>
 */
public class ArraylistStockage<T> implements IStockage<T, Integer> {

    protected final List<T> store = new ArrayList<>();

    @Override
    public int ajouter(T elem) {
        store.add(elem);
        return store.size() - 1;
    }

    @Override
    public T get(Integer id) {
        return store.get(id);
    }

    /**
     * Supprime elem de la liste.
     *
     * La suppression est un remplacement par null pour garder une
     * incrementation automatique de l'id.
     *
     * @param elem
     * @return
     */
    @Override
    public boolean supprimer(T elem) {
        int index = store.indexOf(elem);
        if (index == -1) {
            return false;
        }

        store.set(index, null);
        return true;
    }

    /**
     * Retourne les elements stockes en memoire sauf les valeurs null
     * (supprimees).
     *
     * @return contenu stocke
     */
    @Override
    public List<T> read() {
        List<T> result = new ArrayList<>();
        for (T element : store) {
            if (element != null) {
                result.add(element);
            }
        }
        return result;
    }
}
