package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Database.JDBCUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JLabel lblAnh;
	private JPanel panel;
	private JLabel lblUser;
	private JLabel lblPassword;
	private JCheckBox chckShow;
	private JButton btnLogin;
	private JButton btnCancel;

	JDBCUtils jdbc = new JDBCUtils();
	Connection conn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 335);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);

		panel = new JPanel();
		panel.setBackground(new Color(255, 222, 173));
		panel.setBounds(10, 10, 592, 293);
		contentPane.add(panel);
		panel.setLayout(null);

		lblAnh = new JLabel();
		lblAnh.setBounds(10, 10, 233, 273);
		lblAnh.setIcon(resizeImage("D:\\Java\\Java3_ASSIGNMENT\\src\\image_icon\\qoobe_login.jpg"));
		panel.add(lblAnh);

		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(new Color(0, 255, 0));
		lblLogin.setFont(new Font("Wide Latin", Font.BOLD, 22));
		lblLogin.setBounds(361, 10, 123, 69);
		panel.add(lblLogin);

		lblUser = new JLabel("Username");
		lblUser.setForeground(new Color(218, 165, 32));
		lblUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUser.setBounds(266, 89, 89, 26);
		panel.add(lblUser);

		txtUsername = new JTextField();
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtUsername.setBounds(375, 93, 201, 26);
		panel.add(txtUsername);
		txtUsername.setColumns(10);

		lblPassword = new JLabel("Password");
		lblPassword.setForeground(new Color(218, 165, 32));
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPassword.setBounds(266, 140, 89, 26);
		panel.add(lblPassword);

		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtPassword.setBounds(375, 140, 201, 25);
		panel.add(txtPassword);

		chckShow = new JCheckBox("Show Password?");
		chckShow.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chckShow.setBounds(375, 187, 144, 21);
		ACL_checkbox(chckShow);
		panel.add(chckShow);

		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnLogin.setBounds(375, 238, 96, 21);
		panel.add(btnLogin);

		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnCancel.setBounds(481, 238, 101, 21);
		panel.add(btnCancel);
		ACL_btn(btnLogin);
		ACL_btn(btnCancel);

	}

	public ImageIcon resizeImage(String path) {
		ImageIcon MyImage = new ImageIcon(path);
		Image img = MyImage.getImage();
		Image newImg = img.getScaledInstance(lblAnh.getWidth(), lblAnh.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);
		return image;
	}

	public void ACL_checkbox(JCheckBox chck) {
		chck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (chck.isSelected()) {
					txtPassword.setEchoChar((char) 0);
				} else {
					txtPassword.setEchoChar('*');
				}

			}
		});
	}

	public void ACL_btn(JButton btn) {
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton button = (JButton) e.getSource();
				if (button.getText().equals("Login")) {
					check_findUser();
				}
				if (button.getText().equals("Cancel")) {
					System.exit(0);
				}

			}
		});
	}

	public void check_findUser() {
		if (txtUsername.getText().trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Username is empty");
			txtUsername.requestFocus();
			return;
		}
		try {
			conn = jdbc.getConnection();
			String sql = "select * from Users where username = ? and passwords = ?";
			PreparedStatement prsm = conn.prepareStatement(sql);
			prsm.setString(1, txtUsername.getText().trim());
			prsm.setString(2, String.valueOf(txtPassword.getPassword()).trim());
			ResultSet rs = prsm.executeQuery();
			while (rs.next()) {
				String user = rs.getString("username");
				String pass = rs.getString("passwords");
				String role = rs.getString("roles");
				if (txtUsername.getText().equals(user) && String.valueOf(txtPassword.getPassword()).equals(pass)) {
					if (role.equals("Manager")) {
						Student_Management std_mng = new Student_Management();
						std_mng.setLocationRelativeTo(null);
						std_mng.setVisible(true);
						dispose();
						return;
					}
					if (role.equals("Teacher")) {
						Student_grade std_grd = new Student_grade();
						std_grd.setLocationRelativeTo(null);
						std_grd.setVisible(true);
						dispose();
						return;
					}
				}
			}
			Invalid_message("Invalid account, go to register?");
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void Invalid_message(String message) {
		int choice = JOptionPane.showConfirmDialog(this, message);
		if (choice == JOptionPane.YES_OPTION) {
			Register rgs = new Register();
			rgs.setVisible(true);
			dispose();
			return;
		}
	}
}
