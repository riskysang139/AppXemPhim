package TrangChu.PhimPhoBien;

public class PhoBienImage {
    private String phoBienImage;
    private String textTitlePB;
    private String txtPublished;

    public String getPhoBienImage() {
        return phoBienImage;
    }

    public void setPhoBienImage(String phoBienImage) {
        this.phoBienImage = phoBienImage;
    }

    public String getTextTitlePB() {
        return textTitlePB;
    }

    public void setTextTitlePB(String textTitlePB) {
        this.textTitlePB = textTitlePB;
    }

    public String getTxtPublished() {
        return txtPublished;
    }

    public void setTxtPublished(String txtPublished) {
        this.txtPublished = txtPublished;
    }

    public PhoBienImage(String phoBienImage, String textTitlePB, String txtPublished) {
        this.phoBienImage = phoBienImage;
        this.textTitlePB = textTitlePB;
        this.txtPublished = txtPublished;
    }
}
