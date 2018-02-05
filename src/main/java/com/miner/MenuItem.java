package com.miner;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Lucas Carvalho
 *
 */
public abstract class MenuItem {
	
    private String name;
    private String link;
    private String toolTip;
    private String icon;
    private SubMenu[] subMenu;

    /**
     * @return String
     */ 
    @JsonProperty("icon")
    public String getIcon() {
		return icon;
	}
    /**
     * @param icon
     */
    @JsonProperty("icon")
	public void setIcon(String icon) {
		this.icon = icon;
	}
    
	/**
	 * @return String
	 */
	@JsonProperty("name")
    public String getName() {
		return name;
	}
    /**
     * @param name
     */
    @JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return String
	 */
	@JsonProperty("link")
    public String getLink() { return link; }
    /**
     * @param value
     */
    @JsonProperty("link")
    public void setLink(String value) { this.link = value; }

    /**
     * @return SubMenu[]
     */
    @JsonProperty("subMenu")
    public SubMenu[] getSubMenu() { return subMenu; }
    /**
     * @param value
     */
    @JsonProperty("subMenu")
    public void setSubMenu(SubMenu[] value) { this.subMenu = value; }
	
    /**
     * @return String
     */
    @JsonProperty("toolTip")
    public String getToolTip() {
		return toolTip;
	}
    /**
     * @param toolTip
     */
    @JsonProperty("toolTip")
	public void setToolTip(String toolTip) {
		this.toolTip = toolTip;
	}
    
    
    
}
