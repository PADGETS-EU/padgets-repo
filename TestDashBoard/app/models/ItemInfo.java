/**
 * 
 */
package models;

import com.google.gson.JsonObject;

import play.db.jpa.Model;

/**
 * @author Peter Neve & Lukasz Radziwonowicz
 * 
 */
public class ItemInfo extends Model {
	String userId;
	String itemId;
	String name;	

	@Override
	public String toString() {
		return "UserId: " + userId + ", Id: " + itemId + ", Name: " + name;
	}

}
