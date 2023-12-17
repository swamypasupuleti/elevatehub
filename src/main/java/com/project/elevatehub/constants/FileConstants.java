package com.project.elevatehub.constants;

public final class FileConstants {
    public static final String USER_PROFILE_IMAGE_S3_PATH = "userProfileImages/";
    public static final String DEFAULT_PROFILE_IMAGE_S3_LOCATION = USER_PROFILE_IMAGE_S3_PATH + "defaultUserImage.png";
    public static final String DEFAULT_PROFILE_IMAGE_MIME_TYPE = "image/png";
    public static final String DEFAULT_PROFILE_IMAGE_FILE_NAME = "defaultUserImage.png";
    
    
    public static final String DEFAULT_CONTENT_TYPE = "image/jpeg";
    public static final String ACCEPTED_IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|jpeg))$)";
    public static final Long DEFAULT_PRODUCT_IMAGE_ID = 1L;
    public static final String PRODUCT_IMAGE_S3_PATH = "offerImages/";
    public static final String TOKEN_BATCHES_S3_PATH = "tokenBatches/";
    public static final String ADMIN_PROFILE_S3_PATH = "adminProfileImages/";
    
    public static final String USER_PROFILE = "userProfile";
    public static final String ADMIN_PROFILE = "adminProfile";
    public static final String PRODUCT_OFFER = "productOffer";
    public static final String TOKEN_BATCHES = "tokenBatches";
    public static final String LAST_15_DAYS = "last15Days";
    public static final String LAST_30_DAYS = "last30Days";
    public static final String LAST_2_MONTHS = "last2Months";
    
}
