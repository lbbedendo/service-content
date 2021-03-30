package io.platosedu.helper;

import io.platosedu.configuration.authentication.CustomUserDetails;

import java.util.List;

public class TestHelper {
    public static CustomUserDetails mockCustomUserDetails() {
        return new CustomUserDetails(
                "john.doe",
                List.of("ROLE_ADMIN"),
                "5ecfb01fe805bc9f584a0c6d",
                "john.doe@kroton.com.br",
                "21328594009",
                null);
    }
}
