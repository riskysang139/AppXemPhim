package TrangChu.PhimDacSac;

public class DacSacImage {
        private String dacSacImage;
        private String textDacSac;

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

    public DacSacImage(String dacSacImage, String textDacSac) {
        this.dacSacImage = dacSacImage;
        this.textDacSac = textDacSac;
    }
}
