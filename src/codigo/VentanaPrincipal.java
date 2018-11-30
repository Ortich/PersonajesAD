/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import org.w3c.dom.Document;

/**
 *
 * @author Daniel
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    //Declaraciones de instancia
    //Objetos par manejar los datos
    DOM dom = new DOM();
    SAX sax = new SAX();
    JAXB jaxb = new JAXB();
    GestorXPATH xpath = new GestorXPATH();

    //Variables generales
    //boolean annadiendo = false;
    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        jDialogFileChooser.setSize(560, 350);
        jDialogFileChooser.setResizable(false);
        jDialogBuscador.setSize(365, 285);
        jDialogBuscador.setResizable(false);
        this.setResizable(false);
        jTextFieldAbierto.setEditable(false);
        jTextFieldAbierto.setBackground(Color.red);
        jTextPaneConsola.setEditable(false);
        jLabelConsolaErrores.setText(" ");
        jButtonGuardar.setVisible(false);
        jButtonCancelar.setVisible(false);

    }

    public int abreDocumento(File doc) {
        boolean todosAbiertos = true;
        try {
            jLabelConsolaErrores.setText(" ");

            if (dom.abrir_XML_DOM(doc) != 0) {
                todosAbiertos = false;
            }
            if (sax.abrir_XML_SAX(doc) != 0) {
                todosAbiertos = false;
            }
            if (jaxb.abrir_XML_JAXB(doc) != 0) {
                todosAbiertos = false;
            }
            if (xpath.abrirXPath(doc) != 0) {
                todosAbiertos = false;
            }

            if (todosAbiertos) {
                jTextPaneConsola.setText(sax.recorrerSAX());
                jTextFieldAbierto.setBackground(Color.green);
                jDialogFileChooser.setVisible(false);

                //Aqui metemos todos los personajes en el comboBox
                insertaPersonajesEnComboBox();

            } else {
                jTextFieldAbierto.setBackground(Color.red);
                jLabelConsolaErrores.setText("Error al abrir el archivo");
                jDialogFileChooser.setVisible(false);
                return -1;
            }
            return 0;
        } catch (Exception e) {
            jTextFieldAbierto.setBackground(Color.red);
            jLabelConsolaErrores.setText("Error al abrir el archivo");
            jDialogFileChooser.setVisible(false);
            return -1;
        }
    }

    /**
     * Con esta funcion llenaremos el comboBox con los peronajes del documento
     * xml
     */
    public void insertaPersonajesEnComboBox() {
        boolean sigue = true;
        int index = 0;
        while (sigue) {
            try {
                jComboBoxPersonajes.addItem(jaxb.misPersonajes.getPersonaje().get(index).getNombre());
                index++;
            } catch (Exception e) {
                sigue = false;
            }
        }
    }

    /**
     * Con esta funcion lo que haremos sera modificar el personaje en el que
     * estamos situados, utilizando la estructura JAXB que hemos creado para
     * ello.
     *
     * @param personaje Personaje a modificar
     * @return retorna 0si ha salido bien, -1 si hay un fallo
     */
    /**
     * Esta funcion lo que hara sera actualizar los datos mostrados en la
     * aplicacion
     *
     * @param numero -> Indice del personaje a mosntrar
     */
    public void actualizaDatos(int numero) {
        boolean sigue;
        int index;
        try {
            //Aqui actualizamos caracteristicas generales del personaje
            jTextFieldNombre.setText(jaxb.misPersonajes.getPersonaje().get(numero).getNombre());
            jTextFieldSexo.setText(jaxb.misPersonajes.getPersonaje().get(numero).getSexo());
            jTextFieldAlineamiento.setText(jaxb.misPersonajes.getPersonaje().get(numero).getAlineamiento());
            jTextFieldNivel.setText(jaxb.misPersonajes.getPersonaje().get(numero).getNivel());
            jTextFieldRaza.setText(jaxb.misPersonajes.getPersonaje().get(numero).getRaza());
            jTextFieldClase.setText(jaxb.misPersonajes.getPersonaje().get(numero).getClase());
            jTextFieldExperiencia.setText(jaxb.misPersonajes.getPersonaje().get(numero).getExperiencia());

            //Aqui actualizamos las principales puntuaciones del personaje
            jTextFieldFuerza.setText(jaxb.misPersonajes.getPersonaje().get(numero).getFuerza());
            jTextFieldDestreza.setText(jaxb.misPersonajes.getPersonaje().get(numero).getDestreza());
            jTextFieldConstitucion.setText(jaxb.misPersonajes.getPersonaje().get(numero).getConstitucion());
            jTextFieldInteligencia.setText(jaxb.misPersonajes.getPersonaje().get(numero).getInteligencia());
            jTextFieldSabiduria.setText(jaxb.misPersonajes.getPersonaje().get(numero).getSabiduria());
            jTextFieldCarisma.setText(jaxb.misPersonajes.getPersonaje().get(numero).getCarisma());

            //Ahora vamos a actualizar los jComboBox
            //Primero las armas
            jComboBoxArmas.removeAllItems();
            sigue = true;
            index = 0;
            while (sigue) {
                try {
                    jComboBoxArmas.addItem(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getArmas().getArma().get(index));
                    index++;
                } catch (Exception e) {
                    sigue = false;
                }

            }
            //Ahora vamos a poner en la caja el primer arma
            jTextFieldArmas.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getArmas().getArma().get(0));

            //-----------------------------------
            //Ahora las armaduras, hacemos lo mismo
            jComboBoxArmadura.removeAllItems();
            sigue = true;
            index = 0;
            while (sigue) {
                try {
                    jComboBoxArmadura.addItem(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getArmaduras().getArmadura().get(index));
                    index++;
                } catch (Exception e) {
                    sigue = false;
                }

            }
            //Ahora vamos a poner en la caja la primera armadura
            jTextFieldArmaduras.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getArmaduras().getArmadura().get(0));

            //-----------------------------------
            //Tocan los objetos
            jComboBoxObjetos.removeAllItems();
            sigue = true;
            index = 0;
            while (sigue) {
                try {
                    jComboBoxObjetos.addItem(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getObjetos().getObjeto().get(index).getValue());
                    index++;
                } catch (Exception e) {
                    sigue = false;
                }

            }
            //Ahora vamos a poner en las cajas el primer objeto
            jTextFieldObjetos.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getObjetos().getObjeto().get(0).getValue());
            jTextFieldCantidad.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getObjetos().getObjeto().get(0).getCantidad());

            //-----------------------------------
            //Tocan las dotes
            jComboBoxDotes.removeAllItems();
            sigue = true;
            index = 0;
            while (sigue) {
                try {
                    jComboBoxDotes.addItem(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getDotes().getDote().get(index));
                    index++;
                } catch (Exception e) {
                    sigue = false;
                }

            }
            //Ahora vamos a poner en la caja la primera dote
            jTextFieldObjetos.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getDotes().getDote().get(0));

            //-----------------------------------
            //Tocan las hablidades
            jComboBoxHabilidades.removeAllItems();
            sigue = true;
            index = 0;
            while (sigue) {
                try {
                    jComboBoxHabilidades.addItem(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getHabilidades().getHabilidad().get(index).getValue());
                    index++;
                } catch (Exception e) {
                    sigue = false;
                }

            }
            //Ahora vamos a poner en las cajas la primera habilidad
            jTextFieldHabilidad.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getHabilidades().getHabilidad().get(0).getValue());
            jTextFieldDeClase.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getHabilidades().getHabilidad().get(0).getHabDeClase());
            jTextFieldTotal.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getHabilidades().getHabilidad().get(0).getTotal());
            jTextFieldEntrenada.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getHabilidades().getHabilidad().get(0).getSoloEntrenada());

            //-----------------------------------
            //Tocan los conjuros
            jComboBoxConjuros.removeAllItems();
            sigue = true;
            index = 0;
            while (sigue) {
                try {
                    jComboBoxConjuros.addItem(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getConjuros().getConjuro().get(index).getValue());
                    index++;
                } catch (Exception e) {
                    sigue = false;
                }

            }
            //Ahora vamos a poner en las cajas el primer conjuro
            jTextFieldConjuro.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getConjuros().getConjuro().get(0).getValue());
            jTextFieldConjuroNivel.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getConjuros().getConjuro().get(0).getNivel());
            jTextFieldEscuela.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getConjuros().getConjuro().get(0).getEscuela());
            jTextFieldTiempo.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getConjuros().getConjuro().get(0).getTiempoLanzamiento());
            jTextFieldAlcance.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getConjuros().getConjuro().get(0).getAlcance());

        } catch (Exception e) {
            System.out.println("No hay mas personajes");
        }

    }

    /**
     * Con esta funcion pondremos todas las cajas y comboBox en blanco. TODO:
     * Las habilidades son las mismas para todo, asique estas hay que omitirlas
     * e idear una forma de poder modificarlas
     */
    public void prepararNuevoPersonaje() {

        //Empezamos con las caracteristicas principales
        jTextFieldNombre.setText(" ");
        jTextFieldSexo.setText(" ");
        jTextFieldAlineamiento.setText(" ");
        jTextFieldNivel.setText(" ");
        jTextFieldRaza.setText(" ");
        jTextFieldClase.setText(" ");

        jTextFieldFuerza.setText(" ");
        jTextFieldDestreza.setText(" ");
        jTextFieldConstitucion.setText(" ");
        jTextFieldInteligencia.setText(" ");
        jTextFieldSabiduria.setText(" ");
        jTextFieldCarisma.setText(" ");
        jTextFieldExperiencia.setText(" ");

        //Ahora vamos con los jComboBox, iremos uno a uno, reseteando tambien 
        //sus cajas a la vez.
        //Armas
        jComboBoxArmas.removeAllItems();
        jTextFieldArmas.setText(" ");

        //Armaduras
        jComboBoxArmadura.removeAllItems();
        jTextFieldArmaduras.setText(" ");

        //Objetos
        jComboBoxObjetos.removeAllItems();
        jTextFieldObjetos.setText(" ");
        jTextFieldCantidad.setText(" ");

        //Dotes
        jComboBoxDotes.removeAllItems();
        jTextFieldDote.setText(" ");

        //Habilidades
        jComboBoxHabilidades.removeAllItems();
        jTextFieldHabilidad.setText(" ");
        jTextFieldClase.setText(" ");
        jTextFieldTotal.setText(" ");
        jTextFieldEntrenada.setText(" ");

        //Conjuros
        jComboBoxConjuros.removeAllItems();
        jTextFieldConjuro.setText(" ");
        jTextFieldConjuroNivel.setText(" ");
        jTextFieldEscuela.setText(" ");
        jTextFieldTiempo.setText(" ");
        jTextFieldAlcance.setText(" ");
    }

    /**
     * Con esta funcion vamos a tomar los valores que hay en las cajas y vamos a
     * construir un nuevo personaje utilizando para ello el dom.
     */
    public void annadePersonajeNuevo() {
        //Primero vamos a declarar variables para guardar los datos
        String[] datosBase = new String[13];
        ArrayList<String> armas = new ArrayList<String>();
        ArrayList<String> armadura = new ArrayList<String>();
        ArrayList<String[]> objetos = new ArrayList<String[]>();
        ArrayList<String> dotes = new ArrayList<String>();
        ArrayList<String[]> habilidades = new ArrayList<String[]>();
        ArrayList<String[]> conjuros = new ArrayList<String[]>();

        //Ahora vamos a empezar gaurdando las estadistica base
        datosBase[0] = jTextFieldNombre.getText();
        datosBase[1] = jTextFieldSexo.getText();
        datosBase[2] = jTextFieldAlineamiento.getText();
        datosBase[3] = jTextFieldNivel.getText();
        datosBase[4] = jTextFieldRaza.getText();
        datosBase[5] = jTextFieldClase.getText();

        datosBase[6] = jTextFieldFuerza.getText();
        datosBase[7] = jTextFieldDestreza.getText();
        datosBase[8] = jTextFieldConstitucion.getText();
        datosBase[9] = jTextFieldInteligencia.getText();
        datosBase[10] = jTextFieldSabiduria.getText();
        datosBase[11] = jTextFieldCarisma.getText();
        datosBase[12] = jTextFieldExperiencia.getText();

        //TODO
        //Ahora vamos a recoger todos los jComboBox y su contenido uno a uno
//        for (int i = 0; i < jComboBoxArmas.getItemCount(); i++) {
//            jComboBoxArmas.setSelectedIndex(i);
//            armas.add((String) jComboBoxArmas.getSelectedItem());
//        }
//        for (int i = 0; i < jComboBoxArmadura.getItemCount(); i++) {
//            jComboBoxArmadura.setSelectedIndex(i);
//            armadura.add((String) jComboBoxArmadura.getSelectedItem());
//        }
//        for (int i = 0; i < jComboBoxObjetos.getItemCount(); i++) {
//            jComboBoxObjetos.setSelectedIndex(i);
//            objetos.add((String) jComboBoxObjetos.getSelectedItem(), j);
//        }
        //Una vez tenemos todos los datos, procedemos a pasarselos al dom para que
        //los guarde
        dom.annadirDOM(datosBase, armas, armadura, objetos, dotes, habilidades, conjuros);
        dom.guardarDOMcomoFILE(jFileChooser.getSelectedFile().getAbsolutePath());
        abreDocumento(jFileChooser.getSelectedFile());
    }

    /**
     * Con esta funcion vamos a recorrer las cajas del programa para actualizar
     * el documento gracias a la estructura jaxb, y luego guardaremos el
     * documento con ello.
     */
    public void modificaPersonaje() {
        jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).setNombre(jTextFieldNombre.getText());
        jaxb.guardarJAXBcomoFILE(jFileChooser.getSelectedFile());
        abreDocumento(jFileChooser.getSelectedFile());
    }

    /**
     * Con este metodo haremosu reset al buscador para que al volver a abrirlo,
     * las casillas esten listas para volver a buscar
     */
    public void reseteaBuscador() {
        jTextFieldNombreBuscar.setText("Nombre");
        jTextFieldRazaBuscar.setText("Raza");
        jTextFieldSexoBuscar.setText("Sexo");
        jTextFieldNivelBuscar.setText("Nivel");
        jTextFieldClaseBuscar.setText("Clase");
        jTextFieldAlineamientoBuscar.setText("Alineamiento");

        jTextFieldNombreBuscar.setEnabled(true);
        jTextFieldRazaBuscar.setEnabled(true);
        jTextFieldSexoBuscar.setEnabled(true);
        jTextFieldNivelBuscar.setEnabled(true);
        jTextFieldClaseBuscar.setEnabled(true);
        jTextFieldAlineamientoBuscar.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialogFileChooser = new javax.swing.JDialog();
        jFileChooser = new javax.swing.JFileChooser();
        jDialogBuscador = new javax.swing.JDialog();
        jButtonBuscar = new javax.swing.JButton();
        jLabelNombreBuscar = new javax.swing.JLabel();
        jTextFieldNombreBuscar = new javax.swing.JTextField();
        jLabelRazaBuscar = new javax.swing.JLabel();
        jTextFieldRazaBuscar = new javax.swing.JTextField();
        jLabelSexoBuscar = new javax.swing.JLabel();
        jTextFieldSexoBuscar = new javax.swing.JTextField();
        jLabelNivelBuscar = new javax.swing.JLabel();
        jTextFieldNivelBuscar = new javax.swing.JTextField();
        jLabelClaseBuscar = new javax.swing.JLabel();
        jTextFieldClaseBuscar = new javax.swing.JTextField();
        jLabelAlineamientoBuscar = new javax.swing.JLabel();
        jTextFieldAlineamientoBuscar = new javax.swing.JTextField();
        jButtonObjetosBuscar = new javax.swing.JButton();
        jButtonArmadurasBuscar = new javax.swing.JButton();
        jButtonArmasBuscar = new javax.swing.JButton();
        jButtonConjurosBuscar = new javax.swing.JButton();
        jButtonDotesBuscar = new javax.swing.JButton();
        jLabelAbierto = new javax.swing.JLabel();
        jTextFieldAbierto = new javax.swing.JTextField();
        jLabelNombre = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabelRaza = new javax.swing.JLabel();
        jTextFieldRaza = new javax.swing.JTextField();
        jLabelClase = new javax.swing.JLabel();
        jTextFieldClase = new javax.swing.JTextField();
        jLabelSexo = new javax.swing.JLabel();
        jTextFieldSexo = new javax.swing.JTextField();
        jLabelAlineamiento = new javax.swing.JLabel();
        jTextFieldAlineamiento = new javax.swing.JTextField();
        jLabelNivel = new javax.swing.JLabel();
        jTextFieldNivel = new javax.swing.JTextField();
        jLabelFuerza = new javax.swing.JLabel();
        jTextFieldFuerza = new javax.swing.JTextField();
        jLabelDestreza = new javax.swing.JLabel();
        jTextFieldDestreza = new javax.swing.JTextField();
        jLabelInteligencia = new javax.swing.JLabel();
        jTextFieldInteligencia = new javax.swing.JTextField();
        jLabelConstitucion = new javax.swing.JLabel();
        jTextFieldConstitucion = new javax.swing.JTextField();
        jLabelSabiduria = new javax.swing.JLabel();
        jTextFieldSabiduria = new javax.swing.JTextField();
        jLabelCarisma = new javax.swing.JLabel();
        jTextFieldCarisma = new javax.swing.JTextField();
        jComboBoxArmas = new javax.swing.JComboBox<>();
        jLabelArmas = new javax.swing.JLabel();
        jTextFieldArmas = new javax.swing.JTextField();
        jComboBoxArmadura = new javax.swing.JComboBox<>();
        jLabelArmaduras = new javax.swing.JLabel();
        jTextFieldArmaduras = new javax.swing.JTextField();
        jComboBoxHabilidades = new javax.swing.JComboBox<>();
        jLabelhabilidades = new javax.swing.JLabel();
        jTextFieldHabilidad = new javax.swing.JTextField();
        jTextFieldDeClase = new javax.swing.JTextField();
        jTextFieldTotal = new javax.swing.JTextField();
        jTextFieldEntrenada = new javax.swing.JTextField();
        jLabelHabilidadNombre = new javax.swing.JLabel();
        jLabelHabilidadDeClase = new javax.swing.JLabel();
        jLabelHabilidadTotal = new javax.swing.JLabel();
        jLabelHabilidadEntrenada = new javax.swing.JLabel();
        jComboBoxConjuros = new javax.swing.JComboBox<>();
        jLabelConjuros = new javax.swing.JLabel();
        jTextFieldConjuro = new javax.swing.JTextField();
        jTextFieldEscuela = new javax.swing.JTextField();
        jTextFieldTiempo = new javax.swing.JTextField();
        jTextFieldAlcance = new javax.swing.JTextField();
        jLabelConjuroNombre = new javax.swing.JLabel();
        jLabelConjuroEscuela = new javax.swing.JLabel();
        jLabelConjuroTiempo = new javax.swing.JLabel();
        jLabelConjuroAlcance = new javax.swing.JLabel();
        jTextFieldConjuroNivel = new javax.swing.JTextField();
        jLabelConjuroNivel = new javax.swing.JLabel();
        jLabelObjetos = new javax.swing.JLabel();
        jTextFieldCantidad = new javax.swing.JTextField();
        jComboBoxObjetos = new javax.swing.JComboBox<>();
        jLabelObjetosCantidad = new javax.swing.JLabel();
        jTextFieldObjetos = new javax.swing.JTextField();
        jLabelDotes = new javax.swing.JLabel();
        jComboBoxDotes = new javax.swing.JComboBox<>();
        jTextFieldDote = new javax.swing.JTextField();
        jComboBoxPersonajes = new javax.swing.JComboBox<>();
        jLabelPersonajes = new javax.swing.JLabel();
        jButtonAnnadir = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jButtonAnnadirArma = new javax.swing.JButton();
        jButtonAnnadirArmadura = new javax.swing.JButton();
        jButtonAnnadirObjeto = new javax.swing.JButton();
        jButtonAnnadirDote = new javax.swing.JButton();
        jButtonAnnadirCojuro = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPaneConsola = new javax.swing.JTextPane();
        jLabelExperiencia = new javax.swing.JLabel();
        jTextFieldExperiencia = new javax.swing.JTextField();
        jLabelConsolaErrores = new javax.swing.JLabel();
        jButtonGuardar = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        jFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFileChooserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialogFileChooserLayout = new javax.swing.GroupLayout(jDialogFileChooser.getContentPane());
        jDialogFileChooser.getContentPane().setLayout(jDialogFileChooserLayout);
        jDialogFileChooserLayout.setHorizontalGroup(
            jDialogFileChooserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogFileChooserLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jDialogFileChooserLayout.setVerticalGroup(
            jDialogFileChooserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogFileChooserLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jDialogBuscador.setMinimumSize(new java.awt.Dimension(360, 246));

        jButtonBuscar.setText("Buscar");

        jLabelNombreBuscar.setText("Nombre");

        jTextFieldNombreBuscar.setText("Nombre");
        jTextFieldNombreBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldNombreBuscarMousePressed(evt);
            }
        });

        jLabelRazaBuscar.setText("Raza");

        jTextFieldRazaBuscar.setText("Raza");
        jTextFieldRazaBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldRazaBuscarMousePressed(evt);
            }
        });

        jLabelSexoBuscar.setText("Sexo");

        jTextFieldSexoBuscar.setText("Sexo");
        jTextFieldSexoBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldSexoBuscarMousePressed(evt);
            }
        });

        jLabelNivelBuscar.setText("Nivel");

        jTextFieldNivelBuscar.setText("Nivel");
        jTextFieldNivelBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldNivelBuscarMousePressed(evt);
            }
        });

        jLabelClaseBuscar.setText("Clase");

        jTextFieldClaseBuscar.setText("Clase");
        jTextFieldClaseBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldClaseBuscarMousePressed(evt);
            }
        });

        jLabelAlineamientoBuscar.setText("Alineamiento");

        jTextFieldAlineamientoBuscar.setText("Alineamiento");
        jTextFieldAlineamientoBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldAlineamientoBuscarMousePressed(evt);
            }
        });

        jButtonObjetosBuscar.setText("Todos los Objetos");

        jButtonArmadurasBuscar.setText("Todas las Armaduras");

        jButtonArmasBuscar.setText("Todas las Armas");

        jButtonConjurosBuscar.setText("Todos los Conjuros");

        jButtonDotesBuscar.setText("Todas las Dotes");

        javax.swing.GroupLayout jDialogBuscadorLayout = new javax.swing.GroupLayout(jDialogBuscador.getContentPane());
        jDialogBuscador.getContentPane().setLayout(jDialogBuscadorLayout);
        jDialogBuscadorLayout.setHorizontalGroup(
            jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogBuscadorLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jDialogBuscadorLayout.createSequentialGroup()
                        .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNivelBuscar)
                            .addComponent(jLabelClaseBuscar)
                            .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelRazaBuscar)
                                .addComponent(jLabelSexoBuscar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldClaseBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextFieldNivelBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                .addComponent(jTextFieldSexoBuscar))))
                    .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jTextFieldRazaBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextFieldNombreBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDialogBuscadorLayout.createSequentialGroup()
                        .addComponent(jLabelAlineamientoBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldAlineamientoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jDialogBuscadorLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jLabelNombreBuscar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonArmasBuscar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonArmadurasBuscar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonDotesBuscar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonObjetosBuscar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonConjurosBuscar, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogBuscadorLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButtonBuscar))
        );
        jDialogBuscadorLayout.setVerticalGroup(
            jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogBuscadorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDialogBuscadorLayout.createSequentialGroup()
                        .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldNombreBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNombreBuscar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldRazaBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelRazaBuscar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldSexoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelSexoBuscar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldNivelBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNivelBuscar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldClaseBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelClaseBuscar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldAlineamientoBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelAlineamientoBuscar)))
                    .addGroup(jDialogBuscadorLayout.createSequentialGroup()
                        .addComponent(jButtonArmasBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonArmadurasBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonObjetosBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDotesBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonConjurosBuscar)))
                .addGap(18, 18, 18)
                .addComponent(jButtonBuscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelAbierto.setText("Abierto");

        jLabelNombre.setText("Nombre");

        jTextFieldNombre.setText("Nombre");

        jLabelRaza.setText("Raza");

        jTextFieldRaza.setText("Raza");

        jLabelClase.setText("Clase");

        jTextFieldClase.setText("Clase");

        jLabelSexo.setText("Sexo");

        jTextFieldSexo.setText("Sexo");

        jLabelAlineamiento.setText("Alineamieto");

        jTextFieldAlineamiento.setText("Sexo");

        jLabelNivel.setText("Nivel");

        jTextFieldNivel.setText("Nivel");

        jLabelFuerza.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelFuerza.setText("Fuerza");

        jTextFieldFuerza.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTextFieldFuerza.setText("99");
        jTextFieldFuerza.setMinimumSize(new java.awt.Dimension(40, 40));

        jLabelDestreza.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelDestreza.setText("Destreza");

        jTextFieldDestreza.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTextFieldDestreza.setText("99");
        jTextFieldDestreza.setMinimumSize(new java.awt.Dimension(40, 40));

        jLabelInteligencia.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelInteligencia.setText("Inteligencia");

        jTextFieldInteligencia.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTextFieldInteligencia.setText("99");
        jTextFieldInteligencia.setMinimumSize(new java.awt.Dimension(40, 40));

        jLabelConstitucion.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelConstitucion.setText("Constitucion");

        jTextFieldConstitucion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTextFieldConstitucion.setText("99");
        jTextFieldConstitucion.setMinimumSize(new java.awt.Dimension(40, 40));

        jLabelSabiduria.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelSabiduria.setText("Sabiduria");

        jTextFieldSabiduria.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTextFieldSabiduria.setText("99");
        jTextFieldSabiduria.setMinimumSize(new java.awt.Dimension(40, 40));

        jLabelCarisma.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelCarisma.setText("Carisma");

        jTextFieldCarisma.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTextFieldCarisma.setText("99");
        jTextFieldCarisma.setMinimumSize(new java.awt.Dimension(40, 40));

        jComboBoxArmas.setMaximumRowCount(99);
        jComboBoxArmas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxArmasActionPerformed(evt);
            }
        });

        jLabelArmas.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabelArmas.setText("Armas");

        jTextFieldArmas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldArmas.setText("Arma");

        jComboBoxArmadura.setMaximumRowCount(99);
        jComboBoxArmadura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxArmaduraActionPerformed(evt);
            }
        });

        jLabelArmaduras.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabelArmaduras.setText("Armaduras");

        jTextFieldArmaduras.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldArmaduras.setText("Armadura");

        jComboBoxHabilidades.setMaximumRowCount(99);
        jComboBoxHabilidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxHabilidadesActionPerformed(evt);
            }
        });

        jLabelhabilidades.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabelhabilidades.setText("Habilidades");

        jTextFieldHabilidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldHabilidad.setText("Habilidad");

        jTextFieldDeClase.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldDeClase.setText("Habilidad de clase");

        jTextFieldTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldTotal.setText("Total");

        jTextFieldEntrenada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldEntrenada.setText("Solo Entrenada");

        jLabelHabilidadNombre.setText("Nombre");

        jLabelHabilidadDeClase.setText("De Clase");

        jLabelHabilidadTotal.setText("Total");

        jLabelHabilidadEntrenada.setText("Entrenada");

        jComboBoxConjuros.setMaximumRowCount(99);
        jComboBoxConjuros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxConjurosActionPerformed(evt);
            }
        });

        jLabelConjuros.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabelConjuros.setText("Conjuros");

        jTextFieldConjuro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldConjuro.setText("Conjuro");

        jTextFieldEscuela.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldEscuela.setText("Escuela");

        jTextFieldTiempo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldTiempo.setText("Tiempo");

        jTextFieldAlcance.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldAlcance.setText("Solo Entrenada");

        jLabelConjuroNombre.setText("Nombre");

        jLabelConjuroEscuela.setText("Escuela");

        jLabelConjuroTiempo.setText("Tiempo");

        jLabelConjuroAlcance.setText("Alcance");

        jTextFieldConjuroNivel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldConjuroNivel.setText("99");
        jTextFieldConjuroNivel.setMinimumSize(new java.awt.Dimension(28, 24));

        jLabelConjuroNivel.setText("Nivel");

        jLabelObjetos.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabelObjetos.setText("Objetos");

        jTextFieldCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldCantidad.setText("99");

        jComboBoxObjetos.setMaximumRowCount(99);
        jComboBoxObjetos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxObjetosActionPerformed(evt);
            }
        });

        jLabelObjetosCantidad.setText("Cantidad");

        jTextFieldObjetos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldObjetos.setText("Objeto");

        jLabelDotes.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabelDotes.setText("Dotes");

        jComboBoxDotes.setMaximumRowCount(99);
        jComboBoxDotes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxDotesActionPerformed(evt);
            }
        });

        jTextFieldDote.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldDote.setText("Dote");

        jComboBoxPersonajes.setMaximumRowCount(99);
        jComboBoxPersonajes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPersonajesActionPerformed(evt);
            }
        });

        jLabelPersonajes.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelPersonajes.setText("Personajes");

        jButtonAnnadir.setText("Añadir");
        jButtonAnnadir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonAnnadirMousePressed(evt);
            }
        });

        jButtonModificar.setText("Modificar");
        jButtonModificar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonModificarMousePressed(evt);
            }
        });

        jButtonAnnadirArma.setText("+");

        jButtonAnnadirArmadura.setText("+");

        jButtonAnnadirObjeto.setText("+");

        jButtonAnnadirDote.setText("+");

        jButtonAnnadirCojuro.setText("+");
        jButtonAnnadirCojuro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnnadirCojuroActionPerformed(evt);
            }
        });

        jScrollPane2.setViewportView(jTextPaneConsola);

        jLabelExperiencia.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelExperiencia.setText("Experiencia");

        jTextFieldExperiencia.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTextFieldExperiencia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldExperiencia.setText("99");

        jLabelConsolaErrores.setForeground(new java.awt.Color(255, 0, 0));
        jLabelConsolaErrores.setText("Errores");

        jButtonGuardar.setText("Guardar");
        jButtonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGuardarActionPerformed(evt);
            }
        });

        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonCancelarMousePressed(evt);
            }
        });

        jButton1.setText("Pruebas");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });

        jMenu1.setText("Abrir");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu1MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Buscar");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu2MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNombre)
                            .addComponent(jLabelSexo)
                            .addComponent(jLabelAlineamiento))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                            .addComponent(jTextFieldSexo)
                            .addComponent(jTextFieldAlineamiento))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabelNivel)
                                    .addGap(18, 18, 18))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabelRaza)
                                    .addGap(17, 17, 17)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelClase)
                                .addGap(13, 13, 13)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldNivel, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldRaza)
                            .addComponent(jTextFieldClase, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPersonajes)
                            .addComponent(jButtonAnnadir)))
                    .addComponent(jScrollPane2))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelArmas)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxArmas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelArmaduras)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxArmadura, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelObjetos)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxObjetos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelDotes)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxDotes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelhabilidades, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxHabilidades, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jTextFieldHabilidad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldDeClase, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldEntrenada, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabelConjuros, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBoxConjuros, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jComboBoxPersonajes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelAbierto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldAbierto, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldArmaduras)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAnnadirArmadura))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldObjetos, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelObjetosCantidad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCantidad)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAnnadirObjeto))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jTextFieldArmas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAnnadirArma))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabelHabilidadNombre)
                                        .addGap(278, 278, 278)
                                        .addComponent(jLabelHabilidadTotal)
                                        .addGap(48, 48, 48)
                                        .addComponent(jLabelHabilidadEntrenada)
                                        .addGap(12, 12, 12))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabelHabilidadDeClase)
                                        .addGap(172, 172, 172))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jTextFieldDote)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonAnnadirDote))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelDestreza, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelFuerza, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelConstitucion, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextFieldConstitucion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldDestreza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldFuerza, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabelSabiduria, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelInteligencia, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelCarisma, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jTextFieldCarisma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldSabiduria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextFieldInteligencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelExperiencia, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextFieldExperiencia, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelConjuroNombre)
                                    .addComponent(jTextFieldConjuro, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextFieldConjuroNivel, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldEscuela, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextFieldAlcance, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButtonAnnadirCojuro))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabelConjuroNivel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabelConjuroEscuela)
                                        .addGap(79, 79, 79)
                                        .addComponent(jLabelConjuroTiempo)
                                        .addGap(48, 48, 48)
                                        .addComponent(jLabelConjuroAlcance))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabelConsolaErrores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonCancelar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonModificar)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(38, 38, 38))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelNombre)
                            .addComponent(jTextFieldNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelSexo)
                            .addComponent(jTextFieldSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelAlineamiento)
                            .addComponent(jTextFieldAlineamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelAbierto)
                            .addComponent(jTextFieldAbierto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBoxPersonajes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPersonajes))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonModificar)
                            .addComponent(jLabelConsolaErrores)
                            .addComponent(jButtonGuardar)
                            .addComponent(jButtonCancelar)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButtonAnnadir))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelNivel)
                            .addComponent(jTextFieldNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelRaza)
                            .addComponent(jTextFieldRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelClase)
                            .addComponent(jTextFieldClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldFuerza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelInteligencia)
                                    .addComponent(jLabelFuerza))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldDestreza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelSabiduria)
                                    .addComponent(jLabelDestreza))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldConstitucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelCarisma)
                                    .addComponent(jLabelConstitucion)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldInteligencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelExperiencia))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldSabiduria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldExperiencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextFieldCarisma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelArmas)
                            .addComponent(jComboBoxArmas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldArmas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAnnadirArma))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelArmaduras)
                            .addComponent(jComboBoxArmadura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldArmaduras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAnnadirArmadura))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelObjetos)
                            .addComponent(jComboBoxObjetos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldObjetos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelObjetosCantidad)
                            .addComponent(jTextFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAnnadirObjeto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelDotes)
                            .addComponent(jComboBoxDotes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldDote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonAnnadirDote))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelhabilidades)
                            .addComponent(jComboBoxHabilidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelHabilidadNombre)
                            .addComponent(jLabelHabilidadTotal)
                            .addComponent(jLabelHabilidadEntrenada)
                            .addComponent(jLabelHabilidadDeClase))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextFieldHabilidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldDeClase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldEntrenada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelConjuros)
                            .addComponent(jComboBoxConjuros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelConjuroEscuela)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabelConjuroNombre)
                                    .addComponent(jLabelConjuroTiempo)
                                    .addComponent(jLabelConjuroAlcance))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextFieldConjuro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldEscuela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextFieldAlcance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButtonAnnadirCojuro)
                                    .addComponent(jTextFieldConjuroNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabelConjuroNivel))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MousePressed
        jDialogFileChooser.setVisible(true);

    }//GEN-LAST:event_jMenu1MousePressed

    private void jFileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFileChooserActionPerformed
        if (evt.getActionCommand().equals(javax.swing.JFileChooser.APPROVE_SELECTION)) {
            abreDocumento(jFileChooser.getSelectedFile());
            actualizaDatos(jComboBoxPersonajes.getSelectedIndex());
        } else {
            jDialogFileChooser.setVisible(false);
        }
    }//GEN-LAST:event_jFileChooserActionPerformed

    private void jButtonAnnadirCojuroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnnadirCojuroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAnnadirCojuroActionPerformed

    private void jButtonModificarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonModificarMousePressed
        modificaPersonaje();
    }//GEN-LAST:event_jButtonModificarMousePressed

    private void jComboBoxPersonajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPersonajesActionPerformed
        actualizaDatos(jComboBoxPersonajes.getSelectedIndex());
    }//GEN-LAST:event_jComboBoxPersonajesActionPerformed

    private void jComboBoxArmasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxArmasActionPerformed
        try {
            jTextFieldArmas.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getArmas().getArma().get(jComboBoxArmas.getSelectedIndex()));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jComboBoxArmasActionPerformed

    private void jComboBoxArmaduraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxArmaduraActionPerformed
        try {
            jTextFieldArmaduras.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getArmaduras().getArmadura().get(jComboBoxArmadura.getSelectedIndex()));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jComboBoxArmaduraActionPerformed

    private void jComboBoxObjetosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxObjetosActionPerformed
        try {
            jTextFieldObjetos.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getObjetos().getObjeto().get(jComboBoxObjetos.getSelectedIndex()).getValue());
            jTextFieldCantidad.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getObjetos().getObjeto().get(jComboBoxObjetos.getSelectedIndex()).getCantidad());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jComboBoxObjetosActionPerformed

    private void jComboBoxDotesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxDotesActionPerformed
        try {
            jTextFieldDote.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getDotes().getDote().get(jComboBoxDotes.getSelectedIndex()));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jComboBoxDotesActionPerformed

    private void jComboBoxHabilidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxHabilidadesActionPerformed
        try {
            jTextFieldHabilidad.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getHabilidades().getHabilidad().get(jComboBoxHabilidades.getSelectedIndex()).getValue());
            jTextFieldDeClase.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getHabilidades().getHabilidad().get(jComboBoxHabilidades.getSelectedIndex()).getHabDeClase());
            jTextFieldTotal.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getHabilidades().getHabilidad().get(jComboBoxHabilidades.getSelectedIndex()).getTotal());
            jTextFieldEntrenada.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getHabilidades().getHabilidad().get(jComboBoxHabilidades.getSelectedIndex()).getSoloEntrenada());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jComboBoxHabilidadesActionPerformed

    private void jComboBoxConjurosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxConjurosActionPerformed
        try {
            jTextFieldConjuro.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getConjuros().getConjuro().get(jComboBoxConjuros.getSelectedIndex()).getValue());
            jTextFieldConjuroNivel.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getConjuros().getConjuro().get(jComboBoxConjuros.getSelectedIndex()).getNivel());
            jTextFieldEscuela.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getConjuros().getConjuro().get(jComboBoxConjuros.getSelectedIndex()).getEscuela());
            jTextFieldTiempo.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getConjuros().getConjuro().get(jComboBoxConjuros.getSelectedIndex()).getTiempoLanzamiento());
            jTextFieldAlcance.setText(jaxb.misPersonajes.getPersonaje().get(jComboBoxPersonajes.getSelectedIndex()).getConjuros().getConjuro().get(jComboBoxConjuros.getSelectedIndex()).getAlcance());
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jComboBoxConjurosActionPerformed

    private void jButtonAnnadirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAnnadirMousePressed
        jButtonGuardar.setVisible(true);
        jButtonCancelar.setVisible(true);
        jComboBoxPersonajes.setVisible(false);
        jLabelPersonajes.setVisible(false);
        jButtonAnnadir.setText("Deshacer");
        prepararNuevoPersonaje();
    }//GEN-LAST:event_jButtonAnnadirMousePressed

    private void jButtonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGuardarActionPerformed
        jButtonGuardar.setVisible(false);
        jButtonCancelar.setVisible(false);
        jComboBoxPersonajes.setVisible(true);
        jLabelPersonajes.setVisible(true);
        jButtonAnnadir.setText("Añadir");
        annadePersonajeNuevo();
    }//GEN-LAST:event_jButtonGuardarActionPerformed

    private void jButtonCancelarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCancelarMousePressed
        jButtonGuardar.setVisible(false);
        jButtonCancelar.setVisible(false);
        jComboBoxPersonajes.setVisible(true);
        jLabelPersonajes.setVisible(true);
        jButtonAnnadir.setText("Añadir");
        actualizaDatos(jComboBoxPersonajes.getSelectedIndex());
    }//GEN-LAST:event_jButtonCancelarMousePressed

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        //Boton de pruebas

    }//GEN-LAST:event_jButton1MousePressed

    private void jMenu2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MousePressed
        reseteaBuscador();
        jDialogBuscador.setVisible(true);
    }//GEN-LAST:event_jMenu2MousePressed

    private void jTextFieldNombreBuscarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNombreBuscarMousePressed
        if (jTextFieldNombreBuscar.getText().equals("Nombre") && jTextFieldNombreBuscar.isEnabled()) {
            jTextFieldNombreBuscar.setText("");

            jTextFieldRazaBuscar.setEnabled(false);
            jTextFieldSexoBuscar.setEnabled(false);
            jTextFieldNivelBuscar.setEnabled(false);
            jTextFieldClaseBuscar.setEnabled(false);
            jTextFieldAlineamientoBuscar.setEnabled(false);
        }
    }//GEN-LAST:event_jTextFieldNombreBuscarMousePressed

    private void jTextFieldRazaBuscarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldRazaBuscarMousePressed
        if (jTextFieldRazaBuscar.getText().equals("Raza") && jTextFieldRazaBuscar.isEnabled()) {
            jTextFieldRazaBuscar.setText("");

            jTextFieldNombreBuscar.setEnabled(false);
            jTextFieldSexoBuscar.setEnabled(false);
            jTextFieldNivelBuscar.setEnabled(false);
            jTextFieldClaseBuscar.setEnabled(false);
            jTextFieldAlineamientoBuscar.setEnabled(false);
        }
    }//GEN-LAST:event_jTextFieldRazaBuscarMousePressed

    private void jTextFieldSexoBuscarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldSexoBuscarMousePressed
        if (jTextFieldRazaBuscar.getText().equals("Sexo") && jTextFieldSexoBuscar.isEnabled()) {
            jTextFieldSexoBuscar.setText("");

            jTextFieldNombreBuscar.setEnabled(false);
            jTextFieldRazaBuscar.setEnabled(false);
            jTextFieldNivelBuscar.setEnabled(false);
            jTextFieldClaseBuscar.setEnabled(false);
            jTextFieldAlineamientoBuscar.setEnabled(false);
        }
    }//GEN-LAST:event_jTextFieldSexoBuscarMousePressed

    private void jTextFieldNivelBuscarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNivelBuscarMousePressed
        if (jTextFieldNivelBuscar.getText().equals("Nivel") && jTextFieldNivelBuscar.isEnabled()) {
            jTextFieldNivelBuscar.setText("");

            jTextFieldNombreBuscar.setEnabled(false);
            jTextFieldRazaBuscar.setEnabled(false);
            jTextFieldSexoBuscar.setEnabled(false);
            jTextFieldClaseBuscar.setEnabled(false);
            jTextFieldAlineamientoBuscar.setEnabled(false);
        }
    }//GEN-LAST:event_jTextFieldNivelBuscarMousePressed

    private void jTextFieldClaseBuscarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldClaseBuscarMousePressed
        if (jTextFieldClaseBuscar.getText().equals("Clase") && jTextFieldClaseBuscar.isEnabled()) {
            jTextFieldClaseBuscar.setText("");

            jTextFieldNombreBuscar.setEnabled(false);
            jTextFieldRazaBuscar.setEnabled(false);
            jTextFieldSexoBuscar.setEnabled(false);
            jTextFieldNivelBuscar.setEnabled(false);
            jTextFieldAlineamientoBuscar.setEnabled(false);
        }
    }//GEN-LAST:event_jTextFieldClaseBuscarMousePressed

    private void jTextFieldAlineamientoBuscarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldAlineamientoBuscarMousePressed
        if (jTextFieldAlineamientoBuscar.getText().equals("Alineamiento") && jTextFieldAlineamientoBuscar.isEnabled()) {
            jTextFieldAlineamientoBuscar.setText("");

            jTextFieldNombreBuscar.setEnabled(false);
            jTextFieldRazaBuscar.setEnabled(false);
            jTextFieldSexoBuscar.setEnabled(false);
            jTextFieldNivelBuscar.setEnabled(false);
            jTextFieldClaseBuscar.setEnabled(false);
        }
    }//GEN-LAST:event_jTextFieldAlineamientoBuscarMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButtonAnnadir;
    private javax.swing.JButton jButtonAnnadirArma;
    private javax.swing.JButton jButtonAnnadirArmadura;
    private javax.swing.JButton jButtonAnnadirCojuro;
    private javax.swing.JButton jButtonAnnadirDote;
    private javax.swing.JButton jButtonAnnadirObjeto;
    private javax.swing.JButton jButtonArmadurasBuscar;
    private javax.swing.JButton jButtonArmasBuscar;
    private javax.swing.JButton jButtonBuscar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonConjurosBuscar;
    private javax.swing.JButton jButtonDotesBuscar;
    private javax.swing.JButton jButtonGuardar;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JButton jButtonObjetosBuscar;
    private javax.swing.JComboBox<String> jComboBoxArmadura;
    private javax.swing.JComboBox<String> jComboBoxArmas;
    private javax.swing.JComboBox<String> jComboBoxConjuros;
    private javax.swing.JComboBox<String> jComboBoxDotes;
    private javax.swing.JComboBox<String> jComboBoxHabilidades;
    private javax.swing.JComboBox<String> jComboBoxObjetos;
    private javax.swing.JComboBox<String> jComboBoxPersonajes;
    private javax.swing.JDialog jDialogBuscador;
    private javax.swing.JDialog jDialogFileChooser;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JLabel jLabelAbierto;
    private javax.swing.JLabel jLabelAlineamiento;
    private javax.swing.JLabel jLabelAlineamientoBuscar;
    private javax.swing.JLabel jLabelArmaduras;
    private javax.swing.JLabel jLabelArmas;
    private javax.swing.JLabel jLabelCarisma;
    private javax.swing.JLabel jLabelClase;
    private javax.swing.JLabel jLabelClaseBuscar;
    private javax.swing.JLabel jLabelConjuroAlcance;
    private javax.swing.JLabel jLabelConjuroEscuela;
    private javax.swing.JLabel jLabelConjuroNivel;
    private javax.swing.JLabel jLabelConjuroNombre;
    private javax.swing.JLabel jLabelConjuroTiempo;
    private javax.swing.JLabel jLabelConjuros;
    private javax.swing.JLabel jLabelConsolaErrores;
    private javax.swing.JLabel jLabelConstitucion;
    private javax.swing.JLabel jLabelDestreza;
    private javax.swing.JLabel jLabelDotes;
    private javax.swing.JLabel jLabelExperiencia;
    private javax.swing.JLabel jLabelFuerza;
    private javax.swing.JLabel jLabelHabilidadDeClase;
    private javax.swing.JLabel jLabelHabilidadEntrenada;
    private javax.swing.JLabel jLabelHabilidadNombre;
    private javax.swing.JLabel jLabelHabilidadTotal;
    private javax.swing.JLabel jLabelInteligencia;
    private javax.swing.JLabel jLabelNivel;
    private javax.swing.JLabel jLabelNivelBuscar;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelNombreBuscar;
    private javax.swing.JLabel jLabelObjetos;
    private javax.swing.JLabel jLabelObjetosCantidad;
    private javax.swing.JLabel jLabelPersonajes;
    private javax.swing.JLabel jLabelRaza;
    private javax.swing.JLabel jLabelRazaBuscar;
    private javax.swing.JLabel jLabelSabiduria;
    private javax.swing.JLabel jLabelSexo;
    private javax.swing.JLabel jLabelSexoBuscar;
    private javax.swing.JLabel jLabelhabilidades;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextFieldAbierto;
    private javax.swing.JTextField jTextFieldAlcance;
    private javax.swing.JTextField jTextFieldAlineamiento;
    private javax.swing.JTextField jTextFieldAlineamientoBuscar;
    private javax.swing.JTextField jTextFieldArmaduras;
    private javax.swing.JTextField jTextFieldArmas;
    private javax.swing.JTextField jTextFieldCantidad;
    private javax.swing.JTextField jTextFieldCarisma;
    private javax.swing.JTextField jTextFieldClase;
    private javax.swing.JTextField jTextFieldClaseBuscar;
    private javax.swing.JTextField jTextFieldConjuro;
    private javax.swing.JTextField jTextFieldConjuroNivel;
    private javax.swing.JTextField jTextFieldConstitucion;
    private javax.swing.JTextField jTextFieldDeClase;
    private javax.swing.JTextField jTextFieldDestreza;
    private javax.swing.JTextField jTextFieldDote;
    private javax.swing.JTextField jTextFieldEntrenada;
    private javax.swing.JTextField jTextFieldEscuela;
    private javax.swing.JTextField jTextFieldExperiencia;
    private javax.swing.JTextField jTextFieldFuerza;
    private javax.swing.JTextField jTextFieldHabilidad;
    private javax.swing.JTextField jTextFieldInteligencia;
    private javax.swing.JTextField jTextFieldNivel;
    private javax.swing.JTextField jTextFieldNivelBuscar;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldNombreBuscar;
    private javax.swing.JTextField jTextFieldObjetos;
    private javax.swing.JTextField jTextFieldRaza;
    private javax.swing.JTextField jTextFieldRazaBuscar;
    private javax.swing.JTextField jTextFieldSabiduria;
    private javax.swing.JTextField jTextFieldSexo;
    private javax.swing.JTextField jTextFieldSexoBuscar;
    private javax.swing.JTextField jTextFieldTiempo;
    private javax.swing.JTextField jTextFieldTotal;
    private javax.swing.JTextPane jTextPaneConsola;
    // End of variables declaration//GEN-END:variables
}
