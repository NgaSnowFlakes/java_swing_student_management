package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;

import Controllers.Grade;
import Controllers.Student;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

import Database.JDBCUtils;

import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Student_grade extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtName;
	private JTextField txtStdID;
	private JTextField txtEnglish;
	private JTextField txtTech;
	private JTextField txtPE;
	private JTable tblGradeStudent;
	private JButton btnSearch;
	private JPanel panel;
	private JPanel panel1;
	private JPanel panel2;
	private JLabel lblGrade;
	private JPanel panel3;
	private JButton btnNew;
	private JButton btnSave;
	private JButton btnDelete;
	private JButton btnUpdate;
	private JButton btnExit;
	private JTextField txtTop;
	private JButton btnLast;
	private JButton btnNext;
	private JButton btnPre;
	private JButton btnFirst;
	private JButton btnFilter;
	private DefaultTableModel tblmodel;
	JTableHeader header;
	Connection conn;
	JDBCUtils jdbc = new JDBCUtils();
	PreparedStatement prsm;
	ResultSet rs;
	int index = -1;

	List<Grade> list_grd = new ArrayList<Grade>();
	List<Student> list_std = new ArrayList<Student>();
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
					Student_grade frame = new Student_grade();
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
	public Student_grade() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 782, 738);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBorder(new MatteBorder(4, 2, 4, 2, (Color) new Color(0, 255, 127)));
		panel.setBackground(new Color(245, 222, 179));
		panel.setBounds(10, 10, 742, 691);
		contentPane.add(panel);
		panel.setLayout(null);

		lblTitle = new JLabel("STUDENT GRADE MANGEMENT");
		lblTitle.setForeground(new Color(255, 0, 255));
		lblTitle.setFont(new Font("Wide Latin", Font.BOLD, 16));
		lblTitle.setBounds(13, 10, 550, 56);
		panel.add(lblTitle);

		panel1 = new JPanel();
		panel1.setBackground(new Color(64, 224, 208));
		panel1.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"T\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 255, 255)));
		panel1.setBounds(57, 63, 506, 73);
		panel.add(panel1);
		panel1.setLayout(null);

		JLabel lblID = new JLabel("ID");
		lblID.setForeground(new Color(0, 128, 0));
		lblID.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblID.setBounds(113, 20, 34, 32);
		panel1.add(lblID);

		txtID = new JTextField();
		txtID.setFont(new Font("Dialog", Font.BOLD, 14));
		txtID.setColumns(10);
		txtID.setBounds(157, 24, 196, 25);
		panel1.add(txtID);

		btnSearch = new JButton("Search");
		btnSearch.setIcon(new ImageIcon("D:\\Java\\Java3_ASSIGNMENT\\src\\image\\find.png"));
		btnSearch.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSearch.setBounds(367, 13, 129, 47);
		panel1.add(btnSearch);

		panel2 = new JPanel();
		panel2.setBackground(new Color(152, 251, 152));
		panel2.setLayout(null);
		panel2.setBorder(new LineBorder(new Color(0, 255, 255), 2, true));
		panel2.setBounds(57, 146, 506, 302);
		panel.add(panel2);

		JLabel lblName = new JLabel("Student Name");
		lblName.setForeground(new Color(0, 128, 0));
		lblName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblName.setBounds(27, 20, 120, 32);
		panel2.add(lblName);

		txtName = new JTextField();
		txtName.setEditable(false);
		txtName.setFont(new Font("Dialog", Font.BOLD, 14));
		txtName.setColumns(10);
		txtName.setBounds(157, 24, 196, 25);
		panel2.add(txtName);

		JLabel lblStdID = new JLabel("Student ID");
		lblStdID.setForeground(new Color(0, 128, 0));
		lblStdID.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblStdID.setBounds(27, 80, 93, 32);
		panel2.add(lblStdID);

		txtStdID = new JTextField();
		txtStdID.setFont(new Font("Dialog", Font.BOLD, 14));
		txtStdID.setColumns(10);
		txtStdID.setBounds(157, 84, 196, 25);
		panel2.add(txtStdID);

		JLabel lblEnglish = new JLabel("English");
		lblEnglish.setForeground(new Color(0, 128, 0));
		lblEnglish.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEnglish.setBounds(27, 137, 93, 32);
		panel2.add(lblEnglish);

		txtEnglish = new JTextField();
		txtEnglish.setFont(new Font("Dialog", Font.BOLD, 14));
		txtEnglish.setColumns(10);
		txtEnglish.setBounds(157, 141, 196, 25);
		panel2.add(txtEnglish);

		JLabel lblTech = new JLabel("Technology");
		lblTech.setForeground(new Color(0, 128, 0));
		lblTech.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTech.setBounds(27, 195, 93, 32);
		panel2.add(lblTech);

		txtTech = new JTextField();
		txtTech.setFont(new Font("Dialog", Font.BOLD, 14));
		txtTech.setColumns(10);
		txtTech.setBounds(157, 199, 196, 25);
		panel2.add(txtTech);

		JLabel lblPhysic = new JLabel("PE");
		lblPhysic.setForeground(new Color(0, 128, 0));
		lblPhysic.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPhysic.setBounds(27, 253, 93, 32);
		panel2.add(lblPhysic);

		txtPE = new JTextField();
		txtPE.setFont(new Font("Dialog", Font.BOLD, 14));
		txtPE.setColumns(10);
		txtPE.setBounds(157, 257, 196, 25);
		panel2.add(txtPE);

		JLabel lblAverage = new JLabel("Average");
		lblAverage.setForeground(new Color(0, 0, 255));
		lblAverage.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAverage.setBounds(388, 125, 93, 32);
		panel2.add(lblAverage);

		lblGrade = new JLabel("0.0");
		lblGrade.setForeground(new Color(220, 20, 60));
		lblGrade.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblGrade.setBounds(388, 166, 108, 79);
		lblGrade.setHorizontalAlignment(SwingConstants.CENTER);
		panel2.add(lblGrade);

		panel3 = new JPanel();
		panel3.setBackground(new Color(50, 205, 50));
		panel3.setBorder(new LineBorder(new Color(0, 255, 255), 2, true));
		panel3.setBounds(573, 146, 146, 358);
		panel.add(panel3);
		panel3.setLayout(null);

		btnNew = new JButton("New");
		btnNew.setBackground(new Color(64, 224, 208));
		btnNew.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNew.setBounds(10, 10, 129, 47);
		btnNew.setIcon(new ImageIcon("D:\\Java\\Java3_ASSIGNMENT\\src\\image_icon\\plus.png"));
		panel3.add(btnNew);

		btnSave = new JButton("Save");
		btnSave.setBackground(new Color(64, 224, 208));
		btnSave.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnSave.setBounds(10, 81, 129, 47);
		btnSave.setIcon(new ImageIcon("D:\\Java\\Java3_ASSIGNMENT\\src\\image_icon\\save-file.png"));
		panel3.add(btnSave);

		btnDelete = new JButton("Delete");
		btnDelete.setBackground(new Color(64, 224, 208));
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnDelete.setBounds(10, 155, 129, 47);
		btnDelete.setIcon(new ImageIcon("D:\\Java\\Java3_ASSIGNMENT\\src\\image_icon\\trash.png"));
		panel3.add(btnDelete);

		btnUpdate = new JButton("Update");
		btnUpdate.setBackground(new Color(64, 224, 208));
		btnUpdate.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnUpdate.setBounds(10, 226, 129, 47);
		btnUpdate.setIcon(new ImageIcon("D:\\Java\\Java3_ASSIGNMENT\\src\\image_icon\\update.png"));
		panel3.add(btnUpdate);

		btnExit = new JButton("Exit");
		btnExit.setIcon(new ImageIcon(Student_grade.class.getResource("/image_icon/delete.png")));
		btnExit.setBackground(new Color(64, 224, 208));
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnExit.setBounds(10, 301, 129, 47);
		panel3.add(btnExit);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 560, 659, 109);
		panel.add(scrollPane);

		tblGradeStudent = new JTable();
		tblGradeStudent.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				index = tblGradeStudent.getSelectedRow();
				showToForm(index);
			}
		});
		tblGradeStudent.setBorder(new LineBorder(new Color(0, 255, 255)));
		scrollPane.setViewportView(tblGradeStudent);

		JLabel lblText = new JLabel("Top");
		lblText.setForeground(new Color(72, 209, 204));
		lblText.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblText.setBounds(60, 518, 42, 32);
		panel.add(lblText);

		txtTop = new JTextField();
		txtTop.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtTop.setBounds(112, 522, 58, 28);
		panel.add(txtTop);
		txtTop.setColumns(10);

		btnFilter = new JButton("Filter");
		btnFilter.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnFilter.setBounds(180, 520, 92, 32);
		panel.add(btnFilter);

		btnFirst = new JButton("");
		btnFirst.setIcon(new ImageIcon(Student_grade.class.getResource("/image_icon/back-button.png")));
		btnFirst.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnFirst.setActionCommand("First");
		btnFirst.setBounds(165, 458, 80, 46);
		panel.add(btnFirst);

		btnPre = new JButton("");
		btnPre.setIcon(new ImageIcon(Student_grade.class.getResource("/image_icon/fast-backward.png")));
		btnPre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnPre.setActionCommand("Pre");
		btnPre.setBounds(255, 458, 80, 46);
		panel.add(btnPre);

		btnNext = new JButton("");
		btnNext.setIcon(new ImageIcon(Student_grade.class.getResource("/image_icon/fast-forward-button.png")));
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNext.setActionCommand("Next");
		btnNext.setBounds(345, 458, 80, 46);
		panel.add(btnNext);

		btnLast = new JButton("");
		btnLast.setIcon(new ImageIcon(Student_grade.class.getResource("/image_icon/next-button.png")));
		btnLast.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnLast.setActionCommand("Last");
		btnLast.setBounds(435, 458, 80, 46);
		panel.add(btnLast);
		
		lblBG = new JLabel("");
		lblBG.setBounds(0, 0, 742, 691);
		lblBG.setIcon(resizeBG("D:\\Java\\Java3_ASSIGNMENT\\src\\image_photo\\bg1.jpg"));
		panel.add(lblBG);

		initTable();
		fillTable();
		loadToListStudent();
		first_look();
		createACL();
		Search();
		running_Title();
	}

	public void initTable() {
		tblmodel = (DefaultTableModel) tblGradeStudent.getModel();
		String[] cols = new String[] { "ID", "Name", "English", "Tech", "PE", "Average" };
		tblmodel.setColumnIdentifiers(cols);
		tblGradeStudent.setModel(tblmodel);
		tblGradeStudent.setRowHeight(22);
		tblGradeStudent.setFont(new Font("Tahoma", 0, 14));
		TableColumnModel tblcl = tblGradeStudent.getColumnModel();
		tblcl.getColumn(0).setPreferredWidth(30);
		tblcl.getColumn(1).setPreferredWidth(60);
		tblcl.getColumn(2).setPreferredWidth(30);
		tblcl.getColumn(3).setPreferredWidth(30);
		tblcl.getColumn(4).setPreferredWidth(30);
		tblcl.getColumn(4).setPreferredWidth(40);
		header = tblGradeStudent.getTableHeader();
		header.setFont(new Font("Tahoma", 1, 16));
		header.setBackground(Color.pink);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < tblGradeStudent.getColumnCount(); i++) {
			tblGradeStudent.getColumnModel().getColumn(i).setCellRenderer(dtcr);
		}
	}
	
	public void running_Title() {
		head = new Timer(50, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				lblTitle.setLocation(lblTitle.getX() + 10, lblTitle.getY());
				if(lblTitle.getX() > 200) {
					head.stop();
					back.start();
				}
				
			}
		});
		head.start();
		 back = new Timer(50, new ActionListener() {
			
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
	
	public ImageIcon resizeBG(String path) {
		ImageIcon MyImage = new ImageIcon(path);
		Image img = MyImage.getImage();
		Image newImg = img.getScaledInstance(lblBG.getWidth(), lblBG.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);
		return image;
	}
	

	public void fillTable() {
		try {
			tblmodel.setRowCount(0);
			list_grd.clear();
			conn = jdbc.getConnection();
			String sql = "select Student.masv, Hoten, tienganh, tinhoc, gdtc,CONVERT(DECIMAL(10,2),((tienganh+tinhoc+gdtc)/3.0)) as 'DTB' from Student, Grade where Student.masv = Grade.masv";
			prsm = conn.prepareStatement(sql);
			ResultSet rs = prsm.executeQuery();
			while (rs.next()) {
				tblmodel.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6) });

				String masv = rs.getString(1);
				String name = rs.getString(2);
				int english = rs.getInt(3);
				int tech = rs.getInt(4);
				int pe = rs.getInt(5);
				double dtb = rs.getDouble(6);
				Grade grd = new Grade(masv, english, tech, pe, dtb);
				list_grd.add(grd);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void loadToListStudent() {
		try {
			conn = jdbc.getConnection();
			String sql = "select masv from student";
			prsm = conn.prepareStatement(sql);
			ResultSet rs = prsm.executeQuery();
			while (rs.next()) {
				String masv = rs.getString(1);
				list_std.add(new Student(masv));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void first_look() {
		if (tblGradeStudent.getRowCount() > 0 ) {
			index = 0;
			showToForm(index);
			tblGradeStudent.setRowSelectionInterval(index, index);
		}
	}

	public void showToForm(int index) {
		if (index >= 0) {
			txtStdID.setText((String) tblmodel.getValueAt(index, 0));
			txtName.setText((String) tblmodel.getValueAt(index, 1));
			txtEnglish.setText((String) (tblmodel.getValueAt(index, 2)));
			txtTech.setText((String) (tblmodel.getValueAt(index, 3)));
			txtPE.setText((String) (tblmodel.getValueAt(index, 4)));
			lblGrade.setText(String.valueOf(tblmodel.getValueAt(index, 5)));
		}
	}

	public void reset() {
		txtStdID.setText("");
		txtName.setText("");
		txtEnglish.setText("");
		txtTech.setText("");
		txtPE.setText("");
		lblGrade.setText("0");
		tblGradeStudent.clearSelection();
		txtTop.setText("");
	}

	public boolean check() {
		if (txtStdID.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Student Id is empty");
			return false;
		}

		if (txtEnglish.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Student English grade is empty");
			return false;
		} else {
			try {
				int english = Integer.parseInt(txtEnglish.getText());
				if (english < 0 || english > 10) {
					JOptionPane.showMessageDialog(this, "English grade has to be from 0-10");
					return false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "English grade has to be a number");
				return false;
			}
		}

		if (txtTech.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Student Tech grade is empty");
			return false;
		} else {
			try {
				int tech = Integer.parseInt(txtTech.getText());
				if (tech < 0 || tech > 10) {
					JOptionPane.showMessageDialog(this, "Tech grade has to be from 0-10");
					return false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Tech grade has to be a number");
				return false;
			}
		}

		if (txtPE.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Student PE grade is empty");
			return false;
		} else {
			try {
				int pe = Integer.parseInt(txtPE.getText());
				if (pe < 0 || pe > 10) {
					JOptionPane.showMessageDialog(this, "Pe grade has to be from 0-10");
					return false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "PE grade has to be a number");
				return false;
			}
		}
		return true;
	}

	public boolean check_ID_Student() {
		for (int i = 0; i < list_std.size(); i++) {
			if (txtStdID.getText().trim().compareTo(list_std.get(i).getId()) == 0) {
				return true;
			}
		}
		JOptionPane.showMessageDialog(this, "Student id doesn't exist");
		return false;
	}

	public void save() {
		if (check()) {
			if (check_ID_Student()) {
				for (int i = 0; i < tblGradeStudent.getRowCount(); i++) {
					if (txtStdID.getText().compareTo((String) tblmodel.getValueAt(i, 0)) == 0) {
						int ms = JOptionPane.showConfirmDialog(this,
								"Student already has the grade board! you want to update?", "Confirm",
								JOptionPane.YES_NO_OPTION);
						if (ms == JOptionPane.YES_OPTION) {
							update();
							return;
						}
						return;
					}
				}
				try {
					conn = jdbc.getConnection();
					String sql = "insert into grade values (?, ?, ?, ?)";
					prsm = conn.prepareStatement(sql);
					prsm.setString(1, txtStdID.getText());
					prsm.setString(2, txtEnglish.getText());
					prsm.setString(3, txtTech.getText());
					prsm.setString(4, txtPE.getText());
					int rs = prsm.executeUpdate();
					if (rs > 0) {
						fillTable();
						index = tblmodel.getRowCount() - 1;
						showToForm(index);
						tblGradeStudent.setRowSelectionInterval(index, index);
						JOptionPane.showMessageDialog(this, "Already saved");
					}
					conn.close();
					return;

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public void update() {
		if (check()) {
			if (check_ID_Student()) {
				for (int i = 0; i < tblGradeStudent.getRowCount(); i++) {
					if (txtStdID.getText().compareTo((String) tblmodel.getValueAt(i, 0)) == 0) {
						try {
							conn = jdbc.getConnection();
							String sql = "update grade set tienganh = ?, tinhoc = ?, gdtc =? where masv =?";
							prsm = conn.prepareStatement(sql);
							prsm.setString(1, txtEnglish.getText());
							prsm.setString(2, txtTech.getText());
							prsm.setString(3, txtPE.getText());
							prsm.setString(4, txtStdID.getText());
							int rs = prsm.executeUpdate();
							if (rs > 0) {
								JOptionPane.showMessageDialog(this, "Already updated");
								fillTable();
								tblGradeStudent.setRowSelectionInterval(i, i);
								return;
								
							}

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				int ms = JOptionPane.showConfirmDialog(this, "Student doesn't have the grade board! you want to save?",
						"Confirm", JOptionPane.YES_NO_OPTION);
				if (ms == JOptionPane.YES_OPTION) {
					save();
					return;
				}
			}
		}
	}

	public void delete() {
		if (txtStdID.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Please enter the student id you want to delete");
			return;
		} else {
			for (int i = 0; i < tblGradeStudent.getRowCount(); i++) {
				if (txtStdID.getText().compareTo((String) tblmodel.getValueAt(i, 0)) == 0) {
					int ms = JOptionPane.showConfirmDialog(this, "Do you want to delete?", "Confirm",
							JOptionPane.YES_NO_OPTION);
					if (ms == JOptionPane.YES_OPTION) {
						try {
							conn = jdbc.getConnection();
							String sql = "delete grade where masv = ?";
							prsm = conn.prepareStatement(sql);
							prsm.setString(1, txtStdID.getText());
							int rs = prsm.executeUpdate();
							if (rs > 0) {
								fillTable();
								JOptionPane.showMessageDialog(this, "Already deleted");
								if (index == tblmodel.getRowCount()) {
									index--;
								}
								if (index >= 0) {
									showToForm(index);
									tblGradeStudent.setRowSelectionInterval(index, index);
								} else {
									reset();
								}

							}
							conn.close();
							return;

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			}

		}
	}

	public void Search() {
		txtID.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				doSearch();
				first_look();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				doSearch();

			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				doSearch();
			}
		});
	}

	public void doSearch() {
		try {
			tblmodel.setRowCount(0);
			conn = jdbc.getConnection();
			String sql = "select Student.masv, Hoten, tienganh, tinhoc, gdtc,"
					+ "CONVERT(DECIMAL(10,2),((tienganh+tinhoc+gdtc)/3.0)) as 'DTB' from Student, Grade "
					+ "where Student.masv = Grade.masv and Grade.masv like ? + '%'";
			prsm = conn.prepareStatement(sql);
			prsm.setString(1, txtID.getText());
			ResultSet rs = prsm.executeQuery();
			while (rs.next()) {
				tblmodel.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6) });
			}
			if (tblmodel.getRowCount() == 1) {
				index = 0;
				showToForm(index);
				tblGradeStudent.setRowSelectionInterval(index, index);
				return;
			}
			if (tblmodel.getRowCount() == 0) {
				JOptionPane.showMessageDialog(this, "ID doesn't match");
				reset();
				return;
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void click_Search_BTN() {
		if (txtID.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Enter the student name you want to look for");
			return;
		} else {
			for (int i = 0; i < tblmodel.getRowCount(); i++) {
				if(txtID.getText().trim().equals(tblmodel.getValueAt(i,0))) {
					JOptionPane.showMessageDialog(this, "Find it");
					return;
				}
			}
			
			JOptionPane.showMessageDialog(this, "Not found");
			return;
			
		}
	}

	public void First() {
		index = 0;
		click();
	}

	public void Last() {
		index = tblmodel.getRowCount() - 1;
		click();
	}

	public void Pre() {
		if (index <= 0) {
			index = tblmodel.getRowCount() - 1;
			click();
		} else {
			index--;
			click();
		}
	}

	public void Next() {
		if (index >= tblmodel.getRowCount() - 1) {
			index = 0;
			click();
		} else {
			index++;
			click();
		}
	}

	public void click() {
		tblGradeStudent.setRowSelectionInterval(index, index);
		showToForm(index);
	}

	public void Filter() {
		if (txtTop.getText().equals("")) {
			JOptionPane.showMessageDialog(this, "Enter the filter-top number");
			return;
		} else {
			try {
				int top = Integer.parseInt(txtTop.getText());
				if (top < 0) {
					JOptionPane.showMessageDialog(this, "Filter table number >= 0");
					return;
				}
				if (top > list_grd.size()) {
					JOptionPane.showMessageDialog(this, "The number you've entered is bigger than the student list");
					return;
				}
				if(top == 0) {
					fillTable();
					first_look();
					return;
				}
				tblmodel.setRowCount(0);
				conn = jdbc.getConnection();
				String sql = "select grade.masv, Hoten, tienganh, tinhoc, gdtc,"
						+ " CONVERT(DECIMAL(10,2),((tienganh+tinhoc+gdtc)/3.0)) as 'DTB'"
						+ " from Student inner join Grade on Student.masv = Grade.masv "
						+ "where ((tienganh+ tinhoc + gdtc)/3.0) in "
						+ "(select top (?) ((tienganh + tinhoc + gdtc)/3.0) from Grade"
						+ " group by ((tienganh + tinhoc + gdtc)/3.0))" + "order by ((tienganh + tinhoc + gdtc)/3.0) desc";
				prsm = conn.prepareStatement(sql);

				prsm.setInt(1, top);
				ResultSet rs = prsm.executeQuery();
				while (rs.next()) {
					String id = rs.getString(1);
					String name = rs.getString(2);
					String eng = rs.getString(3);
					String tech = rs.getString(4);
					String pe = rs.getString(5);
					String avg = rs.getString(6);
					tblmodel.addRow(new Object[] { id, name, eng, tech, pe, avg });
					index = 0;
					showToForm(index);
					tblGradeStudent.setRowSelectionInterval(index, index);
				}
				conn.close();
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, "Filter has to be a number!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void createACL() {
		btnSearch.addActionListener(this);
		btnSearch.setActionCommand("Search");
		btnNew.addActionListener(this);
		btnNew.setActionCommand("New");
		btnSave.addActionListener(this);
		btnSave.setActionCommand("Save");
		btnDelete.addActionListener(this);
		btnDelete.setActionCommand("Delete");
		btnUpdate.addActionListener(this);
		btnUpdate.setActionCommand("Update");
		btnExit.addActionListener(this);
		btnExit.setActionCommand("Exit");
		btnFirst.addActionListener(this);
		btnFirst.setActionCommand("First");
		btnPre.addActionListener(this);
		btnPre.setActionCommand("Pre");
		btnNext.addActionListener(this);
		btnNext.setActionCommand("Next");
		btnLast.addActionListener(this);
		btnLast.setActionCommand("Last");
		btnFilter.addActionListener(this);
		btnFilter.setActionCommand("Filter");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {
		case "Search":
			click_Search_BTN();
			break;
		case "New":
			reset();
			break;
		case "Save":
			save();
			break;
		case "Delete":
			delete();
			break;
		case "Update":
			update();
			break;
		case "Exit":
			System.exit(0);
			break;
		case "First":
			First();
			break;
		case "Pre":
			Pre();
			break;
		case "Next":
			Next();
			break;
		case "Last":
			Last();
			break;
		case "Filter":
			Filter();
			break;

		}

	}
}
