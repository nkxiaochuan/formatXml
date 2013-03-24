package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import core.FormatXml;

public class GuiAppMain extends JFrame implements ActionListener{

	private JTextArea jtaSource, jtaDest;
	private JButton jbtnClearReq, jbtnClearResp,jbtnFormat;
	public GuiAppMain(){
		constructAppWindow();
	}
	
	private void constructAppWindow(){
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout(1));
		

		//Request and Response Panel
		JPanel rrPanel = new JPanel();
		rrPanel.setLayout(new GridLayout(1, 2));
		
		jbtnFormat = new JButton("FORMAT");
		JPanel formatPanel = new JPanel();
		formatPanel.setLayout(new BorderLayout());
		controlPanel.add(jbtnFormat,BorderLayout.CENTER);
		
		Font jtaFont = new Font("SansSerif", Font.LAYOUT_LEFT_TO_RIGHT, 11);
		JPanel reqPanel = new JPanel();
		reqPanel.setLayout(new BorderLayout());
		jtaSource = new JTextArea();
		jtaSource.setFont(jtaFont);
		jtaSource.setEditable(true);
		reqPanel.add(new JScrollPane(jtaSource), BorderLayout.CENTER);
		jbtnClearReq = new JButton("Clear Contents");
		reqPanel.add(jbtnClearReq, BorderLayout.SOUTH);
		
		JPanel respPanel = new JPanel();
		respPanel.setLayout(new BorderLayout());
		jtaDest = new JTextArea();
		jtaDest.setFont(jtaFont);
		jtaDest.setEditable(true);
		respPanel.add(new JScrollPane(jtaDest), BorderLayout.CENTER);
		jbtnClearResp = new JButton("Clear Contents");
		respPanel.add(jbtnClearResp, BorderLayout.SOUTH);
		
		rrPanel.add(reqPanel);
		rrPanel.add(respPanel);
		
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(controlPanel, BorderLayout.NORTH);
		getContentPane().add(rrPanel, BorderLayout.CENTER);
		
		jbtnFormat.addActionListener(this);
		jbtnClearReq.addActionListener(this);
		jbtnClearResp.addActionListener(this);
		
		setTitle("XML format");
		setSize(1000, 700);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int screenWidth = screenSize.width;
		int screenHeight = screenSize.height;
		Dimension frameSize = getSize();
		int hor = (int)(0.5 * screenWidth - 0.5 * frameSize.width);
		int ver = (screenHeight - frameSize.height) / 2;
		setLocation(hor, ver);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void getSource(){
		String source = jtaSource.getText();
		if(source ==""){
			JOptionPane.showMessageDialog(null, 
					"Missing Input", 
					"source", JOptionPane.ERROR_MESSAGE);
			return;
		}
		FormatXml fx = new FormatXml();
		String des = fx.goodXml(source);
		jtaDest.setText("");
		jtaDest.append(des);
		jtaDest.setCaretPosition(0);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== jbtnClearReq){
			jtaSource.setText("");
		}else if(e.getSource() == jbtnClearResp){
			jtaDest.setText("");
		}else if(e.getSource() == jbtnFormat){
			getSource();
		}
		
	}
	
	public static void main(String[] args){
		GuiAppMain app = new GuiAppMain();
	}

}
