package com.pixceed.data;

public class Login
{
	public static final String TOKEN_TAG = "access_token";
	public static final String TOKEN_TYPE_TAG = "token_type";
	public static final String EXPIRES_IN_TAG = "expires_in";
	public static final String USER_NAME_TAG = "userName";
	public static final String ISSUED_ON_TAG = ".issued";
	public static final String EXPIRES_ON_TAG = ".expires";
	public static final String ERROR_TAG = "error";
	public static final String ERROR_DESCRIPTION_TAG = "error_description";

	private String token;
	private String tokenType;
	private int expiresIn;
	private String userName;
	private String issuedOn;
	private String expiresOn;
	private String error;
	private String error_description;

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token
	 *            the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the tokenType
	 */
	public String getTokenType() {
		return tokenType;
	}

	/**
	 * @param tokenType
	 *            the tokenType to set
	 */
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	/**
	 * @return the expiresIn
	 */
	public int getExpiresIn() {
		return expiresIn;
	}

	/**
	 * @param expiresIn
	 *            the expiresIn to set
	 */
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the issuedOn
	 */
	public String getIssuedOn() {
		return issuedOn;
	}

	/**
	 * @param issuedOn
	 *            the issuedOn to set
	 */
	public void setIssuedOn(String issuedOn) {
		this.issuedOn = issuedOn;
	}

	/**
	 * @return the expiresOn
	 */
	public String getExpiresOn() {
		return expiresOn;
	}

	/**
	 * @param expiresOn
	 *            the expiresOn to set
	 */
	public void setExpiresOn(String expiresOn) {
		this.expiresOn = expiresOn;
	}

	/**
	 * @return the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return the error_description
	 */
	public String getError_description() {
		return error_description;
	}

	/**
	 * @param error_description
	 *            the error_description to set
	 */
	public void setError_description(String error_description) {
		this.error_description = error_description;
	}

}
