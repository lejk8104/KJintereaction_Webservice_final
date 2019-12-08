package kr.ac.sunmoon.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.layout.HorizontalLayout;

import kr.ac.sunmoon.shared.KJMember;

public class ChatService extends Panel {
	private Label lb2;
	static KJMember kjmember;

	public ChatService() {
		
		super();
		this.setBorder(false);
		this.setPaddings(0);
//		this.setClosable(true);
		this.setWidth(835);
//		this.setBodyStyle("background-color:#CDEB8B");  
		this.setHeight(850);
		this.setMargins(100, 150, 30, 100);
//		this.setPlain(true);
//		this.setCloseAction(this.HIDE);
		
		final FormPanel chatform = new FormPanel();  
        chatform.setFrame(true);
        chatform.setLayout(new HorizontalLayout(0));
        chatform.setTitle("Recommendation Service");
        chatform.setWidth(755);
        chatform.setHeight(270);
        chatform.setButtonAlign(Position.CENTER);
        chatform.setMargins(0, 0, 0, 0);
        
        //User
        Panel user1Panel = new Panel();
        user1Panel.setBorder(false);
        user1Panel.setPaddings(0);
        UserPage userpage = new UserPage();
        user1Panel.add(userpage);
        
      //matching finished
        Panel middlePanel = new Panel();
        middlePanel.setBorder(false);
        middlePanel.setPaddings(0);
        Label lbl = new Label("Recommended Pair!");
        middlePanel.add(lbl);
        
      //another user
        Panel user2Panel = new Panel();
        user2Panel.setBorder(false);
        user2Panel.setPaddings(0);
        
        UserPage userpage2 = new UserPage();
        user2Panel.add(userpage2);
        
        
        chatform.add(user1Panel);
        chatform.add(middlePanel);
        chatform.add(user2Panel);
		
        this.kjMatching();
        
//		KJMembershipServiceAsync service = GWT.create(KJMembershipService.class);
//		service.findLCS("lejk8104", new AsyncCallback<String>() {
//			
//			@Override
//			public void onSuccess(String result) {
//				com.google.gwt.user.client.Window.alert(result);
//				lb2.setText(result);
//			}
//			
//			@Override
//			public void onFailure(Throwable caught) {
//				com.google.gwt.user.client.Window.alert("failed with matching");
//			}
//		});
		
		Button startBtn = new Button("chatting start", new ButtonListenerAdapter() {
			public void onClick(Button startBtn, EventObject e) {
				ChatRoom chatRoom = new ChatRoom();
				chatRoom.show();
			}
		});
		chatform.addButton(startBtn);
		
		Button againBtn = new Button("matching again", new ButtonListenerAdapter() {
			public void onClick(Button againBtn, EventObject e) {
				kjMatching();
			}
		});
		chatform.addButton(againBtn);
		
		Button cancelBtn = new Button("cancel");
		chatform.addButton(cancelBtn);
		
        this.add(chatform);
	}        
    private void kjMatching() {
    	KJMembershipServiceAsync service = GWT.create(KJMembershipService.class);
		service.findLCS(MainPage.getKJMember(), new AsyncCallback<KJMember[]>() {
			
			@Override
			public void onSuccess(KJMember[] result) { // you can get ID and 5 interests
				com.google.gwt.user.client.Window.alert(result[0].getInterests()[0] +", " + result[1].getInterests()[0]);
				kjmember = result[0];
			}
			
			@Override
			public void onFailure(Throwable caught) {
				com.google.gwt.user.client.Window.alert("failed with matching");
			}
		});
    }
}
