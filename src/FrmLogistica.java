import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class FrmLogistica extends JFrame {

    public String[] encabezados = new String[] { "Código", "Cliente", "Peso", "Distancia", "Tipo", "Costo" };

    private JTable tblEnvios;
    private JPanel pnlEditarEnvio;

    private JTextField txtNumero, txtCliente, txtPeso, txtDistancia;
    private JComboBox<String> cmbTipoEnvio;

    public FrmLogistica() {
        setSize(780, 630);
        setTitle("Operador Logísitico");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(700, 400));


        // botones de la barra de herramientas
        JToolBar tbLogistica = new JToolBar();

        JButton btnAgregarEnvio = new JButton();
        btnAgregarEnvio.setIcon(new ImageIcon(getClass().getResource("/iconos/AgregarEnvio.png")));
        btnAgregarEnvio.setToolTipText("Agregar Envío");
        btnAgregarEnvio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                agregarEnvio();    //accion que ejecuta al presionar "agregar envio"
            }
        });
        tbLogistica.add(btnAgregarEnvio);  

        JButton btnQuitarEnvio = new JButton();

        btnQuitarEnvio.setIcon(new ImageIcon(getClass().getResource("/iconos/QuitarEnvio.png")));
        btnQuitarEnvio.setToolTipText("Quitar Envío");
        btnQuitarEnvio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                quitarEnvio();  //accion que ejecuta al presionar "quitar envio"
            }
        });
        tbLogistica.add(btnQuitarEnvio);  // agrega el contenedor del boton quitar y agregar a la barra de herramientas
        

        // Contenedor principal de ENVIOS con BoxLayout (vertical)
        JPanel pnlEnvios = new JPanel();
        pnlEnvios.setLayout(new BoxLayout(pnlEnvios, BoxLayout.Y_AXIS));

        // Panel 1 (oculto por defecto)
        pnlEditarEnvio = new JPanel();
        pnlEditarEnvio.setPreferredSize(new Dimension(pnlEditarEnvio.getWidth(), 250)); // Altura fija de 100px
        pnlEditarEnvio.setLayout(null);


        // nombre que se ubica a la izquierda del contenedor
        JLabel lblNumero = new JLabel("Número");
        lblNumero.setBounds(10, 10, 100, 25);
        pnlEditarEnvio.add(lblNumero);
        //contenedor donde el usuario escribe el dato relacionado
        txtNumero = new JTextField();
        txtNumero.setBounds(110, 10, 100, 25);
        pnlEditarEnvio.add(txtNumero);

        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(10, 40, 100, 25);
        pnlEditarEnvio.add(lblCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(110, 40, 100, 25);
        pnlEditarEnvio.add(txtCliente);

        JLabel lblPeso = new JLabel("Peso");
        lblPeso.setBounds(10, 70, 100, 25);
        pnlEditarEnvio.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(110, 70, 100, 25);
        pnlEditarEnvio.add(txtPeso);
        
        // lista desplegable de tipos de envio
        JLabel lblTipo = new JLabel("Tipo");
        lblTipo.setBounds(220, 10, 100, 25);
        pnlEditarEnvio.add(lblTipo);

        cmbTipoEnvio = new JComboBox<String>();
        cmbTipoEnvio.setBounds(320, 10, 100, 25);
        
        String[] opciones = new String[] { "Terrestre", "Aéreo", "Marítimo" };  // opciones de la lista desplegable
        DefaultComboBoxModel<String> mdlTipoEnvio = new DefaultComboBoxModel<>(opciones);
        cmbTipoEnvio.setModel(mdlTipoEnvio);
        
        pnlEditarEnvio.add(cmbTipoEnvio);

        JLabel lblDistancia = new JLabel("Distancia en Km");
        lblDistancia.setBounds(220, 40, 100, 25);
        pnlEditarEnvio.add(lblDistancia);

        txtDistancia = new JTextField();
        txtDistancia.setBounds(320, 40, 100, 25);
        pnlEditarEnvio.add(txtDistancia);

        // botones de guardar y cancelar, que ejecutan acciones  al ser presionados
        JButton btnGuardarEnvio = new JButton("Guardar");
        btnGuardarEnvio.setBounds(220, 70, 100, 25);
        btnGuardarEnvio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                guardarEnvio();
            }
        });
        pnlEditarEnvio.add(btnGuardarEnvio);

        JButton btnCancelarEnvio = new JButton("Cancelar");
        btnCancelarEnvio.setBounds(320, 70, 100, 25);
        btnCancelarEnvio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelarEnvio();
            }
        });
        pnlEditarEnvio.add(btnCancelarEnvio);

        pnlEditarEnvio.setVisible(false); // Se oculta al inicio


        // Panel 2 (siempre visible)
        tblEnvios = new JTable();
        JScrollPane spListaEnvios = new JScrollPane(tblEnvios);

        DefaultTableModel ccc = new DefaultTableModel(null, encabezados); // tabla con los encabezados 
        tblEnvios.setModel(ccc);

        // Agregar componentes
        pnlEnvios.add(pnlEditarEnvio);
        pnlEnvios.add(spListaEnvios);

        // JScrollPane para permitir desplazamiento si es necesario
        JScrollPane spEnvios = new JScrollPane(pnlEnvios);
        spEnvios.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        getContentPane().add(tbLogistica, BorderLayout.NORTH);
        getContentPane().add(pnlEnvios, BorderLayout.CENTER);
    }

    public void agregarEnvio() {
        pnlEditarEnvio.setVisible(true); // muestra el panel oculto al presionar el boton de agregar envio

    }

    public void quitarEnvio() {
        int fila = tblEnvios.getSelectedRow();
        if (fila >= 0) {
            DefaultTableModel modelo = (DefaultTableModel) tblEnvios.getModel();
            modelo.removeRow(fila);  //elimina la fila seleccionada
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione fila a eliminar.", "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }


    public void guardarEnvio() {

        int codigo = Integer.parseInt(txtNumero.getText()); // obtiene los datos de el texto que escribe el usuario
        String cliente = txtCliente.getText();
        double peso = Double.parseDouble(txtPeso.getText());
        double distancia = Double.parseDouble(txtDistancia.getText());
        String tipo = (String) cmbTipoEnvio.getSelectedItem();

        Envio envio = null;

        switch (tipo) {   // crea el objeto segun el tipo de transporte seleccionado
            case "Terrestre":
                envio = new Terrestre(codigo, cliente, peso, distancia);
                break;
            case "Aéreo":
                envio = new Aereo(codigo, cliente, peso, distancia);
                break;
            case "Marítimo":
                envio = new Mar(codigo, cliente, peso, distancia);
                break;
        }

        double costo = envio.calcularTarifa();

        DefaultTableModel model = (DefaultTableModel) tblEnvios.getModel(); //agrega filas a la tabla
        model.addRow(new Object[] {
                envio.getCodigo(),   //coge el codigo y lo demas, de los getters de Envio
                envio.getCliente(),
                envio.getPeso(),
                envio.getDistancia(),
                tipo,
                costo
        });

        pnlEditarEnvio.setVisible(true);
        
    }

    public void cancelarEnvio() {
        pnlEditarEnvio.setVisible(false); // oculta el panel al presionar cancelar en el panel de editar envio
    }
}
