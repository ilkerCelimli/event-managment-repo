package com.portifolyo.userservice.util;

import com.portifolyo.userservice.BaseTestClass;
import com.portifolyo.userservice.entity.User;
import org.junit.jupiter.api.Test;
import org.portifolyo.requests.userservice.UserRegisterRequest;
import org.portifolyo.utils.UpdateHelper;
import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

class UpdateHelperTest extends BaseTestClass {

    @Test
    void updateHelperTest() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        UpdateHelper<UserRegisterRequest, User> helper = new UpdateHelper<>();
        User user = RandomGenerator.randomUserGenerator();
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest("ilker","çelimli","test@test.org"
                ,"123456789", LocalDateTime.of(1997,03,20,1,1,1));
        User result = helper.updateHelper(userRegisterRequest,user);
        assertNotNull(result);
        assertEquals(userRegisterRequest.email(),result.getEmail());
        assertEquals(userRegisterRequest.name(),result.getName());
        assertEquals(userRegisterRequest.birtday(),result.getBirtday());
        assertEquals(userRegisterRequest.password(),result.getPassword());
    }
}
