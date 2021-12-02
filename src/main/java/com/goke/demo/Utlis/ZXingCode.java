package com.goke.demo.Utlis;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 信息二班郄先生
 * @date 2019-3-24 14:34:47
 * 二维码生成
 */
public class ZXingCode {
    /**
     * 颜色
     */
    private static final int QRCOLOR = 0xFF00FFFF; //0xFF000000

    /**
     * 背景颜色
     */
    private static final int BGWHITE = 0xFFFFFFFF;

//    private CreateWord createWord;
    /**
     * 存放路径
     */
//    private static final String CODEPATH = ".\\library2-provider\\src\\main\\java\\com\\tfjybj\\library\\template\\codeImage\\";
//   public static void main(String[] args) {
        CreateWord createWord = new CreateWord();
//        try {
//            System.out.println(System.currentTimeMillis());
//       Image image = getQRCode("傻呗呗", "扫一扫有惊喜");
//       java.util.List<Image> imageList = null;
//           imageList.add(image);
//            createWord.createDocContext(imageList,httpServletRequest,httpServletResponse);
//            System.out.println(System.currentTimeMillis());
//            System.out.println("生成ok");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    /**
     * 生成二维码方法
     *
     * @param data 索书号
     * @param belowText 书名
     * @return
     */
    public static com.lowagie.text.Image getQRCode(String data, String belowText) {
        com.lowagie.text.Image imageByte =null;
        try {
            ZXingCode zp = new ZXingCode();
            BufferedImage bim = zp.generateQRCodeBufferedImage(data, BarcodeFormat.QR_CODE, 230, 230, zp.getDecodeHintType());
            //字节数组
            imageByte = zp.addTextForQRCode(bim, belowText);

            return imageByte;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imageByte;
    }

    /**
     * @param bim       放在内存中图片
     * @param belowText 二维码下方显示文字
     * @return
     */
    public static com.lowagie.text.Image addTextForQRCode(BufferedImage bim, String belowText) {
        try {
            BufferedImage image = bim;
            if (belowText != null && !belowText.equals("")) {
                BufferedImage outImage = new BufferedImage(230, 255, BufferedImage.TYPE_4BYTE_ABGR);
                Graphics2D outg = outImage.createGraphics();
                outg.drawImage(image, 0, 0, image.getWidth(), image.getHeight(), null);
                outg.setColor(Color.BLACK);
                //获得本地字体列表
//                String[] fonts = this.getFontName();
                outg.setFont(new Font("SansSerif", Font.PLAIN, 16));
                int strWidth = outg.getFontMetrics().stringWidth(belowText);
                outg.drawString(belowText, 100 - strWidth / 2 + 8, image.getHeight() + (outImage.getHeight() - image.getHeight()) / 2 + 5);
                outg.dispose();
                outImage.flush();
                image = outImage;
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            baos.flush();
            ImageIO.write(image, "png", baos);
            BufferedImage newBufferedImage = new BufferedImage(
                    image.getWidth(), image.getHeight(),
                    BufferedImage.TYPE_INT_RGB);
            newBufferedImage.createGraphics().drawImage(image, 0, 0,
                    Color.WHITE, null);
//            存放本地
//            ImageIO.write(newBufferedImage, "png", new File(CODEPATH + "SZ-" + System.currentTimeMillis() + "code.png"));
            com.lowagie.text.Image imageByte = com.lowagie.text.Image.getInstance(baos.toByteArray());
            baos.close();
            image.flush();
            return imageByte;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 绘制二维码，不带文字
     *
     * @param content       扫描内容
     * @param barcodeFormat 格式
     * @param width
     * @param height
     * @param hints         二维码属性设置
     * @return 放到内存中，后续再二维码下方添加文字
     */
    public BufferedImage generateQRCodeBufferedImage(String content, BarcodeFormat barcodeFormat, int width, int height, Map<EncodeHintType, ?> hints) {
        MultiFormatWriter multiFormatWriter = null;
        BitMatrix bm = null;
        BufferedImage image = null;
        try {
            multiFormatWriter = new MultiFormatWriter();
            bm = multiFormatWriter.encode(content, barcodeFormat, width, height, hints);
            int w = bm.getWidth();
            int h = bm.getHeight();
            image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? QRCOLOR : BGWHITE);
                }
            }
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 设置二维码属性
     *
     * @return
     */
    public Map<EncodeHintType, Object> getDecodeHintType() {
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MARGIN, 0);
        hints.put(EncodeHintType.MAX_SIZE, 350);
        hints.put(EncodeHintType.MIN_SIZE, 100);
        return hints;
    }

    /**
     * 获得本地字体列表
     * @return 字体数组
     */
    public String[] getFontName(){
        GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
        String[] fontName = e.getAvailableFontFamilyNames();
        return fontName;
    }

}

