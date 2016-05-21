package br.com.bluesoft;

import java.util.Arrays;
import java.util.Random;

import org.apache.commons.codec.binary.Base32;

public class SecretKeyGenerator {
	
	private final static int SECRET_SIZE = 10;
    private final static int NUMBER_OF_SCRATCH_CODES = 5;
    private final static int SCRATCH_CODE_SIZE = 8;
    
    private Random generator;
    private Base32 codec;

	public String generate(){
		generator = new Random();
		codec = new Base32();
		
		byte[] buffer = new byte[SECRET_SIZE + NUMBER_OF_SCRATCH_CODES * SCRATCH_CODE_SIZE];
        generator.nextBytes(buffer);

        byte[] secretKey = Arrays.copyOf(buffer, SECRET_SIZE);
        byte[] bEncodedKey = codec.encode(secretKey);
        return new String(bEncodedKey);
	}
}
