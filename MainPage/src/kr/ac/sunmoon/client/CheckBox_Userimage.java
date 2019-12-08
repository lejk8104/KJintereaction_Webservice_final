package kr.ac.sunmoon.client;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtext.client.core.EventObject;
import com.gwtext.client.core.Position;
import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.Button;
import com.gwtext.client.widgets.Panel;
import com.gwtext.client.widgets.event.ButtonListenerAdapter;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;
import com.gwtext.client.widgets.layout.VerticalLayout;

import kr.ac.sunmoon.shared.KJMember;

public class CheckBox_Userimage extends GridPanel {

	
	public CheckBox_Userimage() {
		
		super();
		this.setBorder(false);
		this.setPaddings(5);
		this.setButtonAlign(Position.CENTER);
//		this.setLayout(new VerticalLayout(25));
		
		//Gird panel ����
		// data setting 1: store ����
        Store loginStore = new Store(proxy, reader);  
        loginStore.load();  
        this.setStore(loginStore);  
  
        // data setting 2 : grid�� �� colunm ����
        ColumnModel columnModel = new ColumnModel(columns);  
        this.setColumnModel(columnModel);  
  
        this.setFrame(true);  
        this.setStripeRows(true);  
        this.setAutoExpandColumn("image");  
  
        this.setSelectionModel(CheckboxUser);
        this.setWidth(200);  
        this.setHeight(250);  
        this.setFrame(true);  
        this.setIconCls("grid-icon");
        
        this.setStripeRows(true); 
        this.setCollapsible(true);  
        this.setAnimCollapse(false);
	}
	// üũ�ڽ� �����
	final CheckboxSelectionModel CheckboxUser = new CheckboxSelectionModel();  

	//Userimage object
    private Object[][] UserImageList() {  
        return new Object[][]{ 
        	new Object[]{new Image("UserImage/Nomal.png")},
                new Object[]{new Image("UserImage/JapaneseFemale.png")},  
                new Object[]{new Image("UserImage/JapaneseFemale2.png")},  
                new Object[]{new Image("UserImage/JapaneseMale.png")},  
                new Object[]{new Image("UserImage/JapaneseMale2.png")},  
                new Object[]{new Image("UserImage/KoreanFemale.png")},
                new Object[]{new Image("UserImage/KoreanFemale2.png")},
                new Object[]{new Image("UserImage/KoreanMale.png")},
                new Object[]{new Image("UserImage/KoreanMale2.png")}
        };  
    }
    // userimage list read
    RecordDef recordDef = new RecordDef(  
            new FieldDef[]{  
                    new StringFieldDef("image")
            }  
    );
    final Object[][] imagelist = UserImageList();
    
    final MemoryProxy proxy = new MemoryProxy(imagelist);
    final ArrayReader reader = new ArrayReader(recordDef); 
    
    //input grid columns
    final BaseColumnConfig[] columns = new BaseColumnConfig[]{  
            new CheckboxColumnConfig(CheckboxUser),  
            //column ID is company which is later used in setAutoExpandColumn  
            new ColumnConfig("Check to only One Image ", "image", 200, true, null, "image"),  
    };  
}