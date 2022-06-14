package Data;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class AccountAndPasswords {
	private static HashMap<String, String> adminInfoHashMap = new HashMap<String, String>();
	private static HashMap<String, String> userInfoHashMap = new HashMap<String, String>();

	public static String changeUserInfo(String originalKey, char[] oriValue, String newKey, char[] newValue) {
		if (userInfoHashMap.get(originalKey).equals(getSHA256StrJava(oriValue))) {
			userInfoHashMap.remove(originalKey);
			userInfoHashMap.put(newKey, getSHA256StrJava(newValue));
			return "修改成功，請重新登入以套用修改後的資料";
		} else {
			return "原密碼輸入錯誤，請再次確認";
		}
	}

	public static String changeAdminInfo(String originalKey, char[] oriValue, String newKey, char[] newValue) {
		if (adminInfoHashMap.get(originalKey).equals(getSHA256StrJava(oriValue))) {
			adminInfoHashMap.remove(originalKey);
			adminInfoHashMap.put(newKey, getSHA256StrJava(newValue));
			return "修改成功，請重新登入以套用修改後的資料";
		} else {
			return "原密碼輸入錯誤，請再次確認";
		}
	}

	// TODO
	public static String checkUserInfo(String userAccount, char[] password) {
		try {
			if (adminInfoHashMap.get(userAccount) == null) {
				if (userInfoHashMap.get(userAccount) == null) {
					return "查無此用戶，請先註冊或確認輸入內容。";
				} else {
					if (userInfoHashMap.get(userAccount).equals(getSHA256StrJava(password))) {
						return "User";
					} else {
						return "密碼錯誤，請確認輸入密碼是否正確";
					}
				}
			} else {
				if (adminInfoHashMap.get(userAccount).equals(getSHA256StrJava(password))) {
					return "Admin";
				} else {
					return "密碼錯誤，請確認輸入密碼是否正確";
				}
			}
		} catch (Exception e) {
			return "發生錯誤, 請聯絡服務人員";
		}
	}

	public static int checkUserExist(String userAccount) {
		if (adminInfoHashMap.get(userAccount) != null || userInfoHashMap.get(userAccount) != null) {
			return 1;
		} else {
			return 0;
		}
	}

	public static void addAdminInfo(String userAccount, char[] password) {
		adminInfoHashMap.put(userAccount, getSHA256StrJava(password));
	}

	// For password that has already been hashed
	public static void addAdminInfo(String userAccount, String password) {
		adminInfoHashMap.put(userAccount, password);
	}

	public static String getAdminInfo(String userAccount) {
		return adminInfoHashMap.get(userAccount);
	}

	public static void addUserInfo(String userAccount, char[] password) {
		userInfoHashMap.put(userAccount, getSHA256StrJava(password));
	}

	// For password that has already been hashed
	public static void addUserInfo(String userAccount, String password) {
		userInfoHashMap.put(userAccount, password);
	}

	public static String getMemberInfo(String userAccount) {
		return userInfoHashMap.get(userAccount);
	}

	public static String getSHA256StrJava(char[] chr) {
		String str = new String(chr);
		MessageDigest messageDigest;
		String encodeStr = "";
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			messageDigest.update(str.getBytes("UTF-8"));
			encodeStr = byte2Hex(messageDigest.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodeStr;
	}

	private static String byte2Hex(byte[] bytes) {
		StringBuffer stringBuffer = new StringBuffer();
		String temp = null;
		for (int i = 0; i < bytes.length; i++) {
			temp = Integer.toHexString(bytes[i] & 0xFF);
			if (temp.length() == 1) {
				stringBuffer.append("0");
			}
			stringBuffer.append(temp);
		}
		return stringBuffer.toString();
	}
}
