package codigo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import javax.xml.parsers.*;
import javax.xml.xpath.*;
import org.w3c.dom.*;

/**
 *
 * @author Daniel
 */
public class GestorXPATH {

    Document documentXMLDoc = null;

    public int abrirXPath(File fichero) {
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
            documentXMLDoc = builder.parse(fichero);
            //Ahora doc apunta al arbol DOM listo para ser recorrido

            System.out.println("XPATH Leido");
            return 0;

        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
            return -1;
        }
    }

    public String ejecutaXPath(String consulta) {
        String salida = "";
        String auxiliar[] = null;
        Node nodoAuxiliar = null;
        try {
            //No hace falta crear un arbol DOM(parsear) dado que ya lo hemos hecho

            //Crea el objeto XPath
            XPath xpath = XPathFactory.newInstance().newXPath();

            //Crea un XPathExpression con la consulta deseada
            XPathExpression exp = xpath.compile(consulta);

            //Ejecuta la consulta indicando que se ejecute sobre el DOM y que devolvera
            //el resultado como una lista de nodos
            Object result = exp.evaluate(documentXMLDoc, XPathConstants.NODESET);
            NodeList nodeList = (NodeList) result;

            //Ahora recorre la lista para sacarlos resultado
            for (int i = 0; i < nodeList.getLength(); i++) {
                //Primero guardamos 
                nodoAuxiliar = nodeList.item(i);

                //Primero vamos a comprobar si lo que tenemos es un elemento
                //del tipo libro
                if (nodoAuxiliar.getNodeType() == Node.ELEMENT_NODE
                        && nodoAuxiliar.getNodeName() == "Libro") {
                    //Si entramos aqui, tenemos un nodo libro, asique lo procesamos
                    //para posteriormente poder enviarlo a la pantalla
                    auxiliar = procesarLibro(nodoAuxiliar);

                    //Traigo el codigo del DOM para guardar los resultados en 
                    //procesar libro
                    salida = salida + "\n" + "Publicado en: " + auxiliar[0];
                    salida = salida + "\n" + "El autor es: " + auxiliar[2];
                    salida = salida + "\n" + "El titulo es: " + auxiliar[1];
                    salida = salida + "\n -----------------------------";

                } else {

                    salida = salida + "\n"
                            + nodeList.item(i).getChildNodes().item(0).getNodeValue();

                }
            }

            return salida;
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            return salida;
        }
    }

    //Este es un metodo importado directamente desde la clase DOM del ejercicio anterior
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
}
