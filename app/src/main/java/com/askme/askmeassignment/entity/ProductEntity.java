package com.askme.askmeassignment.entity;

import java.util.List;

/**
 * Created by sandeeplabs108 on 28/05/16.
 */
public class ProductEntity {

    private String label;
    private String image;
    private String template;
    private List<ItemEntity> itemEntities;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<ItemEntity> getItemEntities() {
        return itemEntities;
    }

    public void setItemEntities(List<ItemEntity> itemEntities) {
        this.itemEntities = itemEntities;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
