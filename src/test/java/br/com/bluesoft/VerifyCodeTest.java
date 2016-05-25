package br.com.bluesoft;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;

public class VerifyCodeTest {

	private static final String VALID_SECRET_KEY = "EX5DUK2BV3DISAJO";
	private static final String INVALID_SECRET_KEY = "JKJEOIDLKSDPODSI";
	private static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd hh:mm:ss";

	private SimpleDateFormat simpleDateFormat;

	@Before
	public void setUp() {
		simpleDateFormat = new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS);
	}

	@Test
	public void shoudCodeBeValid() throws ParseException {
		long time = simpleDateFormat.parse("2016-05-24 21:00:00").getTime();
		VerifyCode verifyCode = new VerifyCode(VALID_SECRET_KEY);
		boolean verify = verifyCode.verify(880786, time);
		assertThat(verify, is(true));
	}
	
	@Test
	public void shoudCodeBeInvalid() throws ParseException {
		long time = simpleDateFormat.parse("2016-05-24 21:00:00").getTime();
		VerifyCode verifyCode = new VerifyCode(VALID_SECRET_KEY);
		boolean verify = verifyCode.verify(880785, time);
		assertThat(verify, is(false));
	}

	@Test
	public void shoudCodeBeInvalidForThisDate() throws ParseException {
		long time = simpleDateFormat.parse("2016-05-24 21:01:00").getTime();
		VerifyCode verifyCode = new VerifyCode(VALID_SECRET_KEY);
		boolean verify = verifyCode.verify(880786, time);
		assertThat(verify, is(false));
	}

	@Test
	public void shoudCodeBeInvalidForThisSecret() throws ParseException {
		long time = simpleDateFormat.parse("2016-05-24 21:00:00").getTime();
		VerifyCode verifyCode = new VerifyCode(INVALID_SECRET_KEY);
		boolean verify = verifyCode.verify(880785, time);
		assertThat(verify, is(false));
	}
}
