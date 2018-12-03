/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import com.sun.org.apache.xml.internal.serialize.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

/**
 *
 * @author Daniel
 */
public class DOM {

    Document doc = null;//doc representa el arbol DOM

    /**
     * Funcion para abrir un arbol DOM desde un fichero
     *
     * @param fichero
     * @return 0 si se ha abierto, -1 si ha fallado
     */
    public int abrir_XML_DOM(File fichero) {
        try {
            //Se crea un objeto DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            //El modelo DOM no debe contemplar los comentarios que tenga el XML
            factory.setIgnoringComments(true);

            //Ignora los espacios en blanco que tenga el documento
            factory.setIgnoringElementContentWhitespace(true);

            //Crea un objeto DocumentBuilder para cargar el el la estructura del arbol
            //DOM a partir del XML seleccionado
            DocumentBuilder builder = factory.newDocumentBuilder();

            //Interpreta(parsear) el documento XML(File) y genera el DOM equivalente
            doc = builder.parse(fichero);

            System.out.println("DOM Leido");
            //Ahora doc apunta al arbol DOM listo para ser recorrido
            return 0;

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fallando");
            return -1;
        }
    }

    /**
     * Funcion para añadir un nuevo elemento al arbol DOM
     *
     * @param datosBase Lista de atributos del Personaje
     * @param armas Lista de armas
     * @param armadruas Lista de Armaduras
     * @param objetos Lista de Objetos
     * @param dotes Lista de Dotes
     * @param habilidades Lista de Habilidades
     * @param conjuros Lista de Conjuros
     * @return 0 si se ha abierto, -1 si ha fallado
     */
    public int annadirDOM(String[] datosBase, ArrayList<String> armas, ArrayList<String> armadruas,
            ArrayList<String[]> objetos, ArrayList<String> dotes, ArrayList<String[]> habilidades,
            ArrayList<String[]> conjuros) {

        try {
            //Se crea un nodo tipo Element con nombre 'Armas'(<Armas>)
            Node nArmas = doc.createElement("Armas");

            //Se crea un nodo tipo Element con nombre 'Armaduras'(<Armaduras>)
            Node nArmaduras = doc.createElement("Armaduras");

            //Se crea un nodo tipo Element con nombre 'Objetos'(<Objetos>)
            Node nObjetos = doc.createElement("Objetos");

            //Se crea un nodo tipo Element con nombre 'Habilidades'(<Habilidades>)
            Node nHabilidades = doc.createElement("Habilidades");

            //Se crea un nodo tipo Element con nombre 'Dotes'(<Dotes>)
            Node nDotes = doc.createElement("Dotes");

            //Se crea un nodo tipo Element con nombre 'Conjuros'(<Conjuros>)
            Node nConjuros = doc.createElement("Conjuros");

            //Se crea un nodo tipo Element con nombre 'Experiencia'(<Experiencia>)
            Node nExperiencia = doc.createElement("Experiencia");
            //Se crea un nodo tipo texto con el titulo del libro
            Node nexperiencia_text = doc.createTextNode(datosBase[12]);
            //Se añade el nodo de texto con el titulo como hijo del element Experiencia
            nExperiencia.appendChild(nexperiencia_text);

            //Se crea un nodo de tipo elemento 'Personaje'(<Personaje>)
            Node nPersonaje = doc.createElement("Personaje");

            //Al nuevo nodo Personaje se le añaden atributos  
            ((Element) nPersonaje).setAttribute("nombre", datosBase[0]);
            ((Element) nPersonaje).setAttribute("fuerza", datosBase[1]);
            ((Element) nPersonaje).setAttribute("destreza", datosBase[2]);
            ((Element) nPersonaje).setAttribute("constitucion", datosBase[3]);
            ((Element) nPersonaje).setAttribute("inteligencia", datosBase[4]);
            ((Element) nPersonaje).setAttribute("sabiduria", datosBase[5]);
            ((Element) nPersonaje).setAttribute("carisma", datosBase[6]);
            ((Element) nPersonaje).setAttribute("clase", datosBase[7]);
            ((Element) nPersonaje).setAttribute("raza", datosBase[8]);
            ((Element) nPersonaje).setAttribute("sexo", datosBase[9]);
            ((Element) nPersonaje).setAttribute("alineamiento", datosBase[10]);
            ((Element) nPersonaje).setAttribute("nivel", datosBase[11]);

            //Se añade a 'Personaje' el resto de nodos creados anteriormente
            nPersonaje.appendChild(nExperiencia);
            nPersonaje.appendChild(nArmas);
            nPersonaje.appendChild(nArmaduras);
            nPersonaje.appendChild(nObjetos);
            nPersonaje.appendChild(nHabilidades);
            nPersonaje.appendChild(nDotes);
            nPersonaje.appendChild(nConjuros);

            //Se obtiene el primer nodo del documento y a el se le añade como
            //hijo el nodo libro que ta tiene colgando todos sus hijos y atrivutos crados antes
            Node raiz = doc.getChildNodes().item(0);
            raiz.appendChild(nPersonaje);

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Con esta funcion vamos a recoger el arbol DOM y lo guardaremos como XML
     *
     * @param nombre del archivo
     * @return 0 si se ha abierto, -1 si ha fallado
     */
    public int guardarDOMcomoFILE(String nombre) {
        try {

            //Creaun fichero llamad salida.xml
            File archivo_xml = new File(nombre);

            //Especifica el formato de salida
            OutputFormat format = new OutputFormat(doc);

            //Especifica que la salida esta identada
            format.setIndenting(true);

            //escribe el contenido en el File
            XMLSerializer serializer = new XMLSerializer(new FileOutputStream(archivo_xml), format);

            serializer.serialize(doc);

            return 0;

        } catch (Exception e) {
            return -1;
        }
    }
}
