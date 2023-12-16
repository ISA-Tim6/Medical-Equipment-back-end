package com.example.medicalequipment.service;


import com.example.medicalequipment.dto.QRCodeDto;
import com.example.medicalequipment.model.QRCode;
import com.example.medicalequipment.repository.IQRCodeRespository;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QRCodeService {

    private final IQRCodeRespository qrCodeRepository;
    private final RegistratedUserService userService;

    public QRCodeService(IQRCodeRespository qrCodeRepository, RegistratedUserService userService) {
        this.qrCodeRepository = qrCodeRepository;
        this.userService = userService;
    }

    public static byte[] generateQRCode(String content) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.AZTEC, 300, 300);

        BufferedImage qrImage = new BufferedImage(300, 300, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < 300; x++) {
            for (int y = 0; y < 300; y++) {
                qrImage.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(qrImage, "png", baos);
        return baos.toByteArray();
    }

    public  void save(QRCode qrCode){
        qrCodeRepository.save(qrCode);
    }

    public byte[] generateQRCodeImage(String qrCodeText, int width, int height) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(qrCodeText, BarcodeFormat.QR_CODE, width, height, hints);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int bit = bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF;
                    stream.write((bit >> 16) & 0xFF);
                    stream.write((bit >> 8) & 0xFF);
                    stream.write(bit & 0xFF);
                }
            }

            return stream.toByteArray();
        } catch (WriterException e) {
            e.printStackTrace();
            // Handle the exception appropriately
            return null;
        }
    }

   /* public QRCode findByApointmentId(Long appointmentId){
        return qrCodeRepository.findByAppointmentId(appointmentId);
    }*/

    public List<QRCode> findByUserId(Long userId){
        return qrCodeRepository.findByUserId(userId);
    }

    public byte[] loadImageAsBytes(String imagePath) throws IOException {
        File imageFile = new File(imagePath);
        try (FileInputStream inputStream = new FileInputStream(imageFile)) {
            return StreamUtils.copyToByteArray(inputStream);
        }
    }

    public List<QRCode>LoadQRCodes() throws IOException {
        List<QRCode> qrCodes = findByUserId(userService.getCurrentUser().getUser_id());
        List<QRCode>retList = new ArrayList<>();
        for(QRCode qrCode: qrCodes){
        	QRCodeDto qrCodeDto = new QRCodeDto();
            String path = "./src/main/resources/"+qrCode.getReservation().getReservation_id()+".png";
            byte[] image =  loadImageAsBytes(path);
            qrCodeDto.setImage(image);
            //retList.add(qrCodeDto);
        }
         return retList;
    }

}
