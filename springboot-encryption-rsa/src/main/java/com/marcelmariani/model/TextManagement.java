package com.marcelmariani.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.marcelmariani.validation.constraints.KeySize;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Document("text-management-ms")
public class TextManagement {

	public static class ViewPublic {
	}

	public static class ViewGET extends ViewPublic {
	}

	public static class ViewPOST extends ViewPublic {
	}

	@Id
	@NotEmpty(message = "Missing Id in request")
	@JsonView(ViewPublic.class)
	private String uuid;
	@NotNull
	@JsonView(ViewGET.class)
	@JsonProperty("text_data")
	private String textData;
	@KeySize
	@JsonProperty("key_size")
	private Integer keySize;
	@JsonView(ViewPOST.class)
	@Transient
	@JsonProperty("private_key")
	private String privateKey;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTextData() {
		return textData;
	}

	public void setTextData(String textData) {
		this.textData = textData;
	}

	public Integer getKeySize() {
		return keySize;
	}

	public void setKeySize(Integer keySize) {
		this.keySize = keySize;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	@Override
	public int hashCode() {
		return Objects.hash(keySize, privateKey, textData, uuid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TextManagement other = (TextManagement) obj;
		return Objects.equals(keySize, other.keySize) && Objects.equals(privateKey, other.privateKey)
				&& Objects.equals(textData, other.textData) && Objects.equals(uuid, other.uuid);
	}

}
