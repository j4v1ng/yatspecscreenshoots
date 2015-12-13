package screenshootssupport;

import com.googlecode.yatspec.rendering.Renderer;

import java.io.File;
import java.io.FileOutputStream;

public class ScreenshootRenderer implements Renderer<ScreenshootHolder> {

    private File yatspecOutputDir;
    private String testName;

    public ScreenshootRenderer(String testName) {
        this.testName = testName;
    }

    @Override
    public String render(ScreenshootHolder screenshootHolder) throws Exception {
        if (yatspecOutputDir == null) {
            throw new IllegalStateException("You must use screenshootssupport.HtmlWithScreenShootResultListener in your test to use ScreenShotRenderer");
        } else {
            String imageFilename = yatspecOutputDir + File.separator + getImageName();
            try (FileOutputStream fos = new FileOutputStream(imageFilename)) {
                fos.write(screenshootHolder.getPngImageData());
            }
            System.out.println("Rendered screenshot to " + imageFilename);
            return String.format("<div class='nohighlight'><img src=\"%s\" alt=\"%s\"></img></div>", imageFilename, imageFilename);
        }
    }

    public void setYatspecOutputDir(File yatspecOutputDir) {
        this.yatspecOutputDir = yatspecOutputDir;
    }

    protected String getImageName() {
        String timestamp = Long.toString(System.currentTimeMillis());
        return String.format("test-%s-%s.png", testName, timestamp);
    }
}
