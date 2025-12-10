package com.na3t1e.secure_api.service;

import com.na3t1e.secure_api.dto.NoteRequest;
import com.na3t1e.secure_api.entity.Note;
import com.na3t1e.secure_api.entity.Profile;
import com.na3t1e.secure_api.repository.NoteRepository;
import com.na3t1e.secure_api.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NoteService {
    private final ProfileRepository profileRepository;
    private final NoteRepository noteRepository;
    private final JwtService jwtService;


    public List<NoteRequest> getNotes(String username) {
        Profile profile = profileRepository.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        return noteRepository.findByProfile(profile)
                .stream()
                .map(p -> new NoteRequest(p.getId(), p.getContent(), p.getProfile().getNickname()))
                .toList();
    }

    public Note createNotes(String token, String content) {
        String username = jwtService.extractUsername(token);
        Profile profile = profileRepository.findByUserUsername(username)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
        Note note = Note.builder().content(content).profile(profile).build();
        return noteRepository.save(note);
    }
}
