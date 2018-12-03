/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.File;
import javaPersonajes.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Daniel
 */
public class JAXB {

    Personajes misPersonajes;
    JAXBContext context;

    /**
     * Con esta funcion vamos a abrir un documento xml
     *
     * @param fichero Documento a abrir
     * @return 0 si se ha abierto, -1 si ha fallado
     */
    public int abrir_XML_JAXB(File fichero) {

        JAXBContext contexto;
        try {
            //Se crea una instancia JAXB
            context = JAXBContext.newInstance(Personajes.class);

            //Crea un objeto Unmarsheller
            Unmarshaller u = context.createUnmarshaller();

            //Deserializa el fichero
            misPersonajes = (Personajes) u.unmarshal(fichero);

            System.out.println("JAXB Leido");

            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    /**
     * Con esta funcion haremos lo contrario lo guardaremos como archivo XML
     *
     * @param archivo Archivo donde guardarlo
     * @return 0 si se ha abierto, -1 si ha fallado
     */
    public int guardarJAXBcomoFILE(File archivo) {
        try {

            try {

                //Creamos un objeto marshaller
                Marshaller m = context.createMarshaller();
                //Le damos formato para que no nos lo guarde en una linea
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                //llamamos al marshaller y serializamos el objeto misPerros en el archivo que le pasamos como parámetro
                m.marshal(misPersonajes, archivo);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;

        } catch (Exception e) {
            return -1;
        }
    }
}
