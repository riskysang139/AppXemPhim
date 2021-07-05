package TrangChu.PhimDacSac;

public class DacSacFilm {
        private String dacSacImage;
        private String textDacSac;
        private String textCategory;

    public String getTextCategory() {
        return textCategory;
    }

    public void setTextCategory(String textCategory) {
        this.textCategory = textCategory;
    }

    public String getDacSacImage() {
        return dacSacImage;
    }

    public void setDacSacImage(String dacSacImage) {
        this.dacSacImage = dacSacImage;
    }

    public String getTextDacSac() {
        return textDacSac;
    }

    public void setTextDacSac(String textDacSac) {
        this.textDacSac = textDacSac;
    }


    public DacSacFilm(String dacSacImage, String textDacSac, String textCategory) {
        this.dacSacImage = dacSacImage;
        this.textDacSac = textDacSac;
        this.textCategory = textCategory;
    }
}
