package ba.wave.wavebackend.controller;

import ba.wave.wavebackend.service.TalentService;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RepositoryRestController
@RequestMapping("/talents")
public class TalentController {
    private TalentService talentService;

    public TalentController(TalentService talentService) {
        this.talentService = talentService;
    }

}
