package com.gmail.yuomelyanchuk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class JsonMessages {
    private final List<Message> list;

    public JsonMessages() {
        this.list = new ArrayList<>();
    }

    public void addMessages(List<Message> sourceList, int fromIndex){
        for (int i = fromIndex; i < sourceList.size(); i++)
            list.add(sourceList.get(i));
    }

    public List<Message> getList() {
        return Collections.unmodifiableList(list);
    }
}

