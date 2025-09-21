import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.List;

import models.Aereo;
import models.Envio;
import models.Mar;
import models.Terrestre;

public class FrmLogistica extends JFrame {

    public String[] encabezados = new String[] { "Código", "Cliente", "Peso", "Distancia", "Tipo", "Costo" };

    private List<Envio> listaEnvios = new ArrayList<>(); // crea una lista de envios

    private JTable tblEnvios; // paneles
    private JPanel pnlEditarEnvio;

    private JTextField txtNumero, txtCliente, txtPeso, txtDistancia; // convierte los atrbibutos en variables
    private JComboBox<String> cmbTipoEnvio; // lista plegable

    public FrmLogistica() {
        setSize(780, 630);
        setTitle("Operador Logísitico");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centra la ventana en la pantalla

        // botones de la barra de herramientas
        JToolBar tbLogistica = new JToolBar();
        tbLogistica.setBorder(new LineBorder(Color.lightGray, 3));
        tbLogistica.setBorder(new EmptyBorder(20, 20, 20, 20));

        JButton btnAgregarEnvio = new JButton();
        btnAgregarEnvio.setIcon(new ImageIcon(getClass().getResource("/iconos/AgregarEnvio.png")));
        btnAgregarEnvio.setToolTipText("Agregar Envío");
        btnAgregarEnvio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                agregarEnvio(); // accion que ejecuta al presionar "agregar envio"
            }
        });
        tbLogistica.add(btnAgregarEnvio);

        JButton btnQuitarEnvio = new JButton();

        btnQuitarEnvio.setIcon(new ImageIcon(getClass().getResource("/iconos/QuitarEnvio.png")));
        btnQuitarEnvio.setToolTipText("Quitar Envío");
        btnQuitarEnvio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                quitarEnvio(); // accion que ejecuta al presionar "quitar envio"
            }
        });
        tbLogistica.add(btnQuitarEnvio); // agrega el contenedor del boton quitar y agregar a la barra de herramientas

        // Contenedor principal de ENVIOS con BoxLayout (vertical)
        JPanel pnlEnvios = new JPanel();
        pnlEnvios.setLayout(new BoxLayout(pnlEnvios, BoxLayout.Y_AXIS));
        pnlEnvios.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Panel 1 (oculto por defecto)
        pnlEditarEnvio = new JPanel();
        pnlEditarEnvio.setPreferredSize(new Dimension(pnlEditarEnvio.getWidth(), 240)); // Altura fija de 240px
        pnlEditarEnvio.setLayout(null);
        pnlEditarEnvio.setBackground(Color.LIGHT_GRAY);
        pnlEditarEnvio.setBorder(new LineBorder(Color.darkGray, 5));
        pnlEditarEnvio.setBounds(EXIT_ON_CLOSE, ABORT, WIDTH, HEIGHT);

        // nombre que se ubica a la izquierda del contenedor
        JLabel lblNumero = new JLabel("Codigo #");
        lblNumero.setBounds(10, 10, 100, 25);
        pnlEditarEnvio.add(lblNumero);
        // contenedor donde el usuario escribe el dato relacionado
        txtNumero = new JTextField();
        txtNumero.setBounds(110, 10, 100, 25);
        pnlEditarEnvio.add(txtNumero);

        JLabel lblCliente = new JLabel("Cliente");
        lblCliente.setBounds(10, 40, 100, 25);
        pnlEditarEnvio.add(lblCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(110, 40, 100, 25);
        pnlEditarEnvio.add(txtCliente);

        JLabel lblPeso = new JLabel("Peso (KG)");
        lblPeso.setBounds(10, 70, 100, 25);
        pnlEditarEnvio.add(lblPeso);

        txtPeso = new JTextField();
        txtPeso.setBounds(110, 70, 100, 25);
        pnlEditarEnvio.add(txtPeso);

        // lista desplegable de tipos de envio
        JLabel lblTipo = new JLabel("Transporte ");
        lblTipo.setBounds(220, 10, 100, 25);
        pnlEditarEnvio.add(lblTipo);

        cmbTipoEnvio = new JComboBox<String>();
        cmbTipoEnvio.setBounds(320, 10, 100, 25);

        String[] opciones = new String[] { "Terrestre", "Aéreo", "Marítimo" }; // opciones de la lista desplegable
        DefaultComboBoxModel<String> mdlTipoEnvio = new DefaultComboBoxModel<>(opciones);
        cmbTipoEnvio.setModel(mdlTipoEnvio);

        pnlEditarEnvio.add(cmbTipoEnvio);

        JLabel lblDistancia = new JLabel("Distancia en KM");
        lblDistancia.setBounds(220, 70, 100, 25);
        pnlEditarEnvio.add(lblDistancia);

        txtDistancia = new JTextField();
        txtDistancia.setBounds(320, 70, 100, 25);
        pnlEditarEnvio.add(txtDistancia);

        // botones de guardar y cancelar, que ejecutan acciones al ser presionados
        JButton btnGuardarEnvio = new JButton("<html><b>GUARDAR</b></html>");
        btnGuardarEnvio.setBounds(420, 120, 100, 25);
        btnGuardarEnvio.setBackground(Color.green);
        btnGuardarEnvio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                guardarEnvio();
            }
        });
        pnlEditarEnvio.add(btnGuardarEnvio);

        JButton btnCancelarEnvio = new JButton("<html><b>CANCELAR</b></html>");
        btnCancelarEnvio.setBounds(525, 120, 100, 25);
        btnCancelarEnvio.setBackground(Color.red);
        btnCancelarEnvio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelarEnvio();
            }
        });
        pnlEditarEnvio.add(btnCancelarEnvio);

        pnlEditarEnvio.setVisible(false); // Se oculta al inicio.

        // Panel 2 (siempre visible)
        tblEnvios = new JTable();
        JScrollPane spListaEnvios = new JScrollPane(tblEnvios);

        DefaultTableModel ccc = new DefaultTableModel(null, encabezados) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblEnvios.setModel(ccc);

        tblEnvios.getTableHeader().setReorderingAllowed(false);
        tblEnvios.getTableHeader().setBackground(new Color(40, 144, 255)); // estilos del encabezado de la lista
        tblEnvios.getTableHeader().setForeground(Color.WHITE);
        tblEnvios.setBorder(new LineBorder(Color.lightGray, 2));
        tblEnvios.getTableHeader().setFont(new java.awt.Font("Consolas", java.awt.Font.BOLD, 14));

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
        int[] filas = tblEnvios.getSelectedRows(); // todas las filas seleccionadas

        if (filas.length == 0) {
            JOptionPane.showMessageDialog(this, " seleccione al menos una fila a eliminar ", "",
                    JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (int i = filas.length - 1; i >= 0; i--) {
            listaEnvios.remove(filas[i]);
        }

        listarEnvios(); // refrescar tabla
    }
    public void guardarEnvio() {
        // validacion de
        String codigoTexto = txtNumero.getText().trim();
        if (!codigoTexto.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "el codigo debe contener solo nimeros enteros.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        int codigo = Integer.parseInt(codigoTexto); // obtiene los datos de el texto que escribe el usuario
        if (codigo <= 0) {
            JOptionPane.showMessageDialog(this,
                    "el codigo debe ser mayor que cero.",
                    "error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        String cliente = txtCliente.getText().trim();

        if (cliente.isEmpty()) {
            JOptionPane.showMessageDialog(this, " el cliente no puede estar vacio.", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String pesoTexto = txtPeso.getText().trim().replace(',', '.'); // acepta los numeros con punto y comas tambien
        String distanciaTexto = txtDistancia.getText().trim().replace(',', '.');

        if (!pesoTexto.matches("\\d+(\\.\\d+)?")) {
            JOptionPane.showMessageDialog(this, " peso invalido. ingrese un numero real", "error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!distanciaTexto.matches("\\d+(\\.\\d+)?")) {
            JOptionPane.showMessageDialog(this, "distancia invalida. ingrese un numero real.", "error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        double peso = Double.parseDouble(pesoTexto); // obtiene los datos de el texto que escribe el usuario
        double distancia = Double.parseDouble(distanciaTexto);
        String tipo = (String) cmbTipoEnvio.getSelectedItem();

        Envio envio = null; // hereda de la clase Envio

        switch (tipo) { // crea el objeto segun el tipo de transporte seleccionado
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

        listaEnvios.add(envio);
        listarEnvios();

        txtNumero.setText(""); // borra los campos despues que presione aceptar
        txtCliente.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");

        pnlEditarEnvio.setVisible(true);

    }
    public void cancelarEnvio() {
        pnlEditarEnvio.setVisible(false); // oculta el panel al presionar cancelar en el panel de editar envio
    }

    public void listarEnvios() {
        DefaultTableModel model = (DefaultTableModel) tblEnvios.getModel();
        model.setRowCount(0);

        for (Envio envio : listaEnvios) {
            double costo = envio.calcularTarifa();
            String costoFormateado = String.format("%.2f", costo); // limita a dos decimales
            model.addRow(new Object[] {
                    "# " + envio.getCodigo(),
                    envio.getCliente(),
                    envio.getPeso() + " KG",
                    envio.getDistancia() + " KM",
                    envio.getClass().getSimpleName(), // recibe el nombre de la clase que se selecciono terrestre aereo
                                                      // o mar
                    "$ " + costoFormateado
            });
        }
    }
}