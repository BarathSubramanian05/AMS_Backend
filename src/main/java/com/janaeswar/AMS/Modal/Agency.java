package com.janaeswar.AMS.Modal;

import com.janaeswar.AMS.Service.AgencyService;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Agency {
     @Id
     private String agencyId;
     @Indexed(unique = true)
     private String agencyName;
     private String agencyType;
     private String contactPersonName;
     private Long phoneNumber;
     private String email;
     private String address;
     private int pinCode;
     private boolean status;

    public Agency() {
    }

    public String getAgencyType() {
        return agencyType;
    }

    public void setAgencyType(String agencyType) {
        this.agencyType = agencyType;
    }

    public String getAgencyId() {
          return agencyId;
     }

     public void setAgencyId(String agencyId) {
          this.agencyId = agencyId;
     }

     public String getAgencyName() {
          return agencyName;
     }

     public void setAgencyName(String agencyName) {
          this.agencyName = agencyName;
     }

     public String getContactPersonName() {
          return contactPersonName;
     }

     public void setContactPersonName(String contactPersonName) {
          this.contactPersonName = contactPersonName;
     }

     public Long getPhoneNumber() {
          return phoneNumber;
     }

     public void setPhoneNumber(Long phoneNumber) {
          this.phoneNumber = phoneNumber;
     }

     public String getEmail() {
          return email;
     }

     public void setEmail(String email) {
          this.email = email;
     }

     public String getAddress() {
          return address;
     }

     public void setAddress(String address) {
          this.address = address;
     }

     public int getPinCode() {
          return pinCode;
     }

     public void setPinCode(int pinCode) {
          this.pinCode = pinCode;
     }

     public boolean isStatus() {
          return status;
     }

     public void setStatus(boolean status) {
          this.status = status;
     }
}
