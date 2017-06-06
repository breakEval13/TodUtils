package todcloud.utils;

/**
 * Created by zhangjianxin on 2017/6/6.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class RSA {

    /**
     * 公钥
     */
    private RSAPublicKey publicKey;
    /**
     * 私钥
     */
    private RSAPrivateKey privateKey;
    /**
     * 密文的长度
     */
    private int encrytLength = 256;
    /**
     * 持久化的公钥文件
     */
    private static final String publicKeyFile = "conf/id_rsa.key.pub";
    /**
     * 持久化的私钥文件
     */
    private static final String privateKeyFile = "conf/id_rsa.key.key";
    /**
     * generate public key and private key
     * @throws NoSuchAlgorithmException
     */
    public void genKey() throws NoSuchAlgorithmException {
        KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
        kg.initialize(encrytLength * 8);
        KeyPair kp = kg.generateKeyPair();

        publicKey = (RSAPublicKey) kp.getPublic();
        privateKey = (RSAPrivateKey) kp.getPrivate();
        //serialize the public key and the private key
        serailizeKey();
    }

    private void serailizeKey() {
        ObjectOutputStream pulicKeyOop = null;
        ObjectOutputStream privateKeyOop = null;
        try {
            pulicKeyOop = new ObjectOutputStream(new FileOutputStream(publicKeyFile));
            pulicKeyOop.writeObject(publicKey);
            privateKeyOop = new ObjectOutputStream(new FileOutputStream(privateKeyFile));
            privateKeyOop.writeObject(privateKey);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != pulicKeyOop) {
                    pulicKeyOop.close();
                }
                if(null != privateKeyOop) {
                    privateKeyOop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private RSAPublicKey getPublicKey() {
        ObjectInputStream ois = null;
        RSAPublicKey key = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(publicKeyFile));
            key = (RSAPublicKey)ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != ois) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return key;
    }

    private RSAPrivateKey getPrivateKey() {
        RSAPrivateKey key = null;
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(privateKeyFile));
            key = (RSAPrivateKey)ois.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if(null != ois) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return key;
    }

    public byte[] encrypt(byte [] origin) {
        Cipher cipher = null;
        byte[] cn = null;
        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, getPrivateKey());
            cn = cipher.doFinal(origin);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return cn;
    }

    public byte[] decrypt(byte[] enc) {
        Cipher cipher = null;
        byte[] cn = null;
        try {
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, getPublicKey());
            cn = cipher.doFinal(enc);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return cn;
    }


    public static void main(String[] args) {
        RSA rs = new RSA();
//            rs.genKey();
        String content = "hello world...";
        byte[] encryptedContent = rs.encrypt(content.getBytes());
        System.out.println(new String(encryptedContent));
        byte[] decryptedContent = rs.decrypt(encryptedContent);
        System.out.println("\n" + new String(decryptedContent));
    }
}
