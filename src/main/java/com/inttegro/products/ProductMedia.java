package com.inttegro.products;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** Purpose-specific media attached to a product. */
public final class ProductMedia {
    @JsonProperty("hero_image") public String heroImage;
    public String thumbnail;
    @JsonProperty("web_page_url") public String webPageUrl;
    @JsonProperty("brand_logo") public String brandLogo;
    public String infographic;
    @JsonProperty("promo_video") public String promoVideo;
    @JsonProperty("demo_video") public String demoVideo;
    public List<String> gallery;
    public List<String> downloads;
}
