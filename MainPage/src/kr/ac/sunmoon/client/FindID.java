package kr.ac.sunmoon.client;

import com.google.gwt.core.client.EntryPoint;import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.FormPanel;
import com.gwtext.client.widgets.form.TextField;
import com.gwtext.client.widgets.layout.ColumnLayout;  
  
public class FindID extends Window {  
  
	private TextBox[] membershipdata;	
	
    public FindID () {  
    	super();
		this.setBorder(false);
		this.setPaddings(0);
		this.setClosable(true);
		this.setWidth(315);
		this.setHeight(130);
		this.setPlain(true);
		this.setCloseAction(this.HIDE);
  
        final FormPanel findidform = new FormPanel();  
        findidform.setFrame(true);  
        findidform.setTitle("Find ID Service");  
  
        findidform.setWidth(300);  
        findidform.setLabelWidth(40);  
  
        // name input
        TextField txtname = new TextField("Name", "name", 230);  
        txtname.setAllowBlank(false);  
        findidform.add(txtname);  
  
        // Email input
        TextField txtemail = new TextField("Email", "email", 230);  
//        이메일텍스트 확인
        txtemail.setAllowBlank(false);  
        findidform.add(txtemail); 
  
        // find id btn
        Button btnfindid = new Button("Find ID", new ButtonListenerAdapter() {
    		public void onClick(Button btnfindid, EventObject e) {
//			window.show(); //window 연결 버튼 실제 동작과정
    			}
    		});  
        findidform.addButton(btnfindid); 
        // Cancel btn
        Button btncancel = new Button("Cancel", new ButtonListenerAdapter() {
    		public void onClick(Button btncancel, EventObject e) {
    			FindID.this.hide();
    		}
    		});   
        findidform.addButton(btncancel); 

        this.add(findidform);  

        RootPanel.get().add(this);
    }  
}  