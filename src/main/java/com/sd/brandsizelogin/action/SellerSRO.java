package com.sd.brandsizelogin.action;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SellerSRO implements Serializable {

	private static final long serialVersionUID = -1508872162711995469L;

	private String name;

	private String email;

	private String sellerCode;

	private boolean enabled;

	private boolean inventoryAuto;

	private boolean inventoryMOUAccepted;

	private boolean pricingAuto;

	private boolean pricingMOUAccepted;

	private Date created;

	private float sellerScore;

	private Float overallRating;

	private String displayName;

	private boolean sellExistingProductAuto;

	private boolean expressDispatch;

	private boolean noFrills;

	private Float outOfStockRating;

	private Float fastShipRating;

	private Float slaBreachRating;

	private Float complaintRating;

	private boolean mlpCapable;

	public SellerSRO() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSellerCode() {
		return sellerCode;
	}

	public void setSellerCode(String sellerCode) {
		this.sellerCode = sellerCode;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isInventoryAuto() {
		return inventoryAuto;
	}

	public void setInventoryAuto(boolean inventoryAuto) {
		this.inventoryAuto = inventoryAuto;
	}

	public boolean isInventoryMOUAccepted() {
		return inventoryMOUAccepted;
	}

	public void setInventoryMOUAccepted(boolean inventoryMOUAccepted) {
		this.inventoryMOUAccepted = inventoryMOUAccepted;
	}

	public boolean isPricingAuto() {
		return pricingAuto;
	}

	public void setPricingAuto(boolean pricingAuto) {
		this.pricingAuto = pricingAuto;
	}

	public boolean isPricingMOUAccepted() {
		return pricingMOUAccepted;
	}

	public void setPricingMOUAccepted(boolean pricingMOUAccepted) {
		this.pricingMOUAccepted = pricingMOUAccepted;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public float getSellerScore() {
		return sellerScore;
	}

	public void setSellerScore(float sellerScore) {
		this.sellerScore = sellerScore;
	}

	public Float getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(Float overallRating) {
		this.overallRating = overallRating;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public boolean isSellExistingProductAuto() {
		return sellExistingProductAuto;
	}

	public void setSellExistingProductAuto(boolean sellExistingProductAuto) {
		this.sellExistingProductAuto = sellExistingProductAuto;
	}

	public boolean isExpressDispatch() {
		return expressDispatch;
	}

	public void setExpressDispatch(boolean expressDispatch) {
		this.expressDispatch = expressDispatch;
	}

	public boolean isNoFrills() {
		return noFrills;
	}

	public void setNoFrills(boolean noFrills) {
		this.noFrills = noFrills;
	}

	public Float getOutOfStockRating() {
		return outOfStockRating;
	}

	public void setOutOfStockRating(Float outOfStockRating) {
		this.outOfStockRating = outOfStockRating;
	}

	public Float getFastShipRating() {
		return fastShipRating;
	}

	public void setFastShipRating(Float fastShipRating) {
		this.fastShipRating = fastShipRating;
	}

	public Float getSlaBreachRating() {
		return slaBreachRating;
	}

	public void setSlaBreachRating(Float slaBreachRating) {
		this.slaBreachRating = slaBreachRating;
	}

	public Float getComplaintRating() {
		return complaintRating;
	}

	public void setComplaintRating(Float complaintRating) {
		this.complaintRating = complaintRating;
	}

	@JsonIgnore
	public boolean isVendorEligibleForInventoryUpdates() {
		if (isInventoryAuto() && isInventoryMOUAccepted()) {
			return true;
		}
		return false;
	}

	@JsonIgnore
	public boolean isVendorEligibleForPricingUpdates() {
		if (isPricingAuto() && isPricingMOUAccepted()) {
			return true;
		}
		return false;
	}

	public boolean isMlpCapable() {
		return mlpCapable;
	}

	public void setMlpCapable(boolean mlpCapable) {
		this.mlpCapable = mlpCapable;
	}

	@Override
	public String toString() {
		return "SellerSRO [name=" + name + ", email=" + email + ", sellerCode="
				+ sellerCode + ", enabled=" + enabled + ", inventoryAuto="
				+ inventoryAuto + ", inventoryMOUAccepted="
				+ inventoryMOUAccepted + ", pricingAuto=" + pricingAuto
				+ ", pricingMOUAccepted=" + pricingMOUAccepted + ", created="
				+ created + ", sellerScore=" + sellerScore + ", overallRating="
				+ overallRating + ", displayName=" + displayName
				+ ", sellExistingProductAuto=" + sellExistingProductAuto
				+ ", expressDispatch=" + expressDispatch + ", noFrills="
				+ noFrills + ", outOfStockRating=" + outOfStockRating
				+ ", fastShipRating=" + fastShipRating + ", slaBreachRating="
				+ slaBreachRating + ", complaintRating=" + complaintRating
				+ ", mlpCapable=" + mlpCapable + "]";
	}

}
