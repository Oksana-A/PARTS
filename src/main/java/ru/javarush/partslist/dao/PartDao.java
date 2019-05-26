package ru.javarush.partslist.dao;

import ru.javarush.partslist.model.Part;
import ru.javarush.partslist.model.PartList;

public interface PartDao {
    Part getPartById(int id);

    Part addPart(Part part);

    Part updatePartById(int id, Part part);

    void deletePartById(int id);

    PartList getPartList(Integer page, Integer size, String search, String required);
}
