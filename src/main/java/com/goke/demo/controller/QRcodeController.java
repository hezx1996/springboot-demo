package com.goke.demo.controller;

import com.goke.demo.Utlis.CreateWord;
import com.goke.demo.Utlis.ZXingCode;
import com.goke.demo.Utlis.ZxingUtils;
import com.lowagie.text.Image;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * @author 隔壁子
 * @date 2020/5/25 10:52
 */
@Controller
public class QRcodeController {
    // 这里说明一点，想要验证放入session中或者redies中，验证让过即可。
    // 和验证码的实现原理是一样，是不过换了一种验证方式。
    @RequestMapping("/createQRcode")
    public void createQRcode(HttpServletResponse response) {
        String contents = "1000000000001";
        int width = 230; int height = 240; int margin = 2;

        try {
            BufferedImage QRcode = ZxingUtils.createQRImage(contents, width, height, margin);

            String logoPath = "D:/0.jpg";
            int logoSize = 4;
            BufferedImage qRImageWithLogo = ZxingUtils.addQRImagelogo(QRcode, width, height, logoPath, logoSize);
          /*  Image image = ZXingCode.addTextForQRCode(qRImageWithLogo, "扫一扫有惊喜");
            java.util.List<Image> imageList = new ArrayList<>();
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            imageList.add(image);
            try {
                CreateWord.createDocContext(imageList,response);
            }catch (Exception e) {
                e.printStackTrace();
            }*/

            // 写入返回
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(qRImageWithLogo, "jpg", baos);

            byte[] QRJPG = baos.toByteArray();
            response.setHeader("Cache-Control", "no-store");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpeg");

            ServletOutputStream os = response.getOutputStream();
            os.write(QRJPG);// 自此完成一套，图片读入，写入流，转为字节数组，写入输出流
            os.flush();
            os.close();
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /***
     *下载
     */
    @RequestMapping("/create")
    public void ZXingCode(HttpServletResponse response){
        Image image1 = ZXingCode.getQRCode("123456789", "扫一扫有惊喜");
        Image image2 = ZXingCode.getQRCode("1234567810", "扫一扫有惊喜");
        Image image3 = ZXingCode.getQRCode("1234567811", "扫一扫有惊喜");
        Image image4 = ZXingCode.getQRCode("1234567812", "扫一扫有惊喜");
        java.util.List<Image> imageList = new ArrayList<>();
        imageList.add(image1);
        imageList.add(image2);
        imageList.add(image3);
        imageList.add(image4);
        try {
            CreateWord.createDocContext(imageList,response);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }





}
