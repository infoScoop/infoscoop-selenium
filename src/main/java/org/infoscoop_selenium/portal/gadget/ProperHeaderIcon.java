package org.infoscoop_selenium.portal.gadget;

public class ProperHeaderIcon {

    private String type;
    private String imgUrl;
    private String alt;

    public ProperHeaderIcon(String type, String imgUrl, String alt) {
        this.type = type;
        this.imgUrl = imgUrl;
        this.alt = alt;
    }

    public String getType() {
        return type;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getAlt() {
        return alt;
    }

}
