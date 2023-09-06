package net.bcsoft.careergraph.entity;

public class Step {
    private Long id;
    private Long idRoadmap;
    private int ord;
    private String title;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdRoadmap() {
        return idRoadmap;
    }

    public void setIdRoadmap(Long idRoadmap) {
        this.idRoadmap = idRoadmap;
    }

    public int getOrd() {
        return ord;
    }

    public void setOrd(int ord) {
        this.ord = ord;
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