package com.miner;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Lucas Carvalho
 *
 */
public class MenuRaiz implements Serializable{
	private static final long serialVersionUID = 1L;

	private Menu[] raiz;

    /**
     * @return Menu[]
     */
    @JsonProperty("menuRaiz")
    public Menu[] getRaiz() { return raiz; }
    /**
     * @param value
     */
    @JsonProperty("menuRaiz")
    public void setRaiz(Menu[] value) { this.raiz = value; }
}
