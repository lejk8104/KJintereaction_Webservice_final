package kr.ac.sunmoon.client;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.TimeZone;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.form.TextArea;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.layout.AnchorLayoutData;
import com.gwtext.client.widgets.layout.ColumnLayout;
import com.gwtext.client.widgets.layout.FormLayout;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;

import kr.ac.sunmoon.shared.KJMember;

public class ChatRoom extends Window{
	
	private static KJMember kjmember;
	private static String id = kjmember.getID();
	private static String name = kjmember.getName();
	private static String text;
	final private TextArea textArea;
	final private Date date = null;
	
	public ChatRoom() {
		super();
		
		this.setBorder(false);
		this.setPaddings(0);
		this.setClosable(true);
		this.setWidth(700);
		this.setHeight(400);
		this.setPlain(true);
		this.setCloseAction(this.HIDE);
		
		
		final FormPanel chatroomform = new FormPanel();  
        chatroomform.setFrame(true);
        chatroomform.setTitle("Chatting Page");
        chatroomform.setWidth(700);
        chatroomform.setLabelWidth(55);
        chatroomform.setButtonAlign(Position.CENTER);
        chatroomform.setMargins(0, 15, 0, 0);
        chatroomform.setLayout(new ColumnLayout());
        
        Panel overallPanel = new Panel();
        overallPanel.setBorder(false);
//        overallPanel.setPaddings(15);
        overallPanel.setLayout(new HorizontalLayout(15));
        chatroomform.add(overallPanel);
        
        //user1
        Panel user1Panel = new Panel();
        user1Panel.setLayout(new VerticalLayout(15));  
        user1Panel.setBorder(false);
        
        Image imglogo1 = new Image();
		imglogo1.setUrl("UserImage/JapaneseFemale.png");
		user1Panel.add(imglogo1, new AnchorLayoutData("97%"));
		
		overallPanel.add(user1Panel);  
		Label lb1 = new Label("seiya.u77 ");
		lb1.setPosition(50, 10);
		user1Panel.add(lb1, new AnchorLayoutData("97%"));
		
		// ä�÷�
        Panel chatroomPanel = new Panel();  
        chatroomPanel.setLayout(new VerticalLayout(15));
        chatroomform.setMargins(0, 0, 0, 0);
        chatroomPanel.setBorder(false);
        
        //�޼��� �ڽ�
//        TextField messagebox = new TextField();
//        messagebox.setWidth("300");
        final TextArea sendArea = new TextArea();
        sendArea.setWidth(250);
        sendArea.setHeight(50);
        
        chatroomPanel.setHtml("<p>Send your message</p>");
        
        chatroomPanel.add(sendArea, new AnchorLayoutData("97%"));
        
        final Button sendBtn = new Button("Send", new ButtonListenerAdapter() {
        	public void onClick(Button sendBtn, EventObject e) {
        		DateTimeFormat dtf = DateTimeFormat.getFormat("yyyyMMddHHmmss");
        		textArea.setValue(dtf.format(date, TimeZone.createTimeZone(0)));
        		textArea.setValue(sendArea.getText());    		
        	}
        }); 
        
        chatroomPanel.add(sendBtn);
        overallPanel.add(chatroomPanel);
        
        //���� ��ȭâ
        Label messagelabel = new Label("current message");
        chatroomPanel.add(messagelabel);
        
        textArea = new TextArea();
        textArea.setWidth(300);
        textArea.setHeight(150);
        textArea.setReadOnly(true);
        
        chatroomPanel.add(new ScrollPanel(textArea));
        
//        TextArea textArea = new TextArea("currentMessage", "subject");
//        textArea.setValue(id+name+text);
//        HTML html = new HTML("<hr>");
////        textArea.setValue("message : ");
//        textArea.setWidth(300);
//        textArea.setHeight(25);
//        textArea.setHideLabel(true);  
//        chatroomPanel.add(textArea, new AnchorLayoutData("100% -53")); 
//        
//        TextArea textArea2 = new TextArea("currentMessage", "subject");
////        textArea2.setValue(id+name+text);
//        textArea.setValue("message : ");
//        textArea2.setWidth(300);
//        textArea2.setHeight(25);
//        textArea2.setHideLabel(true);  
//        chatroomPanel.add(textArea2, new AnchorLayoutData("100% -53")); 
//        
//        TextArea textArea3 = new TextArea("currentMessage", "subject");
////      textArea2.setValue(id+name+text);
//        textArea.setValue("message : ");
//        textArea3.setWidth(300);
//        textArea3.setHeight(25);
//        textArea3.setHideLabel(true);  
//        chatroomPanel.add(textArea3, new AnchorLayoutData("100% -53")); 
//      
//        TextArea textArea4 = new TextArea("currentMessage", "subject");
////    textArea2.setValue(id+name+text);
//        textArea.setValue("message : ");
//        textArea4.setWidth(300);
//        textArea4.setHeight(25);
//        textArea4.setHideLabel(true);  
//        chatroomPanel.add(textArea4, new AnchorLayoutData("100% -53")); 
        
        //user1
        Panel user1Panel2 = new Panel();
        user1Panel2.setLayout(new VerticalLayout(15));  
        user1Panel2.setBorder(false);
        
        Image imglogo2 = new Image();
		imglogo2.setUrl("image/login.png");
		user1Panel2.add(imglogo2, new AnchorLayoutData("97%"));
		Label lb12 = new Label("jaekeunlee ");
		user1Panel2.add(lb12, new AnchorLayoutData("97%"));
		
        chatroomform.add(user1Panel2);
        
        //interests panel
        GridBox_Interest user1interest = new GridBox_Interest();
        
        Panel interest_user2 = new Panel();  
        interest_user2.setLayout(new FormLayout());  
        interest_user2.setBorder(false);
        user1Panel.add(user1interest);
        
        //interest_user2
        final TextField interest_1 = new TextField("Interest1", "data", 75);  
        interest_1.setAllowBlank(false);  
        interest_1.setValue("kjinteraction"); 
//        interest_user2.add(interest_1);
        
        final TextField interest_2 = new TextField("Interest2", "data", 75);  
        interest_2.setAllowBlank(false);  
        interest_2.setValue("chatting"); 
        interest_user2.add(interest_2);
        
        final TextField interest_3 = new TextField("Interest3", "data", 75);  
        interest_3.setAllowBlank(false);  
        interest_3.setValue("trip"); 
        interest_user2.add(interest_3);
        
        final TextField interest_4 = new TextField("Interest4", "data", 75);  
        interest_4.setAllowBlank(false);  
        interest_4.setValue("tennis"); 
        interest_user2.add(interest_4);
        
        final TextField interest_5 = new TextField("Interest5", "data", 75);  
        interest_5.setAllowBlank(false);  
        interest_5.setValue("reading"); 
        interest_user2.add(interest_5);
        
        user1Panel2.add(interest_user2);
        
        this.add(chatroomform);
	}
}
