package com.pej.web.struts.controller;

import com.opensymphony.xwork2.ActionSupport;
import com.pej.web.struts.model.Student;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StudentsAction extends ActionSupport {


    private static final long serialVersionUID = 6329394260276112660L;
    ResultSet rs = null;

    Student bean = null;
    List<Student> beanList = null;

    //Admin admin = new Admin();
    private boolean noData = false;


    public String execute() throws Exception {
        try {
            beanList = new ArrayList<>();

            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<List<Student>> studentResponse =
                    restTemplate.exchange("http://localhost:8082/user/students",
                            HttpMethod.GET, null, new ParameterizedTypeReference<List<Student>>() {
                            });
            List<Student> beanResponse = studentResponse.getBody();

            int counter=0;
            while (counter < beanResponse.size()) {
                bean = new Student();
                bean.setSname(beanResponse.get(counter).getSname());
                bean.setSemail(beanResponse.get(counter).getSemail());
                bean.setSpass(beanResponse.get(counter).getSpass());
                bean.setSdeg(beanResponse.get(counter).getSdeg());
                beanList.add(bean);
                counter++;
            }

/*
            final int[] iter = {0};
            beanResponse.forEach((Student indice) ->{
                bean = new Student();
                bean.setSname(beanResponse.get(iter[0]).getSname());
                bean.setSemail(beanResponse.get(iter[0]).getSemail());
                bean.setSpass(beanResponse.get(iter[0]).getSpass());
                bean.setSdeg(beanResponse.get(iter[0]).getSdeg());
                beanList.add(bean);
                iter[0]++;
            });
*/





            noData = true;
            //rs = admin.report();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "STUDENTS";
    }

    public boolean isNoData() {
        return noData;
    }

    public void setNoData(boolean noData) {
        this.noData = noData;
    }


    public List<Student> getBeanList() {
        return beanList;
    }

    public void setBeanList(List<Student> beanList) {
        this.beanList = beanList;
    }

}
