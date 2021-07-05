package TrangChu.hotSeries;

public class HotSeriesFilm {
    private String imageHS;
    private String titleHS;
    private String categoryHS;

    public String getImageHS() {
        return imageHS;
    }

    public void setImageHS(String imageHS) {
        this.imageHS = imageHS;
    }

    public String getTitleHS() {
        return titleHS;
    }

    public void setTitleHS(String titleHS) {
        this.titleHS = titleHS;
    }

    public String getCategoryHS() {
        return categoryHS;
    }

    public void setCategoryHS(String categoryHS) {
        this.categoryHS = categoryHS;
    }

    public HotSeriesFilm(String imageHS, String titleHS, String categoryHS) {
        this.imageHS = imageHS;
        this.titleHS = titleHS;
        this.categoryHS = categoryHS;
    }
}
