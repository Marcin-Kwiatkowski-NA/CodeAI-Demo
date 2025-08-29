package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import java.util.ArrayList;
import java.util.List;

public class InfoServiceImpl implements InfoService {

    private final InfoPersistentRepository infoRepository;

    public InfoServiceImpl(InfoPersistentRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    @Override
    public List<InfoResponse> getInfos() {
        // GIVEN: Initialize a list to store InfoResponse objects.
        // WHEN: Call the findAll() method on the InfoPersistentRepository.
        // THEN: The list should contain all Info entities from the database.
        List<Info> infoEntities;
        try {
            infoEntities = this.infoRepository.findAll();
        } catch (Exception ex) {
            throw new InternalServerError(ex);
        }

        List<InfoResponse> res = new ArrayList<>();
        for (Info i : infoEntities) {
            res.add(new InfoResponse(i.getId(), i.getTitle(), i.getDescription()));
        }
        return res;
    }

    @Override
    public InfoResponse getInfo(String id) {
        // GIVEN: Initialize an Info object.
        // WHEN: Call the findById() method on the InfoPersistentRepository with the given ID.
        // THEN: The Info object should contain the data associated with the given ID.
        Info info;
        try {
            info = this.infoRepository.findById(id);
        } catch (Exception ex) {
            throw new BadRequest();
        }
        return new InfoResponse(info.getId(), info.getTitle(), info.getDescription());
    }

    @Override
    public InfoResponse updateInfo(String id, InfoRequest req) {
        // GIVEN: Initialize an Info object.
        // WHEN: Call the insert() method on the InfoPersistentRepository with the request data.
        // THEN: The Info object should be updated with the provided data.
        Info info;
        try {
            info = this.infoRepository.findById(id);
        } catch (Exception ex) {
            throw new BadRequest();
        }

        info = req.convert(info.getId());
        try {
            info = this.infoRepository.insert(info);
        } catch (Exception ex) {
            throw new InternalServerError(ex);
        }

        return new InfoResponse(info.getId(),info.getTitle(), info.getDescription());
    }

    @Override
    public InfoResponse generateInfo(InfoRequest request) {
        // GIVEN: Initialize an Info object.
        // WHEN: Call the insert() method on the InfoPersistentRepository with the request data.
        // THEN: The Info object should be created with the provided data.
        Info info;
        try {
            info = this.infoRepository.insert(request.convert(this.infoRepository.newId()));
        } catch (Conflict ex) {
            throw new Conflict(ex);
        } catch (Exception ex) {
            throw new InternalServerError(ex);
        }
        return new InfoResponse(info.getId(), info.getTitle(), info.getDescription());
    }

    @Override
    public void deleteInfo(String id) {
        // GIVEN: Initialize the deleteInfo method.
        // WHEN: Call the removeById() method on the InfoPersistentRepository with the given ID.
        // THEN: The Info entity with the given ID should be removed from the database.
        try {
            this.infoRepository.removeById(id);
        } catch (Exception ex) {
            throw new InternalServerError(ex);
        }
    }
}
