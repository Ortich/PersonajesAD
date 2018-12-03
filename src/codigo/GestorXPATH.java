package codigo;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.ArrayList;
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
        ArrayList<ArrayList<String>> auxiliar = new ArrayList<ArrayList<String>>();
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
                //del tipo Personaje
                if (nodoAuxiliar.getNodeType() == Node.ELEMENT_NODE
                        && nodoAuxiliar.getNodeName() == "Personaje") {
                    //Si entramos aqui, tenemos un nodo Personaje, asique lo procesamos
                    //para posteriormente poder enviarlo a la pantalla
                    auxiliar.add(procesarPersonaje(nodoAuxiliar));

                    //Primero sacamos el nombre del personaje
                    salida = salida + "\n" + "Nombre del personaje: ";
                    salida = salida + "\n" + auxiliar.get(i).get(8);
                    salida = salida + "\n";

                    //Ahora sacamos los datos principales
                    for (int j = 0; j < auxiliar.get(i).size(); j++) {
                        if (j != 8) {
                            salida = salida + "\n" + auxiliar.get(i).get(j);
                        }
                    }

                    salida = salida + "\n --------------------------------------------------------------------------------------------";

                } //Si lo que tenemos es un nodo de un tipo especifico, vamos a mostrar 
                //solo lo que nos piden
                else if (nodoAuxiliar.getNodeType() == Node.ELEMENT_NODE
                        && nodoAuxiliar.getNodeName() == "Armas") {
                    salida = salida + "\n" + recorreNodoComplejo(nodoAuxiliar);
                    salida = salida + "\n --------------------------------------------------------------------------------------------" + "\n";
                } else if (nodoAuxiliar.getNodeType() == Node.ELEMENT_NODE
                        && nodoAuxiliar.getNodeName() == "Armaduras") {
                    salida = salida + "\n" + recorreNodoComplejo(nodoAuxiliar);
                    salida = salida + "\n --------------------------------------------------------------------------------------------" + "\n";
                } else if (nodoAuxiliar.getNodeType() == Node.ELEMENT_NODE
                        && nodoAuxiliar.getNodeName() == "Objetos") {
                    salida = salida + "\n --------------------------------------------------------------------------------------------" + "\n";
                    salida = salida + "\n" + recorreNodoComplejo(nodoAuxiliar);
                } else if (nodoAuxiliar.getNodeType() == Node.ELEMENT_NODE
                        && nodoAuxiliar.getNodeName() == "Dotes") {
                    salida = salida + "\n --------------------------------------------------------------------------------------------" + "\n";
                    salida = salida + "\n" + recorreNodoComplejo(nodoAuxiliar);
                } else if (nodoAuxiliar.getNodeType() == Node.ELEMENT_NODE
                        && nodoAuxiliar.getNodeName() == "Conjuros") {
                    salida = salida + "\n" + recorreNodoComplejo(nodoAuxiliar);
                    salida = salida + "\n --------------------------------------------------------------------------------------------" + "\n";
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

    protected ArrayList<String> procesarPersonaje(Node n) {

        ArrayList<String> resultado = new ArrayList<String>();
        Node ntemp = null;
        int contador = 1;

        //Obtiene los datos del nodo
        String[] auxAtts = new String[n.getAttributes().getLength()];
        for (int i = 0; i < n.getAttributes().getLength(); i++) {
            if (i != 8) {
                resultado.add(n.getAttributes().item(i).getNodeName() + ": " + n.getAttributes().item(i).getNodeValue());
                contador++;
            } else {
                resultado.add(n.getAttributes().item(i).getNodeValue());
                contador++;
            }
        }

        //Obtiene los hijos del Personaje(Armas, Armaduras, Objetos....)
        NodeList nodos = n.getChildNodes();

        for (int i = 0; i < nodos.getLength(); i++) {
            ntemp = nodos.item(i);

            if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                switch (ntemp.getNodeName()) {

                    case "Experiencia":
                        resultado.add("\n" + "Experiencia: " + ntemp.getChildNodes().item(0).getNodeValue() + "\n --------------------------------------------------------------------------------------------");
                        break;

                    case "Armas":
                        resultado.add("\n" + "Armas: " + recorreNodoSimple(ntemp) + "\n --------------------------------------------------------------------------------------------");
                        break;

                    case "Armaduras":
                        resultado.add("\n" + "Armaduras: " + recorreNodoSimple(ntemp) + "\n --------------------------------------------------------------------------------------------");
                        break;

                    case "Objetos":
                        resultado.add("\n" + "Objetos: " + recorreNodoComplejo(ntemp) + "\n --------------------------------------------------------------------------------------------");
                        break;

                    case "Dotes":
                        resultado.add("\n" + "Dotes: " + recorreNodoSimple(ntemp) + "\n --------------------------------------------------------------------------------------------");
                        break;

                    case "Habilidades":
                        resultado.add("\n" + "Habilidades: " + recorreNodoComplejo(ntemp) + "\n --------------------------------------------------------------------------------------------");
                        break;

                    case "Conjuros":
                        resultado.add("\n" + "Conjuros: " + recorreNodoComplejo(ntemp) + "\n --------------------------------------------------------------------------------------------");
                        break;
                }

            }
        }
        return resultado;
    }

    private String recorreNodoSimple(Node n) {
        String resultado = "";
        Node ntemp = null;

        //Obtiene los hijos del Nodo
        NodeList nodos = n.getChildNodes();

        for (int i = 0; i < nodos.getLength(); i++) {
            ntemp = nodos.item(i);

            if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                //Accedemos al interior del nodo para sacar el texto
                resultado = resultado + "\n" + ntemp.getChildNodes().item(0).getNodeValue();

            }
        }

        return resultado;
    }

    private String recorreNodoComplejo(Node n) {
        String resultado = "";
        Node ntemp = null;
        String auxiliar = "";

        //Obtiene los hijos delNodo
        NodeList nodos = n.getChildNodes();

        if (n.getNodeName() == "Objetos") {
            for (int i = 0; i < nodos.getLength(); i++) {
                ntemp = nodos.item(i);

                if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                    //Vamos a sacar primero el texto del nodo, luego recogeremos los atributos
                    resultado = resultado + "\n" + ntemp.getChildNodes().item(0).getNodeValue();
                    for (int j = 0; j < ntemp.getAttributes().getLength(); j++) {
                        resultado = resultado + " x" + ntemp.getAttributes().item(j).getNodeValue();
                    }
                }
            }
        } else {
            for (int i = 0; i < nodos.getLength(); i++) {
                ntemp = nodos.item(i);

                if (ntemp.getNodeType() == Node.ELEMENT_NODE) {
                    //Vamos a sacar primero el texto del nodo, luego recogeremos los atributos
                    resultado = resultado + "\n" + ntemp.getChildNodes().item(0).getNodeValue();
                    for (int j = 0; j < ntemp.getAttributes().getLength(); j++) {
                        resultado = resultado + "\n" + ntemp.getAttributes().item(j).getNodeName() + ": " + ntemp.getAttributes().item(j).getNodeValue();
                    }
                    resultado = resultado + "\n";
                }
            }
        }
        return resultado;
    }

}
