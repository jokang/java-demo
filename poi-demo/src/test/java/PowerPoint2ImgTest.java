import org.apache.poi.hslf.model.Slide;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;

/**
 * @author zhoukang04
 * @date 2023/10/18
 */
public class PowerPoint2ImgTest {
    @Test
    public void testConvert() throws Exception {
        FileInputStream is = new FileInputStream("/Users/zhoukang04/Downloads/sample.pptx");
        XMLSlideShow ppt = new XMLSlideShow(is);
        is.close();
        XSLFSlide[] slides = ppt.getSlides();
        for (int i = 0; i < slides.length; i++) {
            BufferedImage image = new BufferedImage(slides[i].getSlideShow().getPageSize().width, slides[i].getSlideShow().getPageSize().height, BufferedImage.TYPE_INT_RGB);

            Graphics2D graphics = image.createGraphics();
            slides[i].draw(graphics);

            ImageIO.write(image, "png", new File("/Users/zhoukang04/Downloads/tmp-output/" + i + ".png"));
        }
    }
}
