package com.project.service.taxi;

import java.security.Principal;

public class StubPrincipal implements Principal {
    @Override
    public String getName() {
        return "TestLogin";
    }
}
