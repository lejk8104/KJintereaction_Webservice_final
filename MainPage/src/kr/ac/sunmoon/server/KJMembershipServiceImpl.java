package kr.ac.sunmoon.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.google.gwt.text.client.DoubleRenderer;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import kr.ac.sunmoon.client.KJMembershipService;
import kr.ac.sunmoon.shared.KJMember;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")         //?
public class KJMembershipServiceImpl extends RemoteServiceServlet implements KJMembershipService {
	
	public KJMember findKJmember(String ID, String Name) {
		// TODO Auto-generated method stub
		KJMember kjmember = new KJMember();
		try {
			String url = "jdbc:mysql://localhost:3306/sdp2?useSSL=false";
			String user = "root";
			String password_ = "seiya411";
			
			Connection con = DriverManager.getConnection(url, user, password_);
			
			Statement stmt = con.createStatement();
			String sql = "SELECT* FROM members1hip_data.kjmember Inner Join members1hip_data.local on kjmember.country= local.country; ";
			ResultSet rs1 = stmt.executeQuery(sql);
			while(rs1.next()) {				
				String id = rs1.getString("ID");
				String Password = rs1.getString("PassWord");
				String Check_Password = rs1.getString("CheckPassword");
				String name = rs1.getString("Name");
				String Gender = rs1.getString("Gender");
				String Birth = rs1.getString("Birth");
				String Country = rs1.getString("Country");
				String Local = rs1.getString(" Local");
				//�н����� ã��
				if (ID.equals(id) && Name.equals(name)) {
					kjmember.setID(id);
					kjmember.setCheckPassword(Check_Password);
					kjmember.setName(name);
					kjmember.setGender(Gender);
//					kjmember.setBirth(Birth);
					kjmember.setCountry(Country);
					kjmember.setLocal(Local);
					rs1.close();
					stmt.close();
					con.close();
					return kjmember;
				}
				//���̵�ã��
//				else if 
			}
			rs1.close();
			stmt.close();
			con.close();
				
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public boolean checkID(KJMember kjMember) {
//		try {
//			String id = kjMember.getID();
//			String url = "jdbc:mysql://localhost:3306/sdp2?useSSL=false";
//			String user = "root";
//			String password_ = "seiya411";
//			Connection con = DriverManager.getConnection(url, user, password_);
//		
//			Statement stmt = con.createStatement();
//			String sql = "SELECT ID kjmember where ID='" + id + "';";
//			ResultSet rs1 = stmt.executeQuery(sql);
//			if(rs1.getMetaData().getColumnCount() == 1) {
//				rs1.close();
//				stmt.close();
//				con.close();
//				return false;
//			}
//			rs1.close();
//			stmt.close();
//			con.close();
//			return true;
//
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
		return false;
	}

//	@Override
	public void Register_Membership(KJMember kjMember) {
		System.out.println("you succesfully connected to server and will register the member.");
		try {
			String id = kjMember.getID();
			String password = kjMember.getPassword();
			String checkpassword = kjMember.getCheckPassword();
			String name = kjMember.getName();
			String gender = kjMember.getGender();
			String birth = kjMember.getBirth();
			String country = kjMember.getCountry();
			String local = kjMember.getLocal();
			String email = kjMember.getEmail();
			String[] interests = kjMember.getInterests();
			
			String url = "jdbc:mysql://localhost:3306/sdp2?allowPublicKeyRetrieval=true&useSSL=false";
			String user = "root";
			String password_ = "seiya411";
			
			Connection con = DriverManager.getConnection(url, user, password_);
			
			Statement stmt = con.createStatement();
			String sql = "INSERT into kjmember values(\"" +id + "\", \"" + password + "\", \"" + checkpassword + 
					"\", \"" + name + "\", \"" + gender + "\", \"" + birth + "\", \"" + country + "\", \"" + local + 
					"\", \"" + email + "\", \"" + interests[0] + "\", \"" + interests[1] + "\", \"" + interests[2] + 
					"\", \"" + interests[3] + "\", \"" + interests[4] + "\");";
			int n = stmt.executeUpdate(sql);
			System.out.println(n);
			
//			rs1.close();
			stmt.close();
			con.close();
			
			addTotalNumber(local);
		}
		catch(Exception e)
			{
			e.printStackTrace();
			}
	}
	
	@Override
	public KJMember LoginService(KJMember loginmember) {
		String id = loginmember.getID();
		String password = loginmember.getPassword();
//		List returnlist = new List();
		try {
			String url = "jdbc:mysql://localhost:3306/sdp2?useSSL=false";
			String user = "root";
			String password_ = "seiya411";
			
			Connection con = DriverManager.getConnection(url, user, password_);
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM kjmember;";
			ResultSet rs1 = stmt.executeQuery(sql);
			
			while(rs1.next()) {
				String ID = rs1.getString("ID");
				String Password = rs1.getString("Password");
				if(ID.equals(id) && Password.equals(password)) {
					loginmember.setCheckPassword(rs1.getString("CheckPassword"));
					loginmember.setName(rs1.getString("Name"));
					loginmember.setGender(rs1.getString("Gender"));
					loginmember.setBitrh(rs1.getString("Birth"));
					loginmember.setCountry(rs1.getString("Country"));
					loginmember.setEmail(rs1.getString("Email"));
					rs1.close();
					stmt.close();
					con.close();
					System.out.println(loginmember.getName());
					return loginmember;
				}
			}
			rs1.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginmember;
	}
	
	private int findLCS(int[][] c, String[] X, String[] Y) { //LCS�˰��� ����
		for(int i=1; i<X.length+1; i++) {
			for(int j=1; j<Y.length+1; j++) {
				if(X[i-1].equals(Y[j-1]))
					c[i][j] = c[i-1][j-1] + 1;
				else
					c[i][j] = Math.max(c[i][j-1], c[i-1][j]);
			}
		}
		return c[X.length][Y.length];
	}
	
	@Override
	public KJMember[] findLCS(KJMember kjmember){
		System.out.println("you've successfully connected to server, and findLCS method has worked well.");
		System.out.println("His/Her inputted ID is : " + kjmember.getID());
		System.out.println("His/Her inputted Password is : " + kjmember.getPassword());
		KJMember[] kjmembers = new KJMember[2];
		String matchingID = null;
		try {
			String url = "jdbc:mysql://localhost:3306/sdp2?useSSL=false";
			String user = "root";
			String password = "seiya411";
			
			Connection con = DriverManager.getConnection(url, user, password);
			
			Statement stmt = con.createStatement();
			
			String sql1 = "select Country, interest1, interest2, interest3, interest4, interest5 from kjmember where ID = '" + kjmember.getID() + "';";
			ResultSet rs1 = stmt.executeQuery(sql1);
			String[] myInterests = new String[5];
			while(rs1.next()) {
				kjmember.setCountry(rs1.getString("Country"));
				myInterests[0] = rs1.getString("interest1");
				myInterests[1] = rs1.getString("interest2");
				myInterests[2] = rs1.getString("interest3");
				myInterests[3] = rs1.getString("interest4");
				myInterests[4] = rs1.getString("interest5");
			}
			kjmember.setInterests(myInterests);
			kjmembers[0] = kjmember;
			
			String sql2 = "select ID, Country, interest1, interest2, interest3, interest4, interest5 from kjmember where ID != '" + kjmember.getID() + "';";
			ResultSet rs2 = stmt.executeQuery(sql2);
			String[] interests = null;
			int maxLCS = 0;
			ArrayList<String> tmpID = new ArrayList<String>();
			KJMember kjmember2 = new KJMember();
			while(rs2.next()) {
				interests = new String[5];
				if (!(kjmember.getCountry().equals(rs2.getString("Country")))) {
					String interest1 = rs2.getString("interest1");
					interests[0] = interest1;
					String interest2 = rs2.getString("interest2");
					interests[1] = interest2;
					String interest3 = rs2.getString("interest3");
					interests[2] = interest3;
					String interest4 = rs2.getString("interest4");
					interests[3] = interest4;
					String interest5 = rs2.getString("interest5");
					interests[4] = interest5;
					int[][] c = new int[myInterests.length+1][interests.length+1];
					int LCS = findLCS(c, myInterests, interests);
					if(LCS > maxLCS) {
						maxLCS = LCS;
						tmpID.removeAll(tmpID);
					}
					if(LCS >= maxLCS) {
						tmpID.add(rs2.getString("ID"));
					}
				}
			}
			matchingID = tmpID.get((int)(Math.random()*tmpID.size()));
			kjmember2.setID(matchingID);
			kjmember2.setInterests(interests);
			kjmembers[1] = kjmember2;
			rs2.close();
			stmt.close();
			con.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return kjmembers;
	}

	@Override
	public String[][] fetchDataByLocal() {
		String[][] data = new String[6][2];
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		DecimalFormat df = new DecimalFormat("#.##");
		double total = 0.0;
		try {
			String url = "jdbc:mysql://localhost:3306/sdp2?useSSL=false";
			String user = "root";
			String password_ = "seiya411";
			
			Connection con = DriverManager.getConnection(url, user, password_);
			Statement stmt = con.createStatement();
			String sql = "SELECT Local, Total FROM local;";
			ResultSet rs1 = stmt.executeQuery(sql);
			while(rs1.next()) {
				hm.put(rs1.getString("Local"), rs1.getInt("Total"));
				total += rs1.getInt("Total");
			}			
			rs1.close();
			stmt.close();
			con.close();

			List<Integer> sortedCollection = new ArrayList<Integer>(hm.values());
			Collections.sort(sortedCollection);
			
			for(int i=0; i<data.length; i++) {
				int index = sortedCollection.get(i);
				System.out.println("index: " + index + ", total: " + total);
				data[i][0] = getKey(hm, index);
				data[i][1] = df.format(index/total*100);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0; i<data.length; i++) {
			System.out.println(data[i][0]);
			System.out.println(data[i][1]);
		}
		return data;
	}
	private String getKey(HashMap<String, Integer> hm, int index) {
		for (Entry<String, Integer> entry : hm.entrySet()) {
			if (entry.getValue().equals(index)) {
				return entry.getKey();
			}
		}
		return null;
	}
	private void addTotalNumber(String local) {
		try {
			String url = "jdbc:mysql://localhost:3306/sdp2?useSSL=false";
			String user = "root";
			String password_ = "seiya411";
			
			Connection con = DriverManager.getConnection(url, user, password_);
			Statement stmt = con.createStatement();
			String sql = "UPDATE local SET Total = Total + 1 where local.Local = \"" + local + "\";";
			int n = stmt.executeUpdate(sql);
			System.out.println(n);

//			rs1.close();
			stmt.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}