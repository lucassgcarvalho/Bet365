package com.miner;

import java.util.Map;
import com.fasterxml.jackson.annotation.*;

public class SubMenuSubMenu {
    private String nome;

    @JsonProperty("nome")
    public String getNome() { return nome; }
    @JsonProperty("nome")
    public void setNome(String value) { this.nome = value; }
}
