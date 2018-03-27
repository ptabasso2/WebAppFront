package com.pej.web.struts.controller;

import com.opensymphony.xwork2.ActionSupport;

import com.pej.web.struts.model.Student;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class UpdateAction extends ActionSupport {

	private static final long serialVersionUID = -1905974197186620917L;
	private String sname = "", semail = "", spass = "", sdeg = "", semailhidden = "";
	private String msg = "";
	private HttpStatus errCode;
	//ResultSet rs = null;

	String submitType;

	/*@Override
	public String execute() throws Exception {
		try {
			if (submitType.equals("updatedata")) {
				rs = dao.fetchUserDetails(uemail.trim());
				if (rs != null) {
					while (rs.next()) {
						uname = rs.getString("UNAME");
						uemail = rs.getString("UEMAIL");
						upass = rs.getString("UPASS");
						udeg = rs.getString("UDEG");
					}
				}
			} else {
				int i = dao.updateUserDetails(uname, uemail, upass, udeg, uemailhidden);
				if (i > 0) {
					msg = "Record Updated Successfuly";
				} else {
					msg = "error";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "UPDATE";
	}*/



    @Override
    public String execute() throws Exception {

        try {
            if (submitType.equals("updatedata")) {


                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    RestTemplate restTemplate = new RestTemplate();
                    String url = "http://localhost:8082/user/student/{sname}";
                    HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
                    ResponseEntity<Student> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Student.class, sname);
                    Student student = responseEntity.getBody();


                    sname = student.getSname();
                    semail = student.getSemail();
                    spass = student.getSpass();
                    sdeg = student.getSdeg();

            } else {

                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    RestTemplate restTemplate = new RestTemplate();
                    String urlForUpdate = "http://localhost:8082/user/student";
                    Student student = new Student();

                    student.setSname(sname);
                    student.setSemail(semail);
                    student.setSpass(spass);
                    student.setSdeg(sdeg);

                    HttpEntity<Student> requestEntityPut = new HttpEntity<Student>(student, headers);
                    restTemplate.put(urlForUpdate, requestEntityPut);

                /*if (errCode == HttpStatus.OK) {
                    msg = "Record Updated Successfuly";
                } else {
                    msg = "error";
                }*/
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "UPDATE";
    }



	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
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

	public String getSemail() {
		return semail;
	}

	public void setSemail(String semail) {
		this.semail = semail;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getSemailhidden() {
		return semailhidden;
	}

	public void setSemailhidden(String semailhidden) {
		this.semailhidden = semailhidden;
	}

	public String getSubmitType() {
		return submitType;
	}

	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}

}
