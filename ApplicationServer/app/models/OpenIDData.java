/**
 * 
 */
package models;

/**
 * @author Peter Neve & Lukasz Radziwonowicz
 *
 */
public class OpenIDData {
	/**
	 * @param provider
	 * @param redirectUrl
	 * @param discoveryUrl
	 */
	public OpenIDData(String provider, String redirectUrl, String discoveryUrl) {
		this.provider = provider;
		this.redirectUrl = redirectUrl;
		this.discoveryUrl = discoveryUrl;
		
	}
	public String provider;
	public String redirectUrl;
	public String discoveryUrl;
}
