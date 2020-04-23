package ba.wave.wavebackend.service;

import ba.wave.wavebackend.model.Talent;
import ba.wave.wavebackend.repository.TalentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TalentService {
    private TalentRepository talentRepository;

    public TalentService(TalentRepository talentRepository) {
        this.talentRepository = talentRepository;
    }

    public List<Talent> findAll() {
        return talentRepository.findAll();
    }

}
