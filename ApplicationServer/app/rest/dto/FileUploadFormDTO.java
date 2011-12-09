/**
 * 
 */
package rest.dto;

import javax.ws.rs.FormParam;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

/**
 * @author Peter Neve & Lukasz Radziwonowicz
 * 
 */
public class FileUploadFormDTO {
	@FormParam("uid")
	@PartType("text/plain")
	public String userId;
	
	@FormParam("campainId")
	@PartType("text/plain")
	public long campainId;
	
	@FormParam("mediaId")
	@PartType("text/plain")
	public long mediaId;
	
	@FormParam("filename")
	@PartType("text/plain")
	public String filename;
//	
	@FormParam("targetSMP")
	@PartType("text/plain")
	public String targetSMPs;
	
	@FormParam("description")
	@PartType("text/plain")
	public String description;
//
	@FormParam("file")
	@PartType("application/octet-stream")
	public byte[] filedata;

	public FileUploadFormDTO() {
	}

	public byte[] getFileData() {
		return filedata;
	}

//	@FormParam("file")
//	@PartType("application/octet-stream")
	public void setFileData(final byte[] filedata) {
		this.filedata = filedata;
	}
}
