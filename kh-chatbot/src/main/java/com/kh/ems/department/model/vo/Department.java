package com.kh.ems.department.model.vo;

public class Department {
    private String deptId;
    private String deptTitle;
    private String locationId;

    public Department() {
    }

    public Department(String deptId, String deptTitle, String locationId) {
        this.deptId = deptId;
        this.deptTitle = deptTitle;
        this.locationId = locationId;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptTitle() {
        return deptTitle;
    }

    public void setDeptTitle(String deptTitle) {
        this.deptTitle = deptTitle;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    @Override
    public String toString() {
        return "Department [deptId=" + deptId + ", deptTitle=" + deptTitle + ", locationId=" + locationId + "]";
    }
}
