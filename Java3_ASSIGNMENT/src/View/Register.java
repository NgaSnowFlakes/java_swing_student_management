package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.JDBCUtils;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.Dimension;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Register extends JFrame{

	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirm;
	private JButton btnRegister;
	private JCheckBox chckshow;
	private JButton btnLogin;
	private JRadioButton rdoTeacher;
	private JRadioButton rdoManager;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	JDBCUtils jdbc = new JDBCUtils();
	private Connection conn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(222, 184, 135));
		panel.setBounds(0, 0, 484, 474);
		contentPane.add(panel);
		
		JLabel lblRegister = new JLabel("REGISTER");
		lblRegister.setForeground(Color.GREEN);
		lblRegister.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblRegister.setBounds(164, 23, 164, 55);
		panel.add(lblRegister);
		
		JLabel lblUser = new JLabel("Username");
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUser.setBounds(48, 88, 110, 28);
		panel.add(lblUser);
		
		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUser.setColumns(10);
		txtUser.setBounds(164, 88, 236, 28);
		panel.add(txtUser);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(48, 154, 110, 28);
		panel.add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPassword.setBounds(164, 157, 236, 28);
		panel.add(txtPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblConfirmPassword.setBounds(10, 255, 148, 28);
		panel.add(lblConfirmPassword);
		
		txtConfirm = new JPasswordField();
		txtConfirm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtConfirm.setBounds(164, 258, 236, 28);
		panel.add(txtConfirm);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRole.setBounds(48, 315, 110, 28);
		panel.add(lblRole);
		
		JLabel lblChangeCode = new JLabel("");
		lblChangeCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblChangeCode.setBounds(364, 371, 28, 28);
		panel.add(lblChangeCode);
		
		btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(check()) {
					if(findByUserName()){
						insertInto();
					}
				}
			}
		});
		btnRegister.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnRegister.setBackground(new Color(255, 140, 0));
		btnRegister.setBounds(164, 371, 164, 46);
		panel.add(btnRegister);
		
		chckshow = new JCheckBox("Show Password?");
		chckshow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckshow.isSelected()) {
					txtPassword.setEchoChar((char) 0);
				} else {
					txtPassword.setEchoChar('*');
				}
			}
		});
		chckshow.setMinimumSize(new Dimension(103, 30));
		chckshow.setMaximumSize(new Dimension(103, 30));
		chckshow.setIconTextGap(10);
		chckshow.setFont(new Font("Tahoma", Font.PLAIN, 16));
		chckshow.setBounds(164, 208, 192, 28);
		panel.add(chckshow);
		
		btnLogin = new JButton("Back to Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login lg = new Login();
				lg.setVisible(true);
				dispose();
			}
		});
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBounds(166, 427, 162, 21);
		panel.add(btnLogin);
		
		rdoTeacher = new JRadioButton("Teacher");
		buttonGroup.add(rdoTeacher);
		rdoTeacher.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdoTeacher.setBounds(166, 321, 103, 21);
		panel.add(rdoTeacher);
		
		rdoManager = new JRadioButton("Manager");
		buttonGroup.add(rdoManager);
		rdoManager.setFont(new Font("Tahoma", Font.PLAIN, 16));
		rdoManager.setBounds(297, 321, 103, 21);
		panel.add(rdoManager);
		setLocationRelativeTo(null);
	}
	public boolean check() {
		String user = txtUser.getText().trim();
		String pass =String.valueOf(txtPassword.getPassword()).trim();
		String confirm =String.valueOf(txtConfirm.getPassword()).trim();
		if(user.equals("")) {
			JOptionPane.showMessageDialog(this, "Invalid username!");
			txtUser.requestFocus();
			return false;
		}
		if(pass.equals("")) {
			JOptionPane.showMessageDialog(this, "Invalid password!");
			txtPassword.requestFocus();
			return false;
		}
		if(confirm.compareTo(pass) != 0) {
			JOptionPane.showMessageDialog(this, "Invalid confirm password!");
			txtConfirm.requestFocus();
			return false;
		}
		if(rdoManager.isSelected()==false && rdoTeacher.isSelected()== false) {
			JOptionPane.showMessageDialog(this, "Please choose your role");
			return false;
		}
		return true;
	}
	public boolean findByUserName() {	 
		try {
			conn = jdbc.getConnection();
			 String sql = "select username from Users where username = ?";
			 PreparedStatement prsm = conn.prepareStatement(sql);
			 prsm.setString(1, txtUser.getText());
			 ResultSet rs= prsm.executeQuery();
			 while(rs.next()) {
				 JOptionPane.showMessageDialog(this, "Username's already existed");
				 return false;
			 }
			 conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return true;
	}
	public void insertInto() {
		try {
			conn = jdbc.getConnection();
			String sql  = "insert into Users (username, passwords, roles) values(?, ?, ?)";
			PreparedStatement prsm = conn.prepareStatement(sql);
			prsm.setString(1, txtUser.getText());
			prsm.setString(2, String.valueOf(txtPassword.getPassword()));
			String role;
			if(rdoTeacher.isSelected()) {
				role = "Teacher";
			}else {
				role = "Manager";
			}
			prsm.setString(3, role);
			prsm.executeUpdate();
			conn.close();
			JOptionPane.showMessageDialog(this, "Register successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
