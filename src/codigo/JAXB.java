/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.io.File;
import java.util.List;
import javaPersonajes.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author Daniel
 */
public class JAXB {

    Personajes misPersonajes = null;

    public int abrir_XML_JAXB(File fichero) {

        JAXBContext contexto;
        try {
            //Se crea una instancia JAXB
            contexto = JAXBContext.newInstance(Personajes.class);

            //Crea un objeto Unmarsheller
            Unmarshaller u = contexto.createUnmarshaller();

            //Deserializa el fichero
            misPersonajes = (Personajes) u.unmarshal(fichero);

            System.out.println("JAXB Leido");

            return 0;
        } catch (Exception ex) {
            ex.printStackTrace();
            return -1;
        }
    }

    public String recorrerJAXByMostrar() {

        String cadena_resultado = "";
//
//        //Crea una lista con objetos de tipo libro
//        List<Libros.Libro> lLibros = misLibros.getLibro();
//
//        //Recorre lalista para sacar los valores
//        for (int i = 0; i < lLibros.size(); i++) {
//            cadena_resultado = cadena_resultado + "\n" + "Publicado en:"
//                    + lLibros.get(i).getPublicadoEn();
//            cadena_resultado = cadena_resultado + "\n" + "El Autor es:" + lLibros.get(i).getAutor();
//            cadena_resultado = cadena_resultado + "\n" + "El Titulo es:" + lLibros.get(i).getTitulo();
//            cadena_resultado = cadena_resultado + "\n" + "La Editorial es:" + lLibros.get(i).getEditorial();
//            cadena_resultado = cadena_resultado + "\n" + "------------------------------------";
//        }
        return cadena_resultado;
    }

}
