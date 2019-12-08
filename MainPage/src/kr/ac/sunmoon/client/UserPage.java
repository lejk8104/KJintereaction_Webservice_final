package kr.ac.sunmoon.client;

import com.google.gwt.user.client.ui.Image;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.Window;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.form.Label;
import com.gwtext.client.widgets.layout.ContainerLayout;
import com.gwtext.client.widgets.layout.HorizontalLayout;
import com.gwtext.client.widgets.layout.VerticalLayout;

import kr.ac.sunmoon.shared.KJMember;

public class UserPage extends Panel {
	
//	private static KJMember kjmember;
	
	public UserPage() {
		this.setBorder(false);
		this.setLayout(new HorizontalLayout(10));
		this.setPaddings(0);
		this.setWidth(300);
		this.setHeight(300);
		this.setMargins(10, 15, 15, 10);
//		this.setButtonAlign(Position.CENTER);
		
		Panel userform = new Panel();
		userform.setBorder(false);
		userform.setLayout(new VerticalLayout(10));
		userform.setPaddings(0);
		
		Panel interestform = new Panel();
		interestform.setBorder(false);
		interestform.setPaddings(0);
		
		Image userimage = new Image("UserImage/JapaneseFemale.png");
		Label txtlabel = new Label();
		txtlabel.setText("seiya.u77");
		GridBox_Interest userinterest = new GridBox_Interest();
		
		userform.add(userimage);
		userform.add(txtlabel);
		interestform.add(userinterest);
		this.add(userform);
		this.add(userinterest);
	}

}
