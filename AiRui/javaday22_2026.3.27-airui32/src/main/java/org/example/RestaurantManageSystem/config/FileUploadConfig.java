package org.example.RestaurantManageSystem.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "file.upload")
public class FileUploadConfig {
    private String basePath;
    private StoreImage storeImage;
    private DishImage dishImage;
    private BusinessLicense businessLicense;
    private RentalContract rentalContract;

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public StoreImage getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(StoreImage storeImage) {
        this.storeImage = storeImage;
    }

    public DishImage getDishImage() {
        return dishImage;
    }

    public void setDishImage(DishImage dishImage) {
        this.dishImage = dishImage;
    }

    public BusinessLicense getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(BusinessLicense businessLicense) {
        this.businessLicense = businessLicense;
    }

    public RentalContract getRentalContract() {
        return rentalContract;
    }

    public void setRentalContract(RentalContract rentalContract) {
        this.rentalContract = rentalContract;
    }

    public static class StoreImage {
        private String dir;
        private long maxSize;
        private List<String> allowTypes;

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public long getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(long maxSize) {
            this.maxSize = maxSize;
        }

        public List<String> getAllowTypes() {
            return allowTypes;
        }

        public void setAllowTypes(List<String> allowTypes) {
            this.allowTypes = allowTypes;
        }
    }

    public static class DishImage {
        private String dir;
        private long maxSize;
        private List<String> allowTypes;

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public long getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(long maxSize) {
            this.maxSize = maxSize;
        }

        public List<String> getAllowTypes() {
            return allowTypes;
        }

        public void setAllowTypes(List<String> allowTypes) {
            this.allowTypes = allowTypes;
        }
    }

    public static class BusinessLicense {
        private String dir;
        private long maxSize;
        private List<String> allowTypes;

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public long getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(long maxSize) {
            this.maxSize = maxSize;
        }

        public List<String> getAllowTypes() {
            return allowTypes;
        }

        public void setAllowTypes(List<String> allowTypes) {
            this.allowTypes = allowTypes;
        }
    }

    public static class RentalContract {
        private String dir;
        private long maxSize;
        private List<String> allowTypes;

        public String getDir() {
            return dir;
        }

        public void setDir(String dir) {
            this.dir = dir;
        }

        public long getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(long maxSize) {
            this.maxSize = maxSize;
        }

        public List<String> getAllowTypes() {
            return allowTypes;
        }

        public void setAllowTypes(List<String> allowTypes) {
            this.allowTypes = allowTypes;
        }
    }
}