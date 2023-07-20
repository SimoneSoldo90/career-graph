package net.bcsoft.careergraph.dto;

public class StepDTO {
    Integer id;
    Integer idRoadmap;
    Integer ordine;
    String title;
    String description;

    public StepDTO(){}

    public StepDTO(Integer id, Integer idRoadmap, Integer ordine, String title, String description) {
        this.id = id;
        this.idRoadmap = idRoadmap;
        this.ordine = ordine;
        this.title = title;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdRoadmap() {
        return idRoadmap;
    }

    public void setIdRoadmap(Integer idRoadmap) {
        this.idRoadmap = idRoadmap;
    }

    public Integer getOrdine() {
        return ordine;
    }

    public void setOrdine(Integer ordine) {
        this.ordine = ordine;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
