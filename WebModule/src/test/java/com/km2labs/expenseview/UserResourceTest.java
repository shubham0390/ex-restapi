/*
 * Copyright (c) 2016. . The Km2Labs Project
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 */

package com.km2labs.expenseview;

import com.km2labs.expenseview.rest.model.Device;
import com.km2labs.expenseview.rest.model.User;
import com.km2labs.expenseview.rest.resources.user.SignupRequest;
import com.km2labs.expenseview.rest.resources.user.SignupResponse;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.web.client.RestClientException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserResourceTest extends BaseResourceTest {

    @Test
    public void testDemo() {
        ResponseEntity<User> responseEntity = mRestTemplate.getForEntity(getUrl("user/12345678s/devices"), User.class);
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testSignup() {
        SignupRequest signupRequest = new SignupRequest();

        User user = new User();
        user.setEmail("shubham.k.tyagi@gmail.com");
        user.setName("Subham Tyagi");
        user.setPhoneNumber("9663295153");
        user.setProfileImageUrl("ajsbdlfkjasljdkfhalsjkhdfljs");
        user.setCoverImageUrl("mdnckajsndkjfskdjnfkjd");
        signupRequest.setUser(user);

        Device device = new Device();
        device.setDeviceUUID("afdkasdfkhaskfdhaskdfhfsh");
        device.setGcmToken("ahfdkjhsadjfahsdj");

        user.setDevice(device);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
        ResponseEntity<SignupResponse> responseEntity = null;
        try {
            responseEntity = mRestTemplate.postForEntity(getUrl("user"), httpEntity, SignupResponse.class);
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        SignupResponse apiResult = responseEntity.getBody();
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
        //assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        // assertNotNull(((User) responseEntity.getBody().getData()).getId());
    }
}
