package hajarshaufi.parcel;

public class Parcel {

    private String parcelID;
    private String collectorName;
    private String parcelUnit;
    private String expressBrand;
    private String trackingNumber;
    private String deliveredDate;
    private String collectStatus;
    private String collectedDate;

    public String getParcelID() {
        return parcelID;
    }

    public void setParcelID(String parcelID) {
        this.parcelID = parcelID;
    }

    public String getCollectorName() {
        return collectorName;
    }

    public void setCollectorName(String collectorName) {
        this.collectorName = collectorName;
    }

    public String getParcelUnit() {
        return parcelUnit;
    }

    public void setParcelUnit(String parcelUnit) {
        this.parcelUnit = parcelUnit;
    }

    public String getExpressBrand() {
        return expressBrand;
    }

    public void setExpressBrand(String expressBrand) {
        this.expressBrand = expressBrand;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getDeliveredDate() {
        return deliveredDate;
    }

    public void setDeliveredDate(String deliveredDate) {
        this.deliveredDate = deliveredDate;
    }

    public String getCollectStatus() {
        return collectStatus;
    }

    public void setCollectStatus(String collectStatus) {
        this.collectStatus = collectStatus;
    }

    public String getCollectedDate() {
        return collectedDate;
    }

    public void setCollectedDate(String collectedDate) {
        this.collectedDate = collectedDate;
    }

    public Parcel(String parcelID, String collectorName, String parcelUnit,
                  String expressBrand, String trackingNumber, String deliveredDate,
                  String collectStatus, String collectedDate) {
        this.parcelID = parcelID;
        this.collectorName = collectorName;
        this.parcelUnit = parcelUnit;
        this.expressBrand = expressBrand;
        this.trackingNumber = trackingNumber;
        this.deliveredDate = deliveredDate;
        this.collectStatus = collectStatus;
        this.collectedDate = collectedDate;
    }
}
