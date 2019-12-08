package kr.ac.sunmoon.client;

import java.awt.List;
import java.lang.reflect.Array;
import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.SimpleStore;
import com.gwtext.client.data.Store;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.DateField;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.form.VType;
import com.gwtext.client.widgets.form.event.ComboBoxListenerAdapter;
import com.gwtext.client.widgets.grid.RowSelectionModel;
import com.gwtext.client.widgets.layout.AnchorLayoutData;
import com.gwtext.client.widgets.layout.ColumnLayoutData;
import com.gwtext.client.widgets.layout.FormLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;
import com.sun.java.swing.plaf.windows.resources.windows;

import kr.ac.sunmoon.shared.KJMember;
import kr.ac.sunmoon.shared.Survey_data;
  
  
public class Register_Membership extends Window{  
  
	private ArrayList<String> inputlList = new ArrayList<String>();
	String[] membershipdata = new String[9];
	String[] surveydata =  new String[4];
	Window popup = new Window();
	
    public Register_Membership() {  

    	super();
		this.setBorder(false);
		this.setPaddings(0);
		this.setClosable(true);
		this.setWidth(360);
		this.setHeight(435);
		this.setPlain(true);
		this.setCloseAction(this.HIDE);
    	
		
        FormPanel formPanel = new FormPanel();  
        formPanel.setFrame(true); 
        formPanel.setTitle("KJ Membership Page");
        formPanel.setLabelWidth(65);  
        formPanel.setBorder(false);  
        formPanel.setWidth(345);  
        
        //TabPanel 선언
        final TabPanel tabPanel = new TabPanel();  
        tabPanel.setActiveTab(0);  
  
        final Panel firstTab = new Panel();  
        firstTab.setTitle("KJ Membership");  
        firstTab.setLayout(new FormLayout());  
        firstTab.setAutoHeight(true);  
        firstTab.setPaddings(25);  
  
        //list store 선언
        final Store gender = new SimpleStore(new String[] {"genderData","gender"}, Getgender());
        gender.load();
        final Store countriesStore = new SimpleStore(new String[]{"country", "countryData"}, countries);  
        countriesStore.load();  
        final Store citiesStore = new SimpleStore(new String[]{"cityData", "country", "cityName"}, cities);  
        citiesStore.load(); 
        
        // ID
        final TextField txtID = new TextField("ID", "id", 200);  
        txtID.setAllowBlank(false);  
//        txtID.setValue("id"); 
        firstTab.add(txtID);  
  
        //Password
        final TextField txtPassword = new TextField("Password", "passwordData", 200);  
//        txtPassword.setValue("password");  
        firstTab.add(txtPassword);  
  
        //CheckPassword
        final TextField txtCheckPassword = new TextField("Check Password", "checkpasswordData", 200);  
//        txtCheckPassword.setValue("checkpassword");
        firstTab.add(txtCheckPassword);  

        //Name
        final TextField txtName = new TextField("Name", "nameData", 200);
        txtName.setAllowBlank(false); 
        txtName.setValue("name"); 
        firstTab.add(txtName);  
  
        //E-Mail
        final TextField txtemail = new TextField("Email", "emailData", 200);  
        txtemail.setVtype(VType.EMAIL);  
        firstTab.add(txtemail);  
  
        //Gender list
        final ComboBox listgender = new ComboBox();
        listgender.setAllowBlank(false);
        listgender.setMinChars(1);
        listgender.setFieldLabel("Gender");
        listgender.setStore(gender);
        listgender.setDisplayField("genderData");
        listgender.setEmptyText("Select Gender"); 
        listgender.setTriggerAction(ComboBox.ALL); //? 모르겠다
        listgender.setTypeAhead(true);  // 이를 채우고 자동 선택한다
        listgender.setSelectOnFocus(true); //데이터값 수신할 떄 자동채움
        listgender.setWidth(200);  
        listgender.setResizable(true); //?
        firstTab.add(listgender);
        
        //Birth
        final DateField dataBirth = new DateField("Date of birth", "BirthData", 200);  
        dataBirth.setAllowBlank(false);
        firstTab.add(dataBirth);
        
        // country
        final ComboBox listcountry = new ComboBox();  
        listcountry.setFieldLabel("Select Country");  
        listcountry.setStore(countriesStore);  
        listcountry.setDisplayField("countryData");  
        listcountry.setMode(ComboBox.LOCAL);  
    	listcountry.setTriggerAction(ComboBox.ALL);  
    	listcountry.setForceSelection(true);  
//    	listcountry.setValueField("country");  
    	listcountry.setReadOnly(true);  
    	listcountry.setWidth(200);

    	// local
    	final ComboBox listcity = new ComboBox();  
    	listcity.setFieldLabel("Select City");  
    	listcity.setStore(citiesStore);  
    	listcity.setDisplayField("cityName");  
//    	listlocal.setValueField("local");  
    	listcity.setMode(ComboBox.LOCAL);  
	    listcity.setTriggerAction(ComboBox.ALL);  
	    listcity.setLinked(true);  
	    listcity.setForceSelection(true);  
	    listcity.setReadOnly(true);  
	    listcity.setWidth(200);

	    //동작과정
	    listcountry.addListener(new ComboBoxListenerAdapter() {  
        public void onSelect(ComboBox comboBox, Record record, int index) {  
            listcity.setValue("");  
            citiesStore.filter("country", comboBox.getValue());  
        	}  
	    });  
	    firstTab.add(listcountry);
	    firstTab.add(listcity);
  
        tabPanel.add(firstTab);  
        
        //list store 선언
        final Store preference = new SimpleStore(new String[] {"Text","Preference","data"}, Getpreference());
        preference.load();
        final Store interest1 = new SimpleStore(new String[] {"Text","interests"}, InterestList);
        interest1.load();
        final Store interest2 = new SimpleStore(new String[] {"Text","interests"}, InterestList);
        interest2.load();
        final Store interest3 = new SimpleStore(new String[] {"Text","interests"}, InterestList);
        interest3.load();
        final Store interest4 = new SimpleStore(new String[] {"Text","interests"}, InterestList);
        interest4.load();
        final Store interest5 = new SimpleStore(new String[] {"Text","interests"}, InterestList);
        interest5.load();
        final Store a = new SimpleStore(new String[] {"Text","A","data"}, GetA());
        a.load();
        final Store b = new SimpleStore(new String[] {"Text","B","data"}, GetB());
        b.load();
        final Store c = new SimpleStore(new String[] {"Text","C","data"}, GetC());
        c.load();
        
        // Survey
        final Panel secondTab = new Panel();  
        secondTab.setTitle("Survey");  
//        secondTab.setLayout(new FormLayout());
        secondTab.setLayout(new VerticalLayout(0));
        secondTab.setAutoHeight(true);  
        secondTab.setPaddings(15);  
        
        // preference
        final ComboBox listpreference = new ComboBox();
        listpreference.setAllowBlank(false);
        listpreference.setMinChars(1);
        listpreference.setFieldLabel("Preference");
        listpreference.setStore(preference);
        listpreference.setDisplayField("Text");
        listpreference.setEmptyText("Select Preference"); 
        listpreference.setTriggerAction(ComboBox.ALL); //? 모르겠다
        listpreference.setTypeAhead(true);  // 이를 채우고 자동 선택한다
        listpreference.setSelectOnFocus(true); //데이터값 수신할 떄 자동채움
        listpreference.setWidth(300);  
        listpreference.setResizable(true); //?
//        secondTab.add(listpreference);
        
        Panel inputdataform = new Panel();
        inputdataform.setBorder(false);
        inputdataform.setLayout(new FormLayout());
        inputdataform.setMargins(5, 0, 10, 5);
        
        // interst checkbox
        final ComboBox listinterest1 = new ComboBox();
        final ComboBox listinterest2= new ComboBox();
        final ComboBox listinterest3 = new ComboBox();
        final ComboBox listinterest4 = new ComboBox();
        final ComboBox listinterest5 = new ComboBox();
        	
        listinterest1.setAllowBlank(false);
        listinterest1.setMinChars(1);
        listinterest1.setFieldLabel("interests");
        listinterest1.setStore(interest1);
        listinterest1.setDisplayField("Text");
        listinterest1.setEmptyText("Select Interest1"); 
        listinterest1.setTriggerAction(ComboBox.ALL); //? 모르겠다
        listinterest1.setTypeAhead(true);  // 이를 채우고 자동 선택한다
        listinterest1.setSelectOnFocus(true); //데이터값 수신할 떄 자동채움
        listinterest1.setWidth(300);  
        listinterest1.setResizable(true);
        secondTab.add(listinterest1);
        
        listinterest2.setAllowBlank(false);
        listinterest2.setMinChars(1);
        listinterest2.setFieldLabel("interests");
        listinterest2.setStore(interest1);
        listinterest2.setDisplayField("Text");
        listinterest2.setEmptyText("Select Interest2"); 
        listinterest2.setTriggerAction(ComboBox.ALL); //? 모르겠다
        listinterest2.setTypeAhead(true);  // 이를 채우고 자동 선택한다
        listinterest2.setSelectOnFocus(true); //데이터값 수신할 떄 자동채움
        listinterest2.setWidth(300);  
        listinterest2.setResizable(true);
        secondTab.add(listinterest2);
        
        listinterest3.setAllowBlank(false);
        listinterest3.setMinChars(1);
        listinterest3.setFieldLabel("interests");
        listinterest3.setStore(interest1);
        listinterest3.setDisplayField("Text");
        listinterest3.setEmptyText("Select Interest3"); 
        listinterest3.setTriggerAction(ComboBox.ALL); //? 모르겠다
        listinterest3.setTypeAhead(true);  // 이를 채우고 자동 선택한다
        listinterest3.setSelectOnFocus(true); //데이터값 수신할 떄 자동채움
        listinterest3.setWidth(300);  
        listinterest3.setResizable(true);
        secondTab.add(listinterest3);
        
        listinterest4.setAllowBlank(false);
        listinterest4.setMinChars(1);
        listinterest4.setFieldLabel("interests");
        listinterest4.setStore(interest1);
        listinterest4.setDisplayField("Text");
        listinterest4.setEmptyText("Select Interest4"); 
        listinterest4.setTriggerAction(ComboBox.ALL); //? 모르겠다
        listinterest4.setTypeAhead(true);  // 이를 채우고 자동 선택한다
        listinterest4.setSelectOnFocus(true); //데이터값 수신할 떄 자동채움
        listinterest4.setWidth(300);  
        listinterest4.setResizable(true);
        secondTab.add(listinterest4);
        
        listinterest5.setAllowBlank(false);
        listinterest5.setMinChars(1);
        listinterest5.setFieldLabel("interests");
        listinterest5.setStore(interest1);
        listinterest5.setDisplayField("Text");
        listinterest5.setEmptyText("Select Interest5"); 
        listinterest5.setTriggerAction(ComboBox.ALL); //? 모르겠다
        listinterest5.setTypeAhead(true);  // 이를 채우고 자동 선택한다
        listinterest5.setSelectOnFocus(true); //데이터값 수신할 떄 자동채움
        listinterest5.setWidth(300);  
        listinterest5.setResizable(true);
        secondTab.add(listinterest5);
        
//        final CheckBox_Interest checkbox = new CheckBox_Interest();
//        secondTab.add(checkbox);
        
        Label txtlabel = new Label("Another interest:         ");
        inputdataform.add(txtlabel, new AnchorLayoutData("40%"));
        TextBox inputinterest = new TextBox();
        
        inputdataform.add(inputinterest, new AnchorLayoutData("60%"));
        secondTab.add(inputdataform);
        
        //A
        final ComboBox A = new ComboBox();
        A.setAllowBlank(false);
        A.setMinChars(1);
        A.setFieldLabel("A");
        A.setStore(a);
        A.setDisplayField("Text");
        A.setEmptyText("Select A"); 
        A.setTriggerAction(ComboBox.ALL); //? 모르겠다
        A.setTypeAhead(true);  // 이를 채우고 자동 선택한다
        A.setSelectOnFocus(true); //데이터값 수신할 떄 자동채움
        A.setWidth(300);  
        A.setResizable(true); //?
        secondTab.add(A);
        
        //B
        final ComboBox B = new ComboBox();
        B.setAllowBlank(false);
        B.setMinChars(1);
        B.setFieldLabel("B");
        B.setStore(b);
        B.setDisplayField("Text");
        B.setEmptyText("Select B"); 
        B.setTriggerAction(ComboBox.ALL); //? 모르겠다
        B.setTypeAhead(true);  // 이를 채우고 자동 선택한다
        B.setSelectOnFocus(true); //데이터값 수신할 떄 자동채움
        B.setWidth(300);  
        B.setResizable(true); //?
        secondTab.add(B);
        
        //C
        final ComboBox C = new ComboBox();
        C.setAllowBlank(false);
        C.setMinChars(1);
        C.setFieldLabel("C");
        C.setStore(c);
        C.setDisplayField("Text");
        C.setEmptyText("Select C"); 
        C.setTriggerAction(ComboBox.ALL); //? 모르겠다
        C.setTypeAhead(true);  // 이를 채우고 자동 선택한다
        C.setSelectOnFocus(true); //데이터값 수신할 떄 자동채움
        C.setWidth(300);  
        C.setResizable(true); //?
        secondTab.add(C);

        tabPanel.add(secondTab);
        
        final Button btnok = new Button("OK", new ButtonListenerAdapter() {
        	
        	public void onClick(Button btnok, EventObject e) {
        		// membershipdata list 선언
        		membershipdata[0] = txtID.getText().trim();
        		membershipdata[1] = txtPassword.getText().trim();
        		membershipdata[2] = txtCheckPassword.getText().trim();
        		membershipdata[3] = txtName.getText().trim();
        		membershipdata[4] = listgender.getText().trim();
        		membershipdata[5] = txtemail.getText().trim();
        		membershipdata[6] = dataBirth.getText().trim();
        		membershipdata[7] = listcountry.getText().trim();
        		membershipdata[8] = listcity.getText().trim();
        		
        		// survey data list 선언
        		surveydata[0] = listinterest5.getText().trim();
        		surveydata[1] = A.getText().trim();
        		surveydata[2] = B.getText().trim();
        		surveydata[3] = C.getText().trim();
        		
        		// interest data list 선언
//        		Record[] userInterests = checkbox.getCheckbox_data();
//        		for (int i =0; i<userInterests.length; i++) {
//        			Record userinterest = userInterests[i];
//        			userinterest.getAsString("interest");
//        		} 
//        		String anoterinterest = inputinterest.getText().trim();
        		
        		// empty data 처리
//        		for(int i=0; i<(userInterests.length-1); i++) {
//        			if(userInterests[i].equals("")) {
//        				popup.setTitle("Please, input your interest data");
//        				popup.show();
//        				return;
//        			}
//        		}
        		for(int i=0; i<surveydata.length; i++) {
        			if(surveydata[i].equals("")) {
        				popup.setTitle("Please, input your survey data");
        				popup.show();
        				return;
        			}
        		}
        		for(int i=0; i<membershipdata.length; i++) {
        			if(membershipdata[i].equals("")) {
        				popup.setTitle("Please, input your membership data");
        				popup.show();
        				return;
        			}
        		}
        		//kjmembership_dataset
        		KJMember kjMember = new KJMember();
				kjMember.setID(membershipdata[0]);
				kjMember.setPassword(membershipdata[1]);
				kjMember.setCheckPassword(membershipdata[2]);
				kjMember.setName(membershipdata[3]);
				kjMember.setGender(membershipdata[4]);
				kjMember.setEmail(membershipdata[5]);
//				kjMember.setBirth(membershipdata[6]);
				kjMember.setCountry(membershipdata[7]);
				kjMember.setLocal(membershipdata[7]);
				
				//survey data set
				Survey_data survey_set = new Survey_data();
				survey_set.setID(membershipdata[0]);
				survey_set.setName(membershipdata[3]);
				survey_set.setPreference(surveydata[0]);
				survey_set.setA(surveydata[1]);
				survey_set.setB(surveydata[2]);
				survey_set.setC(surveydata[3]);
				
				//Korean survey data Statistice
				
				//Japanese survey data Statistic
				
				//서버통신
				KJMembershipServiceAsync service = GWT.create(KJMembershipService.class);
				service.Register_Membership(kjMember, new AsyncCallback<Void>() {

					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						popup.setTitle("Welcome to the membership");
        				popup.show();
						Register_Membership.this.hide();

					}
					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

						popup.setTitle("Sorry, Please try again after few minutes");
        				popup.show();
						Register_Membership.this.hide();
					}
				});
			}
		});
        // Cancel
        final Button btncancel = new Button("Cancel",new ButtonListenerAdapter() {
            public void onClick(Button btncancel, EventObject e) {  
                Register_Membership.this.hide();
            }
        });    
        formPanel.addButton(btnok);  
        formPanel.addButton(btncancel);  
  
        formPanel.add(tabPanel);  
        this.add(formPanel);  
  
        RootPanel.get().add(this);  
    }  
//    public Object[] txtinputs(Object txtCheckPassword, Object listlocal) {
//		return new Object[] {
//				new Object[] {txtID, txtPassword, txtCheckPassword, txtName, listgender, txtemail,listcountry,dataBirth,listlocal}
//				
//	};
//}
    // list object
    private Object[][] Getgender()  {
    	return new Object[][] {
    		new Object[] {"Male","gender"},
    		new Object[] {"Female","gender"}
    	};
    }
    
    private Object[][] countries = new Object[][]{  
        new Object[]{"K", "Korean"},  
        new Object[]{"J", "Japanese"},  
    };
    
    private Object[][] cities = new Object[][]{  
        new Object[]{new Integer(001), "K", "Gyeonggi-do"},  
        new Object[]{new Integer(002), "K", "Seoul"},  
        new Object[]{new Integer(003), "K", "Gangwon-do"},  
        new Object[]{new Integer(004), "K", "Chungcheongbuk-do"},  
        new Object[]{new Integer(005), "K", "Chungcheongnam-do"},  
        new Object[]{new Integer(006), "K", "Gyeongsangbuk-do"},  
        new Object[]{new Integer(007), "K", "Gyeongsangnam-do"},  
//        new Object[]{new Integer(008), "K", "Jeollabuk-do"},  
//        new Object[]{new Integer(009), "K", "Jeollanam-do "},  
        new Object[]{new Integer(0010), "J", "Hokkaido"}, 
        new Object[]{new Integer(0011), "J", "Tohoku"},
        new Object[]{new Integer(0012), "J", "Chubu"},
        new Object[]{new Integer(0013), "J", "Kinki"},
        new Object[]{new Integer(0014), "J", "Chugoku"},
        new Object[]{new Integer(0015), "J", "Shikoku"},
        new Object[]{new Integer(0016), "J", "Kyushu"}
    };
    
    private Object[][] InterestList = new Object[][]{  
    	new Object[] {"문화교류", "interests"},
        new Object[]{"음악", "interests"},  
        new Object[]{"영화", "interests"},
        new Object[]{"게임", "interests"},
        new Object[]{"운동", "interests"},
        new Object[]{"독서", "interests"},
        new Object[]{"TV", "interests"},
        new Object[]{"여행", "interests"},
        new Object[]{"요리", "interests"},
        new Object[]{"등산", "interests"},
        new Object[]{"축구", "interests"},
        new Object[] {"낚시", "interests"},
        new Object[] {"그림그리기", "interests"}
    };
    
    private Object[][] Getpreference()  {
    	return new Object[][] {
    		new Object[] {"Very Bad","preference", new Integer(-2)},
    		new Object[] {"Bad","preference", new Integer(-1)},
    		new Object[] {"So So","preference", new Integer(0)},
    		new Object[] {"Good","preference", new Integer(1)},
    		new Object[] {"Very Good","preference", new Integer(2)},
    	};
    }    
    
    private Object[][] GetA()  {
    	return new Object[][] {
    		new Object[] {"Very Bad","a", new Integer(-2)},
    		new Object[] {"Bad","a", new Integer(-1)},
    		new Object[] {"So So","a", new Integer(0)},
    		new Object[] {"Good","a", new Integer(1)},
    		new Object[] {"Very Good","a", new Integer(2)},
    	};
    }
    
    private Object[][] GetB()  {
    	return new Object[][] {
    		new Object[] {"Very Bad","b", new Integer(-2)},
    		new Object[] {"Bad","b", new Integer(-1)},
    		new Object[] {"So So","b", new Integer(0)},
    		new Object[] {"Good","b", new Integer(1)},
    		new Object[] {"Very Good","b", new Integer(2)},
    	};
    }
    
    private Object[][] GetC()  {
    	return new Object[][] {
    		new Object[] {"Very Bad","c", new Integer(-2)},
    		new Object[] {"Bad","c", new Integer(-1)},
    		new Object[] {"So So","c", new Integer(0)},
    		new Object[] {"Good","c", new Integer(1)},
    		new Object[] {"Very Good","c", new Integer(2)},
    	};
    }
}