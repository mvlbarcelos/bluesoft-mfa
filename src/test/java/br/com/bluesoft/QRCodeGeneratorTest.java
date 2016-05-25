package br.com.bluesoft;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.FileOutputStream;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class QRCodeGeneratorTest {

	private static final String USER_NAME = "jonh";
	private static final String ACCOUNT = "securityCorp";
	private static final String SECRET_KEY = "EX5DUK2BV3DISAJO";
	private QRCodeGenerator qrCodeGenerator;
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void shouldGenerateQRCodeURL() throws Exception {
		qrCodeGenerator = new QRCodeGenerator(USER_NAME, ACCOUNT, SECRET_KEY);
		 byte[] qrCode = qrCodeGenerator.getQrCode();
		 assertThat(DigestUtils.md5Hex(qrCode), is("09f86277889768a49e960cbc924a229b"));
	}
	
	@Test
	public void shouldValidadeIfUserNameWasSet() throws Exception {
		expectedEx.expect(NullPointerException.class);
		expectedEx.expectMessage("userName must not be null");
		qrCodeGenerator = new QRCodeGenerator(null, ACCOUNT, SECRET_KEY);
	}
	
	@Test
	public void shouldValidadeIfAccountWasSet() throws Exception {
		expectedEx.expect(NullPointerException.class);
		expectedEx.expectMessage("account must not be null");
		qrCodeGenerator = new QRCodeGenerator(USER_NAME, null, SECRET_KEY);
		
	}
	
	@Test
	public void shouldValidadeIfSecretKeyWasSet() throws Exception {
		expectedEx.expect(NullPointerException.class);
		expectedEx.expectMessage("secretKey must not be null");
		qrCodeGenerator = new QRCodeGenerator(USER_NAME, ACCOUNT, null);
		
	}
}
