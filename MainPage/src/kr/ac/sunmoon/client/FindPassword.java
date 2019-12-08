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
  
public class FindPassword extends Window { 
  
	private TextBox[] membershipdata;	
	
    public FindPassword() {  
    	super();
		this.setBorder(false);
		this.setPaddings(0);
		this.setClosable(true);
		this.setWidth(315);
		this.setHeight(130);
		this.setPlain(true);
		this.setCloseAction(this.HIDE);
		
        final FormPanel findpasswordform = new FormPanel();  
        findpasswordform.setFrame(true);  
        findpasswordform.setTitle("Find Password Service");  
  
        findpasswordform.setWidth(300);  
        findpasswordform.setLabelWidth(40);  
  
        // ID input
        TextField txtid = new TextField("ID", "id", 230);  
        txtid.setAllowBlank(false);  
        findpasswordform.add(txtid);  
  
        // Name input
        TextField txtname = new TextField("Name", "name", 230);  
        txtname.setAllowBlank(false);  
        findpasswordform.add(txtname); 
  
        // find password btn
        Button btnfindID = new Button("Find Password", new ButtonListenerAdapter() {
            public void onClick(Button btnfindID, EventObject e) {  
//              this.show();      //·Î±×ÀÎ
          }
      });  
        findpasswordform.addButton(btnfindID);  
        
        // Cancel btn
        Button btncancel = new Button("Cancel", new ButtonListenerAdapter() {
            public void onClick(Button btncancel, EventObject e) {  
              FindPassword.this.hide();
          }
      });  
        findpasswordform.addButton(btncancel); 

        this.add(findpasswordform);  

    }  
}  