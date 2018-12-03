/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.Attributes;

/**
 *
 * @author Daniel
 */
public class SAX {

    SAXParser parser;
    ManejadorSAX sh;
    File ficheroXML;

    /**
     * Con esta funcion vamos a abrir un documento como SAX
     *
     * @param fichero Documento a abrir
     * @return 0 si se ha abierto, -1 si ha fallado
     */
    public int abrir_XML_SAX(File fichero) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();

            //Se crea un objeto SAXParser para interpretar el documento XML
            parser = factory.newSAXParser();

            //Se crea una instancia del manejador que sera el que recorra
            //el documento XMl secuencialmente
            sh = new ManejadorSAX();

            ficheroXML = fichero;

            System.out.println("SAX Leido");

            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public String recorrerSAX() {
        try {
            parser.parse(ficheroXML, sh);
            return sh.cadena_resultado;
        } catch (SAXException e) {
            e.printStackTrace();
            return "Error al parsear con SAX";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error al parsear con SAX";
        }
    }
}

/**
 * Clase adicional para recorrer el XML
 *
 * @author Daniel
 */
class ManejadorSAX extends DefaultHandler {

    int ultimoelement;
    String cadena_resultado = "";
    //En este string voy a guardar datos para mantener ordenado lo que sale por el textPane
    String cadena_auxiliar = "";

    public ManejadorSAX() {
        ultimoelement = 0;
    }

    /**
     * Cuando un elemento empieza, leere de que tipo es. Segun su resultado un
     * switch dira que hacer. Ademas ire usando la cadena_auxiliar para mantener
     * todo mas ordenado y que quede mas limpio a la hora de ponerlo sobre la
     * pantalla.
     *
     * @param uri
     * @param localName
     * @param qName
     * @param atts
     * @throws SAXException
     */
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes atts) throws SAXException {
        //Lo primero que podemos encontrarnos es un Personaje, asique vmaos a sacar
        //todos los atributos que tiene
        //Posteriormente iremos sacado uno a uno los elementos que tiene un Personaje
        if (qName.equals("Personaje")) {
            cadena_resultado = cadena_resultado
                    + "\nNombre: " + atts.getValue(atts.getQName(0))
                    + "\n"
                    + "\nNivel: " + atts.getValue(atts.getQName(11))
                    + "\nClase: " + atts.getValue(atts.getQName(7))
                    + "\nRaza: " + atts.getValue(atts.getQName(8))
                    + "\nSexo: " + atts.getValue(atts.getQName(9))
                    + "\nAlineaminto: " + atts.getValue(atts.getQName(10))
                    + "\nFuerza: " + atts.getValue(atts.getQName(1))
                    + "\nDestreza: " + atts.getValue(atts.getQName(2))
                    + "\nConstitucion: " + atts.getValue(atts.getQName(3))
                    + "\nInteligencia: " + atts.getValue(atts.getQName(4))
                    + "\nSabiduria: " + atts.getValue(atts.getQName(5))
                    + "\nCarisma: " + atts.getValue(atts.getQName(6))
                    + "\n";
            ultimoelement = 1;
        } else if (qName.equals("Experiencia")) {
            ultimoelement = 2;
            cadena_resultado = cadena_resultado + "\nExperiencia: ";
        } else if (qName.equals("Armas")) {
            cadena_resultado = cadena_resultado + "\n -------------------------------------";
            cadena_resultado = cadena_resultado + "\nEstas son las armas: ";
            ultimoelement = 3;
        } else if (qName.equals("Arma")) {
            cadena_resultado = cadena_resultado + "\n";
            ultimoelement = 4;
        } else if (qName.equals("Armaduras")) {
            cadena_resultado = cadena_resultado + "\n -------------------------------------";
            cadena_resultado = cadena_resultado + "\nEstas son las armaduras: ";
            ultimoelement = 5;
        } else if (qName.equals("Armadura")) {
            cadena_resultado = cadena_resultado + "\n";
            ultimoelement = 6;
        } else if (qName.equals("Objetos")) {
            cadena_resultado = cadena_resultado + "\n -------------------------------------";
            cadena_resultado = cadena_resultado + "\nEstos son los objetos: ";
            ultimoelement = 7;
        } else if (qName.equals("Objeto")) {
            cadena_resultado = cadena_resultado + "\n"
                    + atts.getValue(atts.getQName(0)) + " "
                    + "\n";
            ultimoelement = 8;
        } else if (qName.equals("Habilidades")) {
            cadena_resultado = cadena_resultado + "\n -------------------------------------";
            cadena_resultado = cadena_resultado + "\nEstas son las habilidades: ";
            ultimoelement = 9;
        } else if (qName.equals("Habilidad")) {
            //Ahora guardamos lo siguiente en el auxiliar
            cadena_auxiliar
                    = "\nHabilidad de Clase: " + atts.getValue(atts.getQName(0))
                    + "\nEntrenada: " + atts.getValue(atts.getQName(2))
                    + "\nTotal: " + atts.getValue(atts.getQName(1))
                    + "\n";
            //Ahora metemos un salto de linea para que quede bonito y despues 
            //leemos lo que hay dentro
            cadena_resultado = cadena_resultado + "\n";
            ultimoelement = 10;
        } else if (qName.equals("Dotes")) {
            cadena_resultado = cadena_resultado + "\n -------------------------------------";
            cadena_resultado = cadena_resultado + "\nEstas son las dotes: ";
            ultimoelement = 11;
        } else if (qName.equals("Dote")) {
            cadena_resultado = cadena_resultado + "\n";
            ultimoelement = 12;
        } else if (qName.equals("Conjuros")) {
            cadena_resultado = cadena_resultado + "\n -------------------------------------";
            cadena_resultado = cadena_resultado + "\nEstos son los conjuros: ";
            ultimoelement = 13;
        } else if (qName.equals("Conjuro")) {
            //Ahora guardamos lo siguiente en el auxiliar
            cadena_auxiliar
                    = "\nEscuela: " + atts.getValue(atts.getQName(0))
                    + "\nTiempo de Lanzamiento: " + atts.getValue(atts.getQName(1))
                    + "\nAlcance: " + atts.getValue(atts.getQName(2))
                    + "\nNivel: " + atts.getValue(atts.getQName(3))
                    + "\n";
            //Ahora metemos un salto de linea para que quede bonito y despues 
            //leemos lo que hay dentro
            cadena_resultado = cadena_resultado + "\n";
            ultimoelement = 14;
        }

    }

    // Cuando en este ejemplo se detecta el fnal de un elemento 
    // se pone una linea discontinua a la salida
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if (qName.equals("Personaje")) {
            cadena_resultado = cadena_resultado + "\n --------------------------------------------------------------------------------------------"
                    + "\n --------------------------------------------------------------------------------------------";
        } //Ahora que tenemos el nombre puesto primero, sacamos lo que hay en el auxiliar
        //y lo ponemos debajo.
        else if (qName.equals("Conjuro")) {
            cadena_resultado = cadena_resultado + cadena_auxiliar;
        } else if (qName.equals("Habilidad")) {
            cadena_resultado = cadena_resultado + cadena_auxiliar;
        }
    }

    // Cuando se detecta una cadena de texto posterior a uno de los elementos
    // que hay dentro de PErsonaje entonces guarda ese texto en la variable correspondiente
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (ultimoelement > 0 && ultimoelement <= 14) {
            for (int i = start; i < length + start; i++) {
                cadena_resultado = cadena_resultado + ch[i];
            }
        }
    }

}
