/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sis;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author maruf
 */
public class DueFee extends JFrame{
    static DueFee frame;
	public DueFee() {
		//Code to view data in JTable
		List<Students> list=StudentDB.due();
		int size=list.size();
		
		String data[][]=new String[size][13];
		int row=0;
		for(Students s:list){
			data[row][0]=String.valueOf(s.getRollno());
			data[row][1]=s.getName();
                        data[row][2]=s.getId();
			data[row][3]=s.getEmail();
			data[row][4]=s.getCourse();
			data[row][5]=String.valueOf(s.getFee());
			data[row][6]=String.valueOf(s.getPaid());
			data[row][7]=String.valueOf(s.getDue());
			data[row][8]=s.getAddress();
			data[row][9]=s.getCity();
			data[row][10]=s.getState();
			data[row][11]=s.getCountry();
			data[row][12]=s.getContactno();
			
			row++;
		}
		String columnNames[]={"Rollno","Name","ID","Email","Course","Fee","Paid","Due","Address","City","State","Country","Contact No"};
		
		JTable jt=new JTable(data,columnNames);
		JScrollPane sp=new JScrollPane(jt);
		add(sp);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 400);
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new DueFee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
