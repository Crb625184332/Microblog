package action;

import java.util.regex.Pattern;
import model.*;
import dao.*;
import com.opensymphony.xwork2.ActionSupport;

public class Register extends ActionSupport {

	private String newUser;
	private String newPsw;

	public String getNewUser() {
		return newUser;
	}

	public void setNewUser(String newUser) {
		this.newUser = newUser;
	}

	public String getNewPsw() {
		return newPsw;
	}

	public void setNewPsw(String newPsw) {
		this.newPsw = newPsw;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		if (newUser.length() < 2 || newUser.length() > 20) {
			addFieldError("newUser", "�û������ȱ�����2~20��Χ��");
		}
		if (!Pattern.matches("\\w{6, 20}", newPsw)) {
			addFieldError("newPsw", "�����������ĸ�����ֵ�����ҳ�����6~20��Χ��");
		}
		
		if (hasErrors()) {
			return "error";
		}
		
		User user = new User();
		user.setUserName(this.newUser);
		user.setUserPsw(this.newPsw);
		boolean flag = UserDao.checkRegister(user.getUserName(), user.getUserPsw());
		if (flag) {
			return "T";
		} else {
			return "F";
		}
	}
	
}
