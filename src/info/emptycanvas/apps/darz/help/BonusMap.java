/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package info.emptycanvas.apps.darz.help;

import java.awt.Color;
import java.util.HashMap;

/**
 *
 * @author Se7en
 */
public class BonusMap {
    private static final HashMap<Class, Color> maps = new HashMap<>();
    
    static
            
    {
        
        maps.put(Cheval_Licorne.class, Color.BLUE);
        maps.put(Escargot.class, Color.GRAY);
        maps.put(MouvementDirectionnel.class, Color.GREEN);
        
        
    }
    
    public static HashMap<Class, Color> getMap()
    {
        return maps;
    }
    
}
