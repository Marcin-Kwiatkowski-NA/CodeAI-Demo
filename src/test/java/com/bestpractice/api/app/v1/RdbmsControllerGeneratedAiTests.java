package com.bestpractice.api.app.v1;

```

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
package com.bestpractice.api.app.v1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.net.URI;
import java.net.URISyntaxException;

import com.bestpractice.api.domain.model.InfoRequest;
import com.bestpractice.api.domain.model.InfoResponse;
import com.bestpractice.api.domain.model.InfoServiceImpl;
import java.util.ArrayList;
import java.util.List;

import com.bestpractice.api.common.exception.BadRequest;
import com.bestpractice.api.common.exception.Conflict;
import com.bestpractice.api.infrastrucuture.entity.Info;
import com.bestpractice.api.infrastrucuture.persistent.InfoPersistentRepository;
import java.util.ArrayList;
import java.util.List;

import com.bestpractice.api.domain.service.InfoServiceImpl;

@RestController
@RequestMapping("/api/v1/infos")
public class RdbmsController {

    private final InfoServiceImpl infoService;

    public RdbmsController(InfoServiceImpl infoService) {
        this.infoService = infoService;
    }

    @Override
    public List<InfoResponse> getInfos() {
        return this.infoService.getInfos();
    }

    @Override
    public InfoResponse getInfo(String id) {
        Info info;
        try {
            info = this.infoService.getInfo(id);
        } catch (Exception ex) {
            throw new InternalServerError(ex);
        }
        return new InfoResponse(info.getId(), info.getTitle(), info.getDescription());
    }

    @Override
    public InfoResponse updateInfo(String id, InfoRequest req) {
        Info info;
        try {
            info = this.infoService.findById(id);
        } catch (Exception ex) {
            throw new InternalServerError(ex);
        }
        return new InfoResponse(info.getId(), info.getTitle(), info.getDescription());
    }

    @Override
    public InfoResponse generateInfo(InfoRequest request) {
        Info info;
        try {
            info = this.infoService.insert(request.convert(this.infoService.newId()));
        } catch (Exception ex) {
            throw new InternalServerError(ex);
        }
        return new InfoResponse(info.getId(), info.getTitle(), info.getDescription());
    }

    @Override
    public void deleteInfo(String id) {
        try {
            this.infoService.deleteInfo(id);
        } catch (Exception ex) {
            throw new InternalServerError(ex);
        }
    }

    @Override
    public void getInfos() {
        // No need to implement this method.
    }
}
