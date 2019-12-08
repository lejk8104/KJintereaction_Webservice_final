package kr.ac.sunmoon.client;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;

import kr.ac.sunmoon.shared.KJMember;

public interface KJMembershipServiceAsync {

	void findKJmember(String iD, String name, AsyncCallback<KJMember> asyncCallback);
	void Register_Membership(KJMember kjMember, AsyncCallback<Void> callback);
	
	void checkID(KJMember kjMember, AsyncCallback<Boolean> asyncCallback);
	void LoginService(KJMember loginmember, AsyncCallback<KJMember> asyncCallback);
	void findLCS(KJMember kjmember, AsyncCallback<KJMember[]> callback);



	
}
