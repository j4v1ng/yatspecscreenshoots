package screenshootssupport;

public class ScreenshootHolder {

    public static final String INTERESTING_GIVENS_KEY = "Screenshot";
    private final String base64PngDataString;

    public ScreenshootHolder(String base64PngImageData) {
        this.base64PngDataString = base64PngImageData;
    }

    public byte[] getPngImageData() {
        return org.apache.commons.codec.binary.Base64.decodeBase64(base64PngDataString);
    }
}
