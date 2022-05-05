/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProyectoFinal.example.Hospital.Entidades;

import javax.script.ScriptEngineManager;

/**
 *
 * @author NG
 */
public class EjecucionJavaScript {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ScriptEngineManager scriptEngineManager= new ScriptEngineManager();
        
        final String LENGUAJE = "ECMAScript";
        
        ScriptEngineManager engine = (ScriptEngineManager) scriptEngineManager.getEngineByName(LENGUAJE);
        
        if (engine== null){
            System.out.println("MENSAJE: no se ha podido encontrar el motor " + LENGUAJE);
            return;
        }
        engine.toString();
    }
    
}
