package screenshootssupport;

import com.googlecode.yatspec.junit.SpecResultListener;
import com.googlecode.yatspec.rendering.html.HtmlResultRenderer;
import com.googlecode.yatspec.state.Result;

import java.io.File;

public class HtmlWithScreenshootResultListener implements SpecResultListener {

    private final ScreenshootRenderer screenShotRenderer;
    private HtmlResultRenderer delegate;

    public HtmlWithScreenshootResultListener(String testName) {
        delegate = new HtmlResultRenderer();
        screenShotRenderer = new ScreenshootRenderer(testName);
        delegate.withCustomRenderer(ScreenshootHolder.class, screenShotRenderer);
    }

    @Override
    public void complete(File yatspecOutputDir, Result result) throws Exception {
        screenShotRenderer.setYatspecOutputDir(yatspecOutputDir);
        delegate.complete(yatspecOutputDir, result);
    }
}
