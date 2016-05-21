package br.com.bluesoft;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

public class SecretKeyGeneratorTest {

	private String secretKey; 

	@Before
	public void setUp() {
		secretKey = new SecretKeyGenerator().generate();
	}

	@Test
	public void shouldGenerateSecretKeyWithSizeSixteen() throws Exception {
		assertThat(secretKey.length(), is(16));
	}
}
