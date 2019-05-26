package ru.javarush.partslist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javarush.partslist.dao.PartDao;
import ru.javarush.partslist.model.Part;
import ru.javarush.partslist.model.PartList;

@Service
@Transactional
public class PartServiceImpl implements PartService {
    @Autowired
    @Qualifier("databaseDao")
    private PartDao partDao;

    public Part getPartById(int id) {
        return partDao.getPartById(id);
    }

    public Part addPart(Part part) {
        return partDao.addPart(part);
    }

    public Part updatePartById(int id, Part part) {
        return partDao.updatePartById(id, part);
    }

    public void deletePartById(int id) {
        partDao.deletePartById(id);
    }

    public PartList getPartList(Integer page, Integer size, String search, String required) {
        if (search != null) {
            search = search.trim();
        }
        if (required != null) {
            required = required.trim();
        }

        return partDao.getPartList(page, size, search, required);
    }
}
