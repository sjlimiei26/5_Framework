package com.kh.ems.department.model.dto;

public class DepartmentDTO {
    private String deptId;
    private String deptTitle;
    private String locationName;

    public DepartmentDTO() {
    }

    public DepartmentDTO(String deptId, String deptTitle, String locationName) {
        this.deptId = deptId;
        this.deptTitle = deptTitle;
        this.locationName = locationName;
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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    @Override
    public String toString() {
        return "DepartmentDTO [deptId=" + deptId + ", deptTitle=" + deptTitle + ", locationName=" + locationName
                + "]";
    }
}
