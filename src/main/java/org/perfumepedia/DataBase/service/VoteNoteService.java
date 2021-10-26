package org.perfumepedia.DataBase.service;

import org.perfumepedia.DataBase.model.VoteNote;
import org.perfumepedia.DataBase.repository.VoteNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteNoteService {

    @Autowired
    private VoteNoteRepository voteNoteRepository;


    public VoteNote findVoteNote(Long idUser, Long idNote, Long idProduct) {
        return voteNoteRepository.findByIdUserAndIdNoteAndIdProduct(idUser, idNote, idProduct);
    }

    public VoteNote save(VoteNote voteNote) {
        return voteNoteRepository.save(voteNote);
    }

    public Integer getVoteNoteValueForProduct(Long idNote, Long idProduct) {
        return voteNoteRepository.getSumVoteToNote(idProduct, idNote);
    }
}
