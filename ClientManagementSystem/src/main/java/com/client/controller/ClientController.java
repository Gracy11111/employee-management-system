package com.client.controller;


import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.client.entity.Employee;

@RestController
@RequestMapping("/client/employee")
public class ClientController {
	
	
	 @Autowired
	    private RestTemplate restTemplate;
	
	
	@Autowired
	 private static final String SECRET_KEY = "SecretKey";
    private static final String SALT_VALUE = "endgame";
    
    

    @GetMapping("/employeeId/{employeeID}")
    public Employee getByEmpID(@PathVariable int employeeID) throws Exception {

        Employee employee = restTemplate.getForObject("https://localhost:8787/employee/" + employeeID, Employee.class);
        String decryptedDateOfBirth = decrypt(employee.getDateOfBirth());
        employee.setDateOfBirth(decryptedDateOfBirth);
        return employee;

    }
    

    private static String decrypt(String strToDecrypt) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance(KEY_SPEC_ALGORITHM);
        KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT_VALUE.getBytes(), ITERATIONS, KEY_SIZE);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        IvParameterSpec ivspec = new IvParameterSpec(IV);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    }
    
    private static final byte[] IV = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    private static final String ALGORITHM = "AES/CBC/PKCS5PADDING";
    private static final String KEY_SPEC_ALGORITHM = "PBKDF2WithHmacSHA256";
    private static final int ITERATIONS = 65536;
    private static final int KEY_SIZE = 256;

}
	
	
	

