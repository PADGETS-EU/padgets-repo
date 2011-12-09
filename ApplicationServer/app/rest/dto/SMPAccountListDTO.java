/**
 * 
 */
package rest.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Lukasz Radziwonowicz
 * 
 */
@XmlRootElement(name = "smpaccounts")
@XmlAccessorType(XmlAccessType.FIELD)
public class SMPAccountListDTO {

	public int count;
	@XmlElement(name = "smpaccount")
	public List<SMPAccountDTO> smpAccounts;

	public SMPAccountListDTO(List<SMPAccountDTO> smpAccounts) {
		this.smpAccounts = smpAccounts;
		this.count = smpAccounts.size();
	}
}
