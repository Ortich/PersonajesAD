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

    /**
     * Funcion con la que abriremos el fichero
     *
     * @param fichero
     * @return 0 si se ha abierto, -1 si ha fallado
     */
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

    /**
     * Con esta funcion vamos a recoger una consulta Xpath que entrara como
     * parametro y la ejecutaremos, devolviendo el resultado de la misma.
     *
     * @param consulta consulta a realizar
     * @return Resultado
     */
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

    /**
     * Con esta funcion vamos a recoger un Nodo pesonaje y vamos a destriparlo,
     * guardando asi los datos de sus Armas, Armaduras, Objetos...etc. Una vez
     * lo tengamo todo, se va a guardar en un ArrayList que se devolvera con
     * todo lo que el nodo contenga.
     *
     * @param n Nodo Personaje a destripar
     * @return ArrayList con todas las caracteristicas
     */
    protected ArrayList<String> procesarPersonaje(Node n) {

        ArrayList<String> resultado = new ArrayList<String>();
        Node ntemp = null;

        //Obtiene los atrivutos del nodo Personaje lo primero
        for (int i = 0; i < n.getAttributes().getLength(); i++) {
            //El nombre lo pondremos sin su descripcion, ya que vendra de forma especial en el resultado
            if (i != 8) {
                resultado.add(n.getAttributes().item(i).getNodeName() + ": " + n.getAttributes().item(i).getNodeValue());
            } else {
                resultado.add(n.getAttributes().item(i).getNodeValue());
            }
        }

        //Obtiene los hijos del Personaje(Armas, Armaduras, Objetos....)
        NodeList nodos = n.getChildNodes();

        //Ahora procesa uno a uno esos hijos, guardando todo lo que tienen dentro
        //Para ello llamara a nuevas funciones 
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

    /**
     * Con esta funcion vamos a recorrer un Nodo mas pequeño que no contenga
     * ningun atributo. Devolvera todos los hijos que tenga con su contenido.
     *
     * @param n Nodo a destripar
     * @return Todos los nodos hijos y su contenido
     */
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

    /**
     * Con esta funcion vamos a coger un Nodo que contenga Atributos y elementos
     * y vamos a recoger toda la informacion que contenga. Para facilitar las
     * cosas vamos a devolverlo todo como un solo bloque String.
     *
     * @param n Nodo a destripar
     * @return Todos los nodos hijos, sus atributos y su contenido
     */
    private String recorreNodoComplejo(Node n) {
        String resultado = "";
        Node ntemp = null;
        String auxiliar = "";

        //Obtiene los hijos delNodo
        NodeList nodos = n.getChildNodes();

        //Vamos a diferenciar entre dos clases, Objetos y el resto
        //Los objetos devolveran el resultado de la siguiente forma
        // Objeto xCantidad
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
