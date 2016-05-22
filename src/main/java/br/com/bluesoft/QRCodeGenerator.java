package br.com.bluesoft;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {

	private static final String FORMAT = "PNG";
	private static final String KEY_URI = "otpauth://totp/%s@%s?secret=%s";
	private static final int WIDTH = 300;
	private static final int HEIGHT = 300;

	private String userName;
	private String account;
	private String secretKey;

	private byte[] qrCode;

	public QRCodeGenerator(String userName, String account, String secretKey) throws WriterException, IOException {
		this.userName = userName;
		this.account = account;
		this.secretKey = secretKey;
		validate();
		createQRCode();
	}

	private void createQRCode() throws IOException, WriterException {
		String contents = String.format(KEY_URI, userName, account, secretKey);
		QRCodeWriter writer = new QRCodeWriter();
		BitMatrix matrix = writer.encode(contents, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(matrix, FORMAT, byteArray);
		qrCode = byteArray.toByteArray();
	}

	private void validate() {
		Objects.requireNonNull(userName, "userName must not be null");
		Objects.requireNonNull(account, "account must not be null");
		Objects.requireNonNull(secretKey, "secretKey must not be null");
	}
	
	public byte[] getQrCode() {
		return qrCode;
	}
}
