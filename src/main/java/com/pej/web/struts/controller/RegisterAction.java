package com.pej.web.struts.controller;

import com.opensymphony.xwork2.ActionSupport;
import com.pej.web.struts.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RegisterAction extends ActionSupport {

	private static final long serialVersionUID = 2139116285823340125L;
	private String sname, semail, spass, sdeg;
	private String msg = "";
//	Admin admin = null;
	int ctr = 0;

	@Override
	public String execute() throws Exception {
		//admin = new Admin();

		try {
			ctr = addStudent(sname, semail, spass, sdeg);
			if (ctr > 0) {
				msg = "Registration Successfull";
			} else {
				msg = "Some error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "REGISTER";
	}


	public static int addStudent(String sname, String semail, String spass, String sdeg) {

		RestTemplate restTemplate = new RestTemplate();
		String url = "http://localhost:8082/user/student";
		Student student = new Student();

		student.setSname(sname);
		student.setSemail(semail);
		student.setSpass(spass);
		student.setSdeg(sdeg);

		ResponseEntity<Student> entity= restTemplate.postForEntity(url, student, Student.class);
		return 1;
	}



	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSemail() {
		return semail;
	}

	public void setSemail(String semail) {
		this.semail = semail;
	}

	public String getSpass() {
		return spass;
	}

	public void setSpass(String spass) {
		this.spass = spass;
	}

	public String getSdeg() {
		return sdeg;
	}

	public void setSdeg(String sdeg) {
		this.sdeg = sdeg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCtr() {
		return ctr;
	}

	public void setCtr(int ctr) {
		this.ctr = ctr;
	}

}
