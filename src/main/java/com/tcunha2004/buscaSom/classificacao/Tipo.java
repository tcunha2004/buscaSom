package com.tcunha2004.buscaSom.classificacao;

public enum Tipo {
    SOLO("solo"),
    DUPLA("dupla"),
    BANDA("banda");

    private String tipoString;

    Tipo(String tipoString){
        this.tipoString = tipoString;
    }

    public static Tipo fromString(String texto){
        for(Tipo tipo : Tipo.values()){
            if(tipo.tipoString.equalsIgnoreCase(texto)){
                return tipo;
            }
        }
        throw new IllegalArgumentException("Nenhum tipo encontrado!");
    }
}
