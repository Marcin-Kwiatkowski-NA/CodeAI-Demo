package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import java.util.ArrayList;
import java.util.List;

public class InfoServiceImplGeneratedAiTests {

    private final InfoPersistentRepository infoRepository;

    public InfoServiceImplGeneratedAiTests(InfoPersistentRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    public List<InfoResponse> getInfos() {
        List<InfoResponse> res = new ArrayList<>();
        try {
            List<Info> infoEntities = this.infoRepository.findAll();
            if (!infoEntities.isEmpty()) {
                for (Info i : infoEntities) {
                    res.add(new InfoResponse(i.getId(), i.getTitle(), i.getDescription()));
                }
            }
        } catch (Exception ex) {
            throw new InternalServerError(ex);
        }
        return res;
    }

    public InfoResponse getInfo(String id) {
        Info info;
        try {
            info = this.infoRepository.findById(id);
        } catch (Exception ex) {
            throw new InternalServerError(ex);
        }
        return new InfoResponse(info.getId(), info.getTitle(), info.getDescription());
    }

    public InfoResponse updateInfo(String id, InfoRequest req) {
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

    public InfoResponse generateInfo(InfoRequest request) {
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

    public void deleteInfo(String id) {
        try {
            this.infoRepository.removeById(id);
        } catch (Exception ex) {
            throw new InternalServerError(ex);
        }
    }
}
