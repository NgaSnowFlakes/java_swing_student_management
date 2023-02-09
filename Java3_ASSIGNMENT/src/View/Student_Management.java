package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import Database.JDBCUtils;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class Student_Management extends JFrame {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtEmail;
	private JTextField txtContact;
	private JTextArea txtAdress;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnSave;
	private JButton btnNew;
	private JLabel lblImg;
	private JLabel lblAddress;
	private JLabel lblGender;
	private JRadioButton rdboFemale;
	private JRadioButton rdboMale;
	private DefaultTableModel tblmodel;
	private JTable tblStudent;
	JTableHeader header;
	JDBCUtils jdbc = new JDBCUtils();
	Connection conn;
	int index = -1;
	private JButton btnChoose;
	private String path_CSDL = "p1.jpg";
	private JPanel panel2;
	private JButton btnSearch;
	private JTextField txtSearch;
	private JLabel lblTitle;
	private Timer head;
	private Timer back;
	private JLabel lblBG;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student_Management frame = new Student_Management();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Student_Management() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 825, 783);
		contentPane = new JPanel();
		contentPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(245, 222, 179));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(10, 10, 794, 726);
		contentPane.add(panel);
		panel.setLayout(null);

		lblTitle = new JLabel("STUDENT MANGEMENT");
		lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitle.setForeground(new Color(0, 255, 0));
		lblTitle.setFont(new Font("Wide Latin", Font.BOLD, 20));
		lblTitle.setBounds(10, 10, 505, 39);
		panel.add(lblTitle);

		lblImg = new JLabel();
		lblImg.setBounds(494, 139, 223, 203);
		lblImg.setBorder(new LineBorder(Color.yellow, 4));
		lblImg.setIcon(resizeImg("D:\\Java\\Java3_ASSIGNMENT\\src\\image_photo\\p1.jpg"));
		panel.add(lblImg);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new LineBorder(new Color(255, 99, 71), 4));
		scrollPane.setBounds(56, 565, 704, 151);
		panel.add(scrollPane);

		tblStudent = new JTable();
		tblStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				index = tblStudent.getSelectedRow();
				show(index);
			}
		});
		scrollPane.setViewportView(tblStudent);

		btnChoose = new JButton("Choose picture");
		btnChoose.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnChoose.setBounds(531, 344, 163, 28);
		panel.add(btnChoose);

		JPanel panel1 = new JPanel();
		panel1.setBackground(new Color(127, 255, 212));
		panel1.setBorder(new LineBorder(Color.GREEN, 3));
		panel1.setBounds(34, 139, 401, 412);
		panel.add(panel1);
		panel1.setLayout(null);

		lblAddress = new JLabel("Adress");
		lblAddress.setBounds(39, 322, 93, 32);
		panel1.add(lblAddress);
		lblAddress.setForeground(new Color(0, 128, 0));
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 16));

		txtAdress = new JTextArea();
		txtAdress.setBounds(157, 342, 206, 58);
		panel1.add(txtAdress);
		txtAdress.setLineWrap(true);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(155, 340, 208, 60);
		panel1.add(scrollPane_1);

		lblGender = new JLabel("Gender");
		lblGender.setBounds(39, 255, 93, 32);
		panel1.add(lblGender);
		lblGender.setForeground(new Color(0, 128, 0));
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 16));

		rdboMale = new JRadioButton("Male");
		rdboMale.setBounds(157, 266, 103, 21);
		panel1.add(rdboMale);
		buttonGroup.add(rdboMale);
		rdboMale.setMaximumSize(new Dimension(50, 30));
		rdboMale.setIconTextGap(6);
		rdboMale.setForeground(new Color(255, 0, 0));
		rdboMale.setFont(new Font("Tahoma", Font.PLAIN, 16));

		rdboFemale = new JRadioButton("Female");
		rdboFemale.setBounds(273, 266, 103, 21);
		panel1.add(rdboFemale);
		buttonGroup.add(rdboFemale);
		rdboFemale.setForeground(Color.RED);
		rdboFemale.setFont(new Font("Tahoma", Font.PLAIN, 16));

		txtContact = new JTextField();
		txtContact.setBounds(157, 216, 196, 25);
		panel1.add(txtContact);
		txtContact.setFont(new Font("Dialog", Font.BOLD, 14));
		txtContact.setColumns(10);

		JLabel lblContact = new JLabel("Contact");
		lblContact.setBounds(39, 213, 93, 32);
		panel1.add(lblContact);
		lblContact.setForeground(new Color(0, 128, 0));
		lblContact.setFont(new Font("Tahoma", Font.BOLD, 16));

		txtEmail = new JTextField();
		txtEmail.setBounds(157, 165, 196, 25);
		panel1.add(txtEmail);
		txtEmail.setFont(new Font("Dialog", Font.BOLD, 14));
		txtEmail.setColumns(10);

		JLabel lblStudentEmail = new JLabel("Student Email");
		lblStudentEmail.setBounds(19, 161, 113, 32);
		panel1.add(lblStudentEmail);
		lblStudentEmail.setForeground(new Color(0, 128, 0));
		lblStudentEmail.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblStudentName = new JLabel("Student name");
		lblStudentName.setBounds(19, 111, 120, 32);
		panel1.add(lblStudentName);
		lblStudentName.setForeground(new Color(0, 128, 0));
		lblStudentName.setFont(new Font("Tahoma", Font.BOLD, 16));

		txtName = new JTextField();
		txtName.setBounds(157, 115, 196, 25);
		panel1.add(txtName);
		txtName.setFont(new Font("Dialog", Font.BOLD, 14));
		txtName.setColumns(10);

		JLabel lblID = new JLabel("Student ID");
		lblID.setBounds(25, 51, 93, 32);
		panel1.add(lblID);
		lblID.setForeground(new Color(0, 128, 0));
		lblID.setFont(new Font("Tahoma", Font.BOLD, 16));

		txtID = new JTextField();
		txtID.setBounds(157, 55, 196, 25);
		panel1.add(txtID);
		txtID.setFont(new Font("Dialog", Font.BOLD, 14));
		txtID.setColumns(10);

		panel2 = new JPanel();
		panel2.setBackground(new Color(220, 220, 220));
		panel2.setBorder(new TitledBorder(null, "Controls", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 255, 255)));
		panel2.setBounds(445, 388, 336, 163);
		panel.add(panel2);
		panel2.setLayout(null);

		btnNew = new JButton("New");
		btnNew.setBackground(new Color(0, 255, 255));
		btnNew.setBounds(20, 22, 129, 47);
		panel2.add(btnNew);
		btnNew.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNew.setIcon(new ImageIcon("D:\\Java\\Java3_ASSIGNMENT\\src\\image_icon\\plus.png"));

		btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(0, 255, 255));
		btnDelete.setBounds(20, 98, 129, 47);
		panel2.add(btnDelete);
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDelete.setIcon(new ImageIcon("D:\\Java\\Java3_ASSIGNMENT\\src\\image_icon\\trash.png"));

		btnUpdate = new JButton("Update");
		btnUpdate.setBackground(new Color(0, 255, 255));
		btnUpdate.setBounds(184, 98, 129, 47);
		panel2.add(btnUpdate);
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUpdate.setIcon(new ImageIcon("D:\\Java\\Java3_ASSIGNMENT\\src\\image_icon\\update.png"));

		btnSave = new JButton("Save");
		btnSave.setBackground(new Color(0, 255, 255));
		btnSave.setBounds(184, 22, 129, 47);
		panel2.add(btnSave);
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSave.setIcon(new ImageIcon("D:\\Java\\Java3_ASSIGNMENT\\src\\image_icon\\save-file.png"));

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(148, 0, 211)));
		panel_1.setBackground(new Color(0, 128, 128));
		panel_1.setBounds(200, 55, 445, 74);
		panel.add(panel_1);
		panel_1.setLayout(null);

		btnSearch = new JButton("Search");

		btnSearch.setBounds(306, 10, 129, 47);
		panel_1.add(btnSearch);
		btnSearch.setIcon(new ImageIcon(Student_Management.class.getResource("/image_icon/find.png")));
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSearch.setBackground(Color.CYAN);

		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Dialog", Font.BOLD, 14));
		txtSearch.setColumns(10);
		txtSearch.setBounds(94, 10, 196, 47);
		panel_1.add(txtSearch);

		JLabel lblId = new JLabel("ID");
		lblId.setForeground(new Color(255, 105, 180));
		lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblId.setBounds(41, 10, 43, 47);
		panel_1.add(lblId);
		
		lblBG = new JLabel("");
		lblBG.setBounds(0, 0, 794, 726);
		lblBG.setIcon(resizeBG("D:\\Java\\Java3_ASSIGNMENT\\src\\image_photo\\bg2.jpg"));
		panel.add(lblBG);
		
		initTable();
		fillTable();
		first_look();
		id_filter();
		addACL();
		running_Title();
	}

	public ImageIcon resizeImg(String path) {
		ImageIcon MyImage = new ImageIcon(path);
		Image img = MyImage.getImage();
		Image newImg = img.getScaledInstance(lblImg.getWidth(), lblImg.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);
		return image;
	}
	
	public ImageIcon resizeBG(String path) {
		ImageIcon MyImage = new ImageIcon(path);
		Image img = MyImage.getImage();
		Image newImg = img.getScaledInstance(lblBG.getWidth(), lblBG.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);
		return image;
	}

	public void initTable() {
		tblmodel = (DefaultTableModel) tblStudent.getModel();
		String[] cols = new String[] { "ID", "Name", "Email", "Contact", "Gender", "Adress", "Picture" };
		tblmodel.setColumnIdentifiers(cols);
		tblStudent.setModel(tblmodel);
		tblStudent.setRowHeight(30);
		TableColumnModel tblcl = tblStudent.getColumnModel();
		tblcl.getColumn(0).setPreferredWidth(30);
		tblcl.getColumn(1).setPreferredWidth(60);
		tblcl.getColumn(2).setPreferredWidth(60);
		tblcl.getColumn(3).setPreferredWidth(60);
		tblcl.getColumn(4).setPreferredWidth(40);
		tblcl.getColumn(5).setPreferredWidth(50);
		tblcl.getColumn(6).setPreferredWidth(40);
		header = tblStudent.getTableHeader();
		header.setFont(new Font("Tahoma", 1, 16));
		header.setBackground(Color.pink);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < tblStudent.getColumnCount(); i++) {
			tblStudent.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}
	}
	
	public void running_Title() {
		head = new Timer(100, new ActionListener() {
			

			@Override
			public void actionPerformed(ActionEvent e) {
				lblTitle.setLocation(lblTitle.getX() + 10, lblTitle.getY());
				if(lblTitle.getX() > 280) {
					head.stop();
					back.start();
				}
				
			}
		});
		head.start();
		 back = new Timer(100, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblTitle.setLocation(lblTitle.getX() - 10, lblTitle.getY());
				if(lblTitle.getX() < 20) {
					back.stop();
					head.start();
				}
			}
		});
		back.start();
	}

	public void fillTable() {
		try {
			tblmodel.setRowCount(0);
			conn = jdbc.getConnection();
			String sql = "select * from Student";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String phone = rs.getString(4);
				String gt;
				if (rs.getBoolean(5) == true) {
					gt = "Male";
				} else {
					gt = "Female";
				}
				String address = rs.getString(6);
				String picture = rs.getString(7);
				tblmodel.addRow(new Object[] { id, name, email, phone, gt, address, picture });
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void first_look() {
		if (tblStudent.getRowCount() > 0) {
			index = 0;
			show(index);
			tblStudent.setRowSelectionInterval(index, index);
		}
	}

	public void show(int i) {
		if (i >= 0) {
			txtID.setText((String) tblmodel.getValueAt(i, 0));
			txtName.setText((String) tblmodel.getValueAt(i, 1));
			txtEmail.setText((String) tblmodel.getValueAt(i, 2));
			txtContact.setText((String) tblmodel.getValueAt(i, 3));
			String gender = (String) tblmodel.getValueAt(i, 4);
			if (gender == "Male") {
				rdboMale.setSelected(true);
			} else {
				rdboFemale.setSelected(true);
			}
			txtAdress.setText((String) tblmodel.getValueAt(i, 5));
			String path = "D:\\Java\\Java3_ASSIGNMENT\\src\\image_photo\\" + (String) tblmodel.getValueAt(i, 6);
			lblImg.setIcon(resizeImg(path));
		}
	}

	public boolean check() {
		String id = txtID.getText().trim();
		String name = txtName.getText().trim();
		String contact = txtContact.getText().trim();
		String email = txtEmail.getText().trim();
		String adress = txtAdress.getText();
		boolean Male = rdboMale.isSelected();
		boolean Female = rdboFemale.isSelected();
		if (id.equals("")) {
			JOptionPane.showMessageDialog(this, "Empty ID");
			return false;
		}
		if (name.equals("")) {
			JOptionPane.showMessageDialog(this, "Empty name");
			return false;
		}
		if (contact.equals("")) {
			JOptionPane.showMessageDialog(this, "Empty contact");
			return false;
		} else {
			if (checkPhone(contact) == false) {
				JOptionPane.showMessageDialog(this, "Invalid contact number");
				return false;
			}
		}

		if (checkEmail(email) == false) {
			JOptionPane.showMessageDialog(this, "Invalid Email");
			return false;
		}

		if (Male == false && Female == false) {
			JOptionPane.showMessageDialog(this, "Please choose your gender");
			return false;
		}
		if (adress.equals("")) {
			JOptionPane.showMessageDialog(this, "Empty Adress");
			return false;
		}
		if (path_CSDL == null) {
			JOptionPane.showMessageDialog(this, "Please choose your picture");
			return false;
		}
		return true;
	}

	public boolean check_forUpdate() {
		String name = txtName.getText().trim();
		String contact = txtContact.getText().trim();
		String email = txtEmail.getText().trim();
		String adress = txtAdress.getText();
		boolean Male = rdboMale.isSelected();
		boolean Female = rdboFemale.isSelected();
		if (name.equals("")) {
			JOptionPane.showMessageDialog(this, "Empty name");
			return false;
		}
		if (contact.equals("")) {
			JOptionPane.showMessageDialog(this, "Empty contact");
			return false;
		} else {
			if (checkPhone(contact) == false) {
				JOptionPane.showMessageDialog(this, "Invalid contact number");
				return false;
			}
		}

		if (checkEmail(email) == false) {
			JOptionPane.showMessageDialog(this, "Invalid Email");
			return false;
		}

		if (Male == false && Female == false) {
			JOptionPane.showMessageDialog(this, "Please choose your gender");
			return false;
		}
		if (adress.equals("")) {
			JOptionPane.showMessageDialog(this, "Empty Adress");
			return false;
		}
		if (path_CSDL.equals(null)) {
			JOptionPane.showMessageDialog(this, "Please choose your picture");
			return false;
		}
		return true;
	}

	public boolean checkPhone(String input) {
		return input.charAt(0) == '0' && input.length() == 10 && input.matches("[0-9]+");
	}

	public boolean checkEmail(String email) {
		if (email == null || email.isEmpty()) {
			return false;
		}
		String regex = "^[a-zA-Z][a-zA-Z0-9]+@[a-zA-Z]+(\\.[a-zA-Z]+){1,3}$";
//		String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
		Pattern p = Pattern.compile(regex);
		if (p.matcher(email).find())
			return true;
		else
			return false;
	}

	public boolean checkID_database() {
		try {
			conn = jdbc.getConnection();
			String sql = "select * from student where masv = ?";
			PreparedStatement prsm = conn.prepareStatement(sql);
			prsm.setString(1, txtID.getText().trim());
			ResultSet rs = prsm.executeQuery();
			while (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public void choosePicture() {
		JFileChooser jfl = new JFileChooser("D:\\Java\\Java3_ASSIGNMENT\\src\\image_photo");
		int i = jfl.showOpenDialog(null);
		if (i == JFileChooser.APPROVE_OPTION) {
			File file = jfl.getSelectedFile();
			String path = file.getAbsolutePath();
			path_CSDL = file.getAbsolutePath().replace("D:\\Java\\Java3_ASSIGNMENT\\src\\image_photo\\", "");
			lblImg.setIcon(resizeImg(path));
		}

	}

	public void reset() {
		txtID.setText("");
		txtName.setText("");
		txtContact.setText("");
		txtEmail.setText("");
		txtAdress.setText("");
		buttonGroup.clearSelection();
		lblImg.setIcon(resizeImg("D:\\Java\\Java3_ASSIGNMENT\\src\\image_photo\\p1.jpg"));
		tblStudent.clearSelection();
	}

	public void save() {
		if (check()) {
			if (checkID_database()) {
				try {
					conn = jdbc.getConnection();
					String sql = "insert into student values(?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement prsm = conn.prepareStatement(sql);
					prsm.setString(1, txtID.getText().trim());
					prsm.setString(2, txtName.getText().trim());
					prsm.setString(3, txtEmail.getText().trim());
					prsm.setString(4, txtContact.getText().trim());
					String gender = rdboMale.isSelected() ? "True" : "False";
					prsm.setString(5, gender);
					prsm.setString(6, txtAdress.getText());
					prsm.setString(7, path_CSDL);
					int rs = prsm.executeUpdate();

					// Save to grade table

//					String sql_grade = "insert into grade values(?, null, null, null)";
//					prsm = conn.prepareStatement(sql_grade);
//					prsm.setString(1, txtID.getText().trim());
//					prsm.executeUpdate();
					if (rs > 0) {
//					1st Option
//						fillTable();
//					2nd Option
						String gt = rdboMale.isSelected() ? "Male" : "Female";
						tblmodel.addRow(new Object[] { txtID.getText(), txtName.getText(), txtEmail.getText(),
								txtContact.getText(), gt, txtAdress.getText(), path_CSDL });
						index = tblmodel.getRowCount() - 1;
						show(index);
						tblStudent.setRowSelectionInterval(index, index);
					}
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				JOptionPane.showMessageDialog(this, "ID already existed");
				return;
			}
		}
	}

	public void delete() {
		if (txtID.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter the student's id you want to delete?");
			return;
		}
		if (checkID_database()) {
			JOptionPane.showMessageDialog(this, "ID dosen't existed");
			tblStudent.clearSelection();
			return;
		} else {
			try {
				conn = jdbc.getConnection();
				String sql = "delete from student where masv = ?";
				PreparedStatement prsm = conn.prepareStatement(sql);
				prsm.setString(1, txtID.getText().trim());
				int rs = prsm.executeUpdate();
				if (rs > 0) {
					JOptionPane.showMessageDialog(this, "Already deleted");
					fillTable();
					if (index == tblmodel.getRowCount()) {
						index--;
					}
					if (index >= 0) {
						show(index);
						tblStudent.setRowSelectionInterval(index, index);
					} else
						reset();
				}

				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void find() {
		if (txtSearch.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "Please enter the ID");
			return;
		}
		for (int i = 0; i < tblStudent.getRowCount(); i++) {
			String find = (String) tblmodel.getValueAt(i, 0);
			if (txtSearch.getText().trim().equals(find)) {
				index = i;
				show(index);
				tblStudent.setRowSelectionInterval(index, index);
				return;
			}
		}
		JOptionPane.showMessageDialog(this, "ID doesn't existed");
	}

	public void id_filter() {
		txtSearch.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				fill();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				fill();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				fill();

			}
		});
	}

	public void fill() {
		try {
			tblmodel.setRowCount(0);
			conn = jdbc.getConnection();
			String sql = "select * from student where masv like ? + '%'";
			PreparedStatement prsm = conn.prepareStatement(sql);
			prsm.setString(1, txtSearch.getText());
			ResultSet rs = prsm.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String phone = rs.getString(4);
				String gt;
				if (rs.getBoolean(5) == true) {
					gt = "Male";
				} else {
					gt = "Female";
				}
				String address = rs.getString(6);
				String picture = rs.getString(7);
				tblmodel.addRow(new Object[] { id, name, email, phone, gt, address, picture });
			}
			if(tblmodel.getRowCount()==1) {
				tblStudent.setRowSelectionInterval(index, index);
			}
			first_look();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update() {
		if (txtID.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter the student's id you want to update?");
			return;
		}
		if (checkID_database()) {
			JOptionPane.showMessageDialog(this, "ID dosen't existed");
			return;
		} else {
			try {
				conn = jdbc.getConnection();
				String sql = "update student set Hoten = ?, Email =?, SoDT = ?, Gioitinh = ?, Diachi = ?, Hinh =? where masv = ?";
				PreparedStatement prsm = conn.prepareStatement(sql);
				if (check_forUpdate()) {
					prsm.setString(1, txtName.getText().trim());
					prsm.setString(2, txtEmail.getText().trim());
					prsm.setString(3, txtContact.getText().trim());
					String gt = rdboMale.isSelected() ? "True" : "False";
					prsm.setString(4, gt);
					prsm.setString(5, txtAdress.getText().trim());
					prsm.setString(6, path_CSDL);
					prsm.setString(7, txtID.getText().trim());
					int rs = prsm.executeUpdate();
					if (rs > 0) {
						JOptionPane.showMessageDialog(this, "Already updated");
						fillTable();
						for (int i = 0; i <tblStudent.getRowCount(); i++) {
							if(txtID.getText().equals(tblmodel.getValueAt(i, 0))) {
								tblStudent.setRowSelectionInterval(i, i);
								break;
							}
						}
					}
				}
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void addACL() {
		createACL(btnNew);
		createACL(btnSave);
		createACL(btnDelete);
		createACL(btnUpdate);
		createACL(btnChoose);
		createACL(btnSearch);
	}

	public void createACL(JButton b) {
		b.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				if (btn.getText().equals("New")) {
					reset();
				}
				if (btn.getText().equals("Choose picture")) {
					choosePicture();
				}
				if (btn.getText().equals("Save")) {
					save();
				}
				if (btn.getText().equals("Delete")) {
					delete();
				}
				if (btn.getText().equals("Update")) {
					update();
				}

				if (btn.getText().equals("Search")) {
					find();
				}

			}

		});
	}
}
