/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import com.sun.org.apache.xml.internal.serialize.*;
import java.io.File;
import java.io.FileOutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.*;

/**
 *
 * @author Daniel
 */
public class DOM {

    Document doc = null;//doc representa el arbol DOM

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

    public String recorrerDOMyMostrar(Document doc) {
        String datos_nodo[] = null;
        String salida = "";
        Node node;

        //Obtiene el primer nodo del DOM (primer hijo)
        Node raiz = doc.getFirstChild();

        //Obtiene una lista de nodos con todos los nodos del hijo raiz
        NodeList nodelist = raiz.getChildNodes();

        //Procesa los nodos hijo
        for (int i = 0; i < nodelist.getLength(); i++) {

            node = nodelist.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                //Es un nodo libro
                datos_nodo = procesarLibro(node);

                salida = salida + "\n" + "Publicado en: " + datos_nodo[0];
                salida = salida + "\n" + "El autor es: " + datos_nodo[2];
                salida = salida + "\n" + "El titulo es: " + datos_nodo[1];
                salida = salida + "\n -----------------------------";
            }
        }
        return salida;
    }

    protected String[] procesarLibro(Node n) {

        String datos[] = new String[3];
        Node ntemp = null;
        int contador = 1;

        //Obtiene el valor del primer atributo del nodo
        datos[0] = n.getAttributes().item(0).getNodeValue();

        //Obtiene los hijos del Libro(titulo y autor)
        NodeList nodos = n.getChildNodes();

        for (int i = 0; i < nodos.getLength(); i++) {
            ntemp = nodos.item(i);

            if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                //Para obtener el texto con el titulo y autor se accede 
                //al nodo TEXT hijo de ntemp y se saca el valor
                datos[contador] = ntemp.getChildNodes().item(0).getNodeValue();

                contador++;
            }
        }
        return datos;
    }

    public int annadirDOM(Document doc, String titulo, String autor, String anno) {
        try {
            //Se crea un nodo tipo Element con nombre 'titulo'(<Titulo>)
            Node ntitulo = doc.createElement("Titulo");

            //Se crea un nodo tipo texto con el titulo del libro
            Node ntitulo_text = doc.createTextNode(titulo);

            //Se añade el nodo de texto con el titulo como hijo del element Titulo
            ntitulo.appendChild(ntitulo_text);

            //Se hace lo mismo que con titulo a autor(<Autor>)
            Node nautor = doc.createElement("Autor");
            Node nautor_text = doc.createTextNode(autor);
            nautor.appendChild(nautor_text);

            //Se crea un nodo de tipo elemento (<Libro>)
            Node nlibro = doc.createElement("Libro");

            //Al nuevo nodo libro se le añade un atributo publicnado en 
            ((Element) nlibro).setAttribute("publicado_en", anno);

            //Se añade a libro el nodo autor y titulo creados antes
            nlibro.appendChild(ntitulo);
            nlibro.appendChild(nautor);

            //Se obtiene el primer nodo del documento y a el se le añade como
            //hijo el nodo libro que ta tiene colgando todos sus hijos y atrivutos crados antes
            Node raiz = doc.getChildNodes().item(0);
            raiz.appendChild(nlibro);
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public boolean buscarTitulo(String tituloBuscado) {
        boolean encontrado = false;
        String datos_nodo[] = null;
        Node node;

        //Obtiene el primer nodo del DOM (primer hijo)
        Node raiz = doc.getFirstChild();

        //Obtiene una lista de nodos con todos los nodos del hijo raiz
        NodeList nodelist = raiz.getChildNodes();

        for (int i = 0; i < nodelist.getLength() && !encontrado; i++) {
            node = nodelist.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                datos_nodo = procesarLibro(node);
                for (int j = 0; j < datos_nodo.length && !encontrado; j++) {
                    if (datos_nodo[j].equals(tituloBuscado)) {
                        encontrado = true;
                    }
                }
            }
        }
        return encontrado;
    }

    public int modificaPersonaje(String[] datos, String[] armas, String[] armaduras,
            String[] objetos, String[] dotes, String[] habilidades, String[] conjuros) {
        try {
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    public int annadePersonaje(String[] datos, String[] armas, String[] armaduras,
            String[] objetos, String[] dotes, String[] habilidades, String[] conjuros) {
        try {
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    public boolean modificarTitulo(String tituloBuscado, String tituloModificado) {
        boolean encontrado = false;
        String datos_nodo[] = null;
        Node node;

        //Obtiene el primer nodo del DOM (primer hijo)
        Node raiz = doc.getFirstChild();

        //Obtiene una lista de nodos con todos los nodos del hijo raiz
        NodeList nodelist = raiz.getChildNodes();

        //Segundo nodeList auxiliar
        NodeList nodeAux;
        for (int i = 0; i < nodelist.getLength() && !encontrado; i++) {
            node = nodelist.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                nodeAux = node.getChildNodes();
                if (nodeAux.item(1).getTextContent().equals(tituloBuscado)) {
                    nodeAux.item(1).setTextContent(tituloModificado);
                    System.out.println("MODIFICADO");
                    encontrado = true;
                }
                System.out.println(nodeAux.item(1).getTextContent());
            }
        }
        return encontrado;
    }
}
