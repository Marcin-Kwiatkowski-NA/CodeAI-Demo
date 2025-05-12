package com.bestpractice.api.domain.model;

```python

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import unittest

class Credential:
    def __init__(self, token, token_type, exp, is_refresh):
        self.token = token
        self.token_type = token_type
        self.exp = exp
        self.is_refresh = is_refresh

    def getToken(self):
        return self.token

    def getTokenType(self):
        return self.token_type

    def getExp(self):
        return self.exp

    def isRefresh(self):
        return self.is_refresh

    @test
    def testCreateCredential(self):
        credential = self.create_credential()
        assert credential.getToken() == "test_token"
        assert credential.getExp().getMonth() == 2024
        assert credential.getExp().getDate() == 2024
        assert credential.is_refresh()

    def testGetCredential(self):
        credential = self.create_credential()
        assert credential.getToken() == "test_token"
        assert credential.getTokenType() == "test_type"
        assert credential.getExp() == 2024
        assert credential.is_refresh()

    def testResetCredential(self):
        credential = self.create_credential()
        assert credential.getToken() == "reset_token"
        assert credential.getTokenType() == "reset_type"
        assert credential.getExp() == 2024
        assert credential.is_refresh()

    def create_credential(self):
        return Credential("test_token", "test_type", new Date(2024, 01, 01), True)

if __name__ == '__main__':
    unittest.main()
```
