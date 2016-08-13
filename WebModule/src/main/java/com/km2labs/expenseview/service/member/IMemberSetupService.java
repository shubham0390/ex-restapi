package com.km2labs.expenseview.service.member;

import com.km2labs.expenseview.rest.model.Member;
import org.springframework.stereotype.Service;

/**
 * Created by Subham on 18/05/16.
 */
@Service
public interface IMemberSetupService {

    void setup(Member member);
}
