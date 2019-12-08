package kr.ac.sunmoon.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Margins;
import com.gwtext.client.core.Position;
import com.gwtext.client.core.RegionPosition;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.HTMLPanel;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.TabPanel;
import com.gwtext.client.widgets.Viewport;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListener;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.ComboBox;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.layout.AccordionLayout;
import com.gwtext.client.widgets.layout.AnchorLayoutData;
import com.gwtext.client.widgets.layout.BorderLayout;
import com.gwtext.client.widgets.layout.BorderLayoutData;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.ColumnLayoutData;
import com.gwtext.client.widgets.layout.FitLayout;
import com.gwtext.client.widgets.layout.FormLayout;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;
import com.gwtext.client.widgets.menu.Menu;
import com.gwtext.client.widgets.tree.TreeEditor;
import com.gwtext.client.widgets.tree.TreeNode;
import com.gwtext.client.widgets.tree.TreePanel;

import kr.ac.sunmoon.shared.KJMember;

public class MainPage implements EntryPoint  {

    private Menu menu;  
    private TreeNode ctxNode;  
    private TreeEditor treeEditor; 
    //이벤트 후처리
    private static KJMember kjmember;
    private static Panel accordionPanel;
    private static Panel mypagePanel;
    static Panel hpsearch;
    static Panel fourth_Column;
    private static FormPanel loginform = loginform();
    private static UserPage userpage = new UserPage();
    private static Panel thirdtab = new Panel();
    private static ChatService chatservice;
    private static CheckBox_Userimage userlist = new CheckBox_Userimage();
    static Button logbtn;
    
    private static Panel nomalPanel;
	
    public void onModuleLoad() {
		// TODO Auto-generated method stub
		Panel mainPanel = new Panel();  
        mainPanel.setBorder(false);  
        mainPanel.setPaddings(10);  
        mainPanel.setLayout(new FitLayout());  
  
        Panel borderPanel = new Panel();  
        borderPanel.setLayout(new BorderLayout());  
        
        //add north panel  
        Panel northPanel = new Panel();  
        northPanel.setEl(new HTML("<p></p>").getElement());
        northPanel.setTitle("KJ Interaction WebSite");
        northPanel.setHeight(130);  
        northPanel.setBodyStyle("background-color:#F0F8FF");  
        borderPanel.add(northPanel, new BorderLayoutData(RegionPosition.NORTH));  
        
        
        hpsearch = new Panel();
        hpsearch.setLayout(new ColumnLayout());
        hpsearch.setMargins(5, 233, 252, 5);
        
        // Logo iamage
        Panel firstColumn = new Panel();  
        firstColumn.setLayout(new FormLayout());  
        firstColumn.setBorder(false);
        firstColumn.setMargins(5, 5, 5, 5);
        
        Image imglogo = new Image();
        imglogo.setUrl("image/interactionlog.png");
        firstColumn.add(imglogo, new AnchorLayoutData("100%"));
        hpsearch.add(firstColumn, new ColumnLayoutData(0.17));
		
        //dinamic Search
        Panel secondColumn = new Panel();  
        secondColumn.setLayout(new FormLayout());  
        secondColumn.setBorder(false);
		secondColumn.setMargins(32, 5, 5, 32);
		
        ComboBox loading = new ComboBox(); 
        loading.setDisplayField("title");  
        loading.setTypeAhead(false);  
        loading.setLoadingText("Searching...");  
        loading.setWidth(320);
        loading.setPageSize(10);
        loading.setHideTrigger(true);
        loading.setMode(ComboBox.REMOTE); 
        loading.setHideLabel(true);
        
        FormPanel search = new FormPanel();
        search.setBorder(false);
        search.add(loading,new AnchorLayoutData("97%"));
        secondColumn.add(search);
        hpsearch.add(secondColumn, new ColumnLayoutData(0.5));
        
        // search logo
        Panel third_Column = new Panel();  
        third_Column.setLayout(new FormLayout());  
        third_Column.setBorder(false);
        third_Column.setMargins(30, 0, 30, 10);
        
//        Image imgsearch = new Image();
//        imgsearch.setUrl("image/search.png");
//        third_Column.add(imgsearch);
//        hpsearch.add(third_Column, new ColumnLayoutData(0.1));
        
        // login logo
        fourth_Column = new Panel();  
        fourth_Column.setLayout(new FormLayout());  
        fourth_Column.setBorder(false);
        fourth_Column.setMargins(0, 0, 0, 0);

        logbtn = new Button("Login Service", new ButtonListenerAdapter() {
        	public void onClick(Button logbtn, EventObject e) {
        		final LoginService loginwindow = new LoginService();
        		loginwindow.show(); //window 연결
        	}
        });
//        Image imglogin = new Image();
//        imglogin.setUrl("image/login.png");
//        imglogin.addClickHandler(new ClickHandler() {
			
//			@Override
//			public void onClick(ClickEvent event) {
//				// TODO Auto-generated method stub
//				LoginService dialog_log  = new LoginService();
//				dialog_log.setPopupPosition(Window.getClientWidth()/2-150,100);
//				dialog_log.show();
//			}
//		});
        fourth_Column.add(logbtn);
        hpsearch.add(fourth_Column, new ColumnLayoutData(0.3));
        
        // register logo
        Panel fiveth_Panel = new Panel();  
        fiveth_Panel.setLayout(new FormLayout());  
        fiveth_Panel.setBorder(false);
        fiveth_Panel.setMargins(7, 7, 7, 7);
        
        Image imgRegister = new Image();
        imgRegister.setUrl("image/register.png");
//        imgRegister.addClickHandler(new ClickHandler() {
//			
//			@Override
//			public void onClick(ClickEvent event) {
//				// TODO Auto-generated method stub
//				Register_Membership dialog_RE = new Register_Membership();
//				dialog_RE.setPopupPosition(Window.getClientWidth()/2-150,100);
//				dialog_RE.show();
//			}
//		});
        fiveth_Panel.add(imgRegister);
//        hpsearch.add(fiveth_Panel, new ColumnLayoutData(0.1));
        
        northPanel.add(hpsearch);
        
        //add south panel  
        Panel southPanel = new Panel();  
        southPanel.setHeight(50);  
        southPanel.setBodyStyle("background-color:#CDEB8B");  
        southPanel.setCollapsible(true);  
        southPanel.setTitle("South");  
        
        //?
        Panel southdata2 = new Panel();
        southdata2.setLayout(new ColumnLayout());
        southPanel.setBorder(false);
        
        BorderLayoutData southData = new BorderLayoutData(RegionPosition.SOUTH);  
        southData.setMinSize(50);  
        southData.setMaxSize(100);  
        southData.setMargins(new Margins(10, 10, 10, 0));  
        southData.setSplit(true);
        
        //주소록
        Panel first_Column = new Panel();  
        first_Column.setLayout(new FormLayout());  
        first_Column.setBorder(false);
        
        first_Column.add(new TextField("Address", "first"), new AnchorLayoutData("97%"));
        southdata2.add(first_Column, new ColumnLayoutData(0.5));  
        
        // 연락처
        Panel second_Column = new Panel();  
        second_Column.setLayout(new FormLayout());  
        second_Column.setBorder(false);  

        second_Column.add(new TextField("Phone Number", "last"), new AnchorLayoutData("97%"));
        southdata2.add(second_Column, new ColumnLayoutData(0.5));
        
        southPanel.add(southdata2);
        borderPanel.add(southPanel, southData);
        
        //add My page  
        Panel eastPanel = new Panel();
        eastPanel.setHtml("<p>east panel</p>");  
        eastPanel.setTitle("My Page");  
        eastPanel.setCollapsible(true);  
        eastPanel.setWidth(250);  
  
        Panel accordionPanel = createAccordionPanel();  
        accordionPanel.setHeight(220);  
        accordionPanel.setWidth(250);
        
        BorderLayoutData eastData = new BorderLayoutData(RegionPosition.EAST);  
        eastData.setSplit(true);  
        eastData.setMinSize(175);  
        eastData.setMaxSize(400);  
        eastData.setMargins(new Margins(0, 0, 5, 0));  
        eastPanel.add(accordionPanel);
        
        borderPanel.add(eastPanel, eastData);  
  
        // Main Menu
        Panel westPanel = new Panel();  
        westPanel.setHtml("<p>west panel</p>");  
        westPanel.setTitle("Main menu");  
        westPanel.setBodyStyle("background-color:#EEEEEE");  
        westPanel.setCollapsible(true);  
        westPanel.setWidth(230);  
  
        BorderLayoutData westData = new BorderLayoutData(RegionPosition.WEST);
        westData.setSplit(true);  
        westData.setMinSize(175);  
        westData.setMaxSize(400);  
        westData.setMargins(new Margins(0, 5, 0, 0));  
  
        final TreePanel treePanel = new OutlookTreePanel();
        treePanel.setMargins(2, 2, 2, 2);
        treePanel.setWidth(400);  
        treePanel.setHeight(400);
        TreeNode root = new TreeNode();
        
        westPanel.add(treePanel); 
        borderPanel.add(westPanel, westData);  
  
        Panel centerPanel = new Panel();  
        centerPanel.setBodyStyle("background-color:#C3D9FF");  
  
        TabPanel tabPanel = new TabPanel();  
        tabPanel.setPlain(true);  
        tabPanel.setActiveTab(0);  
        
        Panel secondTab = new Panel();
        secondTab.setTitle("Main Page");
        Advertise_KJinteration piechartPanel = new Advertise_KJinteration();
        secondTab.add(piechartPanel);
        
        thirdtab.setTitle("Chatting");
        Panel nomalpanel = nomalPanel();
        thirdtab.add(nomalpanel);
        //동영상 추천
        Panel fourthtab = new Panel();
        fourthtab.add(new HTML("<iframe width='560' height='315' src='https://www.youtube.com/embed/3SV36d1q740' "
        		+ "frameborder='0' allow='accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture' allowfullscreen></iframe>)"));
        fourthtab.setTitle("Advertisement and Youtube");
        //지도 표시
        Panel fifthtab = new Panel();
        fifthtab.add(new HTML("<div id='map' style='width:500px;height:400px;'></div>)"));
        fifthtab.add(new HTML("<script>\n" + 
        		"		var container = document.getElementById('map');\n" + 
        		"		var options = {\n" + 
        		"			center: new kakao.maps.LatLng(33.450701, 126.570667),\n" + 
        		"			level: 3\n" + 
        		"		};\n" + 
        		"\n" + 
        		"		var map = new kakao.maps.Map(container, options);\n" + 
        		"	</script>"));
//        drawMap();
        fifthtab.setTitle("Trip Recommend");
        
        tabPanel.add(secondTab);
        tabPanel.add(thirdtab);
        tabPanel.add(fourthtab);
        tabPanel.add(fifthtab);
        
        // Main Mathod
        centerPanel.add(tabPanel);
        HTMLPanel htmlPannel = new HTMLPanel();
//        htmlPannel.setMargins(10, 50, 53, 10);
//        htmlPannel.setHtml("<iframe width='560' height='315' src='https://www.youtube.com/embed/bkcitbJzi6c' "
//        		+ "frameborder='0' allow='accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture' allowfullscreen></iframe>");
//        centerPanel.add(htmlPannel);
        borderPanel.add(centerPanel, new BorderLayoutData(RegionPosition.CENTER));  
        mainPanel.add(borderPanel);  
        Viewport viewport = new Viewport(mainPanel);  
    } 
	
	// TreePanel
	class OutlookTreePanel extends TreePanel {  
		  
        public OutlookTreePanel() {  
  
        	// Root
            TreeNode mainService = new TreeNode("Main Service");
            mainService.setIconCls("login-icon");  
            mainService.setExpanded(true);  
            
            // Login Service
            TreeNode login = new TreeNode("Login Service");  
            login.setIconCls("login-icon");  
            login.setExpanded(true); 
            mainService.appendChild(login);
            
            TreeNode register = new TreeNode("Register");  
            register.setIconCls("register-icon");  
            login.appendChild(register);  
  
            TreeNode fidnID = new TreeNode("Find ID");  
            fidnID.setIconCls("find id-icon");  
            login.appendChild(fidnID);  
  
            TreeNode findPassword = new TreeNode("Find Password");  
            findPassword.setIconCls("findpassword-icon");  
            login.appendChild(findPassword);  
  
            // Chatting Service
            TreeNode chattingservice = new TreeNode("Chatting Service");  
            chattingservice.setIconCls("chatting-icon");  
            chattingservice.setExpanded(true);  
  
            TreeNode mypage = new TreeNode("My Page");  
            mypage.setIconCls("mypage-icon");  
            chattingservice.appendChild(mypage);  
  
            TreeNode changeinterests = new TreeNode("Change Interests");  
            changeinterests.setIconCls("changeinterests-icon");  
            chattingservice.appendChild(changeinterests);  
   
            mainService.appendChild(chattingservice);  
            
            //Recommend Service
            TreeNode recommend = new TreeNode("Recommend Service");  
            recommend.setIconCls("recommend-icon");  
            recommend.setExpanded(true);  
  
            TreeNode youtube = new TreeNode("Youtube");  
            youtube.setIconCls("youtube-icon");  
            recommend.appendChild(youtube);  
  
            TreeNode advertisement = new TreeNode("Advertisement for KJ Interaction");  
            advertisement.setIconCls("advertisement-icon");  
            recommend.appendChild(advertisement);  
   
            mainService.appendChild(recommend);
            
          //Website Service
            TreeNode websiteService = new TreeNode("Website Service");  
            websiteService.setIconCls("websiteService-icon");  
            websiteService.setExpanded(true);  
  
            TreeNode recommandTrip_Tour = new TreeNode("Recommand Trip&Tour");  
            recommandTrip_Tour.setIconCls("recommandTrip_Tour-icon");  
            websiteService.appendChild(recommandTrip_Tour);  
  
            TreeNode animation = new TreeNode("Animation korea vs japanese");  
            animation.setIconCls("animation-icon");  
            websiteService.appendChild(animation);  
   
            TreeNode sports = new TreeNode("Sports");  
            sports.setIconCls("sport-icon");  
            websiteService.appendChild(sports);  
            
            TreeNode vehicle = new TreeNode("Vehicle");  
            vehicle.setIconCls("sport-icon");  
            websiteService.appendChild(vehicle);  
            
            TreeNode qna = new TreeNode("QnA");  
            qna.setIconCls("sport-icon");  
            websiteService.appendChild(qna);  
            
            mainService.appendChild(websiteService);
  
            setIconCls("mainpage-icon");  
            setWidth(400);  
            setHeight(400);  
            setEnableDD(true);  
            setRootNode(mainService);
//            afterLogin();
        }  
        
    }  
	private Panel UserPage() {
		
		//호출용 User Panel
		Panel userpage = new Panel();
		userpage.setBorder(false);
		userpage.setLayout(new HorizontalLayout(10));
		userpage.setPaddings(0);
		userpage.setWidth(300);
		userpage.setHeight(300);
		userpage.setMargins(10, 15, 15, 10);
//		this.setButtonAlign(Position.CENTER);
		
		Panel userform = new Panel();
		userform.setBorder(false);
		userform.setLayout(new VerticalLayout(10));
		userform.setPaddings(0);
		
		Panel interestform = new Panel();
		interestform.setBorder(false);
		interestform.setPaddings(0);
		
		Image userimage = new Image("UserImage/JapaneseFemale.png");
		Label txtlabel = new Label("seiya.u77");
		GridBox_Interest userinterest = new GridBox_Interest();
		
		userform.add(userimage);
		userform.add(txtlabel);
		interestform.add(userinterest);
		userpage.add(userform);
		userpage.add(userinterest);
		return userpage;
	}
	
	// Accoredion Panel
	private Panel createAccordionPanel() {  
        Panel accordionPanel = new Panel();      //여기서 문제생김
        accordionPanel.setLayout(new AccordionLayout(true));  
  
        //mypage
        Panel mypagePanel = new Panel();
        mypagePanel.setTitle("Mypage");
        
        mypagePanel.add(loginform());
        accordionPanel.add(mypagePanel);  
  
        //user image list
        Panel selectImagePanel = new Panel("Select Image");
        
        Panel nomalPanel2 = nomalPanel2();
        selectImagePanel.add(nomalPanel2);
        Button button = new Button("Get Selected");
        accordionPanel.add(selectImagePanel);  
        
        //change another interest
        Panel ChangeInterest = new Panel("Change Interest", "<p>Change Interest</p>");  
        return accordionPanel;
	}
	
	//로그인 후처리
	public static void setKJMember(KJMember kjmember) {
		MainPage.kjmember = kjmember;
	}
	public static KJMember getKJMember() {
		return kjmember;
	}

	public static void afterLogin() {
		accordionPanel.remove(loginform);
		accordionPanel.insert(0, userpage);
		
		MainPage.chatservice();
//		thirdtab.remove(w);
		
		
		accordionPanel.insert(0, userlist);
		
	}
	public static void chatservice() {
//		thirdtab.remove(nomalPanel);
		chatservice = new ChatService();
		thirdtab.insert(0, chatservice);
	}
	// 비로그인 시 나타나는 화면 정의
	private static Panel nomalPanel() {
		final Panel panel = new Panel();
		panel.setFrame(true);
		panel.setWidth(1000);  
		panel.setHeight(500);
		panel.setHtml("<p>Our service support only KJ members. </p> <p> please input your login ID</p>");
		return panel;
	}
	private static Panel nomalPanel2() {
		final Panel panel = new Panel();
		panel.setFrame(true);
		panel.setWidth(250);  
		panel.setHeight(120);
		panel.setHtml("<p>Our service support only KJ members. </p> <p> please input your login ID</p>");
		return panel;
	}
	
	// 로그인 폼
	private static FormPanel loginform() {
		final FormPanel loginform = new FormPanel();  
        loginform.setFrame(true);
        loginform.setWidth(250);  
        loginform.setHeight(120);
        loginform.setLabelWidth(55);
        loginform.setMargins(10, 10, 10, 0);
        loginform.setButtonAlign(Position.CENTER);
		
        final Window popup = new Window();
        
     // ID input
        final TextField loginID = new TextField("ID", "id", 150);  
        loginID.setAllowBlank(false);  
        loginform.add(loginID);  
  
//        txtID.getText()
        // Password input
        final TextField loginPassword = new TextField("Password", "password", 150);  
        loginform.add(loginPassword);  
  
        //login btn
        final Button btnlogin = new Button("Login", new ButtonListenerAdapter() {
            public void onClick(Button btnlogin, EventObject e) {  
            	String[] logindata = new String[2];
            	// login data list
            	logindata[0] = loginID.getText().trim();
            	logindata[1] = loginPassword.getText().trim();
            	
        		for(int i=0; i<logindata.length; i++) { 
        			if(logindata[i].equals("")) {
        				popup.setTitle("Please, input your membership data");
        				popup.show();
        				return;
        			}
        		}
        		KJMember loginmember = new KJMember();
        		loginmember.setID(logindata[0]);
        		loginmember.setPassword(logindata[1]);
        		
        		//서버통신
        		KJMembershipServiceAsync service = GWT.create(KJMembershipService.class);
        		service.LoginService(loginmember, new AsyncCallback<KJMember>() {

					public void onSuccess(KJMember result) {
						// TODO Auto-generated method stub
						popup.setTitle("Login is Complete!");
						kjmember = result;
						popup.show();
						popup.hide();
						
					}
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						popup.setTitle("Sorry, please try again after few minutes.");
						popup.show();
						popup.hide();
					}
				});
			}
		});
        loginform.addButton(btnlogin);
		return loginform;  
	}
	//After event handling
}
 	  