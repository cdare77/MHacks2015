package com.walmart.openapi.responses;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class that holds the item json. The comment for each field corresponds to the name in the data feed.
 */
public class Item implements Serializable{

    // catalog_item_id
    private int itemId;

    // parentSKU
    private Integer parentItemId;

    // name
    private String name;

    // baseItemPrice
    private Double msrp;

    // price
    private Double salePrice;

    // twelveDigitStdUpc
    private String upc;

    // char_primary_category_path
    private String categoryPath;

    // inStock && availableOnline
    private Boolean isAvailableOnline;

    // shortDesc
    private String shortDescription;

    // longDesc
    private String longDescription;

    // brandName
    private String brandName;

    // thumbnailImage - generally 100 x 100
    private String thumbnailImage;

    // img150by150
    private String mediumImage;

    // primaryImage - generally 500 x 500
    private String largeImage;

    private String productTrackingUrl;

    // freeShippingFlag
    private Boolean freeShipping;

    // ninetySevenCentShipping
    private Boolean ninetySevenCentShipping;

    // standardShipRate
    private Double standardShipRate;

    // shipTwoThreeRate
    private Double twoThreeDayShippingRate;

    // shipOvernightRate
    private Double overnightShippingRate;

    // size
    private String size;

    // color
    private String color;

    // is marketplace item
    private Boolean marketplace;

    // ship to store
    private Boolean shipToStore;

    // free ship to store
    private Boolean freeShipToStore;

    // model number
    private String modelNumber;

    // sellerInfo
    private String sellerInfo;

    // productUrl
    private String productUrl;

    // Ratings
    private String customerRating;

    private Integer numReviews;


    private List<Integer> variants;

    // shelves
    private List<String> shelves;

    // ratingsImage
    private String customerRatingImage;

    private BestMarketPlacePrice bestMarketplacePrice;

    private String categoryNode;

    private Boolean rollBack;

    private Boolean specialBuy;

    private Boolean bundle;

    private String isbn;

    private Boolean clearance;

    private Boolean preOrder;

    private String preOrderShipsOn;

    private String stock;

    private Boolean freight;

    private Long dealStartTime;

    private Long dealEndTime;

    private Attributes attributes;

    private String gender;

    private String age;

    private static final long serialVersionUID = 1L;

    public static class BestMarketPlacePrice {

        private Double price;

        private String sellerInfo;

        private Double standardShipRate;

        private Double twoThreeDayShippingRate;

        private Double overnightShippingRate;

        private boolean availableOnline;

        private boolean clearance;

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public String getSellerInfo() {
            return sellerInfo;
        }

        public void setSellerInfo(String sellerInfo) {
            this.sellerInfo = sellerInfo;
        }

        public Double getStandardShipRate() {
            return standardShipRate;
        }

        public void setStandardShipRate(Double standardShipRate) {
            this.standardShipRate = standardShipRate;
        }

        public Double getTwoThreeDayShippingRate() {
            return twoThreeDayShippingRate;
        }

        public void setTwoThreeDayShippingRate(Double twoThreeDayShippingRate) {
            this.twoThreeDayShippingRate = twoThreeDayShippingRate;
        }

        public Double getOvernightShippingRate() {
            return overnightShippingRate;
        }

        public void setOvernightShippingRate(Double overnightShippingRate) {
            this.overnightShippingRate = overnightShippingRate;
        }

        public boolean isAvailableOnline() {
            return availableOnline;
        }

        public void setAvailableOnline(boolean availableOnline) {
            this.availableOnline = availableOnline;
        }

        public boolean isClearance(){
            return clearance;
        }

        public void setClearance(boolean clearance){
            this.clearance = clearance;
        }

    }

    public static class Attributes {

        private Map<String, String> map = new TreeMap<String, String>();

        @JsonAnyGetter
        public Map<String, String> get(){
            return map;
        }

        @JsonAnySetter
        public void set(String key, String value){
            map.put(key, value);
        }

    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public Integer getParentItemId() {
        return parentItemId;
    }

    public void setParentItemId(Integer parentItemId) {
        this.parentItemId = parentItemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMsrp() {
        return msrp;
    }

    public void setMsrp(Double msrp) {
        this.msrp = msrp;
    }

    public Double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(Double salePrice) {
        this.salePrice = salePrice;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getCategoryPath() {
        return categoryPath;
    }

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    public Boolean getAvailableOnline() {
        return isAvailableOnline;
    }

    public void setAvailableOnline(Boolean availableOnline) {
        isAvailableOnline = availableOnline;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public String getMediumImage() {
        return mediumImage;
    }

    public void setMediumImage(String mediumImage) {
        this.mediumImage = mediumImage;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    public String getProductTrackingUrl() {
        return productTrackingUrl;
    }

    public void setProductTrackingUrl(String productTrackingUrl) {
        this.productTrackingUrl = productTrackingUrl;
    }

    public Boolean getFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(Boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public Boolean getNinetySevenCentShipping() {
        return ninetySevenCentShipping;
    }

    public void setNinetySevenCentShipping(Boolean ninetySevenCentShipping) {
        this.ninetySevenCentShipping = ninetySevenCentShipping;
    }

    public Double getStandardShipRate() {
        return standardShipRate;
    }

    public void setStandardShipRate(Double standardShipRate) {
        this.standardShipRate = standardShipRate;
    }

    public Double getTwoThreeDayShippingRate() {
        return twoThreeDayShippingRate;
    }

    public void setTwoThreeDayShippingRate(Double twoThreeDayShippingRate) {
        this.twoThreeDayShippingRate = twoThreeDayShippingRate;
    }

    public Double getOvernightShippingRate() {
        return overnightShippingRate;
    }

    public void setOvernightShippingRate(Double overnightShippingRate) {
        this.overnightShippingRate = overnightShippingRate;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getMarketplace() {
        return marketplace;
    }

    public void setMarketplace(Boolean marketplace) {
        this.marketplace = marketplace;
    }

    public Boolean getShipToStore() {
        return shipToStore;
    }

    public void setShipToStore(Boolean shipToStore) {
        this.shipToStore = shipToStore;
    }

    public Boolean getFreeShipToStore() {
        return freeShipToStore;
    }

    public void setFreeShipToStore(Boolean freeShipToStore) {
        this.freeShipToStore = freeShipToStore;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }


    public String getSellerInfo() {
        return sellerInfo;
    }

    public void setSellerInfo(String sellerInfo) {
        this.sellerInfo = sellerInfo;
    }

    public String getProductUrl() {
        return productUrl;
    }

    public void setProductUrl(String productUrl) {
        this.productUrl = productUrl;
    }

    public List<Integer> getVariants() {
        return variants;
    }

    public void setVariants(List<Integer> variants) {
        this.variants = variants;
    }

    public List<String> getShelves() {
        return shelves;
    }

    public void setShelves(List<String> shelves) {
        this.shelves = shelves;
    }

    public String getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(String ratings){
        this.customerRating = ratings;
    }

    public String getCustomerRatingImage() {
        return customerRatingImage;
    }

    public void setCustomerRatingImage(String ratingsImage) {
        this.customerRatingImage = ratingsImage;
    }

    public Integer getNumReviews() {
        return numReviews;
    }

    public void setNumReviews(Integer numReviews) {
        this.numReviews = numReviews;
    }

    public BestMarketPlacePrice getBestMarketplacePrice() {
        return bestMarketplacePrice;
    }

    public void setBestMarketplacePrice(BestMarketPlacePrice bestMarketplacePrice) {
        this.bestMarketplacePrice = bestMarketplacePrice;
    }

    public String getCategoryNode() {
        return categoryNode;
    }

    public void setCategoryNode(String categoryNode) {
        this.categoryNode = categoryNode;
    }

    public Boolean isRollBack() {
        return rollBack;
    }

    public void setRollBack(Boolean rollBack) {
        this.rollBack = rollBack;
    }

    public Boolean isSpecialBuy() {
        return specialBuy;
    }

    public void setSpecialBuy(Boolean specialBuy) {
        this.specialBuy = specialBuy;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Boolean getBundle() {
        return bundle;
    }

    public void setBundle(Boolean bundle) {
        this.bundle = bundle;
    }

    public Boolean getClearance() {
        return clearance;
    }

    public void setClearance(Boolean clearance) {
        this.clearance = clearance;
    }

    public Boolean getPreOrder() {
        return preOrder;
    }

    public void setPreOrder(Boolean preOrder) {
        this.preOrder = preOrder;
    }

    public String getPreOrderShipsOn() {
        return preOrderShipsOn;
    }

    public void setPreOrderShipsOn(String preOrderShipsOn) {
        this.preOrderShipsOn = preOrderShipsOn;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public Boolean getFreight() {
        return freight;
    }

    public void setFreight(Boolean freight) {
        this.freight = freight;
    }

    public Long getDealEndTime() {
        return dealEndTime;
    }

    public void setDealEndTime(Long dealEndTime) {
        this.dealEndTime = dealEndTime;
    }

    public Long getDealStartTime() {
        return dealStartTime;
    }

    public void setDealStartTime(Long dealStartTime) {
        this.dealStartTime = dealStartTime;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}