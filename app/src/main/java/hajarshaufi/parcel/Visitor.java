package hajarshaufi.parcel;

import java.io.Serializable;

public class Visitor implements Serializable {

    private String VisitorName;
    private int PhoneNumber;
    private String CheckInDate;
    private String CheckOutDate;
    private int No_Of_Visitors;
    private String VehicleType;
    private String PlateNumber;
    private String LicenceNumber;
    private String ParkingNumber;
    private String ApproveStatus;

    public Visitor(String parkingNumber) {
    }

    public String getApproveStatus() {
        return ApproveStatus;
    }

    public Visitor(String visitorName, int phoneNumber, String checkInDate, String checkOutDate, String parkingNumber, String approveStatus) {
        VisitorName=visitorName;
        PhoneNumber=phoneNumber;
        CheckInDate=checkInDate;
        CheckOutDate=checkOutDate;
        ParkingNumber=parkingNumber;
        ApproveStatus=approveStatus;

    }

    public String getVisitorName() {
        return VisitorName;
    }

    public void setVisitorName(String visitorName) {
        VisitorName = visitorName;
    }

    public int getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getCheckInDate() {
        return CheckInDate;
    }

    public void setCheckInDate(String checkInDate) {
        CheckInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return CheckOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        CheckOutDate = checkOutDate;
    }

    public int getNo_Of_Visitors() {
        return No_Of_Visitors;
    }

    public void setNo_Of_Visitors(int no_Of_Visitors) {
        No_Of_Visitors = no_Of_Visitors;
    }

    public String getVehicleType() {
        return VehicleType;
    }

    public void setVehicleType(String vehicleType) {
        VehicleType = vehicleType;
    }

    public String getPlateNumber() {
        return PlateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        PlateNumber = plateNumber;
    }

    public String getLicenceNumber() {
        return LicenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        LicenceNumber = licenceNumber;
    }

    public String getParkingNumber() {
        return ParkingNumber;
    }

    public void setParkingNumber(String parkingNumber) {
        ParkingNumber = parkingNumber;
    }
}
