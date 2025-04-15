package com.project.travel.domain.view.service;

import com.project.travel.domain.view.repository.ViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViewService {

    private final ViewRepository viewRepository;
}
