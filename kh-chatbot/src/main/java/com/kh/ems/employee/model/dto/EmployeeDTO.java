package com.kh.ems.employee.model.dto;

public class EmployeeDTO {
    private String empId;
    private String empName;
    private String email;
    private String phone;
    private String hireDate;
    private String jobName;
    private int salary;
    private String deptTitle;

    public EmployeeDTO() {
    }

    public EmployeeDTO(String empId, String empName, String email, String phone, String hireDate, String jobName,
            int salary, String deptTitle) {
        this.empId = empId;
        this.empName = empName;
        this.email = email;
        this.phone = phone;
        this.hireDate = hireDate;
        this.jobName = jobName;
        this.salary = salary;
        this.deptTitle = deptTitle;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getDeptTitle() {
        return deptTitle;
    }

    public void setDeptTitle(String deptTitle) {
        this.deptTitle = deptTitle;
    }

    @Override
    public String toString() {
        return "EmployeeDTO [empId=" + empId + ", empName=" + empName + ", email=" + email + ", phone=" + phone
                + ", hireDate=" + hireDate + ", jobName=" + jobName + ", salary=" + salary + ", deptTitle="
                + deptTitle + "]";
    }
}
