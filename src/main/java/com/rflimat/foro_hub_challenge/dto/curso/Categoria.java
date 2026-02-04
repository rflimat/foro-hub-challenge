package com.rflimat.foro_hub_challenge.dto.curso;

public enum Categoria {
    PROGRAMACION("Programación"),
    FRONT_END("Front End"),
    DATA_SCIENCE("Data Science"),
    INTELIGENCIA_ARTIFICIAL("Inteligencia Artificial"),
    INNOVACION_Y_GESTION("Innovación y Gestión"),
    DEVOPS("DevOps"),
    OFFTOPIC("Off Topic");

    private String categoriaText;
    Categoria (String categoriaText) {
        this.categoriaText = categoriaText;
    }

    public static Categoria fromString(String text) {
        for (Categoria categoria : Categoria.values()) {
            if (categoria.categoriaText.equalsIgnoreCase(text)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Ninguna categoria encontrada: " + text);
    }

    public String getCategoriaText() {
        return categoriaText;
    }
}
