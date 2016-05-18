package com.mmt.shubh.service.member;

import com.mmt.shubh.rest.model.Member;
import org.springframework.stereotype.Service;

/**
 * Created by Subham on 18/05/16.
 */
@Service
public interface IMemberSetupService {

    void setup(Member member);
}
