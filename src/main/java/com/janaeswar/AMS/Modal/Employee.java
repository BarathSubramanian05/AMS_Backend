    package com.janaeswar.AMS.Modal;

    import org.springframework.data.annotation.Id;
    import org.springframework.data.mongodb.core.mapping.Document;

    @Document
    public class Employee {
        @Id
        private String employeeId;
        private String name;
        private char gender;
        private Long phoneNumber;
        private String address;
        private String role;
        private double salary;
        private String agencyId;
        private boolean isActive;
        private String startTime;
        private String endTime;
        private String profileImageId;

        // Getters and setters

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }
        public String getProfileImageId() {
            return profileImageId;
        }

        public void setProfileImageId(String profileImageId) {
            this.profileImageId = profileImageId;
        }
        public Employee() {
            this.isActive = true;
        }

        public boolean getisActive() {
            return isActive;
        }

        public void setisActive(boolean isActive) {
            this.isActive = isActive;
        }

        public String getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public char getGender() {
            return gender;
        }

        public void setGender(char gender) {
            this.gender = gender;
        }

        public Long getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(Long phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }

        public String getagencyId() {
            return agencyId;
        }

        public void setAgencyId(String agencyId) {
            this.agencyId = agencyId;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "employeeId='" + employeeId + '\'' +
                    ", name='" + name + '\'' +
                    ", gender=" + gender +
                    ", phoneNumber=" + phoneNumber +
                    ", address='" + address + '\'' +
                    ", role='" + role + '\'' +
                    ", salary=" + salary +
                    ", agencyId='" + agencyId + '\'' +
                    ", startTime='" + startTime + '\'' +
                    ", endTime='" + endTime + '\'' +
                    '}';
        }

    }
