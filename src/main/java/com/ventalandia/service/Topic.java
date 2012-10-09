package com.ventalandia.service;

/**
 * 
 * @author msulik
 * 
 */
public enum Topic {

    QUESTION("questions"), ORDER("orders"), ITEM("items");

    private String topicName;

    private Topic(String name) {
        topicName = name;
    }

    public boolean is(String topic) {
        return this.topicName.equals(topic);
    }

}
