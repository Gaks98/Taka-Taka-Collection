package models;

import java.util.Objects;

public class Collector {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Collector collector = (Collector) o;
        return feeCharge == collector.feeCharge &&
                customersNumber == collector.customersNumber &&
                id == collector.id &&
                Objects.equals(firmName, collector.firmName) &&
                Objects.equals(estate, collector.estate) &&
                Objects.equals(operationDay, collector.operationDay) &&
                Objects.equals(disposalMode, collector.disposalMode) &&
                Objects.equals(recyclingSite, collector.recyclingSite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firmName, estate, feeCharge, operationDay, disposalMode, recyclingSite, customersNumber, id);
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
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
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
    private int id;
}
