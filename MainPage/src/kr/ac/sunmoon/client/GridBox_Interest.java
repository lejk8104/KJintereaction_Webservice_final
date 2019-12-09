package kr.ac.sunmoon.client;

import com.gwtext.client.data.ArrayReader;
import com.gwtext.client.data.FieldDef;
import com.gwtext.client.data.MemoryProxy;
import com.gwtext.client.data.Record;
import com.gwtext.client.data.RecordDef;
import com.gwtext.client.data.Store;
import com.gwtext.client.data.StringFieldDef;
import com.gwtext.client.widgets.grid.BaseColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxColumnConfig;
import com.gwtext.client.widgets.grid.CheckboxSelectionModel;
import com.gwtext.client.widgets.grid.ColumnConfig;
import com.gwtext.client.widgets.grid.ColumnModel;
import com.gwtext.client.widgets.grid.GridPanel;

import kr.ac.sunmoon.shared.KJMember; 

public class GridBox_Interest extends GridPanel  {
		
	private static KJMember kjmember;
//	private String id = kjmember.getID();

	// Main method
	public GridBox_Interest() {
		super();	
		
		kjmember = ChatService.kjmember;
//		String[] interests = kjmember.getInterests();// not working
		
		// data setting 1: store ����
        final Store interestStore = new Store(proxy, reader);  
        interestStore.load(); 
		this.setStore(interestStore);
		
		// data setting 2 : grid�� �� colunm ����
		ColumnModel columnModel = new ColumnModel(columns);
		this.setColumnModel(columnModel);
		
		this.setFrame(true);
		this.setStripeRows(true);
		this.setAutoExpandColumn("interest");  
		
//		this.setSelectionModel(CheckboxInterest);
		this.setWidth(140);
		this.setHeight(150);
        this.setFrame(true);
	}
	
	final public Record[] getCheckbox_data() {
		Record[] userInterests = CheckboxInterest.getSelections();
		return userInterests;
	}
	
	// üũ�ڽ� �����
	final CheckboxSelectionModel CheckboxInterest = new CheckboxSelectionModel();  
	
	// ������ üũ�ڽ�
	private Object[][] InterestList(){
        return new Object[][]{  
                new Object[]{"kjmember","interest"},  
                new Object[]{"chatting","interest"},  
                new Object[]{"fencing","interest"},  
                new Object[]{"trip","interest"},  
                new Object[]{"movie","interest"},    
        };  
    }  
	//kjmathod : is not working;;;
    private String getid() {
    	String id = kjmember.getID();
		return id;  //not working
    }
    private static String[] getinterest() {
    	
    	String[] interestlist = kjmember.getInterests();
		return interestlist;  //not working
	}
	
	// prefer list read
    RecordDef recordDef = new RecordDef(  
            new FieldDef[]{  
                    new StringFieldDef("interest"),  
            }  
    );
    
    //Db�� ���� ����� preferlist object ����
    final Object[][] interestlist = InterestList();
    
    final MemoryProxy proxy = new MemoryProxy(interestlist);
    final ArrayReader reader = new ArrayReader(recordDef); 
    String a = "seiya.u77";
    String b = "'s interest";
    //input grid columns
    ColumnConfig[] columns = new ColumnConfig[]{  
            new ColumnConfig(a+b, "interest", 160, true, null, "interest"),  
    };  
}