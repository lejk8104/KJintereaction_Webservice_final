package kr.ac.sunmoon.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class KJMember implements IsSerializable {

	private String ID;
	private String Password;
	private String CheckPassword;
	private String Name;
	private String Email;
	private String Gender;
	private String Birth;
	private String Country;
	private String Local;
	private String[] Interests;
	 
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String Password) {
		this.Password = Password;
	}
	public String getCheckPassword() {
		return CheckPassword;
	}
	public void setCheckPassword(String CheckPassword) {
		this.CheckPassword = CheckPassword;
	}
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public void setEmail(String Email) {
		this.Email = Email;
	}
	public String getEmail() {
		return Email;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String Gender) {
		this.Gender = Gender;
	}
	public String getBirth() {
		return Birth;
	}
	public void setBitrh(String Birth) {
		this.Birth = Birth;
	}
	public String getCountry() {
		return Country;
	}
	public void setCountry(String Country) {
		this.Country = Country;
	}
	public String getLocal() {
		return Local;
	}
	public void setLocal(String Local) {
		this.Local = Local;
	}
	public String[] getInterests() {
		return Interests;
	}
	public void setInterests(String[] interests) {
		Interests = interests;
	}
	public void setBirth(String birth) {
		Birth = birth;
	}
}
