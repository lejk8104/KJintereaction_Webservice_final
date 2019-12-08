package kr.ac.sunmoon.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import kr.ac.sunmoon.shared.KJMember;

@RemoteServiceRelativePath("kjmembershipservice")
public interface KJMembershipService extends RemoteService {
	
	public KJMember findKJmember(String iD, String name);
	public void Register_Membership(KJMember kjMember);
	public boolean checkID(KJMember kjMember);
	public KJMember LoginService(KJMember loginmember);
	public KJMember[] findLCS(KJMember kjmember);

}
