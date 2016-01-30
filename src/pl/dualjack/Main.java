package pl.dualjack;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;

public class Main {

	private JFrame frame;
	private JSplitPane splitPane;
	private Connection connection;
	private JSplitPane splitPane_1;
	private JPanel panel;
	private JTextField fieldUserID;
	private JTextField fieldBookID;
	private JLabel lblIdKsiki;
	private JButton btnWypoycz;
	private JButton btnZwr;
	private JTabbedPane tabbedPane;
	private JScrollPane scrollBooks;
	private JScrollPane scrollUsers;
	private JScrollPane scrollAllView;
	private JScrollPane scrollLog;
	private JTable tableBooks;
	private JTable tableUsers;
	private JTable tableLog;
	private DefaultTableModel modelBooks, modelClients, modelAllView, modelLog, modelWypozyczenia;
	private JTable tableAllView;
	private JTabbedPane tabbedPane_1;
	private JScrollPane userTab;
	private JPanel panel_1;
	private JButton btnZaaduj;
	private JLabel lblIdUytkownika;
	private JPanel panel_2;
	private JLabel lblImi;
	private JTextField fieldName;
	private JLabel lblNazwisko;
	private JTextField fieldLastName;
	private JLabel lblEmail;
	private JTextField fieldEmail;
	private JLabel lblTelefon;
	private JTextField fieldPhone;
	private JButton btnZapisz;
	private JCheckBox chckbxNowyUzytkonik;
	private JButton btnUsun;
	private JSplitPane splitPane_2;
	private JTable tableWypozyczenia;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost", "DUALJACK", "mocher");	
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 696, 432);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setLeftComponent(splitPane_1);
		
		panel = new JPanel();
		panel.setBorder(new EmptyBorder(8, 8, 8, 8));
		splitPane_1.setLeftComponent(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{95, 86, 0};
		gbl_panel.rowHeights = new int[]{20, 20, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		lblIdUytkownika = new JLabel("ID u\u017Cytkownika");
		GridBagConstraints gbc_lblIdUytkownika = new GridBagConstraints();
		gbc_lblIdUytkownika.insets = new Insets(0, 0, 5, 0);
		gbc_lblIdUytkownika.gridx = 1;
		gbc_lblIdUytkownika.gridy = 0;
		panel.add(lblIdUytkownika, gbc_lblIdUytkownika);
		lblIdUytkownika.setForeground(Color.GRAY);
		
		// BUTTON ZA£ADUJ
		btnZaaduj = new JButton("Za\u0142aduj");
		btnZaaduj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					refreshEverything();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_btnZaaduj = new GridBagConstraints();
		gbc_btnZaaduj.insets = new Insets(0, 0, 0, 5);
		gbc_btnZaaduj.gridx = 0;
		gbc_btnZaaduj.gridy = 1;
		panel.add(btnZaaduj, gbc_btnZaaduj);
		
		fieldUserID = new JTextField();
		GridBagConstraints gbc_fieldUserID = new GridBagConstraints();
		gbc_fieldUserID.anchor = GridBagConstraints.NORTH;
		gbc_fieldUserID.fill = GridBagConstraints.HORIZONTAL;
		gbc_fieldUserID.gridx = 1;
		gbc_fieldUserID.gridy = 1;
		panel.add(fieldUserID, gbc_fieldUserID);
		fieldUserID.setColumns(10);
		
		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		splitPane_1.setRightComponent(tabbedPane_1);
		
		userTab = new JScrollPane();
		userTab.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		tabbedPane_1.addTab("Dane u\u017Cytkownika", null, userTab, null);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		userTab.setViewportView(panel_1);
		panel_1.setLayout(new MigLayout("", "[][64.00,grow]", "[][][][][][][]"));
		
		lblImi = new JLabel("Imi\u0119");
		panel_1.add(lblImi, "cell 0 0,alignx trailing");
		
		fieldName = new JTextField();
		panel_1.add(fieldName, "cell 1 0,growx");
		fieldName.setColumns(10);
		
		lblNazwisko = new JLabel("Nazwisko");
		panel_1.add(lblNazwisko, "cell 0 1,alignx trailing");
		
		fieldLastName = new JTextField();
		panel_1.add(fieldLastName, "cell 1 1,growx");
		fieldLastName.setColumns(10);
		
		lblEmail = new JLabel("e-mail");
		panel_1.add(lblEmail, "cell 0 2,alignx trailing");
		
		fieldEmail = new JTextField();
		panel_1.add(fieldEmail, "cell 1 2,growx");
		fieldEmail.setColumns(10);
		
		lblTelefon = new JLabel("telefon");
		panel_1.add(lblTelefon, "cell 0 3,alignx trailing");
		
		fieldPhone = new JTextField();
		panel_1.add(fieldPhone, "cell 1 3,growx");
		fieldPhone.setColumns(10);
		
		chckbxNowyUzytkonik = new JCheckBox("Nowy u\u017Cytkonik");
		panel_1.add(chckbxNowyUzytkonik, "cell 0 5");
		
		// BUTTON ZAPISZ
		btnZapisz = new JButton("ZAPISZ");
		btnZapisz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Statement stmt = connection.createStatement();
					if(!chckbxNowyUzytkonik.isSelected()){
						stmt.executeQuery("UPDATE CLIENTS SET NAME='"+fieldName.getText()+"', SURNAME='"+fieldLastName.getText()+"', EMAIL='"+fieldEmail.getText()+"', PHONE='"+fieldPhone.getText()+"' WHERE CLIENT_ID ="+ fieldUserID.getText());
					} else {
						stmt.executeQuery("INSERT INTO CLIENTS (CLIENT_ID, NAME, SURNAME, EMAIL, PHONE) VALUES('"+fieldUserID.getText()+"', '"+fieldName.getText()+"', '"+fieldLastName.getText()+"', '"+fieldEmail.getText()+"', '"+fieldPhone.getText()+"')");
					}
					refreshEverything();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		panel_1.add(btnZapisz, "cell 0 6,alignx center");
		
		//BUTTON USUÑ
		btnUsun = new JButton("USU\u0143");
		btnUsun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Statement stmt = connection.createStatement();
					stmt.executeQuery("DELETE FROM CLIENTS WHERE CLIENT_ID="+ fieldUserID.getText());

					refreshEverything();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		panel_1.add(btnUsun, "cell 1 6,alignx center");
		
		splitPane_2 = new JSplitPane();
		tabbedPane_1.addTab("Wypo\u017Cyczenia", null, splitPane_2, null);
		splitPane_2.setBackground(Color.WHITE);
		splitPane_2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		panel_2 = new JPanel();
		splitPane_2.setLeftComponent(panel_2);
		panel_2.setBackground(Color.WHITE);
		panel_2.setLayout(new MigLayout("", "[76.00px,grow,center][86px,grow,fill]", "[20px][23px]"));
		
		lblIdKsiki = new JLabel("ID ksi\u0105\u017Cki");
		panel_2.add(lblIdKsiki, "cell 0 0,alignx center,aligny center");
		
		fieldBookID = new JTextField();
		panel_2.add(fieldBookID, "cell 1 0,alignx center,aligny center");
		fieldBookID.setColumns(10);
		
		// BUTTON WYPO¯YCZ
		// ---------------------
		btnWypoycz = new JButton("Wypo\u017Cycz");
		panel_2.add(btnWypoycz, "cell 0 1,alignx center,aligny center");
		
		// BUTTON ZWRÓÆ
		// ------------------
		btnZwr = new JButton("Zwr\u00F3\u0107");
		panel_2.add(btnZwr, "cell 1 1,alignx center,aligny center");
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		splitPane_2.setRightComponent(scrollPane);
		
		tableWypozyczenia = new JTable();
		tableWypozyczenia.setFillsViewportHeight(true);
		scrollPane.setViewportView(tableWypozyczenia);
		modelWypozyczenia = new DefaultTableModel(new Object[][] {},
				new String[] {
					"ID", "BOOK_TITLE"
				});
		tableWypozyczenia.setModel(modelWypozyczenia);
		btnZwr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					Statement stmt = connection.createStatement();
					stmt.executeQuery("UPDATE SPECIMEN SET CLIENT_ID=null WHERE SPECIMEN_ID='"+ fieldBookID.getText() +"'");
					
					refreshEverything();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		btnWypoycz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Statement stmt = connection.createStatement();
					stmt.executeQuery("UPDATE SPECIMEN SET CLIENT_ID='"+ fieldUserID.getText() +"' WHERE SPECIMEN_ID='"+ fieldBookID.getText() +"'");
					
					refreshEverything();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		scrollBooks = new JScrollPane();
		tabbedPane.addTab("Ksi\u0105\u017Cki", null, scrollBooks, null);
		
		tableBooks = new JTable();
		modelBooks = new DefaultTableModel(new Object[][] {},
			new String[] {
				"ID", "AUTHOR NAME", "AUTHOR LAST_NAME", "TITLE", "DIMENSION_X", "DIMENSION_Y", "DIMENSION_Z", "PUBLICATION_DATE", "BOOKSTAND_ID", "CORRIDOR","SHELF_HEIGHT","CATEGORY_NAME", "DESCRIPTION"
			});
		tableBooks.setModel(modelBooks);
		tableBooks.getColumnModel().getColumn(0).setPreferredWidth(50);
		tableBooks.getColumnModel().getColumn(0).setMaxWidth(50);
		scrollBooks.setViewportView(tableBooks);
		
		scrollUsers = new JScrollPane();
		tabbedPane.addTab("Uzytkownicy", null, scrollUsers, null);
		
		tableUsers = new JTable();
		modelClients = new DefaultTableModel(new Object[][] {},
			new String[] {
				"CLIENT_ID", "NAME", "SURNAME", "EMAIL", "PHONE"
			}
		);
		tableUsers.setModel(modelClients);

		scrollUsers.setViewportView(tableUsers);
		
		scrollAllView = new JScrollPane();
		tabbedPane.addTab("Wszystkie wypo\u017Cyczenia", null, scrollAllView, null);
		
		tableAllView = new JTable();
		modelAllView = new DefaultTableModel(new Object[][] {},
				new String[] {
					"CLIENT_ID", "NAME", "SURNAME", "SPECIMEN_ID", "TITLE", "AUTHOR FIRST_NAME", "AUTHOR LAST_NAME"
				});
		tableAllView.setModel(modelAllView);
		scrollAllView.setViewportView(tableAllView);
		
		scrollLog = new JScrollPane();
		tabbedPane.addTab("Log", new ImageIcon(Main.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")), scrollLog, null);
		
		tableLog = new JTable();
		modelLog = new DefaultTableModel(new Object[][] {},
			new String[] {
				"LOG_ID", "NAME", "LOG_DATE", "CLIENT_ID", "CLIENT NAME", "CLIENT SURNAME", "DESCRIPTION"
			});
		tableLog.setModel(modelLog);
		scrollLog.setViewportView(tableLog);
	}
	
	// --------------------------------------------------------------
	// --------------------------------------------------------------
	
	public void refreshBooks() throws SQLException{
		Statement stmt = connection.createStatement();
		ResultSet result = stmt.executeQuery("SELECT * FROM ALL_BOOKS");
		
		for (int i = modelBooks.getRowCount() - 1; i >= 0; i--) {
		    modelBooks.removeRow(i);
		}
		
		while(result.next()){
			modelBooks.addRow(
					new Object[] {result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6),
							result.getString(7), result.getString(8), result.getString(9), result.getString(10), result.getString(11), result.getString(12),
							result.getString(13)}
			);
		}
		
	}
	
	public void refreshClients() throws SQLException{
		Statement stmt = connection.createStatement();
		ResultSet result = stmt.executeQuery("SELECT * FROM CLIENTS");
		
		for (int i = modelClients.getRowCount() - 1; i >= 0; i--) {
			modelClients.removeRow(i);
		}
		
		while(result.next()){
			modelClients.addRow(
					new Object[] {result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5)}
			);
		}
		
	}
	
	public void refreshALLView() throws SQLException{
		Statement stmt = connection.createStatement();
		ResultSet result = stmt.executeQuery("SELECT * FROM ALL_RENTS");
		
		for (int i = modelAllView.getRowCount() - 1; i >= 0; i--) {
			modelAllView.removeRow(i);
		}
		
		while(result.next()){
			modelAllView.addRow(
					new Object[] {result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7)}
			);
		}
		
	}
	
	public void refreshLog() throws SQLException{
		Statement stmt = connection.createStatement();
		ResultSet result = stmt.executeQuery("SELECT * FROM ALL_LOGS");
		
		for (int i = modelLog.getRowCount() - 1; i >= 0; i--) {
			modelLog.removeRow(i);
		}
		
		while(result.next()){
			modelLog.addRow(
					new Object[] {result.getString(1), result.getString(2), result.getString(3), result.getString(4), result.getString(5), result.getString(6), result.getString(7)}
			);
		}
		
	}
	
	public void refreshWypozyczenia() throws SQLException {
		
		Statement stmt = connection.createStatement();
		ResultSet result = stmt.executeQuery("SELECT s.SPECIMEN_ID, b.TITLE FROM CLIENTS c "+
												"LEFT OUTER JOIN SPECIMEN s ON s.CLIENT_ID = c.CLIENT_ID "+
												"LEFT OUTER JOIN BOOKS b ON s.BOOK_ID = b.BOOK_ID "+
												"WHERE c.CLIENT_ID = "+	fieldUserID.getText() );
		
		for (int i = modelWypozyczenia.getRowCount() - 1; i >= 0; i--) {
			modelWypozyczenia.removeRow(i);
		}
		
		while(result.next()){
			modelWypozyczenia.addRow(
					new Object[] {result.getString(1), result.getString(2)}
			);
		}
		
	}
	
	public void refreshUserInfo() throws SQLException{
		
		Statement stmt = connection.createStatement();
		ResultSet result = stmt.executeQuery("SELECT NAME, SURNAME, EMAIL, PHONE FROM CLIENTS WHERE CLIENT_ID ="+ fieldUserID.getText());
		
		result.next();
		fieldName.setText(result.getString(1));
		fieldLastName.setText(result.getString(2));
		fieldEmail.setText(result.getString(3));
		fieldPhone.setText(result.getString(4));
		
	}
	
	public void refreshEverything() throws SQLException{
		
		refreshALLView();
		refreshBooks();
		refreshClients();
		refreshLog();
		refreshWypozyczenia();
		refreshUserInfo();
		
	}
}
