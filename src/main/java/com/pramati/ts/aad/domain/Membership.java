package com.pramati.ts.aad.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *  The Membership Class holds together all the information about WAAD Memberships (Groups or Directory Roles)
 *  @author Naveen Kumar G
 */
@XmlRootElement
public class Membership extends DirectoryObject {

	protected String objectId;
	protected String objectType;
	protected String displayName;
	protected String description;
	/*protected String deletionTimestamp;
	protected String dirSyncEnabled;
	protected String lastDirSyncTime;
	protected String mail;
	protected String mailNickname;
	protected String mailEnabled;
	protected String onPremisesDomainName;
	protected String onPremisesNetBiosName;
	protected String onPremisesSamAccountName;
	protected String onPremisesSecurityIdentifier;
	protected String provisioningErrors;
	protected String proxyAddresses;
	protected String securityEnabled;
	protected String isSystem;
	protected String roleDisabled;
	protected String roleTemplateId;*/
	
	
	@Override
	public String getObjectId() {
		return objectId;
	}

	@Override
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public String getObjectType() {
		return objectType;
	}

	@Override
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}

	@Override
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*public String getDeletionTimestamp() {
		return deletionTimestamp;
	}

	public void setDeletionTimestamp(String deletionTimestamp) {
		this.deletionTimestamp = deletionTimestamp;
	}


	public String getDirSyncEnabled() {
		return dirSyncEnabled;
	}

	public void setDirSyncEnabled(String dirSyncEnabled) {
		this.dirSyncEnabled = dirSyncEnabled;
	}

	public String getLastDirSyncTime() {
		return lastDirSyncTime;
	}

	public void setLastDirSyncTime(String lastDirSyncTime) {
		this.lastDirSyncTime = lastDirSyncTime;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMailNickname() {
		return mailNickname;
	}

	public void setMailNickname(String mailNickname) {
		this.mailNickname = mailNickname;
	}

	public String getMailEnabled() {
		return mailEnabled;
	}

	public void setMailEnabled(String mailEnabled) {
		this.mailEnabled = mailEnabled;
	}

	public String getOnPremisesDomainName() {
		return onPremisesDomainName;
	}

	public void setOnPremisesDomainName(String onPremisesDomainName) {
		this.onPremisesDomainName = onPremisesDomainName;
	}

	public String getOnPremisesNetBiosName() {
		return onPremisesNetBiosName;
	}

	public void setOnPremisesNetBiosName(String onPremisesNetBiosName) {
		this.onPremisesNetBiosName = onPremisesNetBiosName;
	}

	public String getOnPremisesSamAccountName() {
		return onPremisesSamAccountName;
	}

	public void setOnPremisesSamAccountName(String onPremisesSamAccountName) {
		this.onPremisesSamAccountName = onPremisesSamAccountName;
	}

	public String getOnPremisesSecurityIdentifier() {
		return onPremisesSecurityIdentifier;
	}

	public void setOnPremisesSecurityIdentifier(String onPremisesSecurityIdentifier) {
		this.onPremisesSecurityIdentifier = onPremisesSecurityIdentifier;
	}

	public String getProvisioningErrors() {
		return provisioningErrors;
	}

	public void setProvisioningErrors(String provisioningErrors) {
		this.provisioningErrors = provisioningErrors;
	}

	public String getProxyAddresses() {
		return proxyAddresses;
	}

	public void setProxyAddresses(String proxyAddresses) {
		this.proxyAddresses = proxyAddresses;
	}

	public String getSecurityEnabled() {
		return securityEnabled;
	}

	public void setSecurityEnabled(String securityEnabled) {
		this.securityEnabled = securityEnabled;
	}

	public String getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(String isSystem) {
		this.isSystem = isSystem;
	}

	public String getRoleDisabled() {
		return roleDisabled;
	}

	public void setRoleDisabled(String roleDisabled) {
		this.roleDisabled = roleDisabled;
	}

	public String getRoleTemplateId() {
		return roleTemplateId;
	}

	public void setRoleTemplateId(String roleTemplateId) {
		this.roleTemplateId = roleTemplateId;
	}*/

}
