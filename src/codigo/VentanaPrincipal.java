/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codigo;

import java.awt.Color;
import java.io.File;
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
    int indexPersonaje = 0;

    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        jDialogFileChooser.setSize(560, 350);
        jDialogFileChooser.setResizable(false);
        this.setResizable(false);
        jTextFieldAbierto.setEditable(false);
        jTextFieldAbierto.setBackground(Color.red);
        jTextPaneConsola.setEditable(false);
        jLabelConsolaErrores.setText(" ");

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

    public int modificarPersonaje(int personaje) {
        try {
            return 0;
        } catch (Exception e) {
            jLabelConsolaErrores.setText("Error al modificar personaje");
            return -1;
        }
    }

    /**
     * Esta funcion lo que hara sera actualizar los datos mostrados en la
     * aplicacion
     *
     * @param numero -> Indice del personaje a mosntrar
     */
    public void actualizaDatos(int numero) {

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

        } catch (Exception e) {
            System.out.println("No hay mas personajes");
        }

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
        jButton9 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
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
        jButtonMostrarEnPantalla = new javax.swing.JButton();
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
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

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

        jButton9.setText("Buscar");

        jLabel1.setText("Nombre");

        jTextField1.setText("Nombre");

        jLabel2.setText("Raza");

        jTextField2.setText("Raza");

        jLabel3.setText("Sexo");

        jTextField3.setText("Sexo");

        jLabel4.setText("Nivel");

        jTextField4.setText("Nivel");

        jLabel5.setText("Clase");

        jTextField5.setText("Clase");

        javax.swing.GroupLayout jDialogBuscadorLayout = new javax.swing.GroupLayout(jDialogBuscador.getContentPane());
        jDialogBuscador.getContentPane().setLayout(jDialogBuscadorLayout);
        jDialogBuscadorLayout.setHorizontalGroup(
            jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialogBuscadorLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton9)
                    .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jDialogBuscadorLayout.createSequentialGroup()
                            .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                    .addComponent(jTextField3))))
                        .addGroup(jDialogBuscadorLayout.createSequentialGroup()
                            .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2))
                            .addGap(18, 18, 18)
                            .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jDialogBuscadorLayout.setVerticalGroup(
            jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialogBuscadorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDialogBuscadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jButton9)
                .addContainerGap(28, Short.MAX_VALUE))
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

        jLabelDestreza.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelDestreza.setText("Destreza");

        jTextFieldDestreza.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTextFieldDestreza.setText("99");

        jLabelInteligencia.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelInteligencia.setText("Inteligencia");

        jTextFieldInteligencia.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTextFieldInteligencia.setText("99");

        jLabelConstitucion.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelConstitucion.setText("Constitucion");

        jTextFieldConstitucion.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTextFieldConstitucion.setText("99");

        jLabelSabiduria.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelSabiduria.setText("Sabiduria");

        jTextFieldSabiduria.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTextFieldSabiduria.setText("99");

        jLabelCarisma.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelCarisma.setText("Carisma");

        jTextFieldCarisma.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jTextFieldCarisma.setText("99");

        jComboBoxArmas.setMaximumRowCount(99);

        jLabelArmas.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabelArmas.setText("Armas");

        jTextFieldArmas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldArmas.setText("Arma");

        jComboBoxArmadura.setMaximumRowCount(99);

        jLabelArmaduras.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabelArmaduras.setText("Armaduras");

        jTextFieldArmaduras.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldArmaduras.setText("Armadura");

        jComboBoxHabilidades.setMaximumRowCount(99);

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

        jLabelConjuroNivel.setText("Nivel");

        jLabelObjetos.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabelObjetos.setText("Objetos");

        jTextFieldCantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldCantidad.setText("99");

        jComboBoxObjetos.setMaximumRowCount(99);

        jLabelObjetosCantidad.setText("Cantidad");

        jTextFieldObjetos.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldObjetos.setText("Objeto");

        jLabelDotes.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabelDotes.setText("Dotes");

        jComboBoxDotes.setMaximumRowCount(99);

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

        jButtonMostrarEnPantalla.setText("Mostrar en Pantalla");
        jButtonMostrarEnPantalla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButtonMostrarEnPantallaMousePressed(evt);
            }
        });

        jButtonAnnadir.setText("Añadir");

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

        jMenu1.setText("Abrir");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu1MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

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
                            .addComponent(jTextFieldClase, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelPersonajes)
                            .addComponent(jButtonAnnadir)))
                    .addComponent(jScrollPane2))
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
                        .addComponent(jTextFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldFuerza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldConstitucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldDestreza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelSabiduria, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelInteligencia, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCarisma, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextFieldInteligencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldCarisma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldSabiduria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelExperiencia)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldExperiencia)
                                .addGap(8, 8, 8))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelConjuroNombre)
                            .addComponent(jTextFieldConjuro, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextFieldConjuroNivel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(jButtonModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonMostrarEnPantalla)))
                .addContainerGap())
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButtonModificar)
                                .addComponent(jLabelConsolaErrores))
                            .addComponent(jButtonMostrarEnPantalla)))
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
                        .addGap(50, 50, 50)
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
            actualizaDatos(indexPersonaje);
        } else {
            jDialogFileChooser.setVisible(false);
        }
    }//GEN-LAST:event_jFileChooserActionPerformed

    private void jButtonAnnadirCojuroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnnadirCojuroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonAnnadirCojuroActionPerformed

    private void jButtonMostrarEnPantallaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonMostrarEnPantallaMousePressed
        indexPersonaje++;
        actualizaDatos(indexPersonaje);
    }//GEN-LAST:event_jButtonMostrarEnPantallaMousePressed

    private void jButtonModificarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonModificarMousePressed

    }//GEN-LAST:event_jButtonModificarMousePressed

    private void jComboBoxPersonajesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPersonajesActionPerformed
        actualizaDatos(jComboBoxPersonajes.getSelectedIndex());
        indexPersonaje = jComboBoxPersonajes.getSelectedIndex();
    }//GEN-LAST:event_jComboBoxPersonajesActionPerformed

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
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButtonAnnadir;
    private javax.swing.JButton jButtonAnnadirArma;
    private javax.swing.JButton jButtonAnnadirArmadura;
    private javax.swing.JButton jButtonAnnadirCojuro;
    private javax.swing.JButton jButtonAnnadirDote;
    private javax.swing.JButton jButtonAnnadirObjeto;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JButton jButtonMostrarEnPantalla;
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabelAbierto;
    private javax.swing.JLabel jLabelAlineamiento;
    private javax.swing.JLabel jLabelArmaduras;
    private javax.swing.JLabel jLabelArmas;
    private javax.swing.JLabel jLabelCarisma;
    private javax.swing.JLabel jLabelClase;
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
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelObjetos;
    private javax.swing.JLabel jLabelObjetosCantidad;
    private javax.swing.JLabel jLabelPersonajes;
    private javax.swing.JLabel jLabelRaza;
    private javax.swing.JLabel jLabelSabiduria;
    private javax.swing.JLabel jLabelSexo;
    private javax.swing.JLabel jLabelhabilidades;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextFieldAbierto;
    private javax.swing.JTextField jTextFieldAlcance;
    private javax.swing.JTextField jTextFieldAlineamiento;
    private javax.swing.JTextField jTextFieldArmaduras;
    private javax.swing.JTextField jTextFieldArmas;
    private javax.swing.JTextField jTextFieldCantidad;
    private javax.swing.JTextField jTextFieldCarisma;
    private javax.swing.JTextField jTextFieldClase;
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
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldObjetos;
    private javax.swing.JTextField jTextFieldRaza;
    private javax.swing.JTextField jTextFieldSabiduria;
    private javax.swing.JTextField jTextFieldSexo;
    private javax.swing.JTextField jTextFieldTiempo;
    private javax.swing.JTextField jTextFieldTotal;
    private javax.swing.JTextPane jTextPaneConsola;
    // End of variables declaration//GEN-END:variables
}
