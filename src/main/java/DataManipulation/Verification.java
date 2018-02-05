/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataManipulation;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * @author Elkamel
 */
public class Verification
    {
    public static String get_SHA_512_SecurePassword(String passwordToHash, String salt) throws UnsupportedEncodingException
        {
        String generatedPassword = null;
        try
            {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(passwordToHash.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++)
                {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
            generatedPassword = sb.toString();
            } catch (NoSuchAlgorithmException e)
            {
            e.printStackTrace();
            }
        return generatedPassword;
        }

    public static boolean verifyName(String s)
        {
        if (s.isEmpty() || s == null) return false;
        return (onlyLettersSpaces(s) && s.length() > 2);
        }

    public static boolean verifyEmail(String s)
        {
        return isValidEmailAddress(s);
        }

    public static boolean verifyUsername(String s)
        {
        return (onlyLettersNumbers(s) && s.length() > 2);
        }

    public static boolean verifyPhone(String s)
        {
        if (s.isEmpty() || s == null) return false;
        if (s.length() != 8) return false;
        return (s.matches("[0-9]+"));
        }

    public static boolean verifyGender(String s)
        {
        return "Male".equals(s) || "Female".equals(s);
        }

    public static boolean verifyBirthDate(LocalDate d)
        {
        if (d == null) return false;
        if (d.isBefore(LocalDate.now())) return true;
        else return false;
        }

    public static boolean verifyConfirmPassword(String password1, String password2)
        {
        return password1.equals(password2);
        }

    public static boolean onlyLettersSpaces(String s)
        {
        int i;
        for (i = 0; i < s.length(); i++)
            {
            char ch = s.charAt(i);
            if (Character.isLetter(ch) || ch == ' ')
                {
                continue;
                }
            return false;
            }
        return true;
        }

    public static boolean onlyLettersNumbers(String s)
        {
        int i;
        for (i = 0; i < s.length(); i++)
            {
            char ch = s.charAt(i);
            if (Character.isLetter(ch) || Character.isDigit(ch))
                {
                continue;
                }
            return false;
            }
        return true;
        }

    public static boolean isValidEmailAddress(String email)
        {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
        }

    public static boolean isStrongPassword(String password)
        {
        boolean flagUppercase = false;
        boolean flagLowercase = false;
        boolean flagDigit = false;
        boolean flag = false;
        if (password.length() >= 6)
            {
            for (int i = 0; i < password.length(); i++)
                {
//                if (!Character.isLetterOrDigit(password.charAt(i)))
//                    {
//                    return false;
//                    }
                if (Character.isDigit(password.charAt(i)) && !flagDigit)
                    {
                    flagDigit = true;
                    }
                if (Character.isUpperCase(password.charAt(i)) && !flagUppercase)
                    {
                    flagUppercase = true;
                    }
                if (Character.isLowerCase(password.charAt(i)) && !flagLowercase)
                    {
                    flagLowercase = true;
                    }
                }
            }
        if (flagDigit && flagUppercase && flagLowercase)
            {
            flag = true;
            
            }
        return flag;
        }

    public static boolean isValidImage(String file_path)
        {      
        if (file_path != null)
            {
           
            File n = new File(file_path);
            if (n.exists())
                {
                try
                    {
                    if (ImageIO.read(n) != null) return true;
                    } catch (IOException ex)
                    {
                    Logger.getLogger(Verification.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else return false;
            } else
            {
       
            return false;
            }
        return false;
        }
    }
