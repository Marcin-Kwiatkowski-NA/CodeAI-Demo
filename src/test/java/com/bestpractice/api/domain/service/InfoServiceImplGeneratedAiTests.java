package com.bestpractice.api.domain.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.AfterAll;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.common.exception.InternalServerError;
import com.bestpractice.api.common.exception.NotFound;
import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {

    private final InfoPersistentRepository infoRepository;

    public InfoServiceImpl(InfoPersistentRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    @Override
    @Override
    public List<InfoResponse> getInfos() {
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
    @Override
    public InfoResponse getInfo(String id) {
        Info info;
        try {
            info = this.infoRepository.findById(id);
        } catch (Exception ex) {
            throw new InternalServerError(ex);
        }
        return new InfoResponse(info.getId(), info.getTitle(), info.getDescription());
    }

    @Override
    @Override
    public InfoResponse updateInfo(String id, InfoRequest req) {
        Info info;
        try {
            info = this.infoRepository.findById(id);
        } catch (Exception ex) {
            throw new InternalServerError(ex);
        }
        info = req.convert(info.getId());
        try {
            info = this.infoRepository.insert(info);
        } catch (Exception ex) {
            throw new InternalServerError(ex);
        }
        return new InfoResponse(info.getId(), info.getTitle(), info.getDescription());
    }

    @Override
    @Override
    public InfoResponse generateInfo(InfoRequest request) {
        Info info;
        try {
            info = this.infoRepository.insert(request.convert(this.infoRepository.newId()));
        } catch (Exception ex) {
            throw new InternalServerError(ex);
        }
        return new InfoResponse(info.getId(), info.getTitle(), info.getDescription());
    }

    @Override
    @Override
    public InfoResponse deleteInfo(String id) {
        try {
            this.infoRepository.removeById(id);
        } catch (Exception ex) {
            throw new InternalServerError(ex);
        }
        return null;
    }
}
