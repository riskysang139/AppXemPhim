package TrangChu.PhimSapChieu;

public class SapChieuFilm {
    private String phoBienImage;
    private String textTitleCS;
    private String txtCategoryCS;


    public String getPhoBienImage() {
        return phoBienImage;
    }

    public void setPhoBienImage(String phoBienImage) {
        this.phoBienImage = phoBienImage;
    }

    public String getTextTitleCS() {
        return textTitleCS;
    }

    public void setTextTitleCS(String textTitleCS) {
        this.textTitleCS = textTitleCS;
    }

    public String getTxtCategoryCS() {
        return txtCategoryCS;
    }

    public void setTxtCategoryCS(String txtCategoryCS) {
        this.txtCategoryCS = txtCategoryCS;
    }

    public SapChieuFilm(String phoBienImage, String textTitleCS, String txtCategoryCS) {
        this.phoBienImage = phoBienImage;
        this.textTitleCS = textTitleCS;
        this.txtCategoryCS = txtCategoryCS;
    }
}
