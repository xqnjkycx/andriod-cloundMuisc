package com.example.gsonClass;

import java.util.List;

public class bannerClass {

    private List<BannersDTO> banners;
    private Integer code;

    public List<BannersDTO> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersDTO> banners) {
        this.banners = banners;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static class BannersDTO {
        private String pic;
        public String getPic() {
            return pic;
        }
        public void setPic(String pic) {
            this.pic = pic;
        }
    }
}
