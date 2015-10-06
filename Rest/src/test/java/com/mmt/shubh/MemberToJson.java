package com.mmt.shubh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmt.shubh.rest.model.Member;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by Subham Tyagi
 * On 8/8/2015.
 * <p>
 * TODO: Add class comments
 */
@Slf4j
public class MemberToJson extends TestCase {


    public void testHello() {
        ObjectMapper mapper = new ObjectMapper();
        Member member = new Member();
        member.setCoverPhotoUrl("skjdfksjdnkjskdfkajsnkfdj");
        member.setDisplayName("shubham");
        member.setMemberEmail("zDFJcnskzdj@zLCXnlz");
        member.setMemberName("AZfdjksdnfkjsndk");
        member.setProfilePhotoUrl("AJWHDqhewoifhwrihriofahfd");
        member.setUserPassword("sjkdfhkjsahdkfhoiaewuuoiuh");

        try {
            String memberj = mapper.writeValueAsString(member);
            log.debug(memberj);
            System.out.println(memberj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        assertFalse(true);
    }

}
