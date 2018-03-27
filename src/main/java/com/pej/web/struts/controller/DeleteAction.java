package com.pej.web.struts.controller;

import com.opensymphony.xwork2.ActionSupport;
import com.pej.web.struts.model.Student;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class DeleteAction extends ActionSupport {

	private static final long serialVersionUID = -146601914103585418L;
	String sname, msg;
	//Admin dao = new Admin();

	public int deleteStudentDetails(String sname) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/user/student/{sname}";
        HttpEntity<Student> requestEntity = new HttpEntity<>(headers);

//		ResponseEntity<Void> re = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, semail);
        restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, Void.class, sname);
        return 1;
    }


	@Override
	public String execute() throws Exception {
		try {
			int isDeleted = deleteStudentDetails(sname);
			if (isDeleted > 0) {
				msg = "Record deleted successfully";
			} else {
				msg = "Some error";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "DELETE";
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
