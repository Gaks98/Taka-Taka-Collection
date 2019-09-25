package models;

import java.util.Objects;

public class Collector {
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;
        Collector collector = (Collector) object;
        return feeCharge == collector.feeCharge &&
                customersNumber == collector.customersNumber &&
                java.util.Objects.equals(firmName, collector.firmName) &&
                java.util.Objects.equals(estate, collector.estate) &&
                java.util.Objects.equals(operationDay, collector.operationDay) &&
                java.util.Objects.equals(disposalMode, collector.disposalMode) &&
                java.util.Objects.equals(recyclingSite, collector.recyclingSite);
    }

    public int hashCode() {
        return Objects.hash(super.hashCode(), firmName, estate, feeCharge, operationDay, disposalMode, recyclingSite, customersNumber);
    }

    public String getFirmName() {
        return firmName;
    }

    public void setFirmName(String firmName) {
        this.firmName = firmName;
    }

    public String getEstate() {
        return estate;
    }

    public void setEstate(String estate) {
        this.estate = estate;
    }

    public int getFeeCharge() {
        return feeCharge;
    }

    public void setFeeCharge(int feeCharge) {
        this.feeCharge = feeCharge;
    }

    public String getOperationDay() {
        return operationDay;
    }

    public void setOperationDay(String operationDay) {
        this.operationDay = operationDay;
    }

    public String getDisposalMode() {
        return disposalMode;
    }

    public void setDisposalMode(String disposalMode) {
        this.disposalMode = disposalMode;
    }

    public String getRecyclingSite() {
        return recyclingSite;
    }

    public void setRecyclingSite(String recyclingSite) {
        this.recyclingSite = recyclingSite;
    }

    public int getCustomersNumber() {
        return customersNumber;
    }

    public void setCustomersNumber(int customersNumber) {
        this.customersNumber = customersNumber;
    }

    public Collector(String firmName, String estate, int feeCharge, String operationDay, String disposalMode, String recyclingSite, int customersNumber) {
        this.firmName = firmName;
        this.estate = estate;
        this.feeCharge = feeCharge;
        this.operationDay = operationDay;
        this.disposalMode = disposalMode;
        this.recyclingSite = recyclingSite;
        this.customersNumber = customersNumber;
    }

    private String firmName;
    private String estate;
    private int feeCharge;
    private String operationDay;
    private String disposalMode;
    private String recyclingSite;
    private int customersNumber;

}
