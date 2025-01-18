/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package stockage1;

import java.util.List;

/**
 *
 * @author Salome
 * @author Ito
 * @author Macky
 *
 * Inspiré du pattern Repository
 */
public interface IStockage<T, K> {

    public int ajouter(T elem);

    public boolean supprimer(T elem);

    public T get(K id);

    public List<T> read();
}
